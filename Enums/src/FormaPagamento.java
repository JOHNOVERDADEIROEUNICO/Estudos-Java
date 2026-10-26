/**
 * Exemplo 4 (continuação): Enum implementando interface
 */
public enum FormaPagamento implements Descontavel {
    PIX(0.10) {
        @Override
        public double aplicarDesconto(double valorOriginal) {
            return valorOriginal * (1 - taxaDesconto);
        }
    },
    BOLETO(0.05) {
        @Override
        public double aplicarDesconto(double valorOriginal) {
            return valorOriginal * (1 - taxaDesconto);
        }
    },
    CARTAO_CREDITO(0.0) {
        @Override
        public double aplicarDesconto(double valorOriginal) {
            return valorOriginal; // sem desconto
        }
    };

    protected final double taxaDesconto;

    FormaPagamento(double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    public double getTaxaDesconto() {
        return taxaDesconto;
    }
}
