package exemplos.delimitados;

/**
 * Exemplo 4: Bounded Type Parameters (Tipos Delimitados / Restritos).
 * 
 * Usamos 'extends' para restringir quais tipos podem ser passados.
 * Aqui, <T extends Number> garante que T seja Number ou qualquer subclasse
 * (ex: Integer, Double, Float, Long, BigDecimal).
 * 
 * Isso permite chamar métodos da classe Number (como doubleValue()).
 */
public class CalculadoraNumerica<T extends Number> {
    private T numero1;
    private T numero2;

    public CalculadoraNumerica(T numero1, T numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    /**
     * Soma os dois números convertendo para double.
     */
    public double somar() {
        return numero1.doubleValue() + numero2.doubleValue();
    }

    /**
     * Compara o valor numérico dos dois itens.
     */
    public boolean saoIguaisEmModulo() {
        return Math.abs(numero1.doubleValue()) == Math.abs(numero2.doubleValue());
    }

    /**
     * Método genérico com delimitador estático:
     * Retorna o maior entre dois elementos comparáveis.
     */
    public static <E extends Comparable<E>> E maior(E a, E b) {
        return (a.compareTo(b) > 0) ? a : b;
    }
}
