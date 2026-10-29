package modelo;

/**
 * Classe modelo usada nos exemplos da Stream API.
 */
public class Pessoa {
    private String nome;
    private int idade;
    private String cidade;
    private double salario;

    public Pessoa(String nome, int idade, String cidade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.salario = salario;
    }

    // Getters
    public String getNome()   { return nome; }
    public int getIdade()     { return idade; }
    public String getCidade() { return cidade; }
    public double getSalario(){ return salario; }

    @Override
    public String toString() {
        return nome + " (idade=" + idade + ", cidade=" + cidade + ", salário=R$" + String.format("%.2f", salario) + ")";
    }
}
