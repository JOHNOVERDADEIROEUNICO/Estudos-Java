import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ============================================================
 *  COLLECTIONS - SET
 * ============================================================
 *
 * O que é um Set?
 *   → Interface que representa um conjunto de elementos
 *     SEM DUPLICATAS. Equivale ao conceito matemático de conjunto.
 *   → Não garante ordem de acesso (depende da implementação).
 *   → Não tem índice (não existe get(i)).
 *
 * Principais implementações:
 *
 *   ┌──────────────────┬───────────────────┬──────────────┐
 *   │  Implementação   │      Ordem        │  Performance │
 *   ├──────────────────┼───────────────────┼──────────────┤
 *   │  HashSet         │  Nenhuma          │  O(1) ✔✔✔   │
 *   │  LinkedHashSet   │  Inserção         │  O(1) ✔✔    │
 *   │  TreeSet         │  Ordenada/Natural │  O(log n) ✔  │
 *   └──────────────────┴───────────────────┴──────────────┘
 *
 * Como o Set garante unicidade?
 *   → Usa hashCode() + equals() para verificar duplicatas.
 *   → IMPORTANTE: Para objetos personalizados, SEMPRE sobrescreva
 *     hashCode() e equals() ao usar em Sets/Maps!
 * ============================================================
 */
public class ExemplosSet {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          EXEMPLOS: Set               ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        exemploHashSet();
        exemploLinkedHashSet();
        exemploTreeSet();
        exemploOperacoesConjunto();
        exemploObjectosCustomizados();
    }

    // ─── 1. HASHSET ───────────────────────────────────────────
    static void exemploHashSet() {
        System.out.println("--- 1. HashSet ---");
        System.out.println("    → SEM ordem garantida, O(1) para add/remove/contains\n");

        Set<String> hashSet = new HashSet<>();
        hashSet.add("Banana");
        hashSet.add("Maçã");
        hashSet.add("Laranja");
        hashSet.add("Banana"); // ← duplicata! será ignorada
        hashSet.add("Uva");

        System.out.println("HashSet: " + hashSet); // ordem não garantida!
        System.out.println("Tamanho: " + hashSet.size()); // 4 (não 5!)

        // contains → muito eficiente no HashSet (O(1))
        System.out.println("contains('Maçã'): " + hashSet.contains("Maçã"));
        System.out.println("contains('Manga'): " + hashSet.contains("Manga"));

        // remove
        hashSet.remove("Uva");
        System.out.println("Após remove('Uva'): " + hashSet);

        // iteração (ordem não previsível)
        System.out.print("Iteração: ");
        for (String fruta : hashSet) {
            System.out.print(fruta + " ");
        }
        System.out.println("\n");
    }

    // ─── 2. LINKEDHASHSET ────────────────────────────────────
    static void exemploLinkedHashSet() {
        System.out.println("--- 2. LinkedHashSet ---");
        System.out.println("    → Mantém ORDEM DE INSERÇÃO, levemente mais lento que HashSet\n");

        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Cachorro");
        linkedHashSet.add("Gato");
        linkedHashSet.add("Peixe");
        linkedHashSet.add("Gato"); // duplicata ignorada
        linkedHashSet.add("Hamster");

        // Diferente do HashSet, a ordem de inserção é preservada!
        System.out.println("LinkedHashSet: " + linkedHashSet);
        System.out.println("(note que a ordem de inserção é mantida!)\n");
    }

    // ─── 3. TREESET ──────────────────────────────────────────
    static void exemploTreeSet() {
        System.out.println("--- 3. TreeSet ---");
        System.out.println("    → Mantém ORDEM NATURAL (ou por Comparator)\n");

        TreeSet<Integer> numeros = new TreeSet<>();
        numeros.add(50);
        numeros.add(10);
        numeros.add(80);
        numeros.add(30);
        numeros.add(10); // duplicata ignorada

        System.out.println("TreeSet<Integer>: " + numeros); // [10, 30, 50, 80]

        // Métodos exclusivos do TreeSet (NavigableSet)
        System.out.println("first() (menor): " + numeros.first());  // 10
        System.out.println("last()  (maior): " + numeros.last());   // 80
        System.out.println("floor(35) (≤35): " + numeros.floor(35));  // 30
        System.out.println("ceiling(35)(≥35): " + numeros.ceiling(35)); // 50
        System.out.println("lower(50) (<50): " + numeros.lower(50));  // 30
        System.out.println("higher(50)(>50): " + numeros.higher(50)); // 80

        // headSet / tailSet / subSet
        System.out.println("headSet(50) [<50]: " + numeros.headSet(50));      // [10, 30]
        System.out.println("tailSet(30) [≥30]: " + numeros.tailSet(30));      // [30, 50, 80]
        System.out.println("subSet(10,80)[10..79]: " + numeros.subSet(10, 80)); // [10, 30, 50]

        // TreeSet com String → ordem alfabética
        TreeSet<String> palavras = new TreeSet<>();
        palavras.add("banana"); palavras.add("abacaxi"); palavras.add("caju");
        System.out.println("TreeSet<String>: " + palavras); // [abacaxi, banana, caju]

        // TreeSet com Comparator personalizado
        TreeSet<String> porTamanho = new TreeSet<>(
            (a, b) -> a.length() != b.length()
                ? Integer.compare(a.length(), b.length())
                : a.compareTo(b) // desempate alfabético
        );
        porTamanho.add("banana"); porTamanho.add("abacaxi"); porTamanho.add("caju");
        System.out.println("TreeSet por tamanho: " + porTamanho);

        System.out.println();
    }

    // ─── 4. OPERAÇÕES DE CONJUNTO ────────────────────────────
    static void exemploOperacoesConjunto() {
        System.out.println("--- 4. Operações matemáticas de conjunto ---");

        Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6, 7));

        // UNIÃO: A ∪ B → todos os elementos de A e B
        Set<Integer> uniao = new HashSet<>(a);
        uniao.addAll(b);
        System.out.println("A:     " + new TreeSet<>(a));
        System.out.println("B:     " + new TreeSet<>(b));
        System.out.println("A ∪ B: " + new TreeSet<>(uniao));

        // INTERSEÇÃO: A ∩ B → apenas elementos em comum
        Set<Integer> intersecao = new HashSet<>(a);
        intersecao.retainAll(b);
        System.out.println("A ∩ B: " + new TreeSet<>(intersecao));

        // DIFERENÇA: A - B → elementos de A que não estão em B
        Set<Integer> diferenca = new HashSet<>(a);
        diferenca.removeAll(b);
        System.out.println("A - B: " + new TreeSet<>(diferenca));

        // containsAll → verifica subconjunto (A ⊆ B?)
        Set<Integer> sub = Set.of(1, 2);
        System.out.println("{1,2} ⊆ A? " + a.containsAll(sub));
        System.out.println("{1,2} ⊆ B? " + b.containsAll(sub));

        System.out.println();
    }

    // ─── 5. OBJETOS CUSTOMIZADOS ─────────────────────────────
    static void exemploObjectosCustomizados() {
        System.out.println("--- 5. Objetos customizados em Set ---");
        System.out.println("    (É essencial sobrescrever hashCode e equals!)\n");

        // Classe Produto definida abaixo
        Set<Produto> produtos = new HashSet<>();
        produtos.add(new Produto(1, "Notebook"));
        produtos.add(new Produto(2, "Mouse"));
        produtos.add(new Produto(1, "Notebook")); // ← duplicata! deve ser ignorada

        System.out.println("Produtos (sem duplicata): " + produtos);
        System.out.println("Tamanho esperado: 2, Tamanho real: " + produtos.size());
        System.out.println();
    }

    // Classe auxiliar com hashCode e equals sobrescritos
    static class Produto {
        int id;
        String nome;

        Produto(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Produto)) return false;
            Produto p = (Produto) o;
            return this.id == p.id && this.nome.equals(p.nome);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(id, nome);
        }

        @Override
        public String toString() {
            return "Produto{id=" + id + ", nome='" + nome + "'}";
        }
    }
}
