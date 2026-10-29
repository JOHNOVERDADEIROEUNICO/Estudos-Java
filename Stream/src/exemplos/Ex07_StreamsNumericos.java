package exemplos;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;

import modelo.Pessoa;

/**
 * ═══════════════════════════════════════════════════════════════
 *  7. STREAMS NUMÉRICOS ESPECIALIZADOS
 * ═══════════════════════════════════════════════════════════════
 *
 *  Java oferece IntStream, LongStream e DoubleStream para evitar
 *  o custo de boxing/unboxing (Integer ↔ int, etc.).
 *
 *  Métodos de conversão:
 *    mapToInt()    → converte Stream<T> para IntStream
 *    mapToDouble() → converte Stream<T> para DoubleStream
 *    mapToLong()   → converte Stream<T> para LongStream
 *    boxed()       → converte IntStream de volta para Stream<Integer>
 *
 *  Métodos exclusivos dos Streams numéricos:
 *    sum(), average(), min(), max(), summaryStatistics()
 */
public class Ex07_StreamsNumericos {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 7 — Streams Numéricos               ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Ana",     25, "São Paulo",      5000),
            new Pessoa("Bruno",   30, "Rio de Janeiro", 7000),
            new Pessoa("Carlos",  22, "Belo Horizonte", 3500),
            new Pessoa("Diana",   28, "São Paulo",      8500),
            new Pessoa("Eduardo", 35, "Curitiba",       6000)
        );

        // -------------------------------------------------------
        // 7.1  mapToInt() + sum() — soma de idades
        // -------------------------------------------------------
        int somaIdades = pessoas.stream()
                .mapToInt(Pessoa::getIdade)
                .sum();
        System.out.println("7.1  soma das idades: " + somaIdades);

        // -------------------------------------------------------
        // 7.2  mapToDouble() + average() — média salarial
        // -------------------------------------------------------
        OptionalDouble mediaSalarial = pessoas.stream()
                .mapToDouble(Pessoa::getSalario)
                .average();
        System.out.println("7.2  média salarial:  R$" +
                String.format("%.2f", mediaSalarial.orElse(0)));

        // -------------------------------------------------------
        // 7.3  mapToDouble() + min() e max()
        // -------------------------------------------------------
        OptionalDouble menorSalario = pessoas.stream()
                .mapToDouble(Pessoa::getSalario)
                .min();
        OptionalDouble maiorSalario = pessoas.stream()
                .mapToDouble(Pessoa::getSalario)
                .max();
        System.out.println("7.3  menor salário:   R$" +
                String.format("%.2f", menorSalario.orElse(0)));
        System.out.println("     maior salário:   R$" +
                String.format("%.2f", maiorSalario.orElse(0)));

        // -------------------------------------------------------
        // 7.4  summaryStatistics() — tudo de uma vez!
        //      Retorna: count, sum, min, average, max
        // -------------------------------------------------------
        DoubleSummaryStatistics stats = pessoas.stream()
                .mapToDouble(Pessoa::getSalario)
                .summaryStatistics();

        System.out.println("\n7.4  summaryStatistics() dos salários:");
        System.out.println("     Quantidade: " + stats.getCount());
        System.out.println("     Soma:       R$" + String.format("%.2f", stats.getSum()));
        System.out.println("     Mínimo:     R$" + String.format("%.2f", stats.getMin()));
        System.out.println("     Média:      R$" + String.format("%.2f", stats.getAverage()));
        System.out.println("     Máximo:     R$" + String.format("%.2f", stats.getMax()));

        // -------------------------------------------------------
        // 7.5  Somar total da folha com filter + mapToDouble + sum
        //      (combinando operações)
        // -------------------------------------------------------
        double folhaSP = pessoas.stream()
                .filter(p -> p.getCidade().equals("São Paulo"))
                .mapToDouble(Pessoa::getSalario)
                .sum();
        System.out.println("\n7.5  Folha salarial de São Paulo: R$" +
                String.format("%.2f", folhaSP));

        System.out.println();
    }
}
