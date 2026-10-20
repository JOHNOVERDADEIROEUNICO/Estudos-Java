import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================
 *  COLLECTIONS - LIST: ArrayList
 * ============================================================
 *
 * O que é uma List?
 *   → Interface que representa uma coleção ORDENADA (mantém a
 *     ordem de inserção) que PERMITE DUPLICATAS.
 *
 * O que é o ArrayList?
 *   → A implementação de List mais usada.
 *   → Internamente usa um ARRAY dinâmico que cresce conforme
 *     novos elementos são adicionados.
 *
 * Quando usar ArrayList?
 *   ✔ Acesso frequente por índice (get/set) → O(1)
 *   ✔ Iteração sequencial
 *   ✘ Inserções/remoções frequentes no meio → O(n) (desloca elementos)
 *
 * Complexidade das operações:
 *   add(elemento)      → O(1) amortizado
 *   add(índice, elem)  → O(n) — desloca elementos
 *   get(índice)        → O(1)
 *   remove(índice)     → O(n) — desloca elementos
 *   contains(objeto)   → O(n) — busca linear
 * ============================================================
 */
public class ExemplosListArrayList {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       EXEMPLOS: ArrayList            ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploBasico();
        exemploAcesso();
        exemploRemocao();
        exemploSubList();
        exemploOrdenacao();
        exemploListOf();
    }

    // ─── 1. CRIAÇÃO E ADIÇÃO ──────────────────────────────────
    static void exemploBasico() {
        System.out.println("--- 1. Criação e adição de elementos ---");

        // Criação simples
        List<String> frutas = new ArrayList<>();

        // add(elemento) → adiciona ao final
        frutas.add("Banana");
        frutas.add("Maçã");
        frutas.add("Laranja");
        frutas.add("Banana"); // ← duplicata é permitida!

        System.out.println("Lista: " + frutas);
        System.out.println("Tamanho: " + frutas.size());

        // add(índice, elemento) → insere em posição específica
        frutas.add(1, "Uva"); // insere "Uva" na posição 1
        System.out.println("Após add(1, 'Uva'): " + frutas);

        // addAll → adiciona todos os elementos de outra coleção
        List<String> mais = new ArrayList<>();
        mais.add("Manga");
        mais.add("Abacaxi");
        frutas.addAll(mais);
        System.out.println("Após addAll: " + frutas);

        System.out.println();
    }

    // ─── 2. ACESSO A ELEMENTOS ───────────────────────────────
    static void exemploAcesso() {
        System.out.println("--- 2. Acesso a elementos ---");

        List<String> cores = new ArrayList<>();
        cores.add("Vermelho");
        cores.add("Verde");
        cores.add("Azul");
        cores.add("Amarelo");

        // get(índice) → acessa elemento pela posição (base 0)
        System.out.println("get(0): " + cores.get(0)); // Vermelho
        System.out.println("get(2): " + cores.get(2)); // Azul

        // set(índice, valor) → substitui o valor em uma posição
        cores.set(1, "Rosa");
        System.out.println("Após set(1, 'Rosa'): " + cores);

        // indexOf / lastIndexOf
        List<Integer> nums = new ArrayList<>();
        nums.add(10); nums.add(20); nums.add(10); nums.add(30);
        System.out.println("indexOf(10): " + nums.indexOf(10));     // 0
        System.out.println("lastIndexOf(10): " + nums.lastIndexOf(10)); // 2

        // contains → verifica se elemento existe
        System.out.println("contains('Azul'): " + cores.contains("Azul"));

        // isEmpty → verifica se está vazia
        System.out.println("isEmpty: " + cores.isEmpty());

        System.out.println();
    }

    // ─── 3. REMOÇÃO ──────────────────────────────────────────
    static void exemploRemocao() {
        System.out.println("--- 3. Remoção de elementos ---");

        List<String> animais = new ArrayList<>();
        animais.add("Gato");
        animais.add("Cachorro");
        animais.add("Peixe");
        animais.add("Pássaro");
        animais.add("Hamster");

        System.out.println("Original: " + animais);

        // remove(índice) → remove pelo índice
        animais.remove(0); // remove "Gato"
        System.out.println("Após remove(0): " + animais);

        // remove(objeto) → remove pela referência do objeto
        animais.remove("Peixe");
        System.out.println("Após remove('Peixe'): " + animais);

        // ATENÇÃO com Integer! remove(int) vs remove(Object)
        List<Integer> inteiros = new ArrayList<>();
        inteiros.add(10); inteiros.add(20); inteiros.add(30);
        inteiros.remove(0);               // remove pelo ÍNDICE 0 → remove o 10
        System.out.println("remove(0) em List<Integer>: " + inteiros); // [20, 30]
        inteiros.remove(Integer.valueOf(20)); // remove pelo VALOR 20
        System.out.println("remove(Integer.valueOf(20)): " + inteiros); // [30]

        // removeIf → remove com predicado (lambda)
        List<String> nomes = new ArrayList<>();
        nomes.add("Ana"); nomes.add("Bruno"); nomes.add("Carlos"); nomes.add("Bia");
        nomes.removeIf(nome -> nome.startsWith("B")); // remove quem começa com B
        System.out.println("removeIf(startsWith 'B'): " + nomes);

        // clear → remove todos
        animais.clear();
        System.out.println("Após clear: " + animais + " | isEmpty: " + animais.isEmpty());

        System.out.println();
    }

    // ─── 4. SUBLISTAS ────────────────────────────────────────
    static void exemploSubList() {
        System.out.println("--- 4. subList ---");

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 10; i++) nums.add(i * 10);
        System.out.println("Lista completa: " + nums);

        // subList(fromIndex, toIndex) → INCLUSIVO no início, EXCLUSIVO no fim
        List<Integer> sub = nums.subList(2, 6); // índices 2,3,4,5
        System.out.println("subList(2, 6): " + sub);

        // ATENÇÃO: subList é uma VIEW da lista original, não uma cópia!
        // Modificar a sub modifica a original
        sub.set(0, 999);
        System.out.println("Após sub.set(0,999), lista original: " + nums);

        System.out.println();
    }

    // ─── 5. ORDENAÇÃO ────────────────────────────────────────
    static void exemploOrdenacao() {
        System.out.println("--- 5. Ordenação ---");

        List<Integer> nums = new ArrayList<>();
        nums.add(42); nums.add(7); nums.add(15); nums.add(3); nums.add(99);
        System.out.println("Antes: " + nums);

        // Collections.sort → ordena em ordem natural (crescente)
        Collections.sort(nums);
        System.out.println("Collections.sort (crescente): " + nums);

        // Collections.sort com Comparator
        Collections.sort(nums, Collections.reverseOrder());
        System.out.println("Collections.sort (decrescente): " + nums);

        // List.sort com lambda (Java 8+)
        List<String> nomes = new ArrayList<>();
        nomes.add("Carlos"); nomes.add("Ana"); nomes.add("Bruno"); nomes.add("Bia");
        nomes.sort((a, b) -> a.compareTo(b)); // ordem alfabética
        System.out.println("sort alfabético: " + nomes);

        nomes.sort((a, b) -> Integer.compare(a.length(), b.length())); // por tamanho
        System.out.println("sort por tamanho: " + nomes);

        System.out.println();
    }

    // ─── 6. LIST.OF e ARRAYS.ASLIST (imutáveis/fixos) ────────
    static void exemploListOf() {
        System.out.println("--- 6. List.of() e Arrays.asList() ---");

        // List.of() → lista IMUTÁVEL (Java 9+)
        List<String> imutavel = List.of("A", "B", "C");
        System.out.println("List.of: " + imutavel);
        // imutavel.add("D"); // ← lança UnsupportedOperationException!

        // Arrays.asList() → tamanho FIXO (permite set, mas não add/remove)
        List<String> fixo = java.util.Arrays.asList("X", "Y", "Z");
        System.out.println("Arrays.asList: " + fixo);
        fixo.set(0, "W"); // ← isso funciona
        System.out.println("Após set: " + fixo);
        // fixo.add("A"); // ← lança UnsupportedOperationException!

        // Para ter uma lista mutável a partir de List.of ou asList:
        List<String> mutavel = new ArrayList<>(List.of("A", "B", "C"));
        mutavel.add("D");
        System.out.println("ArrayList a partir de List.of: " + mutavel);

        System.out.println();
    }
}
