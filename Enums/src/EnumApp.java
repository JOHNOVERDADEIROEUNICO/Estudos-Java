import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Classe principal para demonstrar o funcionamento e os principais
 * recursos dos Enums em Java.
 */
public class EnumApp {

    public static void main(String[] args) {
        imprimirCabecalho("GUIA PRÁTICO DE ENUMS EM JAVA");

        exemplo1_EnumBasicoEMetodosNativos();
        exemplo2_UsoComSwitch();
        exemplo3_EnumComAtributosEConstrutores();
        exemplo4_EnumComComportamentoPolimorfico();
        exemplo5_EnumComInterfaces();
        exemplo6_EnumSetEEnumMap();

        imprimirRodape();
    }

    // =========================================================================
    // 1. Enum Básico e Métodos Nativos de java.lang.Enum
    // =========================================================================
    private static void exemplo1_EnumBasicoEMetodosNativos() {
        imprimirSecao("1. Enum Básico e Métodos Nativos");

        DiaDaSemana hoje = DiaDaSemana.SEXTA;

        // Comparação: Enums podem e DEVEM ser comparados com == (são instâncias únicas)
        System.out.println("Hoje é: " + hoje);
        System.out.println("É sexta-feira? " + (hoje == DiaDaSemana.SEXTA));
        System.out.println("É fim de semana? " + hoje.isFimDeSemana());

        // Métodos que todo Enum herda automaticamente:
        System.out.println("\n--- Métodos embutidos de java.lang.Enum ---");
        System.out.println(".name()    -> " + hoje.name());      // Nome exato da constante como String
        System.out.println(".ordinal() -> " + hoje.ordinal());   // Índice baseado em zero da declaração (SEXTA = 5)

        // valueOf: converte String em Enum (case-sensitive)
        DiaDaSemana sabado = DiaDaSemana.valueOf("SABADO");
        System.out.println("valueOf(\"SABADO\") -> " + sabado + " (Fim de semana? " + sabado.isFimDeSemana() + ")");

        // values(): retorna um array com todos os valores declarados
        System.out.println("\nIterando sobre DiaDaSemana.values():");
        for (DiaDaSemana dia : DiaDaSemana.values()) {
            System.out.printf("  [%d] %-10s -> Fim de semana: %b%n",
                    dia.ordinal(), dia.name(), dia.isFimDeSemana());
        }
    }

    // =========================================================================
    // 2. Uso com Switch (Clássico e Modern Switch Expression)
    // =========================================================================
    private static void exemplo2_UsoComSwitch() {
        imprimirSecao("2. Enums com Switch Expression (Java Moderno)");

        DiaDaSemana dia = DiaDaSemana.DOMINGO;

        // No switch com enum, você não precisa prefixar o tipo (usa apenas DOMINGO, SEXTA, etc.)
        String mensagem = switch (dia) {
            case SEGUNDA -> "Início da semana de trabalho.";
            case TERCA, QUARTA, QUINTA -> "Meio da semana a todo vapor.";
            case SEXTA -> "Sextou! Quase no fim de semana.";
            case SABADO, DOMINGO -> "Fim de semana para descansar!";
        };

        System.out.println("Dia selecionado: " + dia);
        System.out.println("Mensagem: " + mensagem);
    }

    // =========================================================================
    // 3. Enum com Atributos, Construtor e Métodos de Busca
    // =========================================================================
    private static void exemplo3_EnumComAtributosEConstrutores() {
        imprimirSecao("3. Enum com Atributos, Construtor e Getters");

        StatusPedido statusAtual = StatusPedido.PAGO;

        System.out.println("Status: " + statusAtual);
        System.out.println("Código numérico: " + statusAtual.getCodigo());
        System.out.println("Descrição amigável: " + statusAtual.getDescricao());

        // Buscando status pelo código (útil para integração com banco de dados ou APIs)
        System.out.println("\nBuscando status pelo código vindo de uma API (ex: código 4):");
        StatusPedido statusBuscado = StatusPedido.porCodigo(4);
        System.out.println("Encontrado: " + statusBuscado);
    }

    // =========================================================================
    // 4. Polimorfismo e Métodos Abstratos (Strategy Pattern no Enum)
    // =========================================================================
    private static void exemplo4_EnumComComportamentoPolimorfico() {
        imprimirSecao("4. Enum Polimórfico (Métodos Abstratos)");

        double num1 = 10.0;
        double num2 = 2.5;

        System.out.println("Calculando com diferentes operações:");
        for (Operacao op : Operacao.values()) {
            double resultado = op.calcular(num1, num2);
            System.out.printf("  %.1f %s %.1f = %.2f%n", num1, op.getSimbolo(), num2, resultado);
        }
    }

    // =========================================================================
    // 5. Enums Implementando Interfaces
    // =========================================================================
    private static void exemplo5_EnumComInterfaces() {
        imprimirSecao("5. Enum Implementando Interfaces");

        double totalCompra = 1000.00;

        System.out.printf("Valor original da compra: R$ %.2f%n%n", totalCompra);

        for (FormaPagamento forma : FormaPagamento.values()) {
            double valorFinal = forma.aplicarDesconto(totalCompra);
            double desconto = totalCompra - valorFinal;
            System.out.printf("  Forma: %-15s | Desconto: %4.0f%% | Economia: R$ %6.2f | Total a Pagar: R$ %6.2f%n",
                    forma.name(),
                    (forma.getTaxaDesconto() * 100),
                    desconto,
                    valorFinal);
        }
    }

    // =========================================================================
    // 6. Coleções Especializadas: EnumSet e EnumMap
    // =========================================================================
    private static void exemplo6_EnumSetEEnumMap() {
        imprimirSecao("6. Coleções de Alta Performance (EnumSet e EnumMap)");

        // EnumSet: Extremamente rápido e compacto em memória (usa bit vectors internamente)
        EnumSet<DiaDaSemana> diasUteis = EnumSet.range(DiaDaSemana.SEGUNDA, DiaDaSemana.SEXTA);
        EnumSet<DiaDaSemana> fimDeSemana = EnumSet.of(DiaDaSemana.SABADO, DiaDaSemana.DOMINGO);

        System.out.println("Dias úteis (range): " + diasUteis);
        System.out.println("Fim de semana (of): " + fimDeSemana);
        System.out.println("SEGUNDA é dia útil? " + diasUteis.contains(DiaDaSemana.SEGUNDA));

        // EnumMap: Map otimizado onde as chaves são enums (armazenado internamente como array)
        EnumMap<StatusPedido, Integer> contagemPorStatus = new EnumMap<>(StatusPedido.class);
        contagemPorStatus.put(StatusPedido.AGUARDANDO_PAGAMENTO, 15);
        contagemPorStatus.put(StatusPedido.PAGO, 8);
        contagemPorStatus.put(StatusPedido.ENVIADO, 23);
        contagemPorStatus.put(StatusPedido.ENTREGUE, 142);
        contagemPorStatus.put(StatusPedido.CANCELADO, 3);

        System.out.println("\nDashboard de Pedidos (usando EnumMap):");
        contagemPorStatus.forEach((status, total) -> {
            System.out.printf("  [%-20s] -> %3d pedido(s)%n", status.name(), total);
        });
    }

    // =========================================================================
    // Métodos Utilitários de Impressão
    // =========================================================================
    private static void imprimirCabecalho(String titulo) {
        System.out.println("==================================================================");
        System.out.println("           " + titulo);
        System.out.println("==================================================================");
    }

    private static void imprimirSecao(String titulo) {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println(titulo);
        System.out.println("------------------------------------------------------------------");
    }

    private static void imprimirRodape() {
        System.out.println("\n==================================================================");
        System.out.println("   Todos os exemplos foram executados com sucesso!");
        System.out.println("==================================================================");
    }
}
