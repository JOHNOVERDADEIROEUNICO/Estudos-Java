package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import modelo.Pessoa;

/**
 * ═══════════════════════════════════════════════════════════════
 *  4. MAP e FLATMAP — Transformação de elementos
 * ═══════════════════════════════════════════════════════════════
 *
 *  map()     → transforma cada elemento individualmente
 *             (1 entrada → 1 saída)
 *
 *  flatMap() → transforma cada elemento em um Stream e depois
 *              "achata" (flatten) todos em um único Stream
 *              (1 entrada → N saídas)
 *
 *  Analogia:
 *    map()     = abrir uma caixa e trocar o conteúdo
 *    flatMap() = abrir várias caixas e juntar tudo em uma só
 */
public class Ex04_MapEFlatMap {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 4 — map() e flatMap()               ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // -------------------------------------------------------
        // 4.1  map() — extrair uma propriedade de objetos
        // -------------------------------------------------------
        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Ana",    25, "São Paulo",    5000),
            new Pessoa("Bruno",  30, "Rio de Janeiro", 7000),
            new Pessoa("Carlos", 22, "Belo Horizonte", 3500),
            new Pessoa("Diana",  28, "São Paulo",    8500)
        );

        System.out.println("4.1  map() — extrair apenas os nomes:");
        List<String> nomes = pessoas.stream()
                .map(Pessoa::getNome)   // Pessoa → String (nome)
                .collect(Collectors.toList());
        System.out.println("     " + nomes);

        // -------------------------------------------------------
        // 4.2  map() — transformar strings
        // -------------------------------------------------------
        System.out.println("\n4.2  map() — nomes em maiúsculo:");
        List<String> nomesMaiusculos = pessoas.stream()
                .map(Pessoa::getNome)
                .map(String::toUpperCase)   // encadeando dois map()
                .collect(Collectors.toList());
        System.out.println("     " + nomesMaiusculos);

        // -------------------------------------------------------
        // 4.3  flatMap() — "achatar" listas dentro de listas
        //
        //      Sem flatMap:  [[a, b], [c, d]] → Stream de Lists
        //      Com flatMap:  [[a, b], [c, d]] → [a, b, c, d]
        // -------------------------------------------------------
        List<List<String>> listaDeListas = Arrays.asList(
            Arrays.asList("Java", "Kotlin"),
            Arrays.asList("Python", "Ruby"),
            Arrays.asList("JavaScript", "TypeScript")
        );

        System.out.println("\n4.3  flatMap() — achatar lista de listas:");
        List<String> todasLinguagens = listaDeListas.stream()
                .flatMap(lista -> lista.stream())   // List<String> → Stream<String>
                .collect(Collectors.toList());
        System.out.println("     " + todasLinguagens);

        // -------------------------------------------------------
        // 4.4  flatMap() — dividir frases em palavras
        // -------------------------------------------------------
        List<String> frases = Arrays.asList(
            "Java é incrível",
            "Streams são poderosos",
            "Programação funcional"
        );

        System.out.println("\n4.4  flatMap() — separar frases em palavras individuais:");
        List<String> palavras = frases.stream()
                .flatMap(frase -> Arrays.stream(frase.split(" ")))
                .collect(Collectors.toList());
        System.out.println("     " + palavras);

        // -------------------------------------------------------
        // 4.5  Comparação visual: map vs flatMap
        // -------------------------------------------------------
        System.out.println("\n4.5  Comparação: map() vs flatMap()");

        // Com map() — cada frase vira um array (Stream de arrays!)
        System.out.println("     map() → gera Stream<String[]>:");
        frases.stream()
              .map(frase -> frase.split(" "))
              .forEach(arr -> System.out.println("       " + Arrays.toString(arr)));

        // Com flatMap() — tudo "achatado" em um só Stream<String>
        System.out.println("     flatMap() → gera Stream<String>:");
        frases.stream()
              .flatMap(frase -> Arrays.stream(frase.split(" ")))
              .forEach(palavra -> System.out.println("       " + palavra));

        System.out.println();
    }
}
