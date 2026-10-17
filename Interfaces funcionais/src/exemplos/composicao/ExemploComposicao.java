package exemplos.composicao;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ============================================================
 *  TÓPICO 4: COMPOSIÇÃO DE FUNÇÕES
 * ============================================================
 *
 * Interfaces funcionais possuem métodos default que permitem
 * combinar funções — como peças de Lego — para criar pipelines
 * de transformação legíveis e reutilizáveis.
 *
 *  Function<T,R>:
 *    • andThen(after)  → aplica THIS, depois AFTER no resultado
 *    • compose(before) → aplica BEFORE primeiro, depois THIS
 *
 *  Predicate<T>:
 *    • and(other)   → THIS && OTHER
 *    • or(other)    → THIS || OTHER
 *    • negate()     → !THIS
 *
 *  Consumer<T>:
 *    • andThen(after) → executa THIS, depois AFTER
 *
 *  Analogia com matemática:
 *    f.andThen(g)  →  g(f(x))   (pipeline da esquerda para a direita)
 *    f.compose(g)  →  f(g(x))   (pipeline da direita para a esquerda)
 */
public class ExemploComposicao {

    public static void executar() {

        // ════════════════════════════════════════════
        //  1. Function.andThen()  →  f → g
        // ════════════════════════════════════════════
        separador("Function.andThen()  →  pipeline  f → g");

        Function<String, String> removerEspacos  = String::trim;
        Function<String, String> paraMinusculo   = String::toLowerCase;
        Function<String, String> adicionarExclamacao = s -> s + "!";

        // Pipeline: trim → toLowerCase → adiciona "!"
        Function<String, String> processarTexto = removerEspacos
                .andThen(paraMinusculo)
                .andThen(adicionarExclamacao);

        String entrada = "   JAVA É FANTÁSTICO   ";
        System.out.println("Entrada           : '" + entrada + "'");
        System.out.println("Após processamento: '" + processarTexto.apply(entrada) + "'");

        // Exemplo numérico: String → int → dobrar → String
        Function<String, Integer> parsear  = Integer::parseInt;
        Function<Integer, Integer> dobrar  = n -> n * 2;
        Function<Integer, String>  mostrar = n -> "Resultado: " + n;

        Function<String, String> pipeline = parsear.andThen(dobrar).andThen(mostrar);
        System.out.println(pipeline.apply("21"));  // Resultado: 42

        // ════════════════════════════════════════════
        //  2. Function.compose()  →  g → f (ordem inversa)
        // ════════════════════════════════════════════
        separador("Function.compose()  →  ordem inversa  g → f");

        Function<Integer, Integer> multiplicarPor3 = n -> n * 3;
        Function<Integer, Integer> somarCinco      = n -> n + 5;

        //  andThen: multiplicarPor3 DEPOIS somarCinco  → (n*3) + 5
        //  compose: somarCinco ANTES multiplicarPor3   → (n+5) * 3
        Function<Integer, Integer> comAndThen = multiplicarPor3.andThen(somarCinco);
        Function<Integer, Integer> comCompose = multiplicarPor3.compose(somarCinco);

        int valor = 4;
        System.out.println("Valor de entrada  : " + valor);
        System.out.println("andThen (x*3)+5   : " + comAndThen.apply(valor));  // (4*3)+5 = 17
        System.out.println("compose (x+5)*3   : " + comCompose.apply(valor));  // (4+5)*3 = 27

        // ════════════════════════════════════════════
        //  3. Predicate: and(), or(), negate()
        // ════════════════════════════════════════════
        separador("Composição de Predicates: and(), or(), negate()");

        Predicate<String> naoEhNulo   = s -> s != null;
        Predicate<String> naoEhVazio  = s -> !s.isEmpty();
        Predicate<String> temMaisDe5  = s -> s.length() > 5;
        Predicate<String> comecaComJ  = s -> s.startsWith("J");

        // Validação composta: não nulo E não vazio E mais de 5 chars
        Predicate<String> ehValido = naoEhNulo
                .and(naoEhVazio)
                .and(temMaisDe5);

        // Começa com J OU tem mais de 5 chars
        Predicate<String> interessante = comecaComJ.or(temMaisDe5);

        String[] testes = { null, "", "Java", "JavaScript", "Python" };

        System.out.println("\n  Predicado: (não nulo) AND (não vazio) AND (> 5 chars)");
        for (String t : testes) {
            try {
                System.out.printf("    %-12s → %s%n", "'" + t + "'", ehValido.test(t));
            } catch (NullPointerException e) {
                // null causa NPE em naoEhVazio — demonstração intencional
                System.out.printf("    %-12s → false (null detectado antes)%n", "'null'");
            }
        }

        // Predicado seguro com verificação de null primeiro
        Predicate<String> ehValidoSeguro = s -> s != null && !s.isEmpty() && s.length() > 5;
        System.out.println("\n  Predicado seguro:");
        for (String t : testes) {
            System.out.printf("    %-14s → %s%n", "'" + t + "'", ehValidoSeguro.test(t));
        }

        // negate() inverte o predicado
        Predicate<String> ehInvalido = ehValidoSeguro.negate();
        System.out.println("\n  negate() → ehInvalido(\"Java\") = " + ehInvalido.test("Java")); // true

        // ════════════════════════════════════════════
        //  4. Consumer.andThen()
        // ════════════════════════════════════════════
        separador("Consumer.andThen()  →  encadeamento de ações");

        Consumer<String> log1 = s -> System.out.print("  [LOG]    " + s);
        Consumer<String> log2 = s -> System.out.print(" → [AUDIT] " + s);
        Consumer<String> log3 = s -> System.out.println(" → [DB] salvo!");

        Consumer<String> pipeline2 = log1.andThen(log2).andThen(log3);
        pipeline2.accept("Usuário criado");
        pipeline2.accept("Produto atualizado");
    }

    private static void separador(String titulo) {
        System.out.println("\n  [" + titulo + "]");
    }
}
