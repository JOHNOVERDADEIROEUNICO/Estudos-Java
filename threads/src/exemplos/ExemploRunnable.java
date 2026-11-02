package exemplos;

/**
 * CONCEITO 2: Interface Runnable
 * 
 * A interface {@link java.lang.Runnable} representa uma TAREFA a ser executada,
 * separando a lógica do trabalho do mecanismo de execução (a Thread).
 * 
 * PONTOS IMPORTANTES:
 * 1. Desacoplamento: Define "o que fazer", enquanto a Thread define "onde/como rodar".
 * 2. Flexibilidade de Herança: Sua classe pode herdar de qualquer outra classe de negócio
 *    e implementar Runnable normalmente.
 * 3. Interface Funcional: Possui apenas o método {@code void run()}, o que permite uso de
 *    Expressões Lambda e Method References.
 * 4. Sem retorno e sem checked exceptions: Assim como em Thread, run() retorna void.
 */
public class ExemploRunnable {

    // 1. Implementação clássica com classe que implementa Runnable
    static class ProcessadorArquivo implements Runnable {
        private final String arquivo;

        public ProcessadorArquivo(String arquivo) {
            this.arquivo = arquivo;
        }

        @Override
        public void run() {
            System.out.println("[Runnable Classe] Processando " + arquivo + " na thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Processamento cancelado: " + arquivo);
            }
            System.out.println("[Runnable Classe] Concluído: " + arquivo);
        }
    }

    public static void executar() {
        System.out.println("\n==============================================");
        System.out.println("   2. CONCEITO: Interface Runnable");
        System.out.println("==============================================");

        // Forma A: Passando uma instância de classe para a Thread
        Runnable tarefa1 = new ProcessadorArquivo("dados_clientes.csv");
        Thread thread1 = new Thread(tarefa1, "Worker-Arquivo");

        // Forma B: Usando Expressão Lambda (Moderno)
        Runnable tarefa2 = () -> {
            System.out.println("[Runnable Lambda] Enviando emails na thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                System.err.println("Envio de emails interrompido.");
            }
            System.out.println("[Runnable Lambda] Emails enviados com sucesso!");
        };
        Thread thread2 = new Thread(tarefa2, "Worker-Email");

        // Dispara as duas threads
        thread1.start();
        thread2.start();

        // Aguarda a finalização
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("-> Todas as tarefas Runnable foram finalizadas.");
    }

    public static void main(String[] args) {
        executar();
    }
}
