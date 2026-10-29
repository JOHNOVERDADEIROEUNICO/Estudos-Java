package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════
 *  9. PARALLEL STREAMS — Processamento Paralelo
 * ═══════════════════════════════════════════════════════════════
 *
 *  Streams paralelos dividem o trabalho em múltiplas threads
 *  usando o ForkJoinPool, aproveitando múltiplos núcleos da CPU.
 *
 *  Como criar:
 *    lista.parallelStream()        — cria direto da coleção
 *    stream.parallel()             — converte um stream existente
 *
 *  Quando usar:
 *    ✅ Grandes volumes de dados (milhares/milhões de elementos)
 *    ✅ Operações CPU-intensive (cálculos pesados)
 *    ✅ Quando a ordem não importa
 *
 *  Quando NÃO usar:
 *    ❌ Poucos elementos (o overhead de criar threads não compensa)
 *    ❌ Operações com estado compartilhado (risco de race condition)
 *    ❌ Quando a ordem é importante e não controlada
 */
public class Ex09_ParallelStreams {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 9 — Parallel Streams                ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // -------------------------------------------------------
        // 9.1  Stream sequencial vs paralelo — observar as threads
        // -------------------------------------------------------
        System.out.println("9.1  Stream SEQUENCIAL (mesma thread):");
        numeros.stream()
               .forEach(n -> System.out.println("     " + n + " → thread: " + Thread.currentThread().getName()));

        System.out.println("\n     Stream PARALELO (múltiplas threads):");
        numeros.parallelStream()
               .forEach(n -> System.out.println("     " + n + " → thread: " + Thread.currentThread().getName()));

        // -------------------------------------------------------
        // 9.2  forEachOrdered() — paralelo mas mantendo a ordem
        // -------------------------------------------------------
        System.out.println("\n9.2  parallelStream + forEachOrdered() — mantém ordem:");
        numeros.parallelStream()
               .forEachOrdered(n -> System.out.print("     " + n));
        System.out.println();

        // -------------------------------------------------------
        // 9.3  Benchmark simples: sequencial vs paralelo
        //      Processamento "pesado" simulado com Thread.sleep
        // -------------------------------------------------------
        List<Integer> grandeVolume = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("\n9.3  Benchmark — operação pesada (10 itens × ~50ms cada):");

        // Sequencial
        long inicioSeq = System.currentTimeMillis();
        grandeVolume.stream()
                .map(n -> processamentoPesado(n))
                .collect(Collectors.toList());
        long tempoSeq = System.currentTimeMillis() - inicioSeq;

        // Paralelo
        long inicioPar = System.currentTimeMillis();
        grandeVolume.parallelStream()
                .map(n -> processamentoPesado(n))
                .collect(Collectors.toList());
        long tempoPar = System.currentTimeMillis() - inicioPar;

        System.out.println("     Sequencial: " + tempoSeq + "ms");
        System.out.println("     Paralelo:   " + tempoPar + "ms");
        System.out.println("     Speedup:    ~" + String.format("%.1f", (double) tempoSeq / tempoPar) + "x");

        System.out.println();
    }

    /** Simula um processamento pesado (50ms por item) */
    private static int processamentoPesado(int n) {
        try { Thread.sleep(50); } catch (InterruptedException e) { }
        return n * 2;
    }
}
