package exemplos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import modelo.Pessoa;

/**
 * ═══════════════════════════════════════════════════════════════
 *  10. CASO PRÁTICO — Relatório completo com Stream API
 * ═══════════════════════════════════════════════════════════════
 *
 *  Um exemplo realista combinando tudo que aprendemos para gerar
 *  um relatório de funcionários de uma empresa.
 */
public class Ex10_CasoPratico {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 10 — Caso Prático: Relatório        ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Pessoa> funcionarios = Arrays.asList(
            new Pessoa("Ana",      25, "São Paulo",      5200),
            new Pessoa("Bruno",    30, "Rio de Janeiro", 7800),
            new Pessoa("Carlos",   22, "São Paulo",      3200),
            new Pessoa("Diana",    28, "Belo Horizonte", 8500),
            new Pessoa("Eduardo",  35, "Rio de Janeiro", 6100),
            new Pessoa("Fernanda", 26, "São Paulo",      4800),
            new Pessoa("Gabriel",  31, "Curitiba",       9200),
            new Pessoa("Helena",   24, "Belo Horizonte", 3800),
            new Pessoa("Igor",     29, "Curitiba",       7200),
            new Pessoa("Julia",    27, "São Paulo",      5600)
        );

        System.out.println("📋 RELATÓRIO DE FUNCIONÁRIOS\n");
        System.out.println("Total de funcionários: " + funcionarios.size());

        // 1. Funcionários com salário acima da média
        double mediaSalarial = funcionarios.stream()
                .mapToDouble(Pessoa::getSalario)
                .average()
                .orElse(0);

        System.out.println("\n── Salário médio: R$" + String.format("%.2f", mediaSalarial));
        System.out.println("\n── Acima da média salarial:");
        funcionarios.stream()
                .filter(f -> f.getSalario() > mediaSalarial)
                .sorted(Comparator.comparingDouble(Pessoa::getSalario).reversed())
                .forEach(f -> System.out.println("   " + f));

        // 2. Quantidade de funcionários por cidade
        System.out.println("\n── Funcionários por cidade:");
        Map<String, Long> porCidade = funcionarios.stream()
                .collect(Collectors.groupingBy(Pessoa::getCidade, Collectors.counting()));
        porCidade.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println("   " + e.getKey() + ": " + e.getValue()));

        // 3. Média salarial por cidade
        System.out.println("\n── Média salarial por cidade:");
        funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Pessoa::getCidade,
                        Collectors.averagingDouble(Pessoa::getSalario)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(e -> System.out.println("   " + e.getKey() + ": R$" +
                        String.format("%.2f", e.getValue())));

        // 4. Top 3 maiores salários
        System.out.println("\n── Top 3 maiores salários:");
        funcionarios.stream()
                .sorted(Comparator.comparingDouble(Pessoa::getSalario).reversed())
                .limit(3)
                .forEach(f -> System.out.println("   🏆 " + f));

        // 5. Funcionários jovens (< 26 anos) de São Paulo
        System.out.println("\n── Jovens (< 26 anos) de São Paulo:");
        List<String> jovensSP = funcionarios.stream()
                .filter(f -> f.getIdade() < 26)
                .filter(f -> f.getCidade().equals("São Paulo"))
                .map(Pessoa::getNome)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("   " + (jovensSP.isEmpty() ? "Nenhum encontrado" : String.join(", ", jovensSP)));

        // 6. Folha salarial total
        double folhaTotal = funcionarios.stream()
                .mapToDouble(Pessoa::getSalario)
                .sum();
        System.out.println("\n── Folha salarial total: R$" + String.format("%.2f", folhaTotal));

        // 7. Resumo final com joining
        String todosOsNomes = funcionarios.stream()
                .map(Pessoa::getNome)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("\n── Equipe completa: " + todosOsNomes);

        System.out.println();
    }
}
