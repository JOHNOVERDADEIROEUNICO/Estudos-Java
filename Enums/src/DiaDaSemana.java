/**
 * Exemplo 1: Enum Básico
 * 
 * Representa um conjunto fixo e imutável de constantes.
 * 
 * O que acontece por baixo dos panos?
 * - O compilador Java transforma isso em uma classe final que herda de java.lang.Enum<DiaDaSemana>.
 * - Cada constante (DOMINGO, SEGUNDA, etc.) é uma instância estática e final (public static final).
 */
public enum DiaDaSemana {
    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO;

    /**
     * Mesmo um enum simples pode conter métodos auxiliares!
     */
    public boolean isFimDeSemana() {
        return this == DOMINGO || this == SABADO;
    }
}
