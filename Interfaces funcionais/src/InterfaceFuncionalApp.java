/**
 * ============================================================
 *  INTERFACES FUNCIONAIS EM JAVA - PONTO DE ENTRADA PRINCIPAL
 * ============================================================
 * 
 * Este projeto demonstra o uso completo de Interfaces Funcionais
 * em Java, organizadas por tópicos em pacotes separados.
 *
 * Estrutura do projeto:
 *  - exemplos.basico       → O que é uma interface funcional e @FunctionalInterface
 *  - exemplos.builtin      → Interfaces embutidas: Function, Consumer, Supplier, Predicate
 *  - exemplos.method_ref   → Referências de método (Method References)
 *  - exemplos.composicao   → Composição de funções
 *  - exemplos.streams      → Uso com Streams e coleções
 *  - exemplos.pratico      → Caso prático: sistema de pedidos
 */
public class InterfaceFuncionalApp {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  ESTUDO: INTERFACES FUNCIONAIS EM JAVA");
        System.out.println("=================================================\n");

        System.out.println("▶  Executando exemplos básicos...");
        exemplos.basico.ExemploBasico.executar();

        System.out.println("\n▶  Executando interfaces built-in...");
        exemplos.builtin.ExemploBuiltin.executar();

        System.out.println("\n▶  Executando referências de método...");
        exemplos.method_ref.ExemploMethodRef.executar();

        System.out.println("\n▶  Executando composição de funções...");
        exemplos.composicao.ExemploComposicao.executar();

        System.out.println("\n▶  Executando exemplos com Streams...");
        exemplos.streams.ExemploStreams.executar();

        System.out.println("\n▶  Executando caso prático...");
        exemplos.pratico.ExemploPratico.executar();

        System.out.println("\n=================================================");
        System.out.println("  FIM DOS EXEMPLOS");
        System.out.println("=================================================");
    }
}
