/**
 * Exemplo 3: Enum com Métodos Abstratos (Polimorfismo / Strategy Pattern)
 * 
 * Cada constante do Enum pode fornecer sua própria implementação de um método abstrato.
 * Isso é fantástico para substituir blocos switch/case repetitivos por polimorfismo limpo.
 */
public enum Operacao {
    SOMA("+") {
        @Override
        public double calcular(double a, double b) {
            return a + b;
        }
    },
    SUBTRACAO("-") {
        @Override
        public double calcular(double a, double b) {
            return a - b;
        }
    },
    MULTIPLICACAO("*") {
        @Override
        public double calcular(double a, double b) {
            return a * b;
        }
    },
    DIVISAO("/") {
        @Override
        public double calcular(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Não é permitida divisão por zero.");
            }
            return a / b;
        }
    };

    private final String simbolo;

    Operacao(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Método abstrato: cada constante é OBRIGADA a fornecer sua própria implementação.
     */
    public abstract double calcular(double a, double b);
}
