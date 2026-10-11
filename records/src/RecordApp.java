public class RecordApp {
    public static void main(String[] args) {

        System.out.println("=== Criando a persona ===");
        var person = new RecordPerson("  Alice  ", 30); // o construtor faz trim automaticamente

        // ── toString() automático ──────────────────────────────
        System.out.println("\n[toString gerado automaticamente]");
        System.out.println(person); // RecordPerson[name=Alice, age=30]

        // ── Acessores (getters sem "get") ──────────────────────
        System.out.println("\n[Acessores automáticos]");
        System.out.println("Nome : " + person.name());
        System.out.println("Idade: " + person.age());

        // ── Métodos customizados ───────────────────────────────
        System.out.println("\n[Métodos customizados da persona]");
        System.out.println(person.greet());
        System.out.println(person.summary());
        System.out.println("É adulto? " + person.isAdult());

        // ── equals() automático (compara por valor) ────────────
        System.out.println("\n[equals() automático — compara conteúdo, não referência]");
        var pessoa2 = new RecordPerson("Alice", 30);
        System.out.println("person == pessoa2 ? " + (person == pessoa2));   // false (referências diferentes)
        System.out.println("person.equals(pessoa2) ? " + person.equals(pessoa2)); // true (mesmos valores)

        // ── hashCode() automático ──────────────────────────────
        System.out.println("\n[hashCode() automático]");
        System.out.println("hashCode person : " + person.hashCode());
        System.out.println("hashCode pessoa2: " + pessoa2.hashCode()); // igual ao de person

        // ── Imutabilidade ──────────────────────────────────────
        System.out.println("\n[Imutabilidade — atributos são final]");
        System.out.println("Não existe person.name = \"Bob\". Isso causaria erro de compilação.");
        System.out.println("Records são imutáveis por design!");

        // ── Validação no construtor ────────────────────────────
        System.out.println("\n[Validação no construtor compacto]");
        try {
            var invalido = new RecordPerson("", 25);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
        try {
            var invalido = new RecordPerson("Bob", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // ── Campo estático ─────────────────────────────────────
        System.out.println("\n[Campo estático]");
        System.out.println("Maioridade definida em: " + RecordPerson.MAIORIDADE + " anos");
    }
}
