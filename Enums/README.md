# Estudos de Enums em Java

Este projeto contém um guia prático com exemplos progressivos sobre o uso de **Enums** na linguagem Java.

---

## 📂 Arquivos Criados

1. **[`DiaDaSemana.java`](src/DiaDaSemana.java)**
   - Enum básico;
   - Métodos nativos: `.name()`, `.ordinal()`, `.values()`, `valueOf()`;
   - Método auxiliar em enum simples (`isFimDeSemana()`).

2. **[`StatusPedido.java`](src/StatusPedido.java)**
   - Enum com atributos (`codigo`, `descricao`);
   - Construtor customizado (privado);
   - Métodos de busca e conversão (`porCodigo`).

3. **[`Operacao.java`](src/Operacao.java)**
   - Enums polimórficos com métodos abstratos (`calcular`);
   - Padrão *Strategy* simplificado sem necessidade de classes extras.

4. **[`Descontavel.java`](src/Descontavel.java) & [`FormaPagamento.java`](src/FormaPagamento.java)**
   - Enums implementando interfaces;
   - Cálculo dinâmico de regras de negócio.

5. **[`App.java`](src/App.java)**
   - Demonstração completa e formatada no console;
   - Comparação segura usando `==`;
   - Modern Switch Expressions (`switch-case`);
   - Coleções especializadas e otimizadas: `EnumSet` e `EnumMap`.

---

## 🚀 Como Executar

No terminal, dentro da pasta raiz do projeto:

```powershell
# Compilar os arquivos para a pasta bin
javac -d bin src/*.java

# Executar a aplicação
java -cp bin App
```
Ou simplesmente clique no botão **Run** no topo do arquivo [`App.java`](src/App.java) dentro do VS Code.
