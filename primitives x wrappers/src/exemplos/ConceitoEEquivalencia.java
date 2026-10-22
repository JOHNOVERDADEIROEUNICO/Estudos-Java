package exemplos;

/**
 * Explicação sobre o que são Tipos Primitivos vs Classes Wrapper.
 */
public class ConceitoEEquivalencia {

    public static void executar() {
        System.out.println("==================================================");
        System.out.println(" 1. CONCEITO: PRIMITIVOS vs WRAPPERS");
        System.out.println("==================================================");

        /*
         * Tipos Primitivos:
         * - Armazenam o valor diretamente na memória Stack.
         * - Não são objetos, não possuem métodos nem atributos.
         * - Possuem valores padrão (ex: 0, 0.0, false), NUNCA podem ser 'null'.
         * - São muito rápidos e consomem pouca memória.
         *
         * Classes Wrapper:
         * - São classes que "embrulham" (wrap) o primitivo em um objeto.
         * - Ficam alocadas na memória Heap (com referência na Stack).
         * - Podem ser 'null' (essencial para representar valores ausentes/bancos de dados).
         * - Permitem uso em Generics (List, Map, Set) e oferecem métodos utilitários.
         */

        // Tabela de equivalência:
        byte bytePrimitivo = 10;
        Byte byteWrapper = 10;

        short shortPrimitivo = 20;
        Short shortWrapper = 20;

        int intPrimitivo = 100;
        Integer intWrapper = 100;

        long longPrimitivo = 1000L;
        Long longWrapper = 1000L;

        float floatPrimitivo = 3.14f;
        Float floatWrapper = 3.14f;

        double doublePrimitivo = 99.90;
        Double doubleWrapper = 99.90;

        char charPrimitivo = 'J';
        Character charWrapper = 'J';

        boolean booleanPrimitivo = true;
        Boolean booleanWrapper = true;

        System.out.println("Tipos Primitivos -> Classes Wrapper equivalentes:");
        System.out.printf("  byte (%d)       -> Byte (%d)%n", bytePrimitivo, byteWrapper);
        System.out.printf("  short (%d)     -> Short (%d)%n", shortPrimitivo, shortWrapper);
        System.out.printf("  int (%d)       -> Integer (%d)%n", intPrimitivo, intWrapper);
        System.out.printf("  long (%d)     -> Long (%d)%n", longPrimitivo, longWrapper);
        System.out.printf("  float (%.2f)   -> Float (%.2f)%n", floatPrimitivo, floatWrapper);
        System.out.printf("  double (%.2f)  -> Double (%.2f)%n", doublePrimitivo, doubleWrapper);
        System.out.printf("  char ('%c')     -> Character ('%c')%n", charPrimitivo, charWrapper);
        System.out.printf("  boolean (%b)  -> Boolean (%b)%n", booleanPrimitivo, booleanWrapper);

        System.out.println("\n--- Diferença Crucial: Aceitação de Nulo (null) ---");
        // int idadeInvalida = null; // ERRO de compilação! Primitivo nunca é null.
        Integer idadeOpcional = null; // Perfeitamente válido! Útil para representar dados não preenchidos.
        System.out.println("Wrapper aceitando 'null': idadeOpcional = " + idadeOpcional);
        System.out.println();
    }
}
