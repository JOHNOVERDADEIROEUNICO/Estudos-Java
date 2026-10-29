package exemplos.metodos;

/**
 * Exemplo 3: Métodos Genéricos.
 * 
 * Uma classe comum (não genérica) pode ter métodos genéricos.
 * O parâmetro de tipo <T> é declarado ANTES do tipo de retorno.
 */
public class UtilitarioArray {

    /**
     * Imprime qualquer array de objetos (String[], Integer[], Double[], etc.)
     * sem precisar criar sobrecargas para cada tipo.
     */
    public static <T> void imprimirArray(T[] array) {
        System.out.print("[ ");
        for (T elemento : array) {
            System.out.print(elemento + " ");
        }
        System.out.println("]");
    }

    /**
     * Retorna o primeiro elemento de um array genérico ou null se vazio.
     */
    public static <T> T obterPrimeiroElemento(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * Troca a posição de dois elementos em um array de qualquer tipo.
     */
    public static <T> void trocarElementos(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
