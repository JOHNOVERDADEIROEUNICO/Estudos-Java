import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ============================================================
 *  COLLECTIONS - MAP
 * ============================================================
 *
 * O que é um Map?
 *   → Estrutura de dados que armazena pares CHAVE → VALOR.
 *   → Cada chave é ÚNICA (sem duplicatas de chaves).
 *   → Um valor pode ser associado a múltiplas chaves.
 *   → NÃO estende Collection, mas faz parte do JCF.
 *
 * Analogia do mundo real:
 *   Dicionário: palavra (chave) → definição (valor)
 *   Agenda:     nome   (chave) → telefone  (valor)
 *   CPF:        cpf    (chave) → pessoa    (valor)
 *
 * Principais implementações:
 *
 *   ┌──────────────────┬───────────────────┬──────────────┐
 *   │  Implementação   │      Ordem        │  Performance │
 *   ├──────────────────┼───────────────────┼──────────────┤
 *   │  HashMap         │  Nenhuma          │  O(1) ✔✔✔   │
 *   │  LinkedHashMap   │  Inserção         │  O(1) ✔✔    │
 *   │  TreeMap         │  Chaves ordenadas │  O(log n) ✔  │
 *   └──────────────────┴───────────────────┴──────────────┘
 *
 * IMPORTANTE: Assim como Set, Map usa hashCode() + equals()
 * das CHAVES. Sempre sobrescreva esses métodos em chaves customizadas!
 * ============================================================
 */
public class ExemplosMap {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          EXEMPLOS: Map               ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploHashMap();
        exemploLinkedHashMap();
        exemploTreeMap();
        exemploIteracaoMap();
        exemploMetodosAvancados();
        exemploMapAninhado();
    }

    // ─── 1. HASHMAP ───────────────────────────────────────────
    static void exemploHashMap() {
        System.out.println("--- 1. HashMap ---");
        System.out.println("    → Chave-valor, sem ordem garantida, O(1)\n");

        Map<String, Integer> idades = new HashMap<>();

        // put(chave, valor) → adiciona ou substitui
        idades.put("Alice", 30);
        idades.put("Bruno", 25);
        idades.put("Carlos", 35);
        idades.put("Alice", 31); // ← chave duplicada! sobrescreve o valor

        System.out.println("HashMap: " + idades);
        System.out.println("Tamanho: " + idades.size()); // 3, não 4

        // get(chave) → retorna o valor (ou null se não existir)
        System.out.println("Idade de Alice: " + idades.get("Alice")); // 31
        System.out.println("Idade de Diana: " + idades.get("Diana")); // null

        // getOrDefault(chave, valorPadrao) → valor padrão se não existir
        System.out.println("getOrDefault Diana: " + idades.getOrDefault("Diana", 0)); // 0

        // containsKey / containsValue
        System.out.println("containsKey('Bruno'): " + idades.containsKey("Bruno")); // true
        System.out.println("containsValue(99): " + idades.containsValue(99));       // false

        // remove(chave)
        idades.remove("Carlos");
        System.out.println("Após remove('Carlos'): " + idades);

        System.out.println();
    }

    // ─── 2. LINKEDHASHMAP ────────────────────────────────────
    static void exemploLinkedHashMap() {
        System.out.println("--- 2. LinkedHashMap ---");
        System.out.println("    → Mantém ORDEM DE INSERÇÃO das chaves\n");

        Map<String, String> capitais = new LinkedHashMap<>();
        capitais.put("Brasil", "Brasília");
        capitais.put("Argentina", "Buenos Aires");
        capitais.put("Chile", "Santiago");
        capitais.put("Peru", "Lima");

        // Ordem de inserção é preservada!
        System.out.println("LinkedHashMap (ordem de inserção):");
        capitais.forEach((pais, capital) ->
            System.out.println("  " + pais + " → " + capital)
        );

        System.out.println();
    }

    // ─── 3. TREEMAP ──────────────────────────────────────────
    static void exemploTreeMap() {
        System.out.println("--- 3. TreeMap ---");
        System.out.println("    → Chaves ordenadas em ORDEM NATURAL\n");

        TreeMap<String, Integer> placar = new TreeMap<>();
        placar.put("Carlos", 85);
        placar.put("Ana", 92);
        placar.put("Bruno", 78);
        placar.put("Diana", 95);

        // Chaves sempre em ordem alfabética!
        System.out.println("TreeMap (ordem alfabética das chaves): " + placar);

        // Métodos exclusivos do TreeMap (NavigableMap)
        System.out.println("firstKey():  " + placar.firstKey());  // Ana
        System.out.println("lastKey():   " + placar.lastKey());   // Diana
        System.out.println("floorKey('C') (≤'C'): " + placar.floorKey("C")); // Carlos
        System.out.println("ceilingKey('C')(≥'C'): " + placar.ceilingKey("C")); // Carlos

        // headMap / tailMap
        System.out.println("headMap('C') [< 'C']: " + placar.headMap("C")); // {Ana, Bruno}
        System.out.println("tailMap('C') [≥ 'C']: " + placar.tailMap("C")); // {Carlos, Diana}

        // TreeMap com Comparator: ordenar por valor decrescente
        System.out.println("\nTreeMap ordenado por pontuação (decrescente):");
        // Comparator que ordena por valor do mapa (pontuação) decrescente,
        // com desempate alfabético crescente
        Comparator<String> porPts = (a, b) -> {
            int cmp = Integer.compare(placar.get(b), placar.get(a)); // decrescente
            return cmp != 0 ? cmp : a.compareTo(b); // desempate alfabético
        };
        TreeMap<String, Integer> porPontuacao = new TreeMap<>(porPts);
        porPontuacao.putAll(placar);
        porPontuacao.forEach((nome, pts) ->
            System.out.println("  " + nome + ": " + pts)
        );

        System.out.println();
    }

    // ─── 4. ITERAÇÃO SOBRE MAP ───────────────────────────────
    static void exemploIteracaoMap() {
        System.out.println("--- 4. Formas de iterar sobre um Map ---");

        Map<String, Integer> estoque = new HashMap<>();
        estoque.put("Camisa", 50);
        estoque.put("Calça", 30);
        estoque.put("Tênis", 15);

        // Forma 1: entrySet() → par chave-valor (mais eficiente para acessar ambos)
        System.out.println("Forma 1: entrySet()");
        for (Map.Entry<String, Integer> entry : estoque.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue() + " unidades");
        }

        // Forma 2: keySet() → apenas as chaves
        System.out.println("\nForma 2: keySet()");
        for (String produto : estoque.keySet()) {
            System.out.println("  Produto: " + produto);
        }

        // Forma 3: values() → apenas os valores
        System.out.println("\nForma 3: values()");
        int total = 0;
        for (int qtd : estoque.values()) {
            total += qtd;
        }
        System.out.println("  Total em estoque: " + total);

        // Forma 4: forEach com lambda (Java 8+) ← mais concisa e moderna
        System.out.println("\nForma 4: forEach (lambda)");
        estoque.forEach((produto, qtd) ->
            System.out.printf("  %-10s → %d un.%n", produto, qtd)
        );

        System.out.println();
    }

    // ─── 5. MÉTODOS AVANÇADOS (Java 8+) ──────────────────────
    static void exemploMetodosAvancados() {
        System.out.println("--- 5. Métodos avançados do Map (Java 8+) ---");

        Map<String, Integer> votos = new HashMap<>();
        votos.put("Opção A", 10);
        votos.put("Opção B", 5);

        // putIfAbsent → só adiciona se a chave NÃO existir
        votos.putIfAbsent("Opção A", 99); // não muda! já existe
        votos.putIfAbsent("Opção C", 7);  // adiciona!
        System.out.println("putIfAbsent: " + votos);

        // merge → combina valor existente com novo valor
        // Útil para contagem/acumulação!
        votos.merge("Opção A", 5, Integer::sum); // 10 + 5 = 15
        votos.merge("Opção D", 3, Integer::sum); // chave nova → só insere 3
        System.out.println("merge: " + votos);

        // compute → calcula novo valor baseado na chave e valor atual
        votos.compute("Opção B", (chave, valorAtual) ->
            valorAtual == null ? 1 : valorAtual * 2 // dobra o valor
        );
        System.out.println("compute (dobrar Opção B): " + votos);

        // computeIfAbsent → calcula e insere apenas se a chave não existir
        votos.computeIfAbsent("Opção E", chave -> chave.length()); // tamanho da string
        System.out.println("computeIfAbsent (Opção E): " + votos);

        // replaceAll → aplica função a todos os valores
        Map<String, Integer> precos = new HashMap<>();
        precos.put("Camisa", 50);
        precos.put("Calça", 80);
        precos.put("Tênis", 120);
        precos.replaceAll((produto, preco) -> (int)(preco * 1.10)); // reajuste de 10%
        System.out.println("replaceAll (reajuste 10%): " + precos);

        System.out.println();
    }

    // ─── 6. MAP ANINHADO (mapa de mapas) ─────────────────────
    static void exemploMapAninhado() {
        System.out.println("--- 6. Map aninhado (mapa de mapas) ---");
        System.out.println("    Exemplo: Notas de alunos por disciplina\n");

        // Map<Aluno, Map<Disciplina, Nota>>
        Map<String, Map<String, Double>> notas = new LinkedHashMap<>();

        // Adicionando notas de Ana
        Map<String, Double> notasAna = new LinkedHashMap<>();
        notasAna.put("Matemática", 9.5);
        notasAna.put("Português", 8.0);
        notasAna.put("História", 7.5);
        notas.put("Ana", notasAna);

        // Adicionando notas de Bruno
        Map<String, Double> notasBruno = new LinkedHashMap<>();
        notasBruno.put("Matemática", 6.0);
        notasBruno.put("Português", 8.5);
        notasBruno.put("História", 9.0);
        notas.put("Bruno", notasBruno);

        // Exibindo boletim
        notas.forEach((aluno, matérias) -> {
            System.out.println("Aluno: " + aluno);
            matérias.forEach((disc, nota) ->
                System.out.printf("  %-15s → %.1f%n", disc, nota)
            );
            double media = matérias.values().stream()
                .mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.printf("  Média geral: %.2f%n%n", media);
        });
    }
}
