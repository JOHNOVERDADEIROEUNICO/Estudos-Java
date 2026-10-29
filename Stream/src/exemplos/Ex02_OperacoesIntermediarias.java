package exemplos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════
 *  2. OPERAÇÕES INTERMEDIÁRIAS — Transformam o stream e retornam
 *     outro stream (são "lazy", só executam quando há uma
 *     operação terminal).
 * ═══════════════════════════════════════════════════════════════
 *
 *  Principais operações intermediárias:
 *    filter()   — filtra elementos por condição
 *    map()      — transforma cada elemento
 *    sorted()   — ordena os elementos
 *    distinct() — remove duplicatas
 *    peek()     — espiar sem consumir (útil para debug)
 *    limit()    — limita a quantidade de elementos
 *    skip()     — pula N elementos iniciais
 */
public class Ex02_OperacoesIntermediarias {

    public static void executar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO 2 — Operações Intermediárias        ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<Integer> numeros = Arrays.asList(5, 3, 8, 1, 9, 2, 7, 3, 5, 8);

        // -------------------------------------------------------
        // 2.1  filter() — mantém só os elementos que satisfazem a condição
        // -------------------------------------------------------
        System.out.println("2.1  filter() — números maiores que 5:");
        List<Integer> maioresQue5 = numeros.stream()
                .filter(n -> n > 5)
                .collect(Collectors.toList());
        System.out.println("     " + maioresQue5);

        // -------------------------------------------------------
        // 2.2  map() — transforma cada elemento
        //      Aqui: multiplicar cada número por 2
        // -------------------------------------------------------
        System.out.println("\n2.2  map() — cada número multiplicado por 2:");
        List<Integer> dobrados = numeros.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("     " + dobrados);

        // -------------------------------------------------------
        // 2.3  sorted() — ordena os elementos
        // -------------------------------------------------------
        System.out.println("\n2.3  sorted() — números em ordem crescente:");
        List<Integer> ordenados = numeros.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("     " + ordenados);

        // -------------------------------------------------------
        // 2.4  distinct() — remove duplicatas
        // -------------------------------------------------------
        System.out.println("\n2.4  distinct() — sem duplicatas:");
        List<Integer> unicos = numeros.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("     " + unicos);

        // -------------------------------------------------------
        // 2.5  peek() — espiar cada elemento (sem consumir)
        //      Útil para debug: permite ver o que passa pelo pipeline
        // -------------------------------------------------------
        System.out.println("\n2.5  peek() — espiar + filtrar + coletar:");
        List<Integer> resultado = numeros.stream()
                .peek(n -> System.out.println("     [peek antes do filtro] " + n))
                .filter(n -> n > 5)
                .peek(n -> System.out.println("     [peek depois do filtro] " + n))
                .collect(Collectors.toList());
        System.out.println("     Resultado: " + resultado);

        // -------------------------------------------------------
        // 2.6  limit() e skip()
        //      limit(3) → pega os 3 primeiros
        //      skip(2)  → pula os 2 primeiros
        // -------------------------------------------------------
        System.out.println("\n2.6  limit(3) — primeiros 3 elementos:");
        numeros.stream().limit(3).forEach(n -> System.out.print("     " + n));
        System.out.println();

        System.out.println("     skip(7) — pula 7, pega o restante:");
        numeros.stream().skip(7).forEach(n -> System.out.print("     " + n));

        // -------------------------------------------------------
        // 2.7  Combinando operações — o verdadeiro poder do Stream!
        //      Pegar números > 3, sem duplicatas, ordenados, dobrados
        // -------------------------------------------------------
        System.out.println("\n\n2.7  Combinando: filter > distinct > sorted > map:");
        List<Integer> combinado = numeros.stream()
                .filter(n -> n > 3)         // mantém > 3
                .distinct()                  // remove duplicatas
                .sorted()                    // ordena
                .map(n -> n * 2)             // dobra
                .collect(Collectors.toList());
        System.out.println("     Original:  " + numeros);
        System.out.println("     Resultado: " + combinado);

        System.out.println();
    }
}
