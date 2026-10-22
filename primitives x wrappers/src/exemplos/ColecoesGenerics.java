package exemplos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstração do uso obrigatório de Wrappers nas Collections do Java (Generics).
 */
public class ColecoesGenerics {

    public static void executar() {
        System.out.println("==================================================");
        System.out.println(" 4. COLEÇÕES E GENERICS");
        System.out.println("==================================================");

        /*
         * O Java Generics não aceita tipos primitivos:
         * List<int> lista = new ArrayList<>(); // NÃO COMPILA!
         *
         * Por isso, o uso de Wrappers é mandatório em Coleções (List, Set, Map).
         */
        System.out.println("--- Lista de Inteiros (List<Integer>) ---");
        List<Integer> numeros = new ArrayList<>();

        // Graças ao autoboxing, podemos passar literais primitivos diretamente:
        numeros.add(10);
        numeros.add(25);
        numeros.add(42);

        System.out.println("Elementos na lista: " + numeros);

        // Ao iterar, podemos receber tanto como Integer quanto como int (unboxing):
        int somaTotal = 0;
        for (int n : numeros) { // Unboxing automático
            somaTotal += n;
        }
        System.out.println("Soma dos elementos (via unboxing): " + somaTotal);

        /*
         * Uso com Map (Chave -> Valor)
         */
        System.out.println("\n--- Mapa com Wrappers (Map<String, Double>) ---");
        Map<String, Double> notasAlunos = new HashMap<>();
        notasAlunos.put("Ana", 9.5);
        notasAlunos.put("Carlos", 7.0);
        notasAlunos.put("Beatriz", null); // Aluno faltou à prova (representação perfeita de ausência de nota)

        for (Map.Entry<String, Double> entrada : notasAlunos.entrySet()) {
            String aluno = entrada.getKey();
            Double nota = entrada.getValue();

            if (nota != null) {
                System.out.printf("Aluno(a): %-8s | Nota: %.1f%n", aluno, nota);
            } else {
                System.out.printf("Aluno(a): %-8s | Nota: Não compareceu%n", aluno);
            }
        }
        System.out.println();
    }
}
