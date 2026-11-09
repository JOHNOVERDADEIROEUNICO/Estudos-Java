package exemplos.validacao;

public class Usuario {

    @CampoObrigatorio(mensagem = "O nome do usuário não pode ficar em branco!")
    private String nome;

    @GarantirMaioridade(idadeMinima = 18, mensagem = "Usuário deve ser maior de idade para se cadastrar.")
    private int idade;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return "Usuario{nome='" + nome + "', idade=" + idade + "}";
    }
}
