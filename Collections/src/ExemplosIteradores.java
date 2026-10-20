import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * ============================================================
 *  COLLECTIONS - ITERADORES
 * ============================================================
 *
 * Como percorrer coleções no Java? Existem várias formas:
 *
 *  1. for clássico          → só funciona com List (tem índice)
 *  2. for-each (enhanced)   → funciona em qualquer Iterable
 *  3. Iterator              → controle manual, permite remoção segura
 *  4. ListIterator          → bidirecional, só para List
 *  5. forEach + lambda      → Java 8+, moderno e conciso
 *  6. Stream API            → Java 8+, para transformações e filtros
 *
 * Quando usar Iterator vs for-each?
 *  → Use Iterator quando precisar REMOVER elementos durante
 *    a iteração! Remover via for-each normal causa
 *    ConcurrentModificationException.
 * ============================================================
 */
public class ExemplosIteradores {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        EXEMPLOS: Iteradores          ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploForClassico();
        exemploForEach();
        exemploIterator();
        exemploListIterator();
        exemploForEachLambda();
        exemploStreamAPI();
        exemploErroComum();
    }

    // ─── 1. FOR CLÁSSICO ─────────────────────────────────────
    static void exemploForClassico() {
        System.out.println("--- 1. for clássico (só para List) ---");

        List<String> frutas = new ArrayList<>(Arrays.asList("Banana", "Maçã", "Laranja"));

        for (int i = 0; i < frutas.size(); i++) {
            System.out.println("  [" + i + "] → " + frutas.get(i));
        }

        // Útil quando precisa do índice explicitamente
        // ou quando precisa iterar de trás para frente
        System.out.println("  (de trás para frente:)");
        for (int i = frutas.size() - 1; i >= 0; i--) {
            System.out.println("  [" + i + "] → " + frutas.get(i));
        }

        System.out.println();
    }

    // ─── 2. FOR-EACH (enhanced for) ──────────────────────────
    static void exemploForEach() {
        System.out.println("--- 2. for-each (funciona em qualquer Iterable) ---");

        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
        System.out.println();

        // Funciona com Set, Queue, Map.keySet(), etc.
        java.util.Set<String> nomes = new java.util.LinkedHashSet<>(
            Arrays.asList("Ana", "Bruno", "Carlos")
        );
        for (String nome : nomes) {
            System.out.print(nome + " ");
        }
        System.out.println("\n");
    }

    // ─── 3. ITERATOR ─────────────────────────────────────────
    static void exemploIterator() {
        System.out.println("--- 3. Iterator (com remoção segura) ---");

        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original: " + numeros);

        // Criando o iterador
        Iterator<Integer> it = numeros.iterator();

        // hasNext() → tem próximo?
        // next() → retorna o próximo e avança o cursor
        System.out.print("Percorrendo: ");
        while (it.hasNext()) {
            int num = it.next();
            System.out.print(num + " ");
        }
        System.out.println();

        // Remoção SEGURA durante iteração via iterator.remove()
        Iterator<Integer> itRemover = numeros.iterator();
        while (itRemover.hasNext()) {
            int num = itRemover.next();
            if (num % 2 == 0) {        // remove pares
                itRemover.remove();    // ← seguro! não lança exceção
            }
        }
        System.out.println("Após remover pares: " + numeros);

        System.out.println();
    }

    // ─── 4. LISTITERATOR (bidirecional) ──────────────────────
    static void exemploListIterator() {
        System.out.println("--- 4. ListIterator (bidirecional, só para List) ---");

        List<String> letras = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("Original: " + letras);

        ListIterator<String> lit = letras.listIterator();

        // Avançar
        System.out.print("Para frente: ");
        while (lit.hasNext()) {
            System.out.print(lit.nextIndex() + ":" + lit.next() + " ");
        }
        System.out.println();

        // Voltar (cursor já está no final)
        System.out.print("Para trás:  ");
        while (lit.hasPrevious()) {
            System.out.print(lit.previousIndex() + ":" + lit.previous() + " ");
        }
        System.out.println();

        // set() → substitui o último elemento retornado
        ListIterator<String> litSet = letras.listIterator();
        while (litSet.hasNext()) {
            String letra = litSet.next();
            litSet.set(letra.toLowerCase()); // converte para minúscula
        }
        System.out.println("Após set (minúsculo): " + letras);

        // add() → insere antes da posição atual
        ListIterator<String> litAdd = letras.listIterator(2); // começa no índice 2
        litAdd.add("INSERIDO");
        System.out.println("Após add na posição 2: " + letras);

        System.out.println();
    }

    // ─── 5. FOREACH COM LAMBDA ───────────────────────────────
    static void exemploForEachLambda() {
        System.out.println("--- 5. forEach com lambda (Java 8+) ---");

        List<String> frutas = Arrays.asList("Banana", "Maçã", "Laranja", "Uva");

        // forEach básico
        System.out.println("forEach simples:");
        frutas.forEach(f -> System.out.println("  " + f));

        // forEach com method reference
        System.out.print("Method reference: ");
        frutas.forEach(System.out::println);

        // Map.forEach → recebe chave e valor
        java.util.Map<String, Integer> mapa = new java.util.LinkedHashMap<>();
        mapa.put("um", 1);
        mapa.put("dois", 2);
        mapa.put("três", 3);
        System.out.println("\nMap.forEach:");
        mapa.forEach((chave, valor) ->
            System.out.println("  " + chave + " = " + valor)
        );

        System.out.println();
    }

    // ─── 6. STREAM API (Java 8+) ─────────────────────────────
    static void exemploStreamAPI() {
        System.out.println("--- 6. Stream API (processamento funcional) ---");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter → filtra elementos
        System.out.print("Pares: ");
        numeros.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // map → transforma cada elemento
        System.out.print("Quadrados: ");
        numeros.stream()
            .map(n -> n * n)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // filter + map + collect → filtrar, transformar e coletar
        List<String> frutas = Arrays.asList("Banana", "Maçã", "Laranja", "Amora", "Abacaxi");
        List<String> frutasComA = frutas.stream()
            .filter(f -> f.startsWith("A"))
            .map(String::toUpperCase)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Frutas que começam com 'A' (maiúsculas): " + frutasComA);

        // reduce → reduz a um único valor
        int soma = numeros.stream()
            .reduce(0, Integer::sum);
        System.out.println("Soma total: " + soma);

        // count / min / max / average
        System.out.println("Contagem: " + numeros.stream().count());
        System.out.println("Mínimo: " + numeros.stream().mapToInt(i -> i).min().getAsInt());
        System.out.println("Máximo: " + numeros.stream().mapToInt(i -> i).max().getAsInt());
        System.out.printf("Média: %.1f%n", numeros.stream().mapToInt(i -> i).average().getAsDouble());

        // anyMatch / allMatch / noneMatch
        System.out.println("anyMatch(>5): " + numeros.stream().anyMatch(n -> n > 5)); // true
        System.out.println("allMatch(>0): " + numeros.stream().allMatch(n -> n > 0)); // true
        System.out.println("noneMatch(<0): " + numeros.stream().noneMatch(n -> n < 0)); // true

        System.out.println();
    }

    // ─── 7. ERRO COMUM: ConcurrentModificationException ──────
    static void exemploErroComum() {
        System.out.println("--- 7. Erro comum: ConcurrentModificationException ---");
        System.out.println("    Isso acontece ao modificar uma coleção durante iteração\n");

        List<Integer> lista = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        // ✘ ERRADO: remover dentro de um for-each lança exceção!
        System.out.println("✘ ERRADO (causa ConcurrentModificationException):");
        System.out.println("  for (Integer n : lista) {");
        System.out.println("      if (n % 2 == 0) lista.remove(n); // ← PERIGOSO!");
        System.out.println("  }");

        // ✔ CORRETO 1: usar Iterator
        System.out.println("\n✔ CORRETO 1: usar Iterator.remove()");
        List<Integer> copia1 = new ArrayList<>(lista);
        Iterator<Integer> it = copia1.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) it.remove();
        }
        System.out.println("  Resultado: " + copia1);

        // ✔ CORRETO 2: removeIf (Java 8+)
        System.out.println("\n✔ CORRETO 2: removeIf (Java 8+)");
        List<Integer> copia2 = new ArrayList<>(lista);
        copia2.removeIf(n -> n % 2 == 0);
        System.out.println("  Resultado: " + copia2);

        // ✔ CORRETO 3: Stream para criar nova lista filtrada
        System.out.println("\n✔ CORRETO 3: Stream + collect (cria nova lista)");
        List<Integer> copia3 = lista.stream()
            .filter(n -> n % 2 != 0)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("  Resultado: " + copia3);

        System.out.println();
    }
}
