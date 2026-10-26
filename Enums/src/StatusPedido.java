/**
 * Exemplo 2: Enum com Atributos, Construtor e Métodos
 * 
 * Em Java, Enums são cidadãos de primeira classe (classes completas).
 * Eles podem ter:
 * 1. Atributos privados (geralmente finais para garantir imutabilidade).
 * 2. Construtor (que é sempre privado - não pode ser instanciado fora do Enum).
 * 3. Getters e métodos de negócio normais ou estáticos.
 */
public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1, "Aguardando confirmação do pagamento"),
    PAGO(2, "Pagamento confirmado com sucesso"),
    ENVIADO(3, "Pedido despachado para a transportadora"),
    ENTREGUE(4, "Pedido entregue ao destinatário"),
    CANCELADO(5, "Pedido cancelado");

    private final int codigo;
    private final String descricao;

    // O construtor é private por padrão (declarar 'public' gera erro de compilação)
    StatusPedido(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Exemplo de método estático para converter um código numérico no Enum correspondente.
     */
    public static StatusPedido porCodigo(int codigo) {
        for (StatusPedido status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status desconhecido: " + codigo);
    }

    @Override
    public String toString() {
        return String.format("%s (código: %d, descrição: \"%s\")", name(), codigo, descricao);
    }
}
