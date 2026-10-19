/**
 * =============================================================
 *  01 - FUNDAMENTOS DO OPERADOR LAMBDA
 * =============================================================
 *
 * O que é uma Expressão Lambda?
 * -----------------------------
 * Uma expressão lambda é uma função anônima (sem nome) que pode
 * ser passada como argumento, armazenada em variáveis e executada.
 *
 * Surgiu no Java 8 e implementa o conceito de "programação funcional".
 *
 * SINTAXE:
 *   (parâmetros) -> { corpo }
 *
 * Variações de sintaxe:
 *   ()          -> System.out.println("sem parâmetros")
 *   x           -> x * 2                         // 1 param, sem chaves
 *   (x)         -> x * 2                         // 1 param com parênteses
 *   (x, y)      -> x + y                         // 2 params
 *   (x, y)      -> { return x + y; }             // corpo com bloco
 *   (int x, int y) -> { return x + y; }          // tipos explícitos
 *
 * REQUISITO: A lambda DEVE implementar uma @FunctionalInterface —
 *            uma interface com exatamente UM método abstrato.
 */

// Interface funcional personalizada (apenas 1 método abstrato)
@FunctionalInterface
interface Saudacao {
    void cumprimentar(String nome);
}

@FunctionalInterface
interface Operacao {
    int calcular(int a, int b);
}

@FunctionalInterface
interface Verificador {
    boolean verificar(int numero);
}

public class FundamentosLambda {

    public static void executar() {
        System.out.println("\n--- 01. FUNDAMENTOS LAMBDA ---");

        // -------------------------------------------------------
        // ANTES do Java 8: Classe Anônima
        // -------------------------------------------------------
        Saudacao classeAnonima = new Saudacao() {
            @Override
            public void cumprimentar(String nome) {
                System.out.println("[Classe anônima] Olá, " + nome + "!");
            }
        };
        classeAnonima.cumprimentar("Mundo");

        // -------------------------------------------------------
        // COM Lambda: muito mais conciso!
        // -------------------------------------------------------
        Saudacao lambda = nome -> System.out.println("[Lambda] Olá, " + nome + "!");
        lambda.cumprimentar("Mundo");

        // -------------------------------------------------------
        // Lambda sem parâmetros
        // -------------------------------------------------------
        Runnable semParametro = () -> System.out.println("Lambda sem parâmetros executado!");
        semParametro.run();

        // -------------------------------------------------------
        // Lambda com retorno (sem bloco)
        // -------------------------------------------------------
        Operacao soma      = (a, b) -> a + b;
        Operacao subtracao = (a, b) -> a - b;
        Operacao produto   = (a, b) -> a * b;

        System.out.println("Soma(10, 3)      = " + soma.calcular(10, 3));
        System.out.println("Subtração(10, 3) = " + subtracao.calcular(10, 3));
        System.out.println("Produto(10, 3)   = " + produto.calcular(10, 3));

        // -------------------------------------------------------
        // Lambda com retorno booleano
        // -------------------------------------------------------
        Verificador ePar   = n -> n % 2 == 0;
        Verificador eMaior = n -> n > 100;

        System.out.println("42 é par?          " + ePar.verificar(42));
        System.out.println("200 é maior que 100? " + eMaior.verificar(200));

        // -------------------------------------------------------
        // Lambda com bloco de código (múltiplas linhas)
        // -------------------------------------------------------
        Operacao divisaoSegura = (a, b) -> {
            if (b == 0) {
                System.out.println("  [AVISO] Divisão por zero!");
                return 0;
            }
            return a / b;
        };

        System.out.println("Divisão(10, 2)  = " + divisaoSegura.calcular(10, 2));
        System.out.println("Divisão(10, 0)  = " + divisaoSegura.calcular(10, 0));

        // -------------------------------------------------------
        // Lambda capturando variáveis externas (effectively final)
        // -------------------------------------------------------
        String prefixo = ">>"; // deve ser final ou effectively final
        Saudacao comPrefixo = nome -> System.out.println(prefixo + " Olá, " + nome + "!");
        comPrefixo.cumprimentar("Java Lambda");
    }
}
