package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ═══════════════════════════════════════════════════════════════
 *  8. REDUCE — Entendendo a fundo a redução de dados
 * ═══════════════════════════════════════════════════════════════
 *
 *  reduce() combina todos os elementos do stream em um único
 *  resultado, aplicando uma operação binária repetidamente.
 *
 *  Formas:
 *    reduce(BinaryOperator)                → Optional<T>
 *    reduce(identidade, BinaryOperator)    → T
 *    reduce(identidade, BiFunction, BinaryOperator) → U (para paralelo)
 *
 *  Como funciona (exemplo com soma):
 *    [1, 2, 3, 4]
 *    passo 1: acum=0, elem=1 → 0+1 = 1
 *    passo 2: acum=1, elem=2 → 1+2 = 3
 *    passo 3: acum=3, elem=3 → 3+3 = 6
 *    passo 4: acum=6, elem=4 → 6+4 = 10
 */
public class Ex08_Reduce {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 8 — Reduce (redução de dados)       ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // -------------------------------------------------------
        // 8.1  Soma com reduce
        // -------------------------------------------------------
        int soma = numeros.stream()
                .reduce(0, (acum, elem) -> acum + elem);
        System.out.println("8.1  Soma (reduce):        " + soma);

        // -------------------------------------------------------
        // 8.2  Produto com reduce
        // -------------------------------------------------------
        int produto = numeros.stream()
                .reduce(1, (acum, elem) -> acum * elem);
        System.out.println("8.2  Produto (reduce):     " + produto);

        // -------------------------------------------------------
        // 8.3  Maior valor com reduce (sem valor inicial)
        // -------------------------------------------------------
        Optional<Integer> maior = numeros.stream()
                .reduce((a, b) -> a > b ? a : b);
        System.out.println("8.3  Maior (reduce):       " + maior.orElse(0));

        // -------------------------------------------------------
        // 8.4  Concatenar strings com reduce
        // -------------------------------------------------------
        List<String> palavras = Arrays.asList("Java", "Stream", "API", "é", "poderosa");
        String frase = palavras.stream()
                .reduce("", (acum, palavra) -> acum + " " + palavra)
                .trim();
        System.out.println("8.4  Concatenar (reduce):  \"" + frase + "\"");

        // -------------------------------------------------------
        // 8.5  Contar caracteres de todas as palavras com reduce
        // -------------------------------------------------------
        int totalChars = palavras.stream()
                .reduce(0,
                        (acum, palavra) -> acum + palavra.length(), // accumulator
                        Integer::sum);                               // combiner (para paralelo)
        System.out.println("8.5  Total de caracteres:  " + totalChars);

        // -------------------------------------------------------
        // 8.6  Reduce com visualização passo a passo
        // -------------------------------------------------------
        System.out.println("\n8.6  Passo a passo da soma:");
        int resultado = numeros.stream()
                .reduce(0, (acum, elem) -> {
                    int novoValor = acum + elem;
                    System.out.println("     acum=" + acum + " + elem=" + elem + " = " + novoValor);
                    return novoValor;
                });
        System.out.println("     Resultado final: " + resultado);

        System.out.println();
    }
}
