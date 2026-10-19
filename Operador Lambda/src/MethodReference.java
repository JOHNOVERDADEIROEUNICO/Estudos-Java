import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * =============================================================
 *  04 - METHOD REFERENCE (Referência de Método)
 * =============================================================
 *
 * A Referência de Método (::) é um atalho para lambdas que apenas
 * chamam um método já existente. Torna o código ainda mais legível.
 *
 * 4 tipos de Method Reference:
 * ┌──────────────────────────────┬─────────────────────────────────────┐
 * │ Tipo                         │ Sintaxe                             │
 * ├──────────────────────────────┼─────────────────────────────────────┤
 * │ Método estático              │ Classe::metodoEstatico              │
 * │ Método de instância (objeto) │ objeto::metodoDeInstancia           │
 * │ Método de instância (tipo)   │ Classe::metodoDeInstancia           │
 * │ Construtor                   │ Classe::new                         │
 * └──────────────────────────────┴─────────────────────────────────────┘
 */
public class MethodReference {

    // Classe auxiliar para exemplos
    static class Pessoa {
        private String nome;
        private int idade;

        public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        // Construtor apenas com nome (para ::new)
        public Pessoa(String nome) {
            this.nome = nome;
            this.idade = 0;
        }

        public String getNome() { return nome; }
        public int getIdade() { return idade; }

        // Método estático
        public static int compararPorIdade(Pessoa a, Pessoa b) {
            return Integer.compare(a.idade, b.idade);
        }

        // Método de instância
        public boolean eMaiorDeIdade() {
            return this.idade >= 18;
        }

        public void apresentar() {
            System.out.println("  Olá! Sou " + nome + ", tenho " + idade + " anos.");
        }

        @Override
        public String toString() {
            return nome + "(" + idade + ")";
        }
    }

    public static void executar() {
        System.out.println("\n--- 04. METHOD REFERENCE ---");

        // -------------------------------------------------------
        // TIPO 1: Método Estático  →  Classe::metodoEstatico
        // -------------------------------------------------------
        System.out.println("\n[1] Método estático: Classe::metodo");

        // Lambda equivalente: n -> Integer.parseInt(n)
        Function<String, Integer> parseIntLambda = s -> Integer.parseInt(s);
        Function<String, Integer> parseIntRef    = Integer::parseInt;  // <-- method ref!

        System.out.println("Lambda:  " + parseIntLambda.apply("42"));
        System.out.println("Ref:     " + parseIntRef.apply("42"));

        // Lambda equivalente: (a, b) -> Pessoa.compararPorIdade(a, b)
        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Carlos", 35),
            new Pessoa("Ana", 28),
            new Pessoa("João", 42),
            new Pessoa("Bia", 19)
        );

        System.out.println("Ordenando por idade com method reference estático:");
        pessoas.stream()
               .sorted(Pessoa::compararPorIdade)  // equivale a: (a, b) -> Pessoa.compararPorIdade(a, b)
               .forEach(p -> System.out.println("  " + p));

        // -------------------------------------------------------
        // TIPO 2: Método de Instância em objeto específico
        //         →  objetoConcreto::metodo
        // -------------------------------------------------------
        System.out.println("\n[2] Método de instância (objeto específico): obj::metodo");

        String prefixo = "JAVA";
        // Lambda equivalente: s -> prefixo.startsWith(s)
        Predicate<String> comecaCom = prefixo::startsWith;

        System.out.println("\"JAVA\" começa com \"J\"? " + comecaCom.test("J"));
        System.out.println("\"JAVA\" começa com \"K\"? " + comecaCom.test("K"));

        // println como Consumer
        Consumer<String> imprimir = System.out::println;  // equiv: s -> System.out.println(s)
        imprimir.accept("  >> Impresso via System.out::println");

        // -------------------------------------------------------
        // TIPO 3: Método de Instância por Tipo
        //         →  Classe::metodoDeInstancia
        //   (o 1º parâmetro da lambda vira o "this" do método)
        // -------------------------------------------------------
        System.out.println("\n[3] Método de instância (por tipo): Classe::metodo");

        // Lambda equivalente: s -> s.toUpperCase()
        Function<String, String> maiusculo = String::toUpperCase;
        System.out.println("maiusculo(\"java\"): " + maiusculo.apply("java"));

        // Lambda equivalente: s -> s.isEmpty()
        Predicate<String> eVazia = String::isEmpty;
        System.out.println("eVazia(\"\"): " + eVazia.test(""));
        System.out.println("eVazia(\"a\"): " + eVazia.test("a"));

        // Lambda equivalente: p -> p.eMaiorDeIdade()
        System.out.println("Maiores de idade:");
        pessoas.stream()
               .filter(Pessoa::eMaiorDeIdade)  // equiv: p -> p.eMaiorDeIdade()
               .map(Pessoa::getNome)            // equiv: p -> p.getNome()
               .forEach(System.out::println);   // equiv: nome -> System.out.println(nome)

        // Usando apresentar() com forEach
        System.out.println("Apresentações:");
        pessoas.stream()
               .filter(Pessoa::eMaiorDeIdade)
               .forEach(Pessoa::apresentar);

        // -------------------------------------------------------
        // TIPO 4: Referência de Construtor  →  Classe::new
        // -------------------------------------------------------
        System.out.println("\n[4] Referência de construtor: Classe::new");

        // Lambda equivalente: nome -> new Pessoa(nome)
        Function<String, Pessoa> criadorPessoa = Pessoa::new;

        List<String> nomesNovos = Arrays.asList("Paulo", "Carla", "Marcos");
        List<Pessoa> novasPessoas = nomesNovos.stream()
                .map(Pessoa::new)            // equiv: nome -> new Pessoa(nome)
                .collect(Collectors.toList());

        System.out.println("Pessoas criadas com ::new:");
        novasPessoas.forEach(p -> System.out.println("  " + p.getNome()));

        // Supplier como factory
        Supplier<List<String>> novaLista = ArrayList::new;
        List<String> lista = novaLista.get();
        lista.add("Criada com Supplier + ::new");
        System.out.println("Lista via Supplier::new: " + lista);
    }
}
