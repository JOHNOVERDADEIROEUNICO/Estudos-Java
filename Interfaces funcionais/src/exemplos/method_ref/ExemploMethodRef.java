package exemplos.method_ref;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ============================================================
 *  TÓPICO 3: REFERÊNCIAS DE MÉTODO (Method References)
 * ============================================================
 *
 * Referência de método é um atalho para lambdas que apenas
 * chamam um método existente. Usa a sintaxe  Classe::metodo
 *
 * Existem 4 tipos:
 *
 *  1. Referência a método ESTÁTICO
 *     Classe::metodoEstatico
 *     Equivale a:  (args) -> Classe.metodoEstatico(args)
 *
 *  2. Referência a método de INSTÂNCIA de objeto específico
 *     objeto::metodoDeInstancia
 *     Equivale a:  (args) -> objeto.metodoDeInstancia(args)
 *
 *  3. Referência a método de INSTÂNCIA de objeto arbitrário
 *     Classe::metodoDeInstancia
 *     Equivale a:  (obj, args) -> obj.metodoDeInstancia(args)
 *
 *  4. Referência a CONSTRUTOR
 *     Classe::new
 *     Equivale a:  (args) -> new Classe(args)
 */

class Produto {
    private String nome;
    private double preco;

    // Construtor que será referenciado
    public Produto(String nome, double preco) {
        this.nome  = nome;
        this.preco = preco;
    }

    // Método estático
    public static String formatarPreco(double preco) {
        return String.format("R$ %.2f", preco);
    }

    // Método de instância
    public void exibir() {
        System.out.println("  Produto: " + nome + " | Preço: " + formatarPreco(preco));
    }

    // Método de instância com argumento
    public boolean temPrecoMenorQue(double limite) {
        return this.preco < limite;
    }

    @Override
    public String toString() {
        return nome + " (" + formatarPreco(preco) + ")";
    }
}

public class ExemploMethodRef {

    // Método estático para exemplo tipo 1
    public static void imprimirLinha(String texto) {
        System.out.println("  >> " + texto);
    }

    // Método de instância para exemplo tipo 2
    public void processarTexto(String texto) {
        System.out.println("  [processado]: " + texto.trim().toLowerCase());
    }

    public static void executar() {

        // ════════════════════════════════════════════
        //  Tipo 1: Referência a método ESTÁTICO
        // ════════════════════════════════════════════
        separador("Tipo 1: Método Estático  →  Classe::metodoEstatico");

        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carla", "Diego");

        // Lambda equivalente:   s -> ExemploMethodRef.imprimirLinha(s)
        Consumer<String> imprimirRef = ExemploMethodRef::imprimirLinha;
        nomes.forEach(imprimirRef);

        // Math::sqrt é uma referência ao método estático Math.sqrt(double)
        Function<Double, Double> raizQuadrada = Math::sqrt;
        System.out.println("  √16 = " + raizQuadrada.apply(16.0));
        System.out.println("  √81 = " + raizQuadrada.apply(81.0));

        // Integer::parseInt → converte String para int
        Function<String, Integer> parseIntRef = Integer::parseInt;
        System.out.println("  parseInt('42') = " + parseIntRef.apply("42"));

        // ════════════════════════════════════════════
        //  Tipo 2: Referência a método de INSTÂNCIA de objeto específico
        // ════════════════════════════════════════════
        separador("Tipo 2: Instância Específica  →  objeto::metodo");

        ExemploMethodRef instancia = new ExemploMethodRef();

        // Lambda equivalente:  s -> instancia.processarTexto(s)
        Consumer<String> processarRef = instancia::processarTexto;
        processarRef.accept("  JAVA é INCRÍVEL!  ");
        processarRef.accept("  INTERFACES FUNCIONAIS  ");

        // StringBuilder::append (append modifica e retorna o próprio StringBuilder)
        StringBuilder sb = new StringBuilder("Início");
        Consumer<String> acrescentar = sb::append;
        acrescentar.accept(" → Meio");
        acrescentar.accept(" → Fim");
        System.out.println("  StringBuilder: " + sb);

        // ════════════════════════════════════════════
        //  Tipo 3: Referência a método de INSTÂNCIA de objeto ARBITRÁRIO
        // ════════════════════════════════════════════
        separador("Tipo 3: Instância Arbitrária  →  Classe::metodo");

        // String::toUpperCase → o primeiro argumento DA LAMBDA vira o objeto receptor
        // Lambda equivalente:  (s) -> s.toUpperCase()
        Function<String, String> paraUpperCase = String::toUpperCase;
        System.out.println("  " + paraUpperCase.apply("java"));   // JAVA

        // String::length
        Function<String, Integer> comprimento = String::length;
        System.out.println("  Tamanho de 'programação': " + comprimento.apply("programação"));

        // String::compareTo → BiFunction pois recebe o objeto + 1 argumento
        // Lambda equivalente:  (s1, s2) -> s1.compareTo(s2)
        BiFunction<String, String, Integer> comparar = String::compareTo;
        System.out.println("  'abc'.compareTo('abd') = " + comparar.apply("abc", "abd"));

        // Usando com lista de objetos
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 3500.00),
            new Produto("Mouse", 120.00),
            new Produto("Teclado", 250.00)
        );
        System.out.println("  Produtos:");
        produtos.forEach(Produto::exibir);  // Lambda equiv.: p -> p.exibir()

        // ════════════════════════════════════════════
        //  Tipo 4: Referência a CONSTRUTOR
        // ════════════════════════════════════════════
        separador("Tipo 4: Construtor  →  Classe::new");

        // Supplier<T>  → sem argumentos → chama construtor sem parâmetros
        Supplier<StringBuilder> criarSB = StringBuilder::new;
        StringBuilder novoSB = criarSB.get();
        novoSB.append("Criado via Supplier<StringBuilder>");
        System.out.println("  " + novoSB);

        // BiFunction<T, U, R> → dois argumentos → construtor com 2 parâmetros
        // Lambda equivalente:  (nome, preco) -> new Produto(nome, preco)
        BiFunction<String, Double, Produto> criarProduto = Produto::new;
        Produto novoProduto = criarProduto.apply("Monitor", 1200.0);
        novoProduto.exibir();
    }

    private static void separador(String titulo) {
        System.out.println("\n  [" + titulo + "]");
    }
}
