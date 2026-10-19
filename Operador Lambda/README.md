# 🔷 Operador Lambda em Java

Projeto de estudos completo sobre **Expressões Lambda** em Java (Java 8+), com exemplos comentados e progressivos.

---

## 📁 Estrutura do Projeto

```
src/
├── App.java                  → Ponto de entrada (executa todos os exemplos)
├── FundamentosLambda.java    → Sintaxe básica, comparação com classe anônima
├── InterfacesFuncionais.java → Predicate, Function, Consumer, Supplier, ...
├── LambdaComColecoes.java    → Streams: filter, map, reduce, collect, ...
├── MethodReference.java      → Os 4 tipos de referência de método (::)
├── LambdaAvancado.java       → Composição, currying, closures, memoization
└── LambdaComparator.java     → Ordenação com lambdas e Comparator
```

---

## 🚀 Como executar

```bash
# Compilar
javac -d bin src/*.java

# Executar
java -cp bin App
```

---

## 📚 Conceitos Abordados

### 01 · Fundamentos

| Antes (Java 7)         | Com Lambda (Java 8+)          |
|------------------------|-------------------------------|
| `new Interface() { }` | `(params) -> expressão`       |
| Verboso, aninhado      | Conciso e legível             |

**Sintaxe geral:**
```java
// Sem parâmetros
() -> System.out.println("olá")

// Um parâmetro (parênteses opcionais)
x -> x * 2

// Dois parâmetros
(a, b) -> a + b

// Corpo com múltiplas linhas
(a, b) -> {
    if (b == 0) return 0;
    return a / b;
}
```

> **Regra:** a lambda deve implementar uma `@FunctionalInterface` — interface com exatamente **1 método abstrato**.

---

### 02 · Interfaces Funcionais (`java.util.function`)

| Interface           | Método                 | Uso comum                    |
|---------------------|------------------------|------------------------------|
| `Predicate<T>`      | `boolean test(T)`      | Filtros, validações          |
| `Function<T,R>`     | `R apply(T)`           | Transformações, conversões   |
| `Consumer<T>`       | `void accept(T)`       | Efeitos colaterais, impressão|
| `Supplier<T>`       | `T get()`              | Factories, valores lazy      |
| `UnaryOperator<T>`  | `T apply(T)`           | Transformação do mesmo tipo  |
| `BinaryOperator<T>` | `T apply(T,T)`         | Operações binárias           |

**Composição de Predicate:**
```java
Predicate<Integer> par      = n -> n % 2 == 0;
Predicate<Integer> positivo = n -> n > 0;

par.and(positivo)   // par E positivo
par.or(positivo)    // par OU positivo
par.negate()        // NÃO par
```

---

### 03 · Lambda com Streams

```
fonte → [filter] → [map] → [sorted] → [collect]
          ↑              Operações intermediárias (lazy)
                                                    ↑ Terminal (eager)
```

```java
List<String> resultado = lista.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());
```

---

### 04 · Method Reference (`::`)

| Tipo                       | Exemplo                     | Lambda equivalente          |
|----------------------------|-----------------------------|-----------------------------|
| Método estático            | `Integer::parseInt`         | `s -> Integer.parseInt(s)`  |
| Instância (objeto fixo)    | `System.out::println`       | `s -> System.out.println(s)`|
| Instância (por tipo)       | `String::toUpperCase`       | `s -> s.toUpperCase()`      |
| Construtor                 | `Pessoa::new`               | `nome -> new Pessoa(nome)`  |

---

### 05 · Lambda Avançado

**Currying** — aplicação parcial de funções:
```java
Function<Integer, Function<Integer, Integer>> soma = a -> b -> a + b;

Function<Integer, Integer> somarDez = soma.apply(10);
somarDez.apply(5);  // 15
somarDez.apply(20); // 30
```

**Composição com `andThen`:**
```java
Function<Integer, String> pipeline = vezesDois
    .andThen(maisCem)
    .andThen(x -> "Resultado: " + x);
// 5 → 10 → 110 → "Resultado: 110"
```

---

### 06 · Lambda com Comparator

```java
// Simples
lista.sort(Comparator.comparing(Aluno::getNota));

// Decrescente
lista.sort(Comparator.comparing(Aluno::getNota).reversed());

// Múltiplos critérios
lista.sort(Comparator.comparing(Aluno::getNota).reversed()
                     .thenComparing(Aluno::getNome));
```

---

## 💡 Dicas Rápidas

- **Effectively final**: variáveis capturadas por lambdas **não podem ser reatribuídas** após captura
- Prefira **method reference** (`::`) quando a lambda só delega para um método existente — fica mais legível
- Use **`Comparator.comparing()`** em vez de lambdas manuais para ordenação
- Lambdas são **lazy** em Streams: só executam quando uma operação terminal é chamada
