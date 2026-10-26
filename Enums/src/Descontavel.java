/**
 * Exemplo 4: Enums implementando Interfaces
 * 
 * Enums não podem estender outras classes (pois já herdam implicitamente de java.lang.Enum),
 * mas podem implementar qualquer interface.
 */
public interface Descontavel {
    double aplicarDesconto(double valorOriginal);
}
