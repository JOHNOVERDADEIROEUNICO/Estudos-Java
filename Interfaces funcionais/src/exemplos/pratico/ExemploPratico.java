package exemplos.pratico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ============================================================
 *  TÓPICO 6: CASO PRÁTICO — SISTEMA DE PEDIDOS
 * ============================================================
 *
 * Neste exemplo vemos como interfaces funcionais se encaixam
 * em um cenário de negócio real: um pipeline de processamento
 * de pedidos de e-commerce.
 *
 * Demonstramos:
 *  ✔  Interfaces funcionais customizadas
 *  ✔  Padrão Strategy com lambdas (substituindo subclasses)
 *  ✔  Pipeline de validação com Predicate encadeado
 *  ✔  Pipeline de transformação com Function encadeado
 *  ✔  Observer/callback pattern com Consumer
 */

// ── Modelo de domínio ──────────────────────────────────────────────────────────

class Pedido {
    String id;
    String cliente;
    List<String> itens;
    double valorTotal;
    String status;

    Pedido(String id, String cliente, List<String> itens, double valorTotal) {
        this.id         = id;
        this.cliente    = cliente;
        this.itens      = new ArrayList<>(itens);
        this.valorTotal = valorTotal;
        this.status     = "NOVO";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | R$ %,.2f | Status: %s",
            id, cliente, itens, valorTotal, status);
    }
}

// ── Interfaces funcionais customizadas ────────────────────────────────────────

/**
 * Interface funcional customizada para validação de pedido.
 * Retorna uma mensagem de erro, ou null se válido.
 */
@FunctionalInterface
interface ValidadorPedido {
    String validar(Pedido pedido);

    // Método default: combina dois validadores em sequência
    default ValidadorPedido e(ValidadorPedido outro) {
        return pedido -> {
            String erroAtual = this.validar(pedido);
            if (erroAtual != null) return erroAtual;
            return outro.validar(pedido);
        };
    }
}

/**
 * Interface funcional customizada para cálculo de desconto.
 * Recebe o pedido e retorna o percentual de desconto (0.0 a 1.0).
 */
@FunctionalInterface
interface EstrategiaDesconto {
    double calcular(Pedido pedido);
}

public class ExemploPratico {

    // ── Validadores (lambdas que implementam ValidadorPedido) ─────────────────
    static ValidadorPedido clienteNaoNulo =
        p -> (p.cliente == null || p.cliente.isBlank())
            ? "ERRO: Cliente não informado."
            : null;

    static ValidadorPedido valorMinimo =
        p -> p.valorTotal < 50.0
            ? "ERRO: Pedido abaixo do valor mínimo de R$ 50,00."
            : null;

    static ValidadorPedido possuiItens =
        p -> (p.itens == null || p.itens.isEmpty())
            ? "ERRO: Pedido não possui itens."
            : null;

    // ── Estratégias de desconto (Padrão Strategy via lambda) ─────────────────
    static EstrategiaDesconto semDesconto       = pedido -> 0.0;
    static EstrategiaDesconto descontoFidelidade = pedido -> 0.05;  // 5%
    static EstrategiaDesconto descontoPorVolume  = pedido ->
        pedido.valorTotal > 500 ? 0.10 : pedido.valorTotal > 200 ? 0.05 : 0.0;

    static EstrategiaDesconto descontoProgressivo = pedido -> {
        if (pedido.valorTotal > 1000) return 0.15;
        if (pedido.valorTotal > 500)  return 0.10;
        if (pedido.valorTotal > 200)  return 0.05;
        return 0.0;
    };

    // ── Transformações de pedido ──────────────────────────────────────────────
    static Function<Pedido, Pedido> confirmar = pedido -> {
        pedido.status = "CONFIRMADO";
        return pedido;
    };

    static Function<Pedido, Pedido> enviar = pedido -> {
        pedido.status = "ENVIADO";
        return pedido;
    };

    static Function<Pedido, Pedido> aplicarDesconto(EstrategiaDesconto estrategia) {
        return pedido -> {
            double desconto = estrategia.calcular(pedido);
            double valorOriginal = pedido.valorTotal;
            pedido.valorTotal = valorOriginal * (1 - desconto);
            if (desconto > 0) {
                System.out.printf("    💰 Desconto de %.0f%%: R$ %,.2f → R$ %,.2f%n",
                    desconto * 100, valorOriginal, pedido.valorTotal);
            }
            return pedido;
        };
    }

    // ── Observers / Callbacks (Consumer) ─────────────────────────────────────
    static Consumer<Pedido> logConsole =
        p -> System.out.println("    [LOG]   " + p);

    static Consumer<Pedido> notificarCliente =
        p -> System.out.println("    [EMAIL] → Olá " + p.cliente
            + "! Seu pedido " + p.id + " está: " + p.status);

    static Consumer<Pedido> registrarAuditoria =
        p -> System.out.println("    [AUDIT] Pedido " + p.id + " auditado em status " + p.status);

    // ── Processador de pedidos ────────────────────────────────────────────────
    static void processar(Pedido pedido, ValidadorPedido validador,
                          EstrategiaDesconto estrategia,
                          Consumer<Pedido> notificacoes) {
        System.out.println("\n  >> Processando: " + pedido.id);

        // 1. Validar
        String erro = validador.validar(pedido);
        if (erro != null) {
            System.out.println("    ❌ " + erro);
            pedido.status = "REJEITADO";
            return;
        }

        // 2. Pipeline de transformação
        Pedido resultado = confirmar
            .andThen(aplicarDesconto(estrategia))
            .andThen(enviar)
            .apply(pedido);

        // 3. Notificações (Consumer encadeado)
        notificacoes.accept(resultado);
    }

    // ── Análise de pedidos com Streams ────────────────────────────────────────
    static void analisarPedidos(List<Pedido> pedidos) {
        separador("Análise final dos pedidos");

        Predicate<Pedido> foiEnviado = p -> p.status.equals("ENVIADO");
        Predicate<Pedido> foiRejeitado = p -> p.status.equals("REJEITADO");

        long enviados  = pedidos.stream().filter(foiEnviado).count();
        long rejeitados = pedidos.stream().filter(foiRejeitado).count();

        double receitaTotal = pedidos.stream()
            .filter(foiEnviado)
            .mapToDouble(p -> p.valorTotal)
            .sum();

        System.out.println("  Pedidos enviados    : " + enviados);
        System.out.println("  Pedidos rejeitados  : " + rejeitados);
        System.out.printf("  Receita total       : R$ %,.2f%n", receitaTotal);

        System.out.println("\n  Pedidos de alto valor (> R$ 300):");
        pedidos.stream()
            .filter(foiEnviado.and(p -> p.valorTotal > 300))
            .map(p -> "    " + p)
            .forEach(System.out::println);
    }

    public static void executar() {
        separador("CASO PRÁTICO: SISTEMA DE PEDIDOS");

        // ── Criar pedidos de teste ────────────────────────────────────────
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("P001", "Alice",   Arrays.asList("Notebook"),           3200.0),
            new Pedido("P002", "Bruno",   Arrays.asList("Mouse", "Teclado"),   370.0),
            new Pedido("P003", "",        Arrays.asList("Monitor"),            1200.0),  // sem cliente
            new Pedido("P004", "Carla",   new ArrayList<>(),                   150.0),  // sem itens
            new Pedido("P005", "Diego",   Arrays.asList("Headset"),            30.0),   // abaixo do mínimo
            new Pedido("P006", "Elena",   Arrays.asList("Cadeira Gamer"),      890.0)
        );

        // ── Regras de validação encadeadas com .e() ───────────────────────
        ValidadorPedido validacaoCompleta = clienteNaoNulo
            .e(possuiItens)
            .e(valorMinimo);

        // ── Callbacks encadeados com Consumer.andThen() ───────────────────
        Consumer<Pedido> notificacoesCompletas = logConsole
            .andThen(notificarCliente)
            .andThen(registrarAuditoria);

        // ── Processar cada pedido ─────────────────────────────────────────
        for (Pedido pedido : pedidos) {
            processar(pedido, validacaoCompleta, descontoProgressivo, notificacoesCompletas);
        }

        // ── Análise final ─────────────────────────────────────────────────
        analisarPedidos(pedidos);
    }

    private static void separador(String titulo) {
        System.out.println("\n  [" + titulo + "]");
    }
}
