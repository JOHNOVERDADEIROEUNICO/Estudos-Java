package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════
 *  3. OPERAÇÕES TERMINAIS — Consomem o stream e produzem um
 *     resultado final (depois de uma terminal, o stream não
 *     pode mais ser usado).
 * ═══════════════════════════════════════════════════════════════
 *
 *  Principais operações terminais:
 *    forEach()    — executa uma ação para cada elemento
 *    collect()    — transforma o stream em coleção, String, etc.
 *    count()      — conta os elementos
 *    reduce()     — reduz todos os elementos a um único valor
 *    findFirst()  — retorna o primeiro elemento (Optional)
 *    findAny()    — retorna qualquer elemento (Optional)
 *    anyMatch()   — algum elemento atende à condição?
 *    allMatch()   — todos atendem à condição?
 *    noneMatch()  — nenhum atende à condição?
 *    min() / max()— menor / maior elemento
 *    toArray()    — converte para array
 */
public class Ex03_OperacoesTerminais {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 3 — Operações Terminais             ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Integer> numeros = Arrays.asList(5, 3, 8, 1, 9, 2, 7);
        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carlos", "Diana", "Eduardo");

        // -------------------------------------------------------
        // 3.1  forEach() — executa ação para cada elemento
        // -------------------------------------------------------
        System.out.println("3.1  forEach():");
        nomes.stream().forEach(n -> System.out.println("     Olá, " + n + "!"));

        // -------------------------------------------------------
        // 3.2  collect() — coleta em uma lista, set, string, etc.
        // -------------------------------------------------------
        System.out.println("\n3.2  collect() — juntar nomes em uma String:");
        String nomesJuntos = nomes.stream()
                .collect(Collectors.joining(", "));
        System.out.println("     " + nomesJuntos);

        // -------------------------------------------------------
        // 3.3  count() — quantos elementos passaram pelo pipeline
        // -------------------------------------------------------
        long qtdPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("\n3.3  count() — números pares: " + qtdPares);

        // -------------------------------------------------------
        // 3.4  reduce() — acumula todos em um único valor
        //      Aqui: somar todos os números
        // -------------------------------------------------------
        int soma = numeros.stream()
                .reduce(0, (acumulador, elemento) -> acumulador + elemento);
        //                  ↑ valor inicial
        System.out.println("\n3.4  reduce() — soma de todos: " + soma);

        // reduce com method reference (Integer::sum)
        int soma2 = numeros.stream().reduce(0, Integer::sum);
        System.out.println("     reduce() com method reference: " + soma2);

        // reduce sem valor inicial → retorna Optional
        Optional<Integer> maiorOpt = numeros.stream()
                .reduce(Integer::max);
        maiorOpt.ifPresent(m -> System.out.println("     reduce() para encontrar o maior: " + m));

        // -------------------------------------------------------
        // 3.5  findFirst() e findAny()
        // -------------------------------------------------------
        Optional<String> primeiro = nomes.stream()
                .filter(n -> n.startsWith("C"))
                .findFirst();
        System.out.println("\n3.5  findFirst() com 'C': " + primeiro.orElse("nenhum encontrado"));

        // -------------------------------------------------------
        // 3.6  anyMatch(), allMatch(), noneMatch()
        // -------------------------------------------------------
        boolean temMaiorQue8 = numeros.stream().anyMatch(n -> n > 8);
        boolean todosMaioresQue0 = numeros.stream().allMatch(n -> n > 0);
        boolean nenhumNegativo = numeros.stream().noneMatch(n -> n < 0);

        System.out.println("\n3.6  Matchers:");
        System.out.println("     anyMatch(n > 8):   " + temMaiorQue8);
        System.out.println("     allMatch(n > 0):   " + todosMaioresQue0);
        System.out.println("     noneMatch(n < 0):  " + nenhumNegativo);

        // -------------------------------------------------------
        // 3.7  min() e max()
        // -------------------------------------------------------
        Optional<Integer> minimo = numeros.stream().min(Integer::compareTo);
        Optional<Integer> maximo = numeros.stream().max(Integer::compareTo);
        System.out.println("\n3.7  min(): " + minimo.orElse(0) + "  |  max(): " + maximo.orElse(0));

        // -------------------------------------------------------
        // 3.8  toArray() — converte para array
        // -------------------------------------------------------
        Object[] arrayDeNomes = nomes.stream()
                .filter(n -> n.length() > 4)
                .toArray();
        System.out.println("\n3.8  toArray() — nomes com mais de 4 letras: " + Arrays.toString(arrayDeNomes));

        System.out.println();
    }
}
