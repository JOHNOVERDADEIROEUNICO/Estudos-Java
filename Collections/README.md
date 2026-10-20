# 📚 Java Collections Framework — Guia de Estudos

> Um guia prático e completo sobre as principais coleções do Java, com exemplos comentados e prontos para executar.

---

## 📁 Estrutura do Projeto

```
Collections/
├── src/
│   ├── App.java                    ← Ponto de entrada + demonstração rápida
│   ├── ExemplosListArrayList.java  ← ArrayList (List)
│   ├── ExemplosListLinkedList.java ← LinkedList (List + Queue + Deque)
│   ├── ExemplosSet.java            ← HashSet, LinkedHashSet, TreeSet
│   ├── ExemplosQueue.java          ← Queue, PriorityQueue, ArrayDeque
│   ├── ExemplosMap.java            ← HashMap, LinkedHashMap, TreeMap
│   ├── ExemplosCollectionsUtil.java← Classe utilitária Collections
│   └── ExemplosIteradores.java     ← Iterator, ListIterator, Stream API
└── lib/
```

---

## 🗺️ Hierarquia das Collections

```
Iterable
└── Collection
    ├── List           → ordenada, permite duplicatas
    │   ├── ArrayList
    │   └── LinkedList
    ├── Set            → sem duplicatas
    │   ├── HashSet
    │   ├── LinkedHashSet
    │   └── TreeSet
    └── Queue          → fila FIFO
        ├── LinkedList
        ├── PriorityQueue
        └── Deque
            ├── ArrayDeque
            └── LinkedList

Map (não estende Collection)
├── HashMap
├── LinkedHashMap
└── TreeMap
```

---

## 📋 Resumo Rápido — Quando usar cada um?

| Estrutura | Quando usar |
|-----------|-------------|
| `ArrayList` | Acesso por índice frequente, iteração sequencial |
| `LinkedList` | Muitas inserções/remoções no início ou fim |
| `HashSet` | Unicidade sem importar ordem, máxima performance |
| `LinkedHashSet` | Unicidade mantendo ordem de inserção |
| `TreeSet` | Unicidade com elementos sempre ordenados |
| `PriorityQueue` | Fila onde o menor (ou maior) elemento sai primeiro |
| `ArrayDeque` | Pilha (LIFO) ou fila dupla de alta performance |
| `HashMap` | Chave-valor, sem ordem, máxima performance |
| `LinkedHashMap` | Chave-valor mantendo ordem de inserção |
| `TreeMap` | Chave-valor com chaves sempre ordenadas |

---

## 🔑 Comparativo de Performance

| Operação | ArrayList | LinkedList | HashSet | TreeSet | HashMap | TreeMap |
|----------|-----------|------------|---------|---------|---------|---------|
| Acesso por índice | **O(1)** | O(n) | — | — | — | — |
| Busca (contains) | O(n) | O(n) | **O(1)** | O(log n) | **O(1)** | O(log n) |
| Inserção no fim | **O(1)*** | **O(1)** | **O(1)** | O(log n) | **O(1)** | O(log n) |
| Inserção no início | O(n) | **O(1)** | — | — | — | — |
| Remoção | O(n) | O(1)** | **O(1)** | O(log n) | **O(1)** | O(log n) |

> \* Amortizado — pode ser O(n) ao redimensionar o array interno  
> \*\* O(1) apenas para início/fim

---

## 📖 Arquivos de Exemplo

### [`App.java`](src/App.java)
Ponto de entrada do projeto. Contém a hierarquia comentada e uma demonstração rápida de todas as coleções.

### [`ExemplosListArrayList.java`](src/ExemplosListArrayList.java)
- Criação e adição de elementos (`add`, `addAll`)
- Acesso por índice (`get`, `set`, `indexOf`)
- Remoção (`remove`, `removeIf`, `clear`)
- Sublistas (`subList`)
- Ordenação (`Collections.sort`, `List.sort`, lambdas)
- `List.of()` vs `Arrays.asList()` vs `new ArrayList()`

### [`ExemplosListLinkedList.java`](src/ExemplosListLinkedList.java)
- LinkedList como `List`
- LinkedList como `Queue` (FIFO)
- LinkedList como `Stack` (LIFO)
- LinkedList como `Deque` (fila dupla)
- Comparativo ArrayList vs LinkedList

### [`ExemplosSet.java`](src/ExemplosSet.java)
- `HashSet` — sem ordem, máxima performance
- `LinkedHashSet` — ordem de inserção preservada
- `TreeSet` — ordenado, métodos `first`, `last`, `floor`, `ceiling`, `headSet`, `tailSet`
- Operações matemáticas de conjunto: **união ∪**, **interseção ∩**, **diferença −**
- Importância de `hashCode()` e `equals()` em objetos customizados

### [`ExemplosQueue.java`](src/ExemplosQueue.java)
- `Queue` básica com `LinkedList` (FIFO)
- `PriorityQueue` — min-heap, max-heap, com Comparator
- `ArrayDeque` como Deque
- `ArrayDeque` como Stack (preferível a `java.util.Stack`)
- Simulação de fila de atendimento
- Verificação de parênteses balanceados (uso prático de pilha)

### [`ExemplosMap.java`](src/ExemplosMap.java)
- `HashMap` — chave-valor, sem ordem
- `LinkedHashMap` — ordem de inserção
- `TreeMap` — chaves ordenadas, `firstKey`, `lastKey`, `headMap`, `tailMap`
- Formas de iterar: `entrySet`, `keySet`, `values`, `forEach`
- Métodos avançados Java 8+: `putIfAbsent`, `merge`, `compute`, `computeIfAbsent`, `replaceAll`
- Map aninhado (mapa de mapas)

### [`ExemplosCollectionsUtil.java`](src/ExemplosCollectionsUtil.java)
- Ordenação: `sort`, `reverseOrder`, `shuffle`, `reverse`, `rotate`, `swap`
- Busca: `binarySearch`
- Mínimo e máximo: `min`, `max`
- Preenchimento: `fill`, `nCopies`, `copy`
- Frequência: `frequency`, `disjoint`
- Coleções imutáveis: `unmodifiableList`, `singletonList`, `emptyList`
- Coleções sincronizadas (thread-safe)

### [`ExemplosIteradores.java`](src/ExemplosIteradores.java)
- `for` clássico
- `for-each` (enhanced for)
- `Iterator` — remoção segura durante iteração
- `ListIterator` — iteração bidirecional
- `forEach` com lambda e method reference
- **Stream API**: `filter`, `map`, `reduce`, `collect`, `count`, `min`, `max`, `anyMatch`
- Erro comum: `ConcurrentModificationException` e como evitá-lo

---

## ▶️ Como executar

1. Abra a pasta no **VS Code** com a extensão **Java Extension Pack** instalada
2. Abra qualquer arquivo `.java`
3. Clique em **▶ Run** no topo do editor ou clique com o botão direito → `Run Java`
4. Cada arquivo tem seu próprio `main()` e pode ser executado de forma independente

---

## 💡 Dicas importantes

- **`Collection` vs `Collections`**: `Collection` é uma interface; `Collections` é a classe utilitária com métodos estáticos.
- **`equals()` e `hashCode()`**: sempre sobrescreva os dois em objetos usados em `Set` ou como chave de `Map`.
- **`List.of()` vs `new ArrayList()`**: `List.of()` é imutável; use `new ArrayList<>(List.of(...))` para uma lista mutável.
- **`ArrayDeque` vs `Stack`**: prefira `ArrayDeque` para pilhas — `java.util.Stack` é legado e sincronizado desnecessariamente.
- **Remover durante iteração**: use `Iterator.remove()` ou `removeIf()` — nunca remova dentro de um `for-each` direto.
