package exemplos.nativas;

/**
 * Exemplo prático de anotações nativas do Java:
 * - @Override: Garante que estamos sobrescrevendo um método da superclasse/interface.
 * - @Deprecated: Informa que um método/classe está obsoleto e não deve mais ser usado.
 * - @SuppressWarnings: Silencia avisos emitidos pelo compilador intencionalmente.
 */
public class ExemploNativas {

    public interface Notificador {
        void enviar(String destinatario, String mensagem);
    }

    public static class ServicoEmail implements Notificador {

        // @Override garante que o compilador verifique a assinatura.
        // Se errássemos o nome para "enviarr", o compilador nos avisaria com erro.
        @Override
        public void enviar(String destinatario, String mensagem) {
            System.out.println("   [Email] Enviando para " + destinatario + ": " + mensagem);
        }

        // @Deprecated marca o método como descontinuado/obsoleto.
        @Deprecated(since = "2.0", forRemoval = false)
        public void enviarViaSmsLegado(String numero, String mensagem) {
            System.out.println("   [SMS Legado] (Aviso: obsoleto) Enviando para " + numero + ": " + mensagem);
        }
    }

    public static void executar() {
        System.out.println("\n--- 1. ANOTAÇÕES NATIVAS DO JAVA (@Override, @Deprecated, @SuppressWarnings) ---");
        
        ServicoEmail servico = new ServicoEmail();
        servico.enviar("aluno@exemplo.com", "Bem-vindo aos estudos de Annotations!");

        // Chamada de método obsoleto suprimindo o warning do compilador:
        usarMetodoLegado(servico);
    }

    @SuppressWarnings("deprecation")
    private static void usarMetodoLegado(ServicoEmail servico) {
        // Sem @SuppressWarnings("deprecation"), o compilador emitiria um alerta amigável de compilação
        servico.enviarViaSmsLegado("11999999999", "Código via SMS antigo.");
    }
}
