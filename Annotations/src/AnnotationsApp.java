import java.util.List;

import exemplos.execucao.CalculadoraTestes;
import exemplos.execucao.MiniTestRunner;
import exemplos.mapeamento.GeradorSQL;
import exemplos.mapeamento.Produto;
import exemplos.nativas.ExemploNativas;
import exemplos.validacao.Usuario;
import exemplos.validacao.Validador;

public class AnnotationsApp {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("          GUIA PRÁTICO: ANNOTATIONS & REFLECTION EM JAVA       ");
        System.out.println("===============================================================");

        // ---------------------------------------------------------------------
        // 1. ANOTAÇÕES NATIVAS (Compilador)
        // ---------------------------------------------------------------------
        ExemploNativas.executar();

        // ---------------------------------------------------------------------
        // 2. ANOTAÇÕES DE VALIDAÇÃO EM ATRIBUTOS (Baseado no seu PDF)
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. VALIDAÇÃO DINÂMICA DE ATRIBUTOS (@GarantirMaioridade e @CampoObrigatorio) ---");
        
        Usuario usuarioValido = new Usuario("Maria Silva", 22);
        testarValidacao(usuarioValido);

        Usuario usuarioMenorDeIdade = new Usuario("Carlos Santos", 16);
        testarValidacao(usuarioMenorDeIdade);

        Usuario usuarioInvalido = new Usuario("", 14);
        testarValidacao(usuarioInvalido);

        // ---------------------------------------------------------------------
        // 3. ANOTAÇÕES DE EXECUÇÃO EM MÉTODOS (Mini-JUnit)
        // ---------------------------------------------------------------------
        MiniTestRunner.executarTestes(CalculadoraTestes.class);

        // ---------------------------------------------------------------------
        // 4. ANOTAÇÕES DE MAPEAMENTO (Mini-JPA / Hibernate)
        // ---------------------------------------------------------------------
        System.out.println("\n--- 4. MAPEAMENTO DE ENTIDADE / ORM (@Tabela e @Coluna) ---");
        Produto teclado = new Produto(101L, "Teclado Mecânico RGB", 249.90, "Item em estoque promocional");
        
        String sql = GeradorSQL.gerarInsert(teclado);
        System.out.println("Objeto Java: Produto(id=101, titulo='Teclado Mecânico RGB', preco=249.90)");
        System.out.println("SQL gerado automaticamente via Reflection:");
        System.out.println("   " + sql);
        System.out.println("Obs: O campo 'observacaoTemporaria' não possui @Coluna, logo foi ignorado pelo motor!");

        System.out.println("\n===============================================================");
        System.out.println("           TODOS OS EXEMPLOS EXECUTADOS COM SUCESSO!           ");
        System.out.println("===============================================================");
    }

    private static void testarValidacao(Usuario usuario) {
        System.out.println("\nTestando objeto: " + usuario);
        List<String> erros = Validador.validar(usuario);

        if (erros.isEmpty()) {
            System.out.println("   [OK] Validação bem-sucedida! Nenhum erro encontrado.");
        } else {
            System.out.println("   [ERRO] Falha de validação:");
            for (String erro : erros) {
                System.out.println("      * " + erro);
            }
        }
    }
}
