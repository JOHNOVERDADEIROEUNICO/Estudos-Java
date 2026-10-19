import java.util.function.*;

/**
 * =============================================================
 *  05 - LAMBDA AVANÇADO: Composição e Currying
 * =============================================================
 *
 * Tópicos:
 *  1. Composição de funções (andThen, compose)
 *  2. Currying — transformar f(a, b) em f(a)(b)
 *  3. Closures — lambda capturando o ambiente externo
 *  4. Higher-Order Functions — funções que retornam funções
 *  5. Memoization simples com lambda
 */
public class LambdaAvancado {

    public static void executar() {
        System.out.println("\n--- 05. LAMBDA AVANÇADO ---");

        // -------------------------------------------------------
        // 1. COMPOSIÇÃO DE FUNÇÕES
        //    andThen(g) → aplica this, depois g:  g(f(x))
        //    compose(g) → aplica g, depois this:  f(g(x))
        // -------------------------------------------------------
        System.out.println("\n[1] Composição de Funções");

        Function<Integer, Integer> vezesDois  = x -> x * 2;
        Function<Integer, Integer> maisCem    = x -> x + 100;
        Function<Integer, String>  paraString = x -> "Resultado: " + x;

        // Pipeline: x * 2 → +100 → para string
        Function<Integer, String> pipeline = vezesDois
                .andThen(maisCem)
                .andThen(paraString);

        System.out.println(pipeline.apply(5));  // (5*2)+100 = "Resultado: 110"
        System.out.println(pipeline.apply(10)); // (10*2)+100 = "Resultado: 120"

        // -------------------------------------------------------
        // 2. CURRYING
        //    Técnica de transformar f(a, b) em f(a)(b)
        //    Retorna uma nova função para cada argumento parcial.
        //    Muito útil para criar funções especializadas.
        // -------------------------------------------------------
        System.out.println("\n[2] Currying");

        // Versão normal (sem currying):
        BiFunction<Integer, Integer, Integer> somaNormal = (a, b) -> a + b;
        System.out.println("Soma normal(3, 7): " + somaNormal.apply(3, 7));

        // Versão com currying:
        // somaComCurrying recebe 'a' e retorna uma função que recebe 'b'
        Function<Integer, Function<Integer, Integer>> somaComCurrying = a -> b -> a + b;

        // Aplicação parcial: cria uma função que sempre soma 10
        Function<Integer, Integer> somarDez    = somaComCurrying.apply(10);
        Function<Integer, Integer> somarCinco  = somaComCurrying.apply(5);

        System.out.println("somarDez(7):   " + somarDez.apply(7));   // 17
        System.out.println("somarDez(20):  " + somarDez.apply(20));  // 30
        System.out.println("somarCinco(3): " + somarCinco.apply(3)); // 8

        // Outro exemplo: multiplicador curried
        Function<Integer, Function<Integer, Integer>> multiplicar = fator -> valor -> fator * valor;

        Function<Integer, Integer> dobrar    = multiplicar.apply(2);
        Function<Integer, Integer> triplicar = multiplicar.apply(3);
        Function<Integer, Integer> centuplicar = multiplicar.apply(100);

        System.out.println("dobrar(6):      " + dobrar.apply(6));       // 12
        System.out.println("triplicar(6):   " + triplicar.apply(6));    // 18
        System.out.println("centuplicar(6): " + centuplicar.apply(6));  // 600

        // -------------------------------------------------------
        // 3. CLOSURES — Lambda capturando variáveis externas
        //    A variável capturada deve ser "effectively final"
        //    (não modificada após captura).
        // -------------------------------------------------------
        System.out.println("\n[3] Closures");

        String saudacao = "Olá";  // effectively final
        int desconto = 10;         // effectively final

        Runnable saudar = () -> System.out.println(saudacao + ", Lambda!");
        saudar.run();

        Function<Double, Double> aplicarDesconto = preco -> preco * (1 - desconto / 100.0);
        System.out.printf("Preço R$200 com %d%% desconto: R$%.2f%n",
                desconto, aplicarDesconto.apply(200.0));

        // Closure com contadores — usando array para contornar effectively final
        int[] contador = {0};
        Runnable incrementar = () -> contador[0]++;
        incrementar.run();
        incrementar.run();
        incrementar.run();
        System.out.println("Contador após 3 incrementos: " + contador[0]);

        // -------------------------------------------------------
        // 4. HIGHER-ORDER FUNCTIONS
        //    Funções que recebem ou retornam outras funções
        // -------------------------------------------------------
        System.out.println("\n[4] Higher-Order Functions");

        // Função que retorna uma função de validação
        Function<Integer, Predicate<Integer>> criarValidadorMinimo = min ->
                valor -> valor >= min;

        Predicate<Integer> maiorQueDez      = criarValidadorMinimo.apply(10);
        Predicate<Integer> maiorQueCinquenta = criarValidadorMinimo.apply(50);

        System.out.println("15 > 10?  " + maiorQueDez.test(15));
        System.out.println("5 > 10?   " + maiorQueDez.test(5));
        System.out.println("60 > 50?  " + maiorQueCinquenta.test(60));
        System.out.println("40 > 50?  " + maiorQueCinquenta.test(40));

        // Função que aplica outra função N vezes
        Function<Function<Integer, Integer>, Function<Integer, Integer>> aplicarNVezes =
                fn -> valor -> {
                    // Aplica fn 3 vezes (hardcoded para simplicidade)
                    return fn.apply(fn.apply(fn.apply(valor)));
                };

        Function<Integer, Integer> dobrarTresVezes = aplicarNVezes.apply(x -> x * 2);
        System.out.println("Dobrar 3x → 1: " + dobrarTresVezes.apply(1)); // 1→2→4→8

        // -------------------------------------------------------
        // 5. MEMOIZATION SIMPLES
        //    Cache do resultado de funções puras (avaliação lazy).
        //    O HashMap.computeIfAbsent não suporta modificações recursivas
        //    na mesma instância, por isso usamos uma abordagem iterativa
        //    (bottom-up) para preencher o cache com lambda.
        // -------------------------------------------------------
        System.out.println("\n[5] Memoization");

        // Cache pré-populado de forma bottom-up com lambda
        java.util.Map<Integer, Long> cacheFib = new java.util.HashMap<>();
        cacheFib.put(0, 0L);
        cacheFib.put(1, 1L);

        // Preenche o cache usando lambda como "calculadora"
        java.util.function.IntConsumer preencherFib = n -> {
            long anterior = cacheFib.getOrDefault(n - 2, 0L);
            long proximo  = cacheFib.getOrDefault(n - 1, 1L);
            cacheFib.put(n, anterior + proximo);
        };

        // Preenche até fib(45)
        for (int i = 2; i <= 45; i++) {
            preencherFib.accept(i);
        }

        // Consulta via lambda (usando cache já pronto)
        Function<Integer, Long> fibonacci = n -> cacheFib.getOrDefault(n, -1L);

        System.out.println("Fibonacci memoizado:");
        for (int i = 0; i <= 10; i++) {
            System.out.print("fib(" + i + ")=" + fibonacci.apply(i) + "  ");
        }
        System.out.println();
        System.out.println("fib(40) = " + fibonacci.apply(40));
        System.out.println("fib(45) = " + fibonacci.apply(45));

        // Exemplo de memoization genérica com Supplier (lazy one-shot)
        System.out.println("\n[Memoization com Supplier]");
        Supplier<String> valorCaro = memoize(() -> {
            System.out.println("  (calculando valor custoso...)");
            return "resultado_caro_" + System.currentTimeMillis();
        });

        System.out.println("1ª chamada: " + valorCaro.get());
        System.out.println("2ª chamada: " + valorCaro.get()); // não recalcula
        System.out.println("3ª chamada: " + valorCaro.get()); // não recalcula
    }

    // Helper: memoiza um Supplier (calcula apenas na 1ª vez)
    private static <T> Supplier<T> memoize(Supplier<T> supplier) {
        Object[] resultado = {null};
        boolean[] calculado = {false};
        return () -> {
            if (!calculado[0]) {
                resultado[0] = supplier.get();
                calculado[0] = true;
            }
            @SuppressWarnings("unchecked")
            T r = (T) resultado[0];
            return r;
        };
    }
}
