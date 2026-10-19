import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * =============================================================
 *  03 - LAMBDA COM COLEÇÕES E STREAMS
 * =============================================================
 *
 * A API de Streams (java.util.stream) é onde lambdas brilham!
 *
 * Stream Pipeline:
 *   fonte → operações intermediárias → operação terminal
 *
 * Operações Intermediárias (lazy, retornam Stream):
 *   filter()    → filtra elementos com Predicate
 *   map()       → transforma com Function
 *   flatMap()   → transforma e "achata" listas de listas
 *   distinct()  → remove duplicatas
 *   sorted()    → ordena
 *   limit()     → limita quantidade
 *   peek()      → inspeciona sem modificar (debug)
 *
 * Operações Terminais (eager, encerram o pipeline):
 *   forEach()   → consome cada elemento
 *   collect()   → coleta para List, Set, Map, etc.
 *   count()     → conta elementos
 *   findFirst() → retorna o primeiro (Optional)
 *   anyMatch()  → verifica se algum satisfaz
 *   allMatch()  → verifica se todos satisfazem
 *   reduce()    → reduz a um único valor
 *   min()/max() → retorna mínimo/máximo
 */
public class LambdaComColecoes {

    // Classe auxiliar para exemplos
    record Produto(String nome, double preco, String categoria) {}

    public static void executar() {
        System.out.println("\n--- 03. LAMBDA COM COLEÇÕES E STREAMS ---");

        // -------------------------------------------------------
        // ITERAÇÃO COM forEach (substitui for tradicional)
        // -------------------------------------------------------
        System.out.println("\n[forEach com lambda]");
        List<String> frutas = List.of("Maçã", "Banana", "Laranja", "Uva", "Manga");
        frutas.forEach(f -> System.out.println("  Fruta: " + f));

        // -------------------------------------------------------
        // FILTER → seleciona elementos que passam no Predicate
        // -------------------------------------------------------
        System.out.println("\n[filter]");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Pares: " + pares);

        List<Integer> maioresQue5 = numeros.stream()
                .filter(n -> n > 5)
                .collect(Collectors.toList());
        System.out.println("Maiores que 5: " + maioresQue5);

        // -------------------------------------------------------
        // MAP → transforma cada elemento
        // -------------------------------------------------------
        System.out.println("\n[map]");
        List<String> nomes = Arrays.asList("alice", "bob", "charlie", "diana");

        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase)        // method reference!
                .collect(Collectors.toList());
        System.out.println("Maiúsculos: " + maiusculos);

        List<Integer> tamanhosNomes = nomes.stream()
                .map(nome -> nome.length())
                .collect(Collectors.toList());
        System.out.println("Tamanhos: " + tamanhosNomes);

        // -------------------------------------------------------
        // FILTER + MAP em pipeline
        // -------------------------------------------------------
        System.out.println("\n[pipeline filter + map]");
        List<String> resultado = nomes.stream()
                .filter(n -> n.length() > 3)           // apenas nomes > 3 chars
                .map(n -> n.substring(0, 1).toUpperCase() + n.substring(1)) // capitaliza
                .collect(Collectors.toList());
        System.out.println("Nomes longos capitalizados: " + resultado);

        // -------------------------------------------------------
        // REDUCE → reduz a um único valor
        // -------------------------------------------------------
        System.out.println("\n[reduce]");
        int somaTodos = numeros.stream()
                .reduce(0, (acumulador, n) -> acumulador + n);
        System.out.println("Soma total: " + somaTodos);

        int produto = numeros.stream()
                .reduce(1, (acc, n) -> acc * n);
        System.out.println("Produto total: " + produto);

        // -------------------------------------------------------
        // COUNT, MIN, MAX
        // -------------------------------------------------------
        System.out.println("\n[count, min, max]");
        long qtdPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Quantidade de pares: " + qtdPares);

        Optional<Integer> minimo = numeros.stream().min((a, b) -> a - b);
        Optional<Integer> maximo = numeros.stream().max((a, b) -> a - b);
        System.out.println("Mínimo: " + minimo.orElse(-1));
        System.out.println("Máximo: " + maximo.orElse(-1));

        // -------------------------------------------------------
        // ANYmatch, ALLMATCH, NONEMATCH
        // -------------------------------------------------------
        System.out.println("\n[anyMatch, allMatch, noneMatch]");
        boolean algumPar       = numeros.stream().anyMatch(n -> n % 2 == 0);
        boolean todosPositivos = numeros.stream().allMatch(n -> n > 0);
        boolean nenhumNegativo = numeros.stream().noneMatch(n -> n < 0);

        System.out.println("Algum par?        " + algumPar);
        System.out.println("Todos positivos?  " + todosPositivos);
        System.out.println("Nenhum negativo?  " + nenhumNegativo);

        // -------------------------------------------------------
        // COLLECT → agrupa em Map, Set, etc.
        // -------------------------------------------------------
        System.out.println("\n[collect para Map]");
        Map<String, Integer> nomePorTamanho = nomes.stream()
                .collect(Collectors.toMap(
                        nome -> nome,            // chave: o próprio nome
                        nome -> nome.length()    // valor: tamanho
                ));
        nomePorTamanho.forEach((k, v) -> System.out.println("  " + k + " → " + v));

        // -------------------------------------------------------
        // EXEMPLO PRÁTICO COM OBJETOS (Record Produto)
        // -------------------------------------------------------
        System.out.println("\n[exemplo prático com objetos]");
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook",   3500.0, "Eletrônico"),
            new Produto("Celular",    1800.0, "Eletrônico"),
            new Produto("Livro Java", 89.90,  "Livro"),
            new Produto("Tablet",     2200.0, "Eletrônico"),
            new Produto("Caneta",     4.50,   "Papelaria"),
            new Produto("Caderno",    25.00,  "Papelaria")
        );

        // Filtrar eletrônicos e ordenar por preço
        System.out.println("Eletrônicos ordenados por preço:");
        produtos.stream()
                .filter(p -> p.categoria().equals("Eletrônico"))
                .sorted((a, b) -> Double.compare(a.preco(), b.preco()))
                .forEach(p -> System.out.printf("  %-12s → R$ %.2f%n", p.nome(), p.preco()));

        // Soma total dos preços
        double total = produtos.stream()
                .mapToDouble(Produto::preco)  // mapToDouble para primitivos
                .sum();
        System.out.printf("Valor total do estoque: R$ %.2f%n", total);

        // Agrupar por categoria com groupingBy
        System.out.println("Agrupados por categoria:");
        Map<String, List<Produto>> porCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::categoria));
        porCategoria.forEach((cat, lista) -> {
            System.out.println("  " + cat + ":");
            lista.forEach(p -> System.out.println("    - " + p.nome()));
        });
    }
}
