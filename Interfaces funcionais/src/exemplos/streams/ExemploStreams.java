package exemplos.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ============================================================
 *  TÓPICO 5: INTERFACES FUNCIONAIS COM STREAMS E COLEÇÕES
 * ============================================================
 *
 * A Stream API é onde interfaces funcionais brilham de verdade!
 * Cada operação de stream aceita uma interface funcional:
 *
 *   stream.filter(Predicate)          → filtra elementos
 *   stream.map(Function)              → transforma elementos
 *   stream.forEach(Consumer)          → itera sem retorno
 *   stream.sorted(Comparator)         → ordena elementos
 *   stream.reduce(BinaryOperator)     → agrega em um valor
 *   stream.collect(Collector)         → coleta resultado
 *   stream.anyMatch(Predicate)        → algum elemento satisfaz?
 *   stream.allMatch(Predicate)        → todos satisfazem?
 *   stream.noneMatch(Predicate)       → nenhum satisfaz?
 *   stream.findFirst() → Optional<T>  → retorna o primeiro
 */

class Funcionario {
    String nome;
    String departamento;
    double salario;

    Funcionario(String nome, String departamento, double salario) {
        this.nome          = nome;
        this.departamento  = departamento;
        this.salario       = salario;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-10s | R$ %,.2f", nome, departamento, salario);
    }
}

public class ExemploStreams {

    public static void executar() {

        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Alice",   "TI",       8500.00),
            new Funcionario("Bruno",   "RH",       4200.00),
            new Funcionario("Carla",   "TI",       9100.00),
            new Funcionario("Diego",   "Vendas",   5300.00),
            new Funcionario("Elena",   "TI",       7800.00),
            new Funcionario("Felipe",  "RH",       3900.00),
            new Funcionario("Gabriela","Vendas",   6100.00),
            new Funcionario("Hugo",    "TI",       11200.00)
        );

        // ════════════════════════════════════════════
        //  1. filter() com Predicate
        // ════════════════════════════════════════════
        separador("1. filter() com Predicate");

        Predicate<Funcionario> ehDeTI   = f -> f.departamento.equals("TI");
        Predicate<Funcionario> salarioAlto = f -> f.salario > 7000;

        System.out.println("  Funcionários de TI:");
        funcionarios.stream()
            .filter(ehDeTI)
            .forEach(f -> System.out.println("    " + f));

        System.out.println("\n  Funcionários de TI com salário > R$ 7.000:");
        funcionarios.stream()
            .filter(ehDeTI.and(salarioAlto))
            .forEach(f -> System.out.println("    " + f));

        // ════════════════════════════════════════════
        //  2. map() com Function
        // ════════════════════════════════════════════
        separador("2. map() com Function");

        Function<Funcionario, String> extrairNome    = f -> f.nome;
        Function<Funcionario, Double> extrairSalario = f -> f.salario;

        System.out.println("  Nomes de todos os funcionários:");
        List<String> nomes = funcionarios.stream()
            .map(extrairNome)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("    " + nomes);

        System.out.println("\n  Salários com 10% de aumento:");
        funcionarios.stream()
            .map(f -> f.nome + ": R$ " + String.format("%,.2f", f.salario * 1.10))
            .forEach(s -> System.out.println("    " + s));

        // ════════════════════════════════════════════
        //  3. sorted() com Comparator (interface funcional!)
        // ════════════════════════════════════════════
        separador("3. sorted() com Comparator");

        System.out.println("  Ordenados por salário (maior → menor):");
        funcionarios.stream()
            .sorted(Comparator.comparingDouble((Funcionario f) -> f.salario).reversed())
            .forEach(f -> System.out.println("    " + f));

        // ════════════════════════════════════════════
        //  4. reduce() com BinaryOperator
        // ════════════════════════════════════════════
        separador("4. reduce() com BinaryOperator");

        double totalSalarios = funcionarios.stream()
            .map(extrairSalario)
            .reduce(0.0, (acc, salario) -> acc + salario);
        System.out.printf("  Total em folha   : R$ %,.2f%n", totalSalarios);
        System.out.printf("  Média salarial   : R$ %,.2f%n", totalSalarios / funcionarios.size());

        // ════════════════════════════════════════════
        //  5. Collectors: groupingBy, toMap
        // ════════════════════════════════════════════
        separador("5. collect() → groupingBy, toMap");

        // Agrupar por departamento
        Map<String, List<Funcionario>> porDepto = funcionarios.stream()
            .collect(Collectors.groupingBy(f -> f.departamento));

        System.out.println("  Agrupados por departamento:");
        porDepto.forEach((depto, lista) -> {
            System.out.println("    " + depto + ":");
            lista.forEach(f -> System.out.println("      " + f));
        });

        // Média salarial por departamento
        Map<String, Double> mediaPorDepto = funcionarios.stream()
            .collect(Collectors.groupingBy(
                f -> f.departamento,
                Collectors.averagingDouble(f -> f.salario)
            ));

        System.out.println("\n  Média salarial por departamento:");
        mediaPorDepto.forEach((d, m) ->
            System.out.printf("    %-10s → R$ %,.2f%n", d, m));

        // ════════════════════════════════════════════
        //  6. anyMatch, allMatch, noneMatch, findFirst
        // ════════════════════════════════════════════
        separador("6. anyMatch, allMatch, noneMatch, findFirst");

        boolean algumAcimaDe10k = funcionarios.stream()
            .anyMatch(f -> f.salario > 10000);
        System.out.println("  Algum salário > R$ 10.000?   " + algumAcimaDe10k); // true

        boolean todosAcimaDe3k = funcionarios.stream()
            .allMatch(f -> f.salario > 3000);
        System.out.println("  Todos salários > R$ 3.000?   " + todosAcimaDe3k);  // true

        boolean nenhumDeFinanceiro = funcionarios.stream()
            .noneMatch(f -> f.departamento.equals("Financeiro"));
        System.out.println("  Nenhum do Financeiro?        " + nenhumDeFinanceiro); // true

        Optional<Funcionario> primeiroTI = funcionarios.stream()
            .filter(ehDeTI)
            .findFirst();
        primeiroTI.ifPresent(f -> System.out.println("  Primeiro de TI: " + f.nome));

        // ════════════════════════════════════════════
        //  7. mapToDouble, sum, min, max (IntStream/DoubleStream)
        // ════════════════════════════════════════════
        separador("7. Streams primitivos: mapToDouble");

        double maiorSalario = funcionarios.stream()
            .mapToDouble(f -> f.salario)
            .max()
            .orElse(0.0);

        double menorSalario = funcionarios.stream()
            .mapToDouble(f -> f.salario)
            .min()
            .orElse(0.0);

        System.out.printf("  Maior salário: R$ %,.2f%n", maiorSalario);
        System.out.printf("  Menor salário: R$ %,.2f%n", menorSalario);
    }

    private static void separador(String titulo) {
        System.out.println("\n  [" + titulo + "]");
    }
}
