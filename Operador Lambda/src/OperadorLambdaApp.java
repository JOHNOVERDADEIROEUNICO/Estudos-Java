/**
 * =============================================================
 *  OPERADOR LAMBDA EM JAVA - Ponto de entrada principal
 * =============================================================
 *
 * Este projeto contém exemplos didáticos sobre o uso de
 * Expressões Lambda em Java (introduzidas no Java 8).
 *
 * Estrutura dos exemplos:
 *  - 01_FundamentosLambda     → Sintaxe básica e interfaces funcionais
 *  - 02_InterfacesFuncionais   → Predicate, Function, Consumer, Supplier
 *  - 03_LambdaComColecoes      → Uso com List, Map e Stream
 *  - 04_MethodReference        → Referência de método (::)
 *  - 05_LambdaAvancado         → Composição e currying
 *  - 06_LambdaComparator       → Ordenação com lambdas
 */
public class OperadorLambdaApp {
    public static void main(String[] args) throws Exception {
        System.out.println("=================================================");
        System.out.println("  ESTUDOS: OPERADOR LAMBDA EM JAVA");
        System.out.println("=================================================\n");

        System.out.println(">>> Executando exemplos de Fundamentos...");
        FundamentosLambda.executar();

        System.out.println("\n>>> Executando exemplos de Interfaces Funcionais...");
        InterfacesFuncionais.executar();

        System.out.println("\n>>> Executando exemplos com Coleções e Streams...");
        LambdaComColecoes.executar();

        System.out.println("\n>>> Executando exemplos de Method Reference...");
        MethodReference.executar();

        System.out.println("\n>>> Executando exemplos Avançados...");
        LambdaAvancado.executar();

        System.out.println("\n>>> Executando exemplos com Comparator...");
        LambdaComparator.executar();

        System.out.println("\n=================================================");
        System.out.println("  Todos os exemplos foram executados com sucesso!");
        System.out.println("=================================================");
    }
}
