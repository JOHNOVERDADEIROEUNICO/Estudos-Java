import exemplos.*;

/**
 * ═══════════════════════════════════════════════════════════════
 *         STREAM API — Guia Completo com Exemplos em Java
 * ═══════════════════════════════════════════════════════════════
 *
 *  Este projeto contém 10 exemplos organizados que cobrem os
 *  principais conceitos da Stream API do Java (introduzida no Java 8).
 *
 *  Estrutura dos exemplos:
 *    01. Criando Streams          — formas de obter um Stream
 *    02. Operações Intermediárias — filter, map, sorted, distinct...
 *    03. Operações Terminais      — forEach, collect, reduce, count...
 *    04. map() e flatMap()        — transformação de dados
 *    05. Collectors               — toList, groupingBy, joining...
 *    06. Ordenação e Comparação   — sorted(), Comparator
 *    07. Streams Numéricos        — IntStream, sum, average, stats
 *    08. Reduce                   — redução de dados a fundo
 *    09. Parallel Streams         — processamento paralelo
 *    10. Caso Prático             — relatório realista com tudo
 *
 *  Para executar: compile e rode esta classe App.
 *  Cada exemplo imprime sua saída no console de forma separada.
 */
public class StreamApp {
    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("   STREAM API — Guia Completo com Exemplos Java   ");
        System.out.println("═══════════════════════════════════════════════════\n");

        Ex01_CriandoStreams.executar();
        Ex02_OperacoesIntermediarias.executar();
        Ex03_OperacoesTerminais.executar();
        Ex04_MapEFlatMap.executar();
        Ex05_Collectors.executar();
        Ex06_OrdenacaoComparacao.executar();
        Ex07_StreamsNumericos.executar();
        Ex08_Reduce.executar();
        Ex09_ParallelStreams.executar();
        Ex10_CasoPratico.executar();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("   Fim dos exemplos! Bons estudos! 🚀");
        System.out.println("═══════════════════════════════════════════════════");
    }
}
