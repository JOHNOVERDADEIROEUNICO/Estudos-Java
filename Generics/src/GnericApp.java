import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exemplos.classes.Caixa;
import exemplos.classes.Par;
import exemplos.delimitados.CalculadoraNumerica;
import exemplos.metodos.UtilitarioArray;
import exemplos.wildcards.ExemploWildcards;

public class GnericApp {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       GUIA PRATICO DE GENERICS EM JAVA           ");
        System.out.println("==================================================\n");

        // -----------------------------------------------------------------
        // 1. Classes Genéricas
        // -----------------------------------------------------------------
        System.out.println("--- 1. Classes Genericas ---");
        
        Caixa<String> caixaTexto = new Caixa<>("Texto com Type Safety");
        System.out.println(caixaTexto);
        // Não precisa de cast '(String)' ao obter o valor:
        String texto = caixaTexto.getConteudo();
        System.out.println("Conteudo extraido diretamente: " + texto);

        Caixa<Integer> caixaNumero = new Caixa<>(42);
        System.out.println(caixaNumero);
        System.out.println("Numero: " + caixaNumero.getConteudo());

        // Multiplos tipos genericos (K, V)
        Par<String, Double> produto = new Par<>("Notebook", 4500.00);
        System.out.println("Produto: " + produto.getChave() + " | Preco: R$ " + produto.getValor());
        System.out.println();

        // -----------------------------------------------------------------
        // 2. Métodos Genéricos
        // -----------------------------------------------------------------
        System.out.println("--- 2. Metodos Genericos ---");
        
        String[] nomes = {"Ana", "Bruno", "Carlos"};
        Integer[] numeros = {1, 2, 3, 4, 5};

        System.out.print("Array de Strings: ");
        UtilitarioArray.imprimirArray(nomes);

        System.out.print("Array de Inteiros: ");
        UtilitarioArray.imprimirArray(numeros);

        System.out.println("Primeiro nome: " + UtilitarioArray.obterPrimeiroElemento(nomes));
        UtilitarioArray.trocarElementos(nomes, 0, 2);
        System.out.print("Apos inverter primeiro e ultimo: ");
        UtilitarioArray.imprimirArray(nomes);
        System.out.println();

        // -----------------------------------------------------------------
        // 3. Bounded Types (Tipos Delimitados / Restritos)
        // -----------------------------------------------------------------
        System.out.println("--- 3. Bounded Types (<T extends Number>) ---");
        
        CalculadoraNumerica<Integer> calcInt = new CalculadoraNumerica<>(15, 25);
        System.out.println("Soma de Inteiros (15 + 25): " + calcInt.somar());

        CalculadoraNumerica<Double> calcDouble = new CalculadoraNumerica<>(10.5, 4.5);
        System.out.println("Soma de Doubles (10.5 + 4.5): " + calcDouble.somar());

        // O compilador impede tipos nao compativeis:
        // CalculadoraNumerica<String> erro = new CalculadoraNumerica<>("a", "b"); // NAO COMPILA!

        String maiorTexto = CalculadoraNumerica.maior("Zebra", "Abacaxi");
        System.out.println("Maior texto lexicografico: " + maiorTexto);
        System.out.println();

        // -----------------------------------------------------------------
        // 4. Wildcards (Curingas '?') e Principio PECS
        // -----------------------------------------------------------------
        System.out.println("--- 4. Wildcards e PECS (Producer Extends, Consumer Super) ---");

        List<Integer> listaInt = Arrays.asList(10, 20, 30);
        List<Double> listaDouble = Arrays.asList(1.5, 2.5, 3.5);

        // 4.1. Unbounded Wildcard (?)
        System.out.print("Lista generica (?): ");
        ExemploWildcards.imprimirLista(listaInt);

        // 4.2. Upper Bounded (? extends Number) - Produtor (leitura)
        System.out.println("Soma lista de Inteiros: " + ExemploWildcards.somarLista(listaInt));
        System.out.println("Soma lista de Doubles: " + ExemploWildcards.somarLista(listaDouble));

        // 4.3. Lower Bounded (? super Integer) - Consumidor (escrita)
        List<Number> listaDeNumeros = new ArrayList<>();
        ExemploWildcards.adicionarNumeros(listaDeNumeros);
        System.out.println("Lista apos adicionar numeros (? super Integer): " + listaDeNumeros);

        // 4.4. Copia PECS
        List<Integer> origem = Arrays.asList(100, 200, 300);
        List<Number> destino = new ArrayList<>();
        ExemploWildcards.copiar(origem, destino);
        System.out.println("Destino apos copiar: " + destino);

        System.out.println("\n==================================================");
        System.out.println(" Execucao concluida com sucesso! ");
        System.out.println("==================================================");
    }
}
