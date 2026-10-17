package exemplos.builtin;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * ============================================================
 *  TÓPICO 2: INTERFACES FUNCIONAIS BUILT-IN DO JAVA (java.util.function)
 * ============================================================
 *
 * O Java 8 trouxe o pacote java.util.function com as interfaces
 * mais usadas no dia a dia. As 4 principais são:
 *
 *  ┌─────────────────┬────────────────────────┬──────────────────────────────────┐
 *  │ Interface       │ Assinatura do método   │ Descrição                        │
 *  ├─────────────────┼────────────────────────┼──────────────────────────────────┤
 *  │ Function<T,R>   │ R apply(T t)           │ Transforma T em R                │
 *  │ Consumer<T>     │ void accept(T t)       │ Consome T, sem retorno           │
 *  │ Supplier<T>     │ T get()               │ Produz/fornece um T              │
 *  │ Predicate<T>    │ boolean test(T t)      │ Testa T, retorna true/false      │
 *  └─────────────────┴────────────────────────┴──────────────────────────────────┘
 *
 *  Variações úteis:
 *  - BiFunction<T,U,R>  → recebe 2 entradas, produz 1 saída
 *  - UnaryOperator<T>   → Function<T,T> (entrada e saída do mesmo tipo)
 *  - BinaryOperator<T>  → BiFunction<T,T,T> (dois T → um T)
 */
public class ExemploBuiltin {

    public static void executar() {

        // ════════════════════════════════════════════════════════════
        //  1. FUNCTION<T, R>  →  Transforma T em R
        // ════════════════════════════════════════════════════════════
        separador("Function<T, R>");

        // Converte String para inteiro (length)
        Function<String, Integer> tamanho = str -> str.length();
        System.out.println("Tamanho de 'Java': " + tamanho.apply("Java"));  // 4

        // Converte inteiro para String formatada
        Function<Integer, String> formatarIdade = idade -> "Idade: " + idade + " anos";
        System.out.println(formatarIdade.apply(25));

        // Converter temperatura: Celsius → Fahrenheit
        Function<Double, Double> celsiusParaFahrenheit = c -> (c * 9.0 / 5.0) + 32;
        System.out.println("30°C em Fahrenheit: " + celsiusParaFahrenheit.apply(30.0) + "°F");

        // ════════════════════════════════════════════════════════════
        //  2. CONSUMER<T>  →  Consome T, não retorna nada (void)
        // ════════════════════════════════════════════════════════════
        separador("Consumer<T>");

        Consumer<String> imprimirMaiusculo = texto -> System.out.println(texto.toUpperCase());
        imprimirMaiusculo.accept("olá, mundo!");  // OLÁ, MUNDO!

        Consumer<Integer> imprimirQuadrado = n -> System.out.println(n + "² = " + (n * n));
        imprimirQuadrado.accept(7);  // 7² = 49

        // andThen(): encadeia dois Consumers
        Consumer<String> imprimirOriginal = s -> System.out.print("Original: " + s + " | ");
        Consumer<String> imprimirInverso  = s -> System.out.println("Invertido: " + new StringBuilder(s).reverse());
        Consumer<String> imprimirAmbos    = imprimirOriginal.andThen(imprimirInverso);
        imprimirAmbos.accept("Java");

        // ════════════════════════════════════════════════════════════
        //  3. SUPPLIER<T>  →  Produz um valor (sem receber entrada)
        // ════════════════════════════════════════════════════════════
        separador("Supplier<T>");

        Supplier<String> saudacao     = () -> "Bom dia!";
        Supplier<Double> numeroAleatorio = () -> Math.random() * 100;
        Supplier<Long>   timestampAtual  = () -> System.currentTimeMillis();

        System.out.println("Supplier<String>  : " + saudacao.get());
        System.out.println("Supplier<Double>  : " + String.format("%.2f", numeroAleatorio.get()));
        System.out.println("Supplier<Long>    : " + timestampAtual.get());

        // Uso prático: lazy initialization (só cria o objeto se necessário)
        Supplier<String> dadosPesados = () -> {
            // Simula uma operação pesada (ex: consulta ao banco)
            return "Dados carregados: [registro1, registro2, ...]";
        };
        // O Supplier só executa quando .get() é chamado
        System.out.println(dadosPesados.get());

        // ════════════════════════════════════════════════════════════
        //  4. PREDICATE<T>  →  Testa T, retorna boolean
        // ════════════════════════════════════════════════════════════
        separador("Predicate<T>");

        Predicate<String> eVazio     = str -> str.isEmpty();
        Predicate<Integer> ePar      = n   -> n % 2 == 0;
        Predicate<Integer> eMaiorQueDez = n -> n > 10;

        System.out.println("'' é vazio?          " + eVazio.test(""));         // true
        System.out.println("'Java' é vazio?      " + eVazio.test("Java"));     // false
        System.out.println("4 é par?             " + ePar.test(4));             // true
        System.out.println("7 é par?             " + ePar.test(7));             // false

        // Composição de Predicates:
        //   and()  → ambos devem ser true
        //   or()   → pelo menos um deve ser true
        //   negate() → inverte o resultado
        Predicate<Integer> parEMaiorDez = ePar.and(eMaiorQueDez);
        Predicate<Integer> parOuMaiorDez = ePar.or(eMaiorQueDez);
        Predicate<Integer> eImpar = ePar.negate();

        System.out.println("12 é par E > 10?     " + parEMaiorDez.test(12));    // true
        System.out.println("8  é par E > 10?     " + parEMaiorDez.test(8));     // false
        System.out.println("11 é par OU > 10?    " + parOuMaiorDez.test(11));   // true
        System.out.println("7  é ímpar?          " + eImpar.test(7));           // true

        // ════════════════════════════════════════════════════════════
        //  5. VARIAÇÕES: BiFunction, UnaryOperator, BinaryOperator
        // ════════════════════════════════════════════════════════════
        separador("Variações: BiFunction, UnaryOperator, BinaryOperator");

        // BiFunction<T, U, R> → 2 entradas, 1 saída
        BiFunction<String, Integer, String> repetirTexto =
            (texto, vezes) -> texto.repeat(vezes);
        System.out.println("BiFunction        : " + repetirTexto.apply("Ha", 3)); // HaHaHa

        // UnaryOperator<T> → Function<T,T> (entrada e saída do mesmo tipo)
        UnaryOperator<String> paraUpperCase = String::toUpperCase;
        UnaryOperator<Integer> dobrar = n -> n * 2;
        System.out.println("UnaryOperator str : " + paraUpperCase.apply("java"));   // JAVA
        System.out.println("UnaryOperator int : " + dobrar.apply(15));               // 30

        // BinaryOperator<T> → BiFunction<T,T,T> (dois T → um T)
        BinaryOperator<Integer> somar    = (a, b) -> a + b;
        BinaryOperator<String>  concatenar = (a, b) -> a + " " + b;
        System.out.println("BinaryOperator int: " + somar.apply(10, 20));           // 30
        System.out.println("BinaryOperator str: " + concatenar.apply("Olá", "Mundo")); // Olá Mundo
    }

    private static void separador(String titulo) {
        System.out.println("\n  [" + titulo + "]");
    }
}
