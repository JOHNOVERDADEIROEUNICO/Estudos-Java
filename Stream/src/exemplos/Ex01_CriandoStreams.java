package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ═══════════════════════════════════════════════════════════════
 *  1. CRIANDO STREAMS — As diferentes formas de obter um Stream
 * ═══════════════════════════════════════════════════════════════
 *
 *  Um Stream é um "pipeline" de dados que permite processar
 *  elementos de forma declarativa (funcional), sem modificar
 *  a coleção original.
 *
 *  Características principais:
 *  - NÃO armazena dados (processa sob demanda)
 *  - NÃO modifica a fonte original
 *  - É "consumido" uma única vez (não pode ser reutilizado)
 *  - Pode ser sequencial ou paralelo
 */
public class Ex01_CriandoStreams {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 1 — Formas de Criar Streams        ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // -------------------------------------------------------
        // 1.1  A partir de uma LISTA (forma mais comum)
        // -------------------------------------------------------
        List<String> frutas = Arrays.asList("Maçã", "Banana", "Laranja", "Uva");
        Stream<String> streamDeLista = frutas.stream();
        System.out.println("1.1  Stream de Lista:");
        streamDeLista.forEach(f -> System.out.println("     " + f));

        // -------------------------------------------------------
        // 1.2  Usando Stream.of() — valores avulsos
        // -------------------------------------------------------
        System.out.println("\n1.2  Stream.of():");
        Stream.of("Java", "Python", "JavaScript")
              .forEach(lang -> System.out.println("     " + lang));

        // -------------------------------------------------------
        // 1.3  A partir de um ARRAY
        // -------------------------------------------------------
        int[] numeros = {10, 20, 30, 40, 50};
        System.out.println("\n1.3  Stream de Array (IntStream):");
        IntStream streamDeArray = Arrays.stream(numeros);
        streamDeArray.forEach(n -> System.out.println("     " + n));

        // -------------------------------------------------------
        // 1.4  Stream.iterate() — sequência infinita (com limite)
        //      Gera: 0, 2, 4, 6, 8 ...
        // -------------------------------------------------------
        System.out.println("\n1.4  Stream.iterate() — pares de 0 a 8:");
        Stream.iterate(0, n -> n + 2)
              .limit(5)
              .forEach(n -> System.out.println("     " + n));

        // -------------------------------------------------------
        // 1.5  Stream.generate() — valores gerados por um Supplier
        // -------------------------------------------------------
        System.out.println("\n1.5  Stream.generate() — 3 números aleatórios (0-99):");
        Stream.generate(() -> (int) (Math.random() * 100))
              .limit(3)
              .forEach(n -> System.out.println("     " + n));

        // -------------------------------------------------------
        // 1.6  IntStream.range() e rangeClosed()
        // -------------------------------------------------------
        System.out.println("\n1.6  IntStream.rangeClosed(1, 5):");
        IntStream.rangeClosed(1, 5)
                 .forEach(n -> System.out.println("     " + n));

        System.out.println();
    }
}
