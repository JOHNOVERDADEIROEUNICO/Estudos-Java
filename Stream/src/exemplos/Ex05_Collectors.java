package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import modelo.Pessoa;

/**
 * ═══════════════════════════════════════════════════════════════
 *  5. COLLECTORS — Coletando resultados do Stream
 * ═══════════════════════════════════════════════════════════════
 *
 *  A classe Collectors fornece métodos prontos para transformar
 *  o resultado de um Stream em:
 *    - List, Set, Map
 *    - String (joining)
 *    - Agrupamentos (groupingBy)
 *    - Partições (partitioningBy)
 *    - Estatísticas (summarizing)
 */
public class Ex05_Collectors {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 5 — Collectors                      ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Ana",     25, "São Paulo",      5000),
            new Pessoa("Bruno",   30, "Rio de Janeiro", 7000),
            new Pessoa("Carlos",  22, "São Paulo",      3500),
            new Pessoa("Diana",   28, "Rio de Janeiro", 8500),
            new Pessoa("Eduardo", 35, "Belo Horizonte", 6000),
            new Pessoa("Fernanda",26, "São Paulo",      4500),
            new Pessoa("Gabriel", 31, "Belo Horizonte", 9000)
        );

        // -------------------------------------------------------
        // 5.1  toList(), toSet()
        // -------------------------------------------------------
        List<String> listaNomes = pessoas.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.toList());

        Set<String> setCidades = pessoas.stream()
                .map(Pessoa::getCidade)
                .collect(Collectors.toSet());  // sem duplicatas

        System.out.println("5.1  toList() de nomes:  " + listaNomes);
        System.out.println("     toSet() de cidades: " + setCidades);

        // -------------------------------------------------------
        // 5.2  joining() — concatenar strings
        // -------------------------------------------------------
        String nomesJuntos = pessoas.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.joining(" | "));
        System.out.println("\n5.2  joining(): " + nomesJuntos);

        // -------------------------------------------------------
        // 5.3  groupingBy() — agrupar por uma propriedade
        //      Resultado: Map<Chave, List<Pessoa>>
        // -------------------------------------------------------
        Map<String, List<Pessoa>> porCidade = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getCidade));

        System.out.println("\n5.3  groupingBy(cidade):");
        porCidade.forEach((cidade, lista) -> {
            System.out.println("     " + cidade + ":");
            lista.forEach(p -> System.out.println("       - " + p.getNome()));
        });

        // -------------------------------------------------------
        // 5.4  groupingBy() com counting() — quantos por grupo
        // -------------------------------------------------------
        Map<String, Long> contagemPorCidade = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getCidade, Collectors.counting()));
        System.out.println("\n5.4  groupingBy + counting():");
        contagemPorCidade.forEach((cidade, qtd) ->
                System.out.println("     " + cidade + ": " + qtd + " pessoa(s)"));

        // -------------------------------------------------------
        // 5.5  groupingBy() com averagingDouble() — média salarial
        // -------------------------------------------------------
        Map<String, Double> mediaSalarialPorCidade = pessoas.stream()
                .collect(Collectors.groupingBy(
                        Pessoa::getCidade,
                        Collectors.averagingDouble(Pessoa::getSalario)
                ));
        System.out.println("\n5.5  groupingBy + averagingDouble() — média salarial:");
        mediaSalarialPorCidade.forEach((cidade, media) ->
                System.out.println("     " + cidade + ": R$" + String.format("%.2f", media)));

        // -------------------------------------------------------
        // 5.6  partitioningBy() — divide em 2 grupos (true/false)
        // -------------------------------------------------------
        Map<Boolean, List<Pessoa>> particao = pessoas.stream()
                .collect(Collectors.partitioningBy(p -> p.getSalario() >= 6000));

        System.out.println("\n5.6  partitioningBy(salário >= 6000):");
        System.out.println("     Salário >= R$6000:");
        particao.get(true).forEach(p -> System.out.println("       - " + p));
        System.out.println("     Salário < R$6000:");
        particao.get(false).forEach(p -> System.out.println("       - " + p));

        // -------------------------------------------------------
        // 5.7  toMap() — criar um Map a partir do stream
        //      Chave: nome, Valor: salário
        // -------------------------------------------------------
        Map<String, Double> nomeSalario = pessoas.stream()
                .collect(Collectors.toMap(
                        Pessoa::getNome,     // keyMapper
                        Pessoa::getSalario   // valueMapper
                ));
        System.out.println("\n5.7  toMap(nome → salário):");
        nomeSalario.forEach((nome, salario) ->
                System.out.println("     " + nome + " → R$" + String.format("%.2f", salario)));

        System.out.println();
    }
}
