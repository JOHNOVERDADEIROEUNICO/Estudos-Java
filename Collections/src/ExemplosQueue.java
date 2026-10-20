import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * ============================================================
 *  COLLECTIONS - QUEUE (Fila) e DEQUE
 * ============================================================
 *
 * O que é uma Queue?
 *   → Interface que representa uma FILA com política FIFO:
 *     First In, First Out (Primeiro a entrar, primeiro a sair).
 *   → Como uma fila de banco: quem chega primeiro é atendido primeiro.
 *
 *   Entrada → [3][2][1] → Saída
 *               ↑               ↑
 *              tail            head
 *
 * O que é uma Deque?
 *   → Double-Ended Queue: fila com inserção/remoção em AMBAS as pontas.
 *   → Pode funcionar como Stack (LIFO) ou como Queue (FIFO).
 *
 * Principais implementações:
 *
 *   LinkedList     → Queue + Deque, lista encadeada
 *   PriorityQueue  → Fila de prioridade (min-heap por padrão)
 *   ArrayDeque     → Deque com array circular (preferível a LinkedList como Deque)
 *
 * Métodos da interface Queue — dois grupos:
 *
 *   ┌──────────┬────────────────────┬──────────────────────────┐
 *   │ Operação │  Lança Exceção     │  Retorna null/false      │
 *   ├──────────┼────────────────────┼──────────────────────────┤
 *   │ Inserir  │  add(e)            │  offer(e)                │
 *   │ Remover  │  remove()          │  poll()                  │
 *   │ Examinar │  element()         │  peek()                  │
 *   └──────────┴────────────────────┴──────────────────────────┘
 *
 *   → Prefira os métodos da direita (offer, poll, peek) pois não
 *     lançam exceções quando a fila está vazia/cheia.
 * ============================================================
 */
public class ExemplosQueue {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       EXEMPLOS: Queue e Deque        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploQueueFIFO();
        exemploPriorityQueue();
        exemploArrayDeque();
        exemploDequeComoPilha();
        exemploSimulacaoFila();
    }

    // ─── 1. QUEUE BÁSICA (FIFO) ───────────────────────────────
    static void exemploQueueFIFO() {
        System.out.println("--- 1. Queue (FIFO) com LinkedList ---");

        Queue<String> fila = new LinkedList<>();

        // offer → adiciona no FINAL da fila
        fila.offer("Cliente A");
        fila.offer("Cliente B");
        fila.offer("Cliente C");
        System.out.println("Fila: " + fila);

        // peek → vê quem está na FRENTE sem remover
        System.out.println("peek (próximo): " + fila.peek()); // Cliente A

        // poll → REMOVE e retorna o elemento da FRENTE
        System.out.println("poll: " + fila.poll()); // Cliente A
        System.out.println("poll: " + fila.poll()); // Cliente B
        System.out.println("Fila restante: " + fila);

        // poll em fila vazia retorna null (não lança exceção)
        fila.poll(); // remove Cliente C
        System.out.println("poll em fila vazia: " + fila.poll()); // null

        System.out.println();
    }

    // ─── 2. PRIORITYQUEUE ────────────────────────────────────
    static void exemploPriorityQueue() {
        System.out.println("--- 2. PriorityQueue (Fila de Prioridade) ---");
        System.out.println("    → Ordena automaticamente por PRIORIDADE (min-heap por padrão)\n");

        // Por padrão, menor valor = maior prioridade (min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(50);
        pq.offer(10);
        pq.offer(80);
        pq.offer(30);

        System.out.println("PriorityQueue após adds: " + pq);
        System.out.println("(A representação interna pode parecer desordenada)");
        System.out.println("peek (menor): " + pq.peek()); // sempre o menor!

        System.out.print("Removendo em ordem: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " "); // sai sempre do menor para o maior
        }
        System.out.println();

        // Max-heap: maior valor = maior prioridade (usando Collections.reverseOrder)
        System.out.println("\nMax-heap (maior prioridade = maior valor):");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(java.util.Collections.reverseOrder());
        maxHeap.offer(50); maxHeap.offer(10); maxHeap.offer(80); maxHeap.offer(30);
        System.out.print("Removendo em ordem: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // sai sempre do maior para o menor
        }
        System.out.println();

        // PriorityQueue com objetos e Comparator
        System.out.println("\nPriorityQueue de Tarefas por prioridade:");
        PriorityQueue<Tarefa> tarefas = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.prioridade, b.prioridade)
        );
        tarefas.offer(new Tarefa("Enviar email", 3));
        tarefas.offer(new Tarefa("Corrigir bug crítico", 1));
        tarefas.offer(new Tarefa("Reunião", 2));

        while (!tarefas.isEmpty()) {
            Tarefa t = tarefas.poll();
            System.out.println("  Executando: " + t);
        }

        System.out.println();
    }

    // ─── 3. ARRAYDEQUE ───────────────────────────────────────
    static void exemploArrayDeque() {
        System.out.println("--- 3. ArrayDeque (Deque com array circular) ---");
        System.out.println("    → Mais eficiente que LinkedList para Deque/Stack\n");

        Deque<String> deque = new ArrayDeque<>();

        // Inserção em ambas as pontas
        deque.addFirst("Centro");
        deque.addFirst("Início 1");  // [Início 1, Centro]
        deque.addLast("Final 1");    // [Início 1, Centro, Final 1]
        deque.addFirst("Início 2");  // [Início 2, Início 1, Centro, Final 1]
        deque.addLast("Final 2");    // [Início 2, Início 1, Centro, Final 1, Final 2]

        System.out.println("Deque: " + deque);
        System.out.println("peekFirst: " + deque.peekFirst()); // Início 2
        System.out.println("peekLast:  " + deque.peekLast());  // Final 2

        // Remoção em ambas as pontas
        System.out.println("pollFirst: " + deque.pollFirst()); // Início 2
        System.out.println("pollLast:  " + deque.pollLast());  // Final 2
        System.out.println("Deque: " + deque);

        System.out.println();
    }

    // ─── 4. ARRAYDEQUE COMO PILHA (LIFO) ─────────────────────
    static void exemploDequeComoPilha() {
        System.out.println("--- 4. ArrayDeque como Stack (LIFO) ---");
        System.out.println("    → Preferível a java.util.Stack (que é sincronizado/legado)\n");

        Deque<String> pilha = new ArrayDeque<>();

        // push = addFirst (adiciona no topo)
        pilha.push("Camada 1 (base)");
        pilha.push("Camada 2");
        pilha.push("Camada 3 (topo)");

        System.out.println("Pilha: " + pilha);
        System.out.println("peek (topo): " + pilha.peek()); // Camada 3

        System.out.print("Desempilhando: ");
        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop() + " | "); // pop = removeFirst
        }
        System.out.println();

        // Caso de uso: verificar parênteses balanceados
        System.out.println("\nVerificando balanceamento de parênteses:");
        System.out.println("'(a + (b * c))' → " + (estaBalanceado("(a + (b * c))") ? "✔ balanceado" : "✘ não balanceado"));
        System.out.println("'(a + [b * c)' → " + (estaBalanceado("(a + [b * c)") ? "✔ balanceado" : "✘ não balanceado"));
        System.out.println("'{[()]}' → " + (estaBalanceado("{[()]}") ? "✔ balanceado" : "✘ não balanceado"));

        System.out.println();
    }

    // ─── 5. SIMULAÇÃO DE FILA ────────────────────────────────
    static void exemploSimulacaoFila() {
        System.out.println("--- 5. Simulação: Fila de Atendimento ---");

        Queue<String> fila = new LinkedList<>();

        // Chegada de clientes
        String[] chegadas = {"Ana", "Bruno", "Carlos", "Diana", "Eduardo"};
        for (String cliente : chegadas) {
            fila.offer(cliente);
            System.out.println(cliente + " entrou na fila. Fila: " + fila);
        }

        System.out.println();

        // Atendimento (FIFO)
        int guiche = 1;
        while (!fila.isEmpty()) {
            String atendido = fila.poll();
            System.out.println("Guichê " + guiche + " atende: " + atendido
                + " | Ainda aguardando: " + fila.size());
            guiche++;
        }
        System.out.println();
    }

    // Método auxiliar para verificar parênteses balanceados
    static boolean estaBalanceado(String expressao) {
        Deque<Character> pilha = new ArrayDeque<>();
        for (char c : expressao.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pilha.isEmpty()) return false;
                char topo = pilha.pop();
                if ((c == ')' && topo != '(') ||
                    (c == ']' && topo != '[') ||
                    (c == '}' && topo != '{')) {
                    return false;
                }
            }
        }
        return pilha.isEmpty();
    }

    // Classe auxiliar Tarefa
    static class Tarefa {
        String nome;
        int prioridade;

        Tarefa(String nome, int prioridade) {
            this.nome = nome;
            this.prioridade = prioridade;
        }

        @Override
        public String toString() {
            return "'" + nome + "' (prioridade " + prioridade + ")";
        }
    }
}
