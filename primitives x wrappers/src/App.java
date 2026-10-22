import exemplos.AutoboxingUnboxing;
import exemplos.ColecoesGenerics;
import exemplos.ConceitoEEquivalencia;
import exemplos.MetodosUtilitarios;
import exemplos.PegadinhasEPerformance;

public class App {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       GUIA COMPLETO: WRAPPERS EM JAVA            ");
        System.out.println("==================================================\n");

        // 1. O que são e equivalências
        ConceitoEEquivalencia.executar();

        // 2. Autoboxing e Unboxing (e o risco de NPE)
        AutoboxingUnboxing.executar();

        // 3. Métodos utilitários e conversões
        MetodosUtilitarios.executar();

        // 4. Uso em Coleções e Generics
        ColecoesGenerics.executar();

        // 5. Comparação, cache e impactos de performance
        PegadinhasEPerformance.executar();

        System.out.println("==================================================");
        System.out.println("       EXECUÇÃO DOS EXEMPLOS CONCLUÍDA!           ");
        System.out.println("==================================================");
    }
}
