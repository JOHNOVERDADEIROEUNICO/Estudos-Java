# Estudos de Annotations (Anotações) em Java ☕

Este projeto foi estruturado para demonstrar na prática os conceitos fundamentais de **Annotations** e **Reflection** em Java, cobrindo exatamente o que está descrito no seu material de estudos.

---

## 📌 O que são Anotações?
Anotações são **metadados** (dados que descrevem outros dados ou partes do programa). Elas não alteram a lógica direta de execução por si sós; funcionam como "etiquetas" informativas lidas por terceiros:
1. **Pelo Compilador**: Para checar erros (`@Override`) ou avisos (`@Deprecated`).
2. **Em Tempo de Compilação**: Processadores que geram código auxiliar (ex: Lombok).
3. **Em Tempo de Execução (Runtime)**: Frameworks (Spring, JPA/Hibernate, Jackson, JUnit) através da API de **Reflection**.

---

## 📂 Estrutura dos Exemplos Práticos

```
src/
├── App.java                               # Ponto de entrada que executa todos os exemplos
├── exemplos/
│   ├── nativas/
│   │   └── ExemploNativas.java            # @Override, @Deprecated e @SuppressWarnings
│   ├── validacao/
│   │   ├── GarantirMaioridade.java        # Anotação em FIELD com parâmetro (do seu PDF)
│   │   ├── CampoObrigatorio.java          # Anotação em FIELD para campos nulos/vazios
│   │   ├── Usuario.java                   # Classe anotada
│   │   └── Validador.java                 # Motor com Reflection que lê e valida os campos
│   ├── execucao/
│   │   ├── TesteUnitario.java             # Anotação em METHOD (estilo JUnit)
│   │   ├── CalculadoraTestes.java         # Métodos marcados para execução dinâmica
│   │   └── MiniTestRunner.java            # Executa dinamicamente métodos anotados
│   └── mapeamento/
│       ├── Tabela.java                    # Anotação em TYPE (estilo @Table do JPA)
│       ├── Coluna.java                    # Anotação em FIELD (estilo @Column do JPA)
│       ├── Produto.java                   # Entidade anotada
│       └── GeradorSQL.java                # Inspeciona a classe e gera queries SQL automáticas
```

---

## 🚀 Como Executar

No terminal, na pasta raiz do projeto, execute:

```powershell
java -cp src src/App.java
```

Ou no VS Code, basta abrir o arquivo `src/App.java` e clicar no botão **Run** / **Play** no canto superior direito.

---

## 🧠 Resumo dos Conceitos-Chave Utilizados

### Meta-Anotações Essenciais
* `@Target(ElementType.xxx)`: Especifica onde a anotação pode ser usada:
  * `ElementType.TYPE`: Classes e interfaces (ex: `@Tabela`)
  * `ElementType.FIELD`: Atributos e variáveis (ex: `@GarantirMaioridade`, `@Coluna`)
  * `ElementType.METHOD`: Métodos (ex: `@TesteUnitario`, `@Override`)
* `@Retention(RetentionPolicy.xxx)`: Até quando a anotação sobrevive:
  * `RetentionPolicy.SOURCE`: Descartada pelo compilador.
  * `RetentionPolicy.CLASS`: Gravada no `.class`, mas ignorada pela JVM em runtime.
  * `RetentionPolicy.RUNTIME`: Fica disponível durante a execução para ser lida via **Reflection**.
