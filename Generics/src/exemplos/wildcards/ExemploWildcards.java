package exemplos.wildcards;

import java.util.List;

/**
 * Exemplo 5: Wildcards (Curingas - '?') e o Princípio PECS.
 * 
 * PECS significa: "Producer Extends, Consumer Super"
 * - Producer (fonte de dados para leitura): use '? extends T'
 * - Consumer (destino onde você insere dados): use '? super T'
 */
public class ExemploWildcards {

    /**
     * 1. Unbounded Wildcard (List<?>):
     * Aceita lista de QUALQUER tipo (List<String>, List<Integer>, etc).
     * Ideal apenas para leitura genérica como Object.
     */
    public static void imprimirLista(List<?> lista) {
        System.out.print("[ ");
        for (Object item : lista) {
            System.out.print(item + " ");
        }
        System.out.println("]");
    }

    /**
     * 2. Upper Bounded Wildcard (List<? extends Number>):
     * PRODUCER: A lista produz/fornece dados do tipo Number.
     * Aceita List<Integer>, List<Double>, List<Number>, etc.
     * PERMITIDO: Ler elementos como 'Number'.
     * PROIBIDO: Adicionar elementos (o compilador não sabe o tipo exato da lista em tempo de execução).
     */
    public static double somarLista(List<? extends Number> lista) {
        double total = 0.0;
        for (Number n : lista) {
            total += n.doubleValue(); // Leitura segura
        }
        return total;
    }

    /**
     * 3. Lower Bounded Wildcard (List<? super Integer>):
     * CONSUMER: A lista consome/recebe dados do tipo Integer.
     * Aceita List<Integer>, List<Number>, List<Object>.
     * PERMITIDO: Adicionar elementos do tipo Integer (ou subclasses).
     */
    public static void adicionarNumeros(List<? super Integer> lista) {
        lista.add(10);
        lista.add(20);
        lista.add(30);
    }

    /**
     * 4. Exemplo PECS completo: Copiar de uma lista para outra.
     * de (origem/produtor) -> <? extends T>
     * para (destino/consumidor) -> <? super T>
     */
    public static <T> void copiar(List<? extends T> origem, List<? super T> destino) {
        for (T item : origem) {
            destino.add(item);
        }
    }
}
