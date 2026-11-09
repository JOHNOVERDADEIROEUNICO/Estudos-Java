package exemplos.execucao;

public class CalculadoraTestes {

    @TesteUnitario(descricao = "Deve somar dois números positivos com sucesso")
    public void testarSoma() {
        int resultado = 10 + 20;
        if (resultado != 30) {
            throw new AssertionError("Esperava 30, mas obteve: " + resultado);
        }
    }

    @TesteUnitario(descricao = "Deve lançar exceção ao dividir por zero")
    public void testarDivisaoPorZero() {
        try {
            int resultado = 10 / 0;
            throw new AssertionError("Deveria ter lançado ArithmeticException, mas deu " + resultado);
        } catch (ArithmeticException e) {
            // Sucesso esperado!
        }
    }

    @TesteUnitario(descricao = "Teste que falha propositalmente para demonstrar detecção de erro")
    public void testarFalhaEsperada() {
        int resultado = 2 * 2;
        if (resultado != 5) {
            throw new AssertionError("Erro proposital: 2 * 2 não é 5!");
        }
    }

    @TesteUnitario(descricao = "Teste de recurso ainda em desenvolvimento", ignorar = true)
    public void testeIgnorado() {
        System.out.println("Este método não deve ser executado porque tem ignorar=true");
    }

    // Este método NÃO tem anotação, então o TestRunner vai ignorá-lo completamente!
    public void metodoAuxiliarInterno() {
        System.out.println("Método auxiliar não anotado.");
    }
}
