import java.util.function.*;

/**
 * =============================================================
 *  02 - INTERFACES FUNCIONAIS DO JAVA (java.util.function)
 * =============================================================
 *
 * O Java 8 trouxe o pacote java.util.function com interfaces
 * funcionais prontas para os casos de uso mais comuns.
 *
 * As 4 principais interfaces:
 * ┌─────────────────┬─────────────────────────────┬──────────────────────────┐
 * │ Interface       │ Método                      │ Descrição                │
 * ├─────────────────┼─────────────────────────────┼──────────────────────────┤
 * │ Predicate<T>    │ boolean test(T t)           │ Testa uma condição       │
 * │ Function<T,R>   │ R apply(T t)                │ Transforma T em R        │
 * │ Consumer<T>     │ void accept(T t)            │ Consome/usa o valor      │
 * │ Supplier<T>     │ T get()                     │ Fornece/produz um valor  │
 * └─────────────────┴─────────────────────────────┴──────────────────────────┘
 *
 * Variantes especializadas:
 *   BiPredicate<T,U>    → test(T, U)
 *   BiFunction<T,U,R>   → apply(T, U)
 *   BiConsumer<T,U>     → accept(T, U)
 *   UnaryOperator<T>    → apply(T)        (Function<T,T>)
 *   BinaryOperator<T>   → apply(T, T)     (BiFunction<T,T,T>)
 *   IntPredicate, LongFunction, etc. → variantes primitivas
 */
public class InterfacesFuncionais {

    public static void executar() {
        System.out.println("\n--- 02. INTERFACES FUNCIONAIS ---");

        // -------------------------------------------------------
        // PREDICATE<T> → boolean test(T t)
        // Usado para: filtros, validações, condições
        // -------------------------------------------------------
        System.out.println("\n[Predicate<T>]");

        Predicate<Integer> ePar         = n -> n % 2 == 0;
        Predicate<String>  eLongo       = s -> s.length() > 5;
        Predicate<Integer> ePositivo    = n -> n > 0;

        System.out.println("ePar(4):          " + ePar.test(4));
        System.out.println("ePar(7):          " + ePar.test(7));
        System.out.println("eLongo(\"Java\"):   " + eLongo.test("Java"));
        System.out.println("eLongo(\"Lambda\"): " + eLongo.test("Lambda"));

        // Combinando Predicates com and(), or(), negate()
        Predicate<Integer> parEPositivo = ePar.and(ePositivo);
        Predicate<Integer> parOuNegativo = ePar.or(ePositivo.negate());

        System.out.println("parEPositivo(4):   " + parEPositivo.test(4));
        System.out.println("parEPositivo(-2):  " + parEPositivo.test(-2));
        System.out.println("ePar.negate()(3):  " + ePar.negate().test(3));

        // -------------------------------------------------------
        // FUNCTION<T, R> → R apply(T t)
        // Usado para: transformações, conversões, mapeamentos
        // -------------------------------------------------------
        System.out.println("\n[Function<T, R>]");

        Function<String, Integer>  tamanho     = s -> s.length();
        Function<Integer, String>  intParaStr  = n -> "Número: " + n;
        Function<String, String>   maiusculo   = s -> s.toUpperCase();

        System.out.println("tamanho(\"Lambda\"): " + tamanho.apply("Lambda"));
        System.out.println("intParaStr(42):    " + intParaStr.apply(42));
        System.out.println("maiusculo(\"java\"): " + maiusculo.apply("java"));

        // Encadeando com andThen() e compose()
        // andThen: aplica f depois g  → g(f(x))
        // compose: aplica f antes de g → f(g(x)) (ordem inversa)
        Function<String, String> processarNome = maiusculo.andThen(s -> "[" + s + "]");
        System.out.println("processarNome(\"lambda\"): " + processarNome.apply("lambda"));

        Function<Integer, Integer> dobrar    = n -> n * 2;
        Function<Integer, Integer> somarDez  = n -> n + 10;
        Function<Integer, Integer> dobrarDepoisSomarDez = dobrar.andThen(somarDez);
        Function<Integer, Integer> somarDezDepoisDobrar = dobrar.compose(somarDez);

        System.out.println("dobrar depois somarDez(5): " + dobrarDepoisSomarDez.apply(5)); // (5*2)+10 = 20
        System.out.println("somarDez depois dobrar(5): " + somarDezDepoisDobrar.apply(5)); // (5+10)*2 = 30

        // -------------------------------------------------------
        // CONSUMER<T> → void accept(T t)
        // Usado para: efeitos colaterais, impressão, persistência
        // -------------------------------------------------------
        System.out.println("\n[Consumer<T>]");

        Consumer<String>  imprimir    = s -> System.out.println("  >> " + s);
        Consumer<Integer> imprimirDob = n -> System.out.println("  Dobro de " + n + " = " + (n * 2));

        imprimir.accept("Olá do Consumer!");
        imprimirDob.accept(7);

        // Encadeando Consumers com andThen()
        Consumer<String> imprimirEContar = imprimir
                .andThen(s -> System.out.println("  (tamanho: " + s.length() + " chars)"));

        imprimirEContar.accept("Consumer encadeado");

        // BiConsumer<T, U>
        BiConsumer<String, Integer> exibir = (nome, idade) ->
                System.out.println("  Nome: " + nome + ", Idade: " + idade);
        exibir.accept("Alice", 30);

        // -------------------------------------------------------
        // SUPPLIER<T> → T get()
        // Usado para: lazy evaluation, factories, valores padrão
        // -------------------------------------------------------
        System.out.println("\n[Supplier<T>]");

        Supplier<String>  saudacaoPadrao = () -> "Olá, visitante!";
        Supplier<Double>  numeroAleatorio = () -> Math.random();
        Supplier<String>  timestamp       = () -> java.time.LocalDateTime.now().toString();

        System.out.println("Saudação padrão: " + saudacaoPadrao.get());
        System.out.println("Número aleatório: " + String.format("%.4f", numeroAleatorio.get()));
        System.out.println("Timestamp: " + timestamp.get());

        // -------------------------------------------------------
        // UNARYOPERATOR<T> e BINARYOPERATOR<T>
        // -------------------------------------------------------
        System.out.println("\n[UnaryOperator e BinaryOperator]");

        UnaryOperator<String>  colocarExcl  = s -> s + "!";
        UnaryOperator<Integer> triplicar    = n -> n * 3;
        BinaryOperator<Integer> maximo      = (a, b) -> a > b ? a : b;
        BinaryOperator<String>  concatenar  = (a, b) -> a + " " + b;

        System.out.println("colocarExcl(\"Java\"): " + colocarExcl.apply("Java"));
        System.out.println("triplicar(4):         " + triplicar.apply(4));
        System.out.println("maximo(7, 12):         " + maximo.apply(7, 12));
        System.out.println("concatenar(\"Hello\",\"World\"): " + concatenar.apply("Hello", "World"));
    }
}
