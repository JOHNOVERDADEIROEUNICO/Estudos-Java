package exemplos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * CONCEITO 3: Interface Callable<V>
 * 
 * A interface {@link java.util.concurrent.Callable} é a evolução do Runnable
 * introduzida no Java 5 (java.util.concurrent).
 * 
 * DIFERENÇAS CRUCIAIS EM RELAÇÃO AO RUNNABLE:
 * 1. Retorno de Valor: O método {@code V call()} retorna um tipo genérico <V>.
 * 2. Lança Exceções: O método call() declara {@code throws Exception}, permitindo
 *    lançar checked exceptions diretamente.
 * 3. Execução via ExecutorService: Não pode ser passada diretamente para {@code new Thread()}.
 *    É submetida a um pool de threads (ExecutorService) e retorna um {@link java.util.concurrent.Future}.
 * 4. Future: Objeto que representa o resultado futuro da computação assíncrona.
 *    - future.get(): Bloqueia a thread atual até o cálculo terminar e devolve o valor.
 *    - future.isDone(): Checa se a tarefa já terminou.
 */
public class ExemploCallable {

    // Simulação de cálculo pesado
    static class CalculoFatorial implements Callable<Long> {
        private final int numero;

        public CalculoFatorial(int numero) {
            this.numero = numero;
        }

        @Override
        public Long call() throws Exception {
            if (numero < 0) {
                throw new IllegalArgumentException("Número não pode ser negativo: " + numero);
            }
            System.out.println("[Callable Classe] Calculando fatorial de " + numero + " na thread: " + Thread.currentThread().getName());
            
            long resultado = 1;
            for (int i = 1; i <= numero; i++) {
                resultado *= i;
                Thread.sleep(150); // Simula custo de processamento
            }
            return resultado;
        }
    }

    public static void executar() {
        System.out.println("\n==============================================");
        System.out.println("   3. CONCEITO: Interface Callable<V> + Future");
        System.out.println("==============================================");

        // 1. Cria um pool de threads com capacidade para 2 threads concorrentes
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Forma A: Instância de classe Callable
            Callable<Long> tarefaFatorial = new CalculoFatorial(5);
            Future<Long> futureFatorial = executor.submit(tarefaFatorial);

            // Forma B: Callable usando Lambda (com retorno de String)
            Callable<String> tarefaConsultaAPI = () -> {
                System.out.println("[Callable Lambda] Consultando serviço remoto...");
                Thread.sleep(600); // Simula latência de rede
                return "{\"status\": 200, \"mensagem\": \"Dados recebidos com sucesso\"}";
            };
            Future<String> futureAPI = executor.submit(tarefaConsultaAPI);

            System.out.println("-> Tarefas submetidas ao Executor! A thread principal continua livre...");

            // Enquanto espera, podemos fazer outras coisas ou verificar status
            while (!futureFatorial.isDone() || !futureAPI.isDone()) {
                System.out.println("-> Processando em background... aguardando conclusão...");
                Thread.sleep(300);
            }

            // future.get() retorna o resultado do método call()
            Long resultadoFatorial = futureFatorial.get();
            String respostaAPI = futureAPI.get();

            System.out.println("\n--- RESULTADOS OBTIDOS ---");
            System.out.println("Resultado do Fatorial(5): " + resultadoFatorial);
            System.out.println("Resposta da API remota: " + respostaAPI);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        } finally {
            // SEMPRE encerre o ExecutorService para liberar os recursos do sistema
            executor.shutdown();
            try {
                if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            System.out.println("-> ExecutorService finalizado com segurança.");
        }
    }

    public static void main(String[] args) {
        executar();
    }
}
