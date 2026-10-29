# Estudos de Generics em Java

Guia prático e objetivo sobre o funcionamento de **Generics** em Java.

---

## 1. O que é Generics e por que usar?

Introduzido no Java 5, **Generics** permite parametrizar tipos em classes, interfaces e métodos. Isso significa que você pode especificar o tipo de dado com o qual uma classe ou método vai operar no momento da sua instanciação ou chamada.

### Benefícios:
1. **Type Safety (Segurança de Tipos em Tempo de Compilação):** Erros de tipo são detectados na compilação, e não em execução (`ClassCastException`).
2. **Eliminação de Casts:** Não é necessário fazer casting manual como `(String) objeto`.
3. **Reutilização de Código:** O mesmo algoritmo/classe funciona com múltiplos tipos com total segurança.

---

## 2. Convenção de Nomenclatura

Por convenção, os parâmetros de tipo usam letras maiúsculas de caractere único:
- `T` - Type (Tipo genérico)
- `E` - Element (usado extensivamente pela Java Collections Framework)
- `K` - Key (Chave, comum em Maps)
- `V` - Value (Valor, comum em Maps)
- `N` - Number (Números)
- `S`, `U`, `V` - 2º, 3º e 4º tipos adicionais

---

## 3. Conceitos Fundamentais

### 3.1. Classes Genéricas
Permitem criar estruturas que guardam ou manipulam qualquer tipo de dado:
```java
public class Caixa<T> {
    private T conteudo;

    public void setConteudo(T conteudo) { this.conteudo = conteudo; }
    public T getConteudo() { return conteudo; }
}

// Uso:
Caixa<String> caixaTexto = new Caixa<>();
caixaTexto.setConteudo("Olá");
String texto = caixaTexto.getConteudo(); // Sem casting!
```

Também é possível usar mais de um tipo:
```java
public class Par<K, V> {
    private K chave;
    private V valor;
    // ...
}
```

---

### 3.2. Métodos Genéricos
Um método pode declarar seus próprios parâmetros de tipo independentemente da classe ser genérica ou não. A declaração do tipo genérico fica **antes do tipo de retorno**:

```java
public static <T> void imprimirArray(T[] array) {
    for (T elemento : array) {
        System.out.println(elemento);
    }
}
```

---

### 3.3. Bounded Types (Tipos Delimitados / Restritos)
Quando você quer limitar quais tipos podem ser usados:

```java
// T só pode ser Number ou qualquer subclasse de Number (Integer, Double, etc.)
public class Calculadora<T extends Number> {
    private T valor;
    
    public double aoQuadrado() {
        return valor.doubleValue() * valor.doubleValue();
    }
}
```

---

### 3.4. Wildcards (Curingas `?`) e a regra PECS

O caractere `?` representa um tipo desconhecido em Generics.

| Tipo de Wildcard | Sintaxe | Quando usar? | Ação permitida |
| :--- | :--- | :--- | :--- |
| **Unbounded** | `List<?>` | Quando o tipo não importa | Leitura como `Object` |
| **Upper Bounded** | `List<? extends Number>` | **Producer** (dados saem da lista) | Leitura segura de `Number` |
| **Lower Bounded** | `List<? super Integer>` | **Consumer** (dados entram na lista) | Inserção segura de `Integer` |

> 💡 **Regra PECS:** **P**roducer **E**xtends, **C**onsumer **S**uper
> - Se você só precisa **ler** itens de uma coleção genérica, use `? extends T`.
> - Se você só precisa **escrever/adicionar** itens em uma coleção genérica, use `? super T`.
> - Se precisar de ambos, não use wildcard; use o tipo exato `<T>`.

---

### 3.5. Type Erasure (Apagamento de Tipos)
Generics foi implementado para manter compatibilidade com versões antigas do Java. Em tempo de compilação:
- O compilador verifica todos os tipos para garantir o type safety.
- Em seguida, os parâmetros de tipo são **apagados** e substituídos por `Object` (ou pelo limite superior, no caso de `extends`).
- Os casts apropriados são inseridos automaticamente no bytecode gerado.
- *Consequência:* Em tempo de execução (runtime), `List<String>` e `List<Integer>` são exatamente o mesmo tipo bruto (`List`).

---

## 4. Estrutura dos Arquivos Criados

```text
Generics/
├── README.md                                 # Este guia conceitual
└── src/
    ├── App.java                              # Executável demonstrando todos os conceitos
    └── exemplos/
        ├── classes/
        │   ├── Caixa.java                    # Classe genérica simples <T>
        │   └── Par.java                      # Classe genérica com 2 tipos <K, V>
        ├── metodos/
        │   └── UtilitarioArray.java          # Métodos genéricos utilitários <T>
        ├── delimitados/
        │   └── CalculadoraNumerica.java      # Bounded Type Parameters (<T extends Number>)
        └── wildcards/
            └── ExemploWildcards.java         # Unbounded, Upper Bounded e Lower Bounded (PECS)
```

---

## 5. Como Executar

No terminal, dentro da pasta do projeto:

```powershell
# Execução direta com o Java:
java -cp src src/App.java
```

Ou no VS Code, basta abrir o arquivo [App.java](file:///c:/Desktop/AREA%20DE%20TRABALHO/Estudos%20Java/Estudos-Java/Generics/src/App.java) e clicar no botão **Run** / **Executar**.
