package exemplos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import modelo.Pessoa;

/**
 * ═══════════════════════════════════════════════════════════════
 *  6. ORDENAÇÃO E COMPARAÇÃO — sorted() e Comparator
 * ═══════════════════════════════════════════════════════════════
 *
 *  sorted() pode receber:
 *    - Nenhum argumento → ordem natural (Comparable)
 *    - Um Comparator → ordem personalizada
 *
 *  Comparator possui métodos encadeáveis:
 *    comparing()       — compara por uma propriedade
 *    thenComparing()   — desempate
 *    reversed()        — ordem inversa
 */
public class Ex06_OrdenacaoComparacao {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 6 — Ordenação e Comparação          ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Carlos",  22, "São Paulo",      3500),
            new Pessoa("Ana",     25, "Rio de Janeiro", 5000),
            new Pessoa("Bruno",   30, "São Paulo",      7000),
            new Pessoa("Diana",   25, "Belo Horizonte", 8500),
            new Pessoa("Eduardo", 22, "São Paulo",      6000)
        );

        // -------------------------------------------------------
        // 6.1  Ordenar por nome (ordem alfabética)
        // -------------------------------------------------------
        System.out.println("6.1  sorted() por nome:");
        pessoas.stream()
               .sorted(Comparator.comparing(Pessoa::getNome))
               .forEach(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.2  Ordenar por idade (crescente)
        // -------------------------------------------------------
        System.out.println("\n6.2  sorted() por idade crescente:");
        pessoas.stream()
               .sorted(Comparator.comparingInt(Pessoa::getIdade))
               .forEach(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.3  Ordenar por salário (decrescente) usando reversed()
        // -------------------------------------------------------
        System.out.println("\n6.3  sorted() por salário DECRESCENTE:");
        pessoas.stream()
               .sorted(Comparator.comparingDouble(Pessoa::getSalario).reversed())
               .forEach(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.4  Ordenar por idade, desempate por nome
        // -------------------------------------------------------
        System.out.println("\n6.4  sorted() por idade + desempate por nome:");
        pessoas.stream()
               .sorted(Comparator.comparingInt(Pessoa::getIdade)
                                  .thenComparing(Pessoa::getNome))
               .forEach(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.5  Pessoa com maior salário usando max()
        // -------------------------------------------------------
        Optional<Pessoa> maisRica = pessoas.stream()
                .max(Comparator.comparingDouble(Pessoa::getSalario));
        System.out.println("\n6.5  max() — maior salário:");
        maisRica.ifPresent(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.6  Pessoa mais nova usando min()
        // -------------------------------------------------------
        Optional<Pessoa> maisNova = pessoas.stream()
                .min(Comparator.comparingInt(Pessoa::getIdade));
        System.out.println("\n6.6  min() — mais nova:");
        maisNova.ifPresent(p -> System.out.println("     " + p));

        // -------------------------------------------------------
        // 6.7  Top 3 maiores salários
        // -------------------------------------------------------
        System.out.println("\n6.7  Top 3 maiores salários:");
        List<Pessoa> top3 = pessoas.stream()
                .sorted(Comparator.comparingDouble(Pessoa::getSalario).reversed())
                .limit(3)
                .collect(Collectors.toList());
        top3.forEach(p -> System.out.println("     " + p));

        System.out.println();
    }
}
