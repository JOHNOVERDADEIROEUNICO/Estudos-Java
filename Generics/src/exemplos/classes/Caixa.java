package exemplos.classes;

/**
 * Exemplo 1: Classe Genérica simples com um único parâmetro de tipo (T).
 * 
 * T representa "Type" (Tipo). Na instanciação, T será substituído
 * por um tipo concreto de objeto (ex: String, Integer, Produto).
 * 
 * Vantagens:
 * 1. Type Safety: Não permite guardar objetos de tipo incompatível.
 * 2. Sem necessidade de casting: getConteudo() já retorna o tipo exato T.
 */
public class Caixa<T> {
    private T conteudo;

    public Caixa() {}

    public Caixa(T conteudo) {
        this.conteudo = conteudo;
    }

    public T getConteudo() {
        return conteudo;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }

    public boolean estaVazia() {
        return this.conteudo == null;
    }

    @Override
    public String toString() {
        return "Caixa contendo: [" + conteudo + "]";
    }
}
