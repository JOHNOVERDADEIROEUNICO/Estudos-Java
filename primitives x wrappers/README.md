# Guia de Estudos: Tipos Primitivos x Classes Wrapper em Java

Este projeto contém uma demonstração prática, modular e detalhada sobre o funcionamento e uso de **Classes Wrapper** no Java.

---

## 📌 O que são Classes Wrapper?

Em Java, os tipos primitivos (`int`, `double`, `boolean`, etc.) não são objetos — eles armazenam valores diretamente na memória Stack para maximizar o desempenho.

No entanto, em muitas situações da programação orientada a objetos (como coleções, generics e frameworks de banco de dados), precisamos tratar esses valores como **objetos**. É aí que entram as **Classes Wrapper** (*embrulhadores*), que encapsulam o dado primitivo dentro de um objeto na memória Heap.

### Tabela de Correspondência

| Tipo Primitivo | Classe Wrapper | Tamanho | Valor Padrão |
| :--- | :--- | :--- | :--- |
| `byte` | `java.lang.Byte` | 8 bits | `0` |
| `short` | `java.lang.Short` | 16 bits | `0` |
| `int` | `java.lang.Integer` | 32 bits | `0` |
| `long` | `java.lang.Long` | 64 bits | `0L` |
| `float` | `java.lang.Float` | 32 bits | `0.0f` |
| `double` | `java.lang.Double` | 64 bits | `0.0d` |
| `char` | `java.lang.Character` | 16 bits | `'\u0000'` |
| `boolean` | `java.lang.Boolean` | 1 bit | `false` |

> 💡 **Nota:** Wrappers são classes imutáveis e herdam de `Object` (os numéricos também herdam de `java.lang.Number`). O valor padrão de qualquer variável Wrapper é `null`.

---

## 🗂 Estrutura do Projeto

Os exemplos estão divididos em tópicos didáticos dentro de `src/exemplos/`:

- [App.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/App.java): Ponto de entrada que orquestra e executa todos os exemplos.
- [ConceitoEEquivalencia.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/exemplos/ConceitoEEquivalencia.java): Tabela de tipos e a diferença fundamental da aceitação de `null`.
- [AutoboxingUnboxing.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/exemplos/AutoboxingUnboxing.java): Empacotamento/desempacotamento automático e o perigo do `NullPointerException`.
- [MetodosUtilitarios.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/exemplos/MetodosUtilitarios.java): Parsing (`parseInt`, `valueOf`), limites (`MAX_VALUE`), conversão de bases e métodos de `Character`.
- [ColecoesGenerics.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/exemplos/ColecoesGenerics.java): Por que coleções como `List<Integer>` e `Map<String, Double>` exigem Wrappers.
- [PegadinhasEPerformance.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/primitives%20x%20wrappers/src/exemplos/PegadinhasEPerformance.java): A armadilha do operador `==` vs `.equals()`, o *Integer Cache* (-128 a 127) e benchmark de performance em loops.

---

## 🚀 Como Executar

No terminal, a partir da raiz da pasta `primitives x wrappers`:

```powershell
# Compilar os arquivos:
javac -d bin src/exemplos/*.java src/App.java

# Executar a aplicação:
java -cp bin App
```
