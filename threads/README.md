# Estudos de Threads em Java

Projeto didático contendo explicações e exemplos práticos dos três principais mecanismos de concorrência e execução assíncrona em Java:

1. **Subclasse de `Thread`**
2. **Interface `Runnable`**
3. **Interface `Callable<V>` (com `Future` e `ExecutorService`)**

---

## 📁 Estrutura de Pastas

```text
threads/
├── src/
│   ├── ThreadApp.java                  # Orquestrador principal (executa todos os exemplos)
│   └── exemplos/
│       ├── ExemploThread.java          # 1. Demonstração estendendo a classe Thread
│       ├── ExemploRunnable.java        # 2. Demonstração com a interface Runnable (classe & lambda)
│       └── ExemploCallable.java        # 3. Demonstração com Callable, Future e ThreadPool
└── README.md                           # Guia de conceitos e execução
```

---

## 🧠 Resumo Teórico dos Conceitos

| Recurso | Herdar de `Thread` | Implementar `Runnable` | Implementar `Callable<V>` |
| :--- | :--- | :--- | :--- |
| **Objetivo** | Define o motor e a tarefa juntos | Define apenas a tarefa a ser executada | Define uma tarefa que computa um valor |
| **Retorno** | `void` | `void` | Retorna valor genérico (`V`) |
| **Exceções** | Não lança checked exceptions | Não lança checked exceptions | Pode lançar `throws Exception` |
| **Herança de Classes** | Bloqueada (Java não tem herança múltipla) | Livre (pode herdar de outra classe) | Livre (pode herdar de outra classe) |
| **Execução** | `thread.start()` | `new Thread(runnable).start()` | Submetido a um `ExecutorService` |
| **Lambdas / Moderno** | Não | Sim (`@FunctionalInterface`) | Sim (`@FunctionalInterface`) |

---

## 🚀 Como Executar

### Opção 1: Pelo VS Code
- Abra qualquer um dos arquivos `.java` no VS Code.
- Clique no botão **`Run`** ou **`Debug`** que aparece acima do método `main`.

### Opção 2: Pelo Terminal
Para executar o orquestrador completo:
```bash
java -cp bin ThreadApp
```

Para executar qualquer exemplo individualmente:
```bash
java -cp bin exemplos.ExemploThread
java -cp bin exemplos.ExemploRunnable
java -cp bin exemplos.ExemploCallable
```
