package exemplos;

/**
 * Exemplos de métodos estáticos e constantes utilitárias fornecidas pelos Wrappers.
 */
public class MetodosUtilitarios {

    public static void executar() {
        System.out.println("==================================================");
        System.out.println(" 3. MÉTODOS UTILITÁRIOS E CONSTANTES");
        System.out.println("==================================================");

        /*
         * 1. Parsing: Conversão de String para números e booleanos.
         * parseXxx() retorna o primitivo.
         * valueOf() retorna a classe Wrapper (com suporte a cache).
         */
        System.out.println("--- 1. Conversão (Parsing) de Strings ---");
        String textoNumero = "12345";
        int numeroInt = Integer.parseInt(textoNumero);       // Retorna int primitivo
        Integer numeroObj = Integer.valueOf(textoNumero);     // Retorna Integer Wrapper

        double valorDouble = Double.parseDouble("199.99");
        boolean flagAtivo = Boolean.parseBoolean("true");

        System.out.println("Integer.parseInt(\"12345\"): " + numeroInt);
        System.out.println("Integer.valueOf(\"12345\"): " + numeroObj);
        System.out.println("Double.parseDouble(\"199.99\"): " + valorDouble);
        System.out.println("Boolean.parseBoolean(\"true\"): " + flagAtivo);

        // Tratamento de NumberFormatException
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException capturado ao tentar converter \"abc\" para número.");
        }

        /*
         * 2. Constantes Úteis: Limites de valores suportados pelos tipos numéricos
         */
        System.out.println("\n--- 2. Constantes dos Wrappers ---");
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE:    " + Long.MAX_VALUE);
        System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);

        /*
         * 3. Conversões de Base Numérica
         */
        System.out.println("\n--- 3. Conversões de Base Numérica ---");
        int numero = 255;
        System.out.println("Decimal " + numero + " em Binário: " + Integer.toBinaryString(numero));
        System.out.println("Decimal " + numero + " em Hexadecimal: " + Integer.toHexString(numero));
        System.out.println("Decimal " + numero + " em Octal: " + Integer.toOctalString(numero));

        /*
         * 4. Utilitários da classe Character (Inspeção de caracteres)
         */
        System.out.println("\n--- 4. Utilitários da classe Character ---");
        char c1 = '7';
        char c2 = 'a';
        char c3 = ' ';

        System.out.println("'" + c1 + "' é dígito? " + Character.isDigit(c1));
        System.out.println("'" + c2 + "' é letra? " + Character.isLetter(c2));
        System.out.println("'" + c2 + "' maiúsculo: " + Character.toUpperCase(c2));
        System.out.println("'" + c3 + "' é espaço em branco? " + Character.isWhitespace(c3));

        /*
         * 5. Métodos matemáticos e comparações
         */
        System.out.println("\n--- 5. Comparações e Métodos Estáticos ---");
        System.out.println("Integer.max(50, 80): " + Integer.max(50, 80));
        System.out.println("Integer.compare(10, 20): " + Integer.compare(10, 20) + " (negativo pois 10 < 20)");
        System.out.println("Integer.compare(20, 10): " + Integer.compare(20, 10) + " (positivo pois 20 > 10)");
        System.out.println("Integer.compare(10, 10): " + Integer.compare(10, 10) + " (zero pois são iguais)");
        System.out.println();
    }
}
