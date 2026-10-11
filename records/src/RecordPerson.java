/**
 * ╔══════════════════════════════════════════════════════════╗
 *  O QUE É UM RECORD?
 *  Records são classes imutáveis e transparentes introduzidas
 *  no Java 16. Eles existem para modelar dados puros, onde o
 *  foco é o QUE o objeto representa, não o que ele faz.
 *
 *  Ao declarar: public record RecordPerson(String name, int age)
 *  O Java gera AUTOMATICAMENTE para você:
 *    ✔ Atributos privados e finais (name, age)
 *    ✔ Construtor canônico com todos os parâmetros
 *    ✔ Acessores (getters) sem prefixo "get": name(), age()
 *    ✔ equals() baseado nos valores dos campos
 *    ✔ hashCode() baseado nos valores dos campos
 *    ✔ toString() legível: RecordPerson[name=..., age=...]
 * ╚══════════════════════════════════════════════════════════╝
 */
public record RecordPerson(String name, int age) {

    // ─────────────────────────────────────────────────────────
    // CAMPOS ESTÁTICOS
    // Records só aceitam campos estáticos (static) além dos
    // declarados no cabeçalho. Campos de instância extras
    // não são permitidos — isso reforça a imutabilidade.
    // ─────────────────────────────────────────────────────────
    static final int MAIORIDADE = 18;

    // ─────────────────────────────────────────────────────────
    // CONSTRUTOR COMPACTO (Compact Constructor)
    // Ideal para VALIDAÇÃO. Não precisa atribuir os campos —
    // o próprio record faz isso depois que o bloco terminar.
    // ─────────────────────────────────────────────────────────
    public RecordPerson {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Idade inválida: " + age);
        }
        // Normalização também é permitida aqui:
        name = name.trim();
    }

    // ─────────────────────────────────────────────────────────
    // MÉTODOS CUSTOMIZADOS
    // Você pode adicionar métodos livremente, desde que NÃO
    // tentem alterar os atributos (eles são finais!).
    // ─────────────────────────────────────────────────────────

    /** Retorna uma saudação personalizada da persona. */
    public String greet() {
        return "Olá! Meu nome é " + name + " e tenho " + age + " anos.";
    }

    /** Verifica se a persona é maior de idade. */
    public boolean isAdult() {
        return age >= MAIORIDADE;
    }

    /** Retorna uma versão resumida da persona. */
    public String summary() {
        String status = isAdult() ? "adulto(a)" : "menor de idade";
        return name + " é " + status + ".";
    }
}
