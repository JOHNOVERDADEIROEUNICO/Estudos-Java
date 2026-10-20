import java.util.LinkedList;
import java.util.List;

/**
 * ============================================================
 *  COLLECTIONS - LIST: LinkedList
 * ============================================================
 *
 * O que é LinkedList?
 *   → Implementa tanto List quanto Deque (fila dupla).
 *   → Internamente é uma LISTA DUPLAMENTE ENCADEADA:
 *     cada elemento (nó) guarda referência para o anterior
 *     e para o próximo.
 *
 *   [null ←] [Node1] ↔ [Node2] ↔ [Node3] [→ null]
 *              head                          tail
 *
 * ArrayList vs LinkedList — quando usar cada um?
 *
 *   Operação              | ArrayList | LinkedList
 *   ─────────────────────────────────────────────
 *   get(índice)           |   O(1) ✔  |   O(n)
 *   add(fim)              |   O(1)*   |   O(1)
 *   add(início/meio)      |   O(n)    |   O(1) ✔
 *   remove(índice)        |   O(n)    |   O(n)
 *   remove(início/fim)    |   O(n)    |   O(1) ✔
 *   Uso de memória        |  Menor ✔  |  Maior (ponteiros extras)
 *
 *   (*) amortizado — pode ser O(n) quando precisa redimensionar
 *
 * → Use LinkedList quando tiver MUITAS inserções/remoções
 *   no início ou no final da lista.
 * → Use ArrayList na maioria dos outros casos.
 * ============================================================
 */
public class ExemplosListLinkedList {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       EXEMPLOS: LinkedList           ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploComoLista();
        exemploComoFila();
        exemploComoPilha();
        exemploDeque();
    }

    // ─── 1. USANDO COMO LIST (igual ao ArrayList) ─────────────
    static void exemploComoLista() {
        System.out.println("--- 1. LinkedList como List ---");

        List<String> lista = new LinkedList<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");

        System.out.println("Lista: " + lista);
        System.out.println("get(1): " + lista.get(1));

        lista.add(0, "Z"); // inserção no início → O(1) no LinkedList!
        System.out.println("Após add(0,'Z'): " + lista);

        lista.remove(0); // remoção do início → O(1) no LinkedList!
        System.out.println("Após remove(0): " + lista);

        System.out.println();
    }

    // ─── 2. USANDO COMO FILA (Queue) ─────────────────────────
    static void exemploComoFila() {
        System.out.println("--- 2. LinkedList como Queue (FIFO) ---");
        System.out.println("    FIFO = First In, First Out\n");

        // LinkedList implementa Queue, então pode ser usada como fila
        LinkedList<String> fila = new LinkedList<>();

        // offer → adiciona no FINAL da fila
        fila.offer("Primeiro");
        fila.offer("Segundo");
        fila.offer("Terceiro");
        System.out.println("Fila: " + fila);

        // peek → vê quem está na FRENTE sem remover
        System.out.println("peek (frente): " + fila.peek());

        // poll → remove e retorna o elemento da FRENTE
        System.out.println("poll: " + fila.poll());
        System.out.println("poll: " + fila.poll());
        System.out.println("Fila restante: " + fila);

        // Diferença entre métodos que lançam exceção vs retornam null:
        //  add()    vs offer()  → add lança exceção se capacidade excedida
        //  remove() vs poll()   → poll retorna null se vazia
        //  element()vs peek()   → element lança exceção se vazia

        System.out.println();
    }

    // ─── 3. USANDO COMO PILHA (Stack) ────────────────────────
    static void exemploComoPilha() {
        System.out.println("--- 3. LinkedList como Stack (LIFO) ---");
        System.out.println("    LIFO = Last In, First Out\n");

        LinkedList<String> pilha = new LinkedList<>();

        // push → adiciona no INÍCIO (topo da pilha)
        pilha.push("Base");
        pilha.push("Meio");
        pilha.push("Topo");
        System.out.println("Pilha: " + pilha); // [Topo, Meio, Base]

        // peek → vê o topo sem remover
        System.out.println("peek (topo): " + pilha.peek());

        // pop → remove e retorna o TOPO
        System.out.println("pop: " + pilha.pop());
        System.out.println("pop: " + pilha.pop());
        System.out.println("Pilha restante: " + pilha);

        System.out.println();
    }

    // ─── 4. USANDO COMO DEQUE (fila dupla) ───────────────────
    static void exemploDeque() {
        System.out.println("--- 4. LinkedList como Deque (fila dupla) ---");
        System.out.println("    Deque = Double-Ended Queue (inserção/remoção em ambas as pontas)\n");

        LinkedList<String> deque = new LinkedList<>();

        // Inserção no INÍCIO e no FIM
        deque.addFirst("B");
        deque.addFirst("A"); // [A, B]
        deque.addLast("C");
        deque.addLast("D");  // [A, B, C, D]
        System.out.println("Deque: " + deque);

        // Acesso ao início e fim
        System.out.println("peekFirst: " + deque.peekFirst()); // A
        System.out.println("peekLast:  " + deque.peekLast());  // D

        // Remoção do início e fim
        System.out.println("pollFirst: " + deque.pollFirst()); // A
        System.out.println("pollLast:  " + deque.pollLast());  // D
        System.out.println("Deque restante: " + deque);

        System.out.println();
    }
}
