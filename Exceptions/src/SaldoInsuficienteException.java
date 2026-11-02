// Checked Exception (herda diretamente de Exception)
// O compilador OBRIGA quem chamar o método que lança esta exceção a tratá-la ou repassá-la (throws).
public class SaldoInsuficienteException extends Exception {
    
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
