package exemplos.mapeamento;

@Tabela(nome = "tb_produtos")
public class Produto {

    @Coluna(nome = "id_produto", chavePrimaria = true)
    private Long id;

    @Coluna(nome = "ds_titulo")
    private String titulo;

    @Coluna(nome = "vl_preco")
    private Double preco;

    // Campo sem anotação: não será salvo no banco (equivalente a @Transient)
    private String observacaoTemporaria;

    public Produto(Long id, String titulo, Double preco, String observacaoTemporaria) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.observacaoTemporaria = observacaoTemporaria;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Double getPreco() { return preco; }
    public String getObservacaoTemporaria() { return observacaoTemporaria; }
}
