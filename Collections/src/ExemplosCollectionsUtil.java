import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================
 *  COLLECTIONS - Classe Utilitária: Collections
 * ============================================================
 *
 * A classe java.util.Collections (com 's' no final!) é uma
 * classe de UTILITÁRIOS com métodos estáticos para:
 *   → Ordenar, embaralhar, inverter listas
 *   → Buscar elementos
 *   → Encontrar mín/máx
 *   → Criar coleções imutáveis e sincronizadas
 *   → Preencher e copiar listas
 *
 * NÃO confundir com java.util.Collection (sem 's'):
 *   Collection  → interface (List, Set, Queue estendem ela)
 *   Collections → classe de utilitários (com métodos estáticos)
 * ============================================================
 */
public class ExemplosCollectionsUtil {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    EXEMPLOS: Collections (util)      ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploOrdenacao();
        exemploBusca();
        exemploMinMax();
        exemploFill();
        exemploFrequency();
        exemploImutavel();
        exemploSincronia();
    }

    // ─── 1. ORDENAÇÃO ────────────────────────────────────────
    static void exemploOrdenacao() {
        System.out.println("--- 1. Ordenação ---");

        List<Integer> nums = new ArrayList<>(Arrays.asList(42, 7, 15, 3, 99, 22));
        System.out.println("Original: " + nums);

        // sort → ordena em ordem natural crescente (precisa implementar Comparable)
        Collections.sort(nums);
        System.out.println("sort (crescente): " + nums);

        // reverseOrder → ordem decrescente
        Collections.sort(nums, Collections.reverseOrder());
        System.out.println("reverseOrder: " + nums);

        // shuffle → embaralha aleatoriamente (útil para jogos, testes)
        Collections.shuffle(nums);
        System.out.println("shuffle: " + nums);

        // reverse → inverte a ordem atual (não ordena, apenas inverte)
        List<String> letras = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        Collections.reverse(letras);
        System.out.println("reverse: " + letras);

        // rotate → rotaciona os elementos
        List<Integer> rotacao = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.rotate(rotacao, 2); // move 2 posições para a direita
        System.out.println("rotate(2): " + rotacao); // [4, 5, 1, 2, 3]

        // swap → troca dois elementos de posição
        List<String> swap = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.swap(swap, 0, 3); // troca posição 0 com posição 3
        System.out.println("swap(0,3): " + swap); // [D, B, C, A]

        System.out.println();
    }

    // ─── 2. BUSCA BINÁRIA ────────────────────────────────────
    static void exemploBusca() {
        System.out.println("--- 2. Busca Binária ---");
        System.out.println("    ATENÇÃO: a lista DEVE estar ordenada!\n");

        List<Integer> nums = new ArrayList<>(Arrays.asList(3, 7, 15, 22, 42, 99));
        System.out.println("Lista ordenada: " + nums);

        // binarySearch → retorna o ÍNDICE do elemento (O(log n))
        int idx = Collections.binarySearch(nums, 22);
        System.out.println("binarySearch(22): índice = " + idx); // 3

        // Quando o elemento NÃO existe, retorna valor negativo:
        // retorno = -(ponto de inserção) - 1
        int idxNaoExiste = Collections.binarySearch(nums, 50);
        System.out.println("binarySearch(50) [não existe]: " + idxNaoExiste); // -(5)-1 = -6
        System.out.println("(valor negativo indica que 50 seria inserido na posição "
            + (Math.abs(idxNaoExiste) - 1) + ")");

        System.out.println();
    }

    // ─── 3. MÍN E MÁX ────────────────────────────────────────
    static void exemploMinMax() {
        System.out.println("--- 3. Mínimo e Máximo ---");

        List<Integer> nums = Arrays.asList(42, 7, 15, 3, 99, 22);
        System.out.println("Lista: " + nums);

        System.out.println("min: " + Collections.min(nums)); // 3
        System.out.println("max: " + Collections.max(nums)); // 99

        // Com Comparator
        List<String> nomes = Arrays.asList("Carlos", "Ana", "Bruno", "Bia");
        System.out.println("nomes: " + nomes);
        System.out.println("min (alfabético): " + Collections.min(nomes)); // Ana
        System.out.println("max (tamanho): " +
            Collections.max(nomes, java.util.Comparator.comparingInt(String::length))); // Carlos

        System.out.println();
    }

    // ─── 4. FILL E COPY ──────────────────────────────────────
    static void exemploFill() {
        System.out.println("--- 4. fill, copy e nCopies ---");

        // fill → preenche todos os elementos com o mesmo valor
        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.fill(lista, "X");
        System.out.println("fill('X'): " + lista); // [X, X, X, X]

        // nCopies → cria lista imutável com N cópias de um valor
        List<String> copias = Collections.nCopies(5, "Java");
        System.out.println("nCopies(5, 'Java'): " + copias); // [Java, Java, Java, Java, Java]

        // copy → copia elementos de uma lista para outra (destino deve ser >= tamanho)
        List<Integer> origem = Arrays.asList(10, 20, 30);
        List<Integer> destino = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        Collections.copy(destino, origem);
        System.out.println("copy: destino = " + destino); // [10, 20, 30, 0, 0]

        System.out.println();
    }

    // ─── 5. FREQUENCY E DISJOINT ─────────────────────────────
    static void exemploFrequency() {
        System.out.println("--- 5. frequency e disjoint ---");

        List<String> frutas = Arrays.asList("Banana", "Maçã", "Banana", "Laranja", "Banana");
        System.out.println("Lista: " + frutas);

        // frequency → conta quantas vezes um elemento aparece
        System.out.println("frequency('Banana'): " + Collections.frequency(frutas, "Banana")); // 3
        System.out.println("frequency('Uva'): " + Collections.frequency(frutas, "Uva"));       // 0

        // disjoint → verifica se duas coleções não têm elementos em comum
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);
        List<Integer> c = Arrays.asList(3, 7, 8);

        System.out.println("disjoint([1,2,3], [4,5,6]): " + Collections.disjoint(a, b)); // true (sem comum)
        System.out.println("disjoint([1,2,3], [3,7,8]): " + Collections.disjoint(a, c)); // false (3 em comum)

        System.out.println();
    }

    // ─── 6. COLEÇÕES IMUTÁVEIS ───────────────────────────────
    static void exemploImutavel() {
        System.out.println("--- 6. Coleções imutáveis ---");

        // unmodifiableList/Set/Map → envolve a coleção original
        // qualquer tentativa de modificação lança UnsupportedOperationException
        List<String> original = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> imutavel = Collections.unmodifiableList(original);

        System.out.println("Imutável: " + imutavel);

        // ATENÇÃO: a lista original ainda pode ser modificada!
        original.add("D");
        System.out.println("Após modificar original, imutável mostra: " + imutavel);
        // Para uma cópia verdadeiramente imutável, use List.of() (Java 9+)

        // Coleção de um único elemento (singleton)
        List<String> singleton = Collections.singletonList("Único");
        System.out.println("singletonList: " + singleton);

        // Coleção vazia imutável
        List<Object> vazia = Collections.emptyList();
        System.out.println("emptyList: " + vazia);

        System.out.println();
    }

    // ─── 7. COLEÇÕES SINCRONIZADAS (thread-safe) ─────────────
    static void exemploSincronia() {
        System.out.println("--- 7. Coleções sincronizadas (thread-safe) ---");
        System.out.println("    Útil para ambientes multi-threaded\n");

        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // synchronizedList → torna a lista thread-safe
        List<String> sincronizada = Collections.synchronizedList(lista);
        System.out.println("Lista sincronizada criada: " + sincronizada);

        // DICA: Para melhor performance em ambientes concorrentes,
        // prefira as classes do java.util.concurrent:
        //   CopyOnWriteArrayList → para List
        //   ConcurrentHashMap    → para Map
        //   ConcurrentLinkedQueue → para Queue
        System.out.println("DICA: Prefira java.util.concurrent para melhor performance");
        System.out.println("      em aplicações multi-threaded.");

        System.out.println();
    }
}
