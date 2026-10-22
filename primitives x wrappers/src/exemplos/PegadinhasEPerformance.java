package exemplos;

/**
 * Principais armadilhas no uso de Wrappers:
 * 1. Comparação com '==' vs '.equals()' e o Cache de Inteiros.
 * 2. Custo de performance do Autoboxing em loops massivos.
 */
public class PegadinhasEPerformance {

    public static void executar() {
        System.out.println("==================================================");
        System.out.println(" 5. PEGADINHAS E PERFORMANCE");
        System.out.println("==================================================");

        /*
         * ARMADILHA 1: Comparação de Wrappers ('==' vs '.equals()')
         * 
         * Wrappers são OBJETOS. O operador '==' compara referências de memória,
         * enquanto '.equals()' compara o valor contido no objeto.
         * 
         * Contudo, a JVM mantém um "Integer Cache" para valores entre -128 e 127.
         * Nesses valores, a JVM reutiliza a mesma instância!
         */
        System.out.println("--- 1. O Integer Cache (-128 a 127) ---");
        Integer num1 = 127;
        Integer num2 = 127;

        System.out.println("num1 = 127, num2 = 127");
        System.out.println("num1 == num2: " + (num1 == num2) + " (True devido ao Integer Cache!)");
        System.out.println("num1.equals(num2): " + num1.equals(num2));

        Integer num3 = 128;
        Integer num4 = 128;

        System.out.println("\nnum3 = 128, num4 = 128");
        System.out.println("num3 == num4: " + (num3 == num4) + " (False! Fora do cache, são objetos distintos na Heap!)");
        System.out.println("num3.equals(num4): " + num3.equals(num4) + " (Correto: compara o valor)");

        System.out.println("\n> REGRA DE OURO: Sempre use '.equals()' para comparar Wrappers!");

        /*
         * ARMADILHA 2: Impacto de Performance em Loops (Autoboxing invisível)
         * 
         * Se você usar um Wrapper como acumulador de um loop, cada soma criará
         * um novo objeto na memória, sobrecarregando o Garbage Collector.
         */
        System.out.println("\n--- 2. Performance: Primitivo vs Wrapper em Loops ---");
        int limite = 10_000_000;

        // Teste com Primitivo (long)
        long inicioPrimitivo = System.currentTimeMillis();
        long somaPrimitiva = 0L;
        for (long i = 0; i < limite; i++) {
            somaPrimitiva += i;
        }
        long fimPrimitivo = System.currentTimeMillis();
        long tempoPrimitivo = fimPrimitivo - inicioPrimitivo;
        System.out.printf("Loop com primitivo 'long' (%d iterações): %d ms (Resultado: %d)%n",
                limite, tempoPrimitivo, somaPrimitiva);

        // Teste com Wrapper (Long) -> Cria 10 milhões de instâncias de Long!
        long inicioWrapper = System.currentTimeMillis();
        Long somaWrapper = 0L; // ATENÇÃO: Wrapper como acumulador
        for (long i = 0; i < limite; i++) {
            somaWrapper += i; // Desempacota, soma, e cria uma NOVA instância de Long
        }
        long fimWrapper = System.currentTimeMillis();
        long tempoWrapper = fimWrapper - inicioWrapper;
        System.out.printf("Loop com wrapper 'Long'   (%d iterações): %d ms (Resultado: %d)%n",
                limite, tempoWrapper, somaWrapper);

        System.out.printf("> Diferença: O wrapper foi ~%.1fx mais lento por criar objetos na Heap repetidamente!%n",
                (double) Math.max(tempoWrapper, 1) / Math.max(tempoPrimitivo, 1));
        System.out.println();
    }
}
