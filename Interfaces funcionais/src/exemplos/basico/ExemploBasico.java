package exemplos.basico;

/**
 * ============================================================
 *  TÓPICO 1: O QUE É UMA INTERFACE FUNCIONAL?
 * ============================================================
 *
 * Uma Interface Funcional é qualquer interface que possui
 * EXATAMENTE UM método abstrato.
 *
 * - A anotação @FunctionalInterface é OPCIONAL, mas recomendada.
 *   Ela instrui o compilador a garantir que a interface tenha
 *   apenas 1 método abstrato — se não tiver, dá erro de compilação.
 *
 * - Interfaces funcionais são a base das expressões lambda e
 *   referências de método introduzidas no Java 8.
 *
 * - Podem conter múltiplos métodos DEFAULT e STATIC (que têm
 *   implementação própria), sem violar o contrato funcional.
 */
@FunctionalInterface
interface Saudacao {
    // ✅ Único método ABSTRATO → interface funcional válida
    String cumprimentar(String nome);

    // ✅ Métodos default e static são permitidos
    default void imprimir(String nome) {
        System.out.println(cumprimentar(nome));
    }

    static String descricao() {
        return "Interface funcional de saudação";
    }
}

/**
 * Antes do Java 8, precisávamos de uma Classe Anônima.
 * Verbose e repetitivo.
 */
class SaudacaoFormal implements Saudacao {
    @Override
    public String cumprimentar(String nome) {
        return "Bom dia, Sr(a). " + nome + "!";
    }
}

public class ExemploBasico {

    public static void executar() {
        separador("EXEMPLO BÁSICO: @FunctionalInterface e Lambdas");

        // ── 1. Classe concreta (modo antigo — Java < 8) ─────────────────
        Saudacao formal = new SaudacaoFormal();
        System.out.println("Classe concreta   : " + formal.cumprimentar("Silva"));

        // ── 2. Classe Anônima (alternativa pré-Java 8) ──────────────────
        Saudacao anonima = new Saudacao() {
            @Override
            public String cumprimentar(String nome) {
                return "Olá, " + nome + "! (classe anônima)";
            }
        };
        System.out.println("Classe anônima    : " + anonima.cumprimentar("Maria"));

        // ── 3. Lambda (Java 8+) — muito mais limpo! ─────────────────────
        //
        //   Sintaxe:  (parâmetros) -> expressão
        //   ou:       (parâmetros) -> { bloco de código; }
        //
        Saudacao lambda = nome -> "Oi, " + nome + "! (lambda)";
        System.out.println("Lambda            : " + lambda.cumprimentar("João"));

        // ── 4. Usando método default ─────────────────────────────────────
        Saudacao casual = nome -> "E aí, " + nome + "! Tudo bem?";
        casual.imprimir("Pedro");  // chama o método default que usa cumprimentar()

        // ── 5. Usando método static ──────────────────────────────────────
        System.out.println("Método static     : " + Saudacao.descricao());

        // ── 6. Lambdas com bloco de código ──────────────────────────────
        Saudacao elaborada = nome -> {
            String inicio = "Prezado(a) " + nome;
            String fim    = ", seja bem-vindo(a)!";
            return inicio + fim;
        };
        System.out.println("Lambda com bloco  : " + elaborada.cumprimentar("Ana"));
    }

    private static void separador(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
    }
}
