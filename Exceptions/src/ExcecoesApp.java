public class ExcecoesApp {

    public static void main(String[] args) {
        System.out.println("=== ESTUDO: THROWABLE E EXCEPTION EM JAVA ===\n");

        exemplo1_MetodosThrowable();
        exemplo2_CheckedException();
        exemplo3_UncheckedException();
        exemplo4_ErrorVsException();
    }

    /**
     * 1. THROWABLE:
     * Raiz de qualquer erro ou exceção no Java.
     * Ela fornece métodos essenciais como getMessage(), printStackTrace() e getCause().
     */
    private static void exemplo1_MetodosThrowable() {
        System.out.println("--- 1. Métodos da classe Throwable ---");
        try {
            // Lançamos uma Exception propositalmente
            throw new Exception("Falha simulada para demonstrar métodos de Throwable");
        } catch (Throwable t) { // Throwable captura tanto Exception quanto Error
            System.out.println("Classe da exceção: " + t.getClass().getName());
            System.out.println("Mensagem (getMessage): " + t.getMessage());
        }
        System.out.println();
    }

    /**
     * 2. CHECKED EXCEPTION:
     * Herda diretamente de Exception (mas NÃO de RuntimeException).
     * O compilador exige tratamento obrigatório (try/catch ou throws na assinatura).
     */
    private static void exemplo2_CheckedException() {
        System.out.println("--- 2. Checked Exception (Exige tratamento) ---");
        double saldo = 100.0;
        double valorSaque = 150.0;

        try {
            sacar(saldo, valorSaque);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
        System.out.println();
    }

    // Como SaldoInsuficienteException é Checked, precisamos declarar 'throws'
    private static void sacar(double saldo, double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente! Saldo atual: R$ " + saldo + ", Saque: R$ " + valor);
        }
        System.out.println("Saque realizado com sucesso!");
    }

    /**
     * 3. UNCHECKED EXCEPTION (RuntimeException):
     * Herda de RuntimeException (que herda de Exception).
     * O compilador NÃO obriga a declarar throws ou usar try/catch.
     * Costuma indicar erro de lógica ou validação de entrada de dados.
     */
    private static void exemplo3_UncheckedException() {
        System.out.println("--- 3. Unchecked Exception (RuntimeException) ---");
        try {
            int resultado = 10 / 0; // ArithmeticException herda de RuntimeException
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Erro de execução tratado: Divisão por zero não permitida! (" + e.getMessage() + ")");
        }
        System.out.println();
    }

    /**
     * 4. ERROR vs EXCEPTION (ambos filhos de Throwable):
     * Error representa falhas severas da JVM/ambiente (ex: falta de memória, estouro de pilha).
     * Não devemos tentar tratar Error em aplicações reais, pois o estado da JVM fica instável.
     */
    private static void exemplo4_ErrorVsException() {
        System.out.println("--- 4. Error vs Exception ---");
        System.out.println("Hierarquia:");
        System.out.println("  Object");
        System.out.println("    └── Throwable");
        System.out.println("          ├── Error (Falhas graves da JVM: OutOfMemoryError, StackOverflowError)");
        System.out.println("          └── Exception (Problemas tratáveis da aplicação)");
        System.out.println("                ├── Checked (ex: IOException, SaldoInsuficienteException)");
        System.out.println("                └── RuntimeException (Unchecked, ex: NullPointerException, ArithmeticException)");
        System.out.println();
    }
}
