/**
 * ============================================================
 *  JAVA COLLECTIONS FRAMEWORK - Arquivo Principal de Estudos
 * ============================================================
 *
 * O Java Collections Framework (JCF) é um conjunto de interfaces
 * e classes que representam e manipulam coleções de dados.
 *
 * HIERARQUIA DAS PRINCIPAIS INTERFACES:
 *
 *   Iterable
 *   └── Collection
 *       ├── List       → elementos ordenados, permite duplicatas
 *       │   ├── ArrayList
 *       │   ├── LinkedList
 *       │   └── Vector / Stack (legado)
 *       ├── Set        → sem duplicatas
 *       │   ├── HashSet
 *       │   ├── LinkedHashSet
 *       │   └── TreeSet
 *       └── Queue      → fila (FIFO)
 *           ├── LinkedList
 *           ├── PriorityQueue
 *           └── Deque (fila dupla)
 *               ├── ArrayDeque
 *               └── LinkedList
 *
 *   Map (NÃO estende Collection)
 *   ├── HashMap
 *   ├── LinkedHashMap
 *   ├── TreeMap
 *   └── Hashtable (legado)
 *
 * COMO NAVEGAR PELOS EXEMPLOS:
 *   Execute cada arquivo separadamente ou descomente
 *   as chamadas abaixo para rodar todos de uma vez.
 *
 * ARQUIVOS DISPONÍVEIS:
 *   - ExemplosListArrayList.java   → ArrayList detalhado
 *   - ExemplosListLinkedList.java  → LinkedList detalhado
 *   - ExemplosSet.java             → HashSet, LinkedHashSet, TreeSet
 *   - ExemplosQueue.java           → Queue, PriorityQueue, Deque
 *   - ExemplosMap.java             → HashMap, LinkedHashMap, TreeMap
 *   - ExemplosCollectionsUtil.java → classe utilitária Collections
 *   - ExemplosIteradores.java      → Iterator, ListIterator, forEach
 * ============================================================
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== JAVA COLLECTIONS FRAMEWORK ===\n");
        System.out.println("Este projeto contém exemplos educativos sobre:");
        System.out.println("  • List    → ArrayList, LinkedList");
        System.out.println("  • Set     → HashSet, LinkedHashSet, TreeSet");
        System.out.println("  • Queue   → Queue, PriorityQueue, Deque");
        System.out.println("  • Map     → HashMap, LinkedHashMap, TreeMap");
        System.out.println("  • Collections (utilitários)");
        System.out.println("  • Iteradores\n");
        System.out.println("Abra cada arquivo .java na pasta src/ para ver os exemplos!");
        System.out.println("\nDica: Cada classe possui um método main() próprio.");
        System.out.println("      Clique com o botão direito no arquivo e escolha 'Run Java'.");

        System.out.println("\n--- Executando uma demonstração rápida ---\n");

        // Descomente para executar todos os exemplos em sequência:
        // ExemplosListArrayList.main(args);
        // ExemplosListLinkedList.main(args);
        // ExemplosSet.main(args);
        // ExemplosQueue.main(args);
        // ExemplosMap.main(args);
        // ExemplosCollectionsUtil.main(args);
        // ExemplosIteradores.main(args);

        demonstracaoRapida();
    }

    /**
     * Uma demonstração rápida de cada tipo de coleção.
     */
    static void demonstracaoRapida() {
        // --- List ---
        java.util.List<String> lista = new java.util.ArrayList<>();
        lista.add("Banana");
        lista.add("Maçã");
        lista.add("Laranja");
        lista.add("Banana"); // duplicata permitida!
        System.out.println("List (ArrayList): " + lista);

        // --- Set ---
        java.util.Set<String> conjunto = new java.util.HashSet<>(lista);
        System.out.println("Set  (HashSet)  : " + conjunto + " ← sem duplicatas");

        // --- Queue ---
        java.util.Queue<String> fila = new java.util.LinkedList<>();
        fila.offer("Primeiro");
        fila.offer("Segundo");
        fila.offer("Terceiro");
        System.out.println("Queue           : " + fila + " ← FIFO, peek=" + fila.peek());

        // --- Map ---
        java.util.Map<String, Integer> mapa = new java.util.HashMap<>();
        mapa.put("um", 1);
        mapa.put("dois", 2);
        mapa.put("três", 3);
        System.out.println("Map  (HashMap)  : " + mapa);

        System.out.println("\nExplore os arquivos de exemplo para detalhes de cada coleção!");
    }
}
