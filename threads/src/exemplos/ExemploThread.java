package exemplos;

/**
 * CONCEITO 1: Classe Thread
 * 
 * Uma Thread em Java representa uma linha de execução independente.
 * A forma mais básica de criar uma thread é estender a classe {@link java.lang.Thread}
 * e sobrescrever o método {@code run()}.
 * 
 * PONTOS IMPORTANTES:
 * 1. start() vs run():
 *    - thread.start(): Cria uma nova thread no sistema operacional e chama run() concorrentemente.
 *    - thread.run(): Apenas executa o método na thread atual (sem concorrência).
 * 2. Limitação de Herança: Como o Java não suporta herança múltipla, ao estender Thread,
 *    sua classe não pode herdar de mais nenhuma outra classe.
 * 3. Sem retorno e sem checked exceptions: O método run() retorna void e não pode lançar checked exceptions.
 */
public class ExemploThread {

    // Subclasse interna estendendo Thread
    static class ContadorThread extends Thread {
        private final String nome;

        public ContadorThread(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            System.out.println("[" + nome + "] Iniciou na thread: " + Thread.currentThread().getName());
            for (int i = 1; i <= 3; i++) {
                System.out.println("[" + nome + "] Passo " + i);
                try {
                    Thread.sleep(400); // Pausa de 400ms para simular trabalho
                } catch (InterruptedException e) {
                    System.err.println("[" + nome + "] Foi interrompida.");
                }
            }
            System.out.println("[" + nome + "] Finalizou.");
        }
    }

    public static void executar() {
        System.out.println("\n==============================================");
        System.out.println("   1. CONCEITO: Estendendo a classe Thread");
        System.out.println("==============================================");

        ContadorThread t1 = new ContadorThread("Thread-A");
        ContadorThread t2 = new ContadorThread("Thread-B");

        // Inicia ambas em paralelo no sistema operacional
        t1.start();
        t2.start();

        // Aguarda ambas terminarem para prosseguir
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("-> Ambas as threads terminaram a execução.");
    }

    public static void main(String[] args) {
        executar();
    }
}
