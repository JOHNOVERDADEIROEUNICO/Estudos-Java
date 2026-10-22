package exemplos;

/**
 * Explicação sobre Autoboxing e Unboxing (introduzidos no Java 5).
 */
public class AutoboxingUnboxing {

    public static void executar() {
        System.out.println("==================================================");
        System.out.println(" 2. AUTOBOXING E UNBOXING");
        System.out.println("==================================================");

        /*
         * AUTOBOXING:
         * É a conversão automática que o compilador Java faz de um tipo primitivo
         * para a sua respectiva classe Wrapper.
         * Por debaixo dos panos, o compilador faz: Integer.valueOf(primitivo).
         */
        int valorPrimitivo = 42;
        Integer valorWrapper = valorPrimitivo; // Autoboxing automático!
        System.out.println("Autoboxing (int -> Integer): " + valorWrapper);

        /*
         * UNBOXING:
         * É a conversão automática do objeto Wrapper de volta para o tipo primitivo.
         * Por debaixo dos panos, o compilador chama: wrapper.intValue() (ou equivalente).
         */
        Integer notaWrapper = 95;
        int notaPrimitiva = notaWrapper; // Unboxing automático!
        System.out.println("Unboxing (Integer -> int): " + notaPrimitiva);

        /*
         * Operações aritméticas com Wrappers:
         * O Java desempacota (unboxing), realiza a conta com primitivos e empacota de volta (autoboxing).
         */
        Integer a = 15;
        Integer b = 25;
        Integer soma = a + b; // Faz unboxing de a e b, soma os ints, e faz autoboxing no resultado
        System.out.println("Soma com Wrappers (a + b): " + soma);

        /*
         * ATENÇÃO: O Perigo do NullPointerException no Unboxing!
         * Se um Wrapper estiver 'null' e o Java tentar fazer unboxing para um primitivo,
         * o compilador tentará chamar '.intValue()', gerando um NullPointerException em tempo de execução.
         */
        System.out.println("\n--- Cuidado com NullPointerException no Unboxing ---");
        Integer saldo = null;
        try {
            int saldoPrimitivo = saldo; // Tenta fazer saldo.intValue() -> BOOM!
            System.out.println("Saldo: " + saldoPrimitivo);
        } catch (NullPointerException e) {
            System.out.println("Capturamos a exceção: NullPointerException ao tentar fazer unboxing de Wrapper 'null'!");
            System.out.println("Dica: Sempre valide se o Wrapper não é nulo antes de converter para primitivo.");
        }
        System.out.println();
    }
}
