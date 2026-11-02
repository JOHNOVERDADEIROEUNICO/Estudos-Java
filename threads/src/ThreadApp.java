import exemplos.ExemploCallable;
import exemplos.ExemploRunnable;
import exemplos.ExemploThread;

/**
 * Ponto de entrada principal para os estudos de Threads em Java.
 * 
 * Executa sequencialmente os exemplos didáticos dos 3 principais conceitos:
 * 1. Thread (subclasse)
 * 2. Runnable (interface funcional de tarefas sem retorno)
 * 3. Callable (interface funcional de tarefas com retorno e exceções)
 */
public class ThreadApp {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         GUIA PRÁTICO: THREADS EM JAVA            ");
        System.out.println("==================================================");

        // 1. Demonstração de Thread
        ExemploThread.executar();

        // 2. Demonstração de Runnable
        ExemploRunnable.executar();

        // 3. Demonstração de Callable
        ExemploCallable.executar();

        System.out.println("\n==================================================");
        System.out.println("          TODOS OS EXEMPLOS FINALIZADOS!          ");
        System.out.println("==================================================");
    }
}
