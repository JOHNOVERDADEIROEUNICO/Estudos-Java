# 📚 Estudo: Interfaces Funcionais em Java

Projeto de estudo completo e comentado sobre **Interfaces Funcionais** em Java (Java 8+).

---

## 🗂️ Estrutura de Arquivos

```
src/
├── App.java                              ← Ponto de entrada (executa todos os exemplos)
│
└── exemplos/
    ├── basico/
    │   └── ExemploBasico.java            ← @FunctionalInterface, lambdas vs classes anônimas
    ├── builtin/
    │   └── ExemploBuiltin.java           ← Function, Consumer, Supplier, Predicate e variações
    ├── method_ref/
    │   └── ExemploMethodRef.java         ← 4 tipos de referências de método (Classe::metodo)
    ├── composicao/
    │   └── ExemploComposicao.java        ← andThen(), compose(), and(), or(), negate()
    ├── streams/
    │   └── ExemploStreams.java           ← filter, map, reduce, collect, sorted com Streams
    └── pratico/
        └── ExemploPratico.java           ← Sistema de pedidos: Strategy, validação, observers
```

---

## 📖 Tópicos Cobertos

### 1. `exemplos.basico` — O que é uma Interface Funcional?
- Anotação `@FunctionalInterface` e sua garantia de compilação
- Comparativo: **classe concreta → classe anônima → lambda**
- Métodos `default` e `static` em interfaces funcionais
- Lambdas com expressão simples vs bloco de código

### 2. `exemplos.builtin` — Interfaces do `java.util.function`

| Interface | Método | O que faz |
|---|---|---|
| `Function<T,R>` | `R apply(T t)` | Transforma T em R |
| `Consumer<T>` | `void accept(T t)` | Consome T, sem retorno |
| `Supplier<T>` | `T get()` | Produz/fornece um T |
| `Predicate<T>` | `boolean test(T t)` | Testa T, retorna true/false |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | 2 entradas → 1 saída |
| `UnaryOperator<T>` | `T apply(T t)` | Function<T,T> |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | BiFunction<T,T,T> |

### 3. `exemplos.method_ref` — Referências de Método (`Classe::metodo`)

| Tipo | Sintaxe | Equivalente lambda |
|---|---|---|
| Método estático | `Classe::metodoEstatico` | `(args) -> Classe.metodo(args)` |
| Instância específica | `objeto::metodo` | `(args) -> objeto.metodo(args)` |
| Instância arbitrária | `Classe::metodo` | `(obj, args) -> obj.metodo(args)` |
| Construtor | `Classe::new` | `(args) -> new Classe(args)` |

### 4. `exemplos.composicao` — Composição de Funções

- `Function.andThen(g)` → aplica `f` depois `g`  *(f → g)*
- `Function.compose(g)` → aplica `g` antes de `f`  *(g → f)*
- `Predicate.and()`, `.or()`, `.negate()` — combinação lógica
- `Consumer.andThen()` — encadeamento de ações

### 5. `exemplos.streams` — Streams e Coleções

- `filter(Predicate)`, `map(Function)`, `sorted(Comparator)`
- `reduce(BinaryOperator)` — agrega em um único valor
- `collect()` com `groupingBy`, `averagingDouble`
- `anyMatch`, `allMatch`, `noneMatch`, `findFirst`
- `mapToDouble()` → streams primitivos com `sum()`, `min()`, `max()`

### 6. `exemplos.pratico` — Sistema de Pedidos (Caso Real)

- ✅ **Interface funcional customizada** (`@FunctionalInterface`)
- ✅ **Padrão Strategy** via lambda (sem subclasses!)
- ✅ **Pipeline de validação** encadeado com método `default`
- ✅ **Pipeline de transformação** com `Function.andThen()`
- ✅ **Padrão Observer/Callback** com `Consumer.andThen()`

---

## ▶️ Como Executar

```bash
# Compilar
javac -encoding UTF-8 -d bin src/App.java src/exemplos/**/*.java

# Executar
java -cp bin App
```

Ou simplesmente pressione **F5** no VS Code.

---

## 💡 Regra de Ouro

> Uma interface funcional tem **exatamente 1 método abstrato**.  
> Qualquer lambda ou referência de método é uma implementação dela.
