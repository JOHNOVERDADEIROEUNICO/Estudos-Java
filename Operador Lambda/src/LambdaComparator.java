import java.util.*;
import java.util.stream.*;

/**
 * =============================================================
 *  06 - LAMBDA COM COMPARATOR
 * =============================================================
 *
 * Comparator é uma das interfaces mais usadas com lambda.
 * Permite ordenar listas e streams de forma flexível.
 *
 * Métodos úteis de Comparator:
 *   Comparator.comparing(keyExtractor)         → ordena por um campo
 *   Comparator.comparingInt/Double/Long(...)   → versões otimizadas
 *   .reversed()                                → inverte a ordem
 *   .thenComparing(...)                        → critério secundário
 *   Comparator.naturalOrder()                  → ordem natural
 *   Comparator.reverseOrder()                  → ordem inversa natural
 */
public class LambdaComparator {

    record Aluno(String nome, double nota, int idade) {}
    record Funcionario(String nome, String departamento, double salario) {}

    public static void executar() {
        System.out.println("\n--- 06. LAMBDA COM COMPARATOR ---");

        List<Aluno> alunos = Arrays.asList(
            new Aluno("Carlos",  7.5, 20),
            new Aluno("Ana",     9.0, 22),
            new Aluno("Bruno",   7.5, 19),
            new Aluno("Diana",   8.0, 21),
            new Aluno("Eduardo", 6.0, 23)
        );

        // -------------------------------------------------------
        // ORDENAÇÃO SIMPLES COM LAMBDA
        // -------------------------------------------------------
        System.out.println("\n[Lambda] Ordenado por nota (crescente):");
        alunos.stream()
              .sorted((a, b) -> Double.compare(a.nota(), b.nota()))
              .forEach(a -> System.out.printf("  %-10s %.1f%n", a.nome(), a.nota()));

        // -------------------------------------------------------
        // COMPARATOR.COMPARING — forma idiomática
        // -------------------------------------------------------
        System.out.println("\n[Comparator.comparing] Por nota:");
        alunos.stream()
              .sorted(Comparator.comparingDouble(Aluno::nota))
              .forEach(a -> System.out.printf("  %-10s %.1f%n", a.nome(), a.nota()));

        // -------------------------------------------------------
        // REVERSED — ordem decrescente
        // -------------------------------------------------------
        System.out.println("\n[.reversed()] Por nota (decrescente):");
        alunos.stream()
              .sorted(Comparator.comparingDouble(Aluno::nota).reversed())
              .forEach(a -> System.out.printf("  %-10s %.1f%n", a.nome(), a.nota()));

        // -------------------------------------------------------
        // THENCOMPARING — critério de desempate
        // -------------------------------------------------------
        System.out.println("\n[thenComparing] Por nota, desempate por nome:");
        alunos.stream()
              .sorted(Comparator.comparingDouble(Aluno::nota)
                                .thenComparing(Aluno::nome))
              .forEach(a -> System.out.printf("  %-10s %.1f%n", a.nome(), a.nota()));

        // -------------------------------------------------------
        // MÚLTIPLOS CRITÉRIOS DE ORDENAÇÃO
        // -------------------------------------------------------
        System.out.println("\n[múltiplos critérios] Por nota desc, depois por idade asc:");
        alunos.stream()
              .sorted(Comparator.comparingDouble(Aluno::nota).reversed()
                                .thenComparingInt(Aluno::idade))
              .forEach(a -> System.out.printf("  %-10s nota=%.1f  idade=%d%n",
                      a.nome(), a.nota(), a.idade()));

        // -------------------------------------------------------
        // SORTED EM UMA LIST DIRETAMENTE
        // -------------------------------------------------------
        System.out.println("\n[List.sort()] Ordenando a lista por nome:");
        List<Aluno> alunosOrdenados = new ArrayList<>(alunos);
        alunosOrdenados.sort(Comparator.comparing(Aluno::nome));
        alunosOrdenados.forEach(a -> System.out.println("  " + a.nome()));

        // -------------------------------------------------------
        // EXEMPLO COMPLETO COM FUNCIONÁRIOS
        // -------------------------------------------------------
        System.out.println("\n[Funcionários] Ordenação composta:");
        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Zélia",   "TI",      8500.0),
            new Funcionario("André",   "RH",      5200.0),
            new Funcionario("Clara",   "TI",      9200.0),
            new Funcionario("Marcos",  "RH",      5200.0),
            new Funcionario("Patrícia","Vendas",  6800.0),
            new Funcionario("Rafael",  "TI",      7100.0)
        );

        // Departamento → Salário desc → Nome
        Comparator<Funcionario> comparadorComplexo = Comparator
                .comparing(Funcionario::departamento)
                .thenComparing(Comparator.comparingDouble(Funcionario::salario).reversed())
                .thenComparing(Funcionario::nome);

        System.out.println("  Por departamento, salário desc, nome:");
        funcionarios.stream()
                    .sorted(comparadorComplexo)
                    .forEach(f -> System.out.printf(
                            "  %-10s | %-8s | R$ %.2f%n",
                            f.nome(), f.departamento(), f.salario()));

        // -------------------------------------------------------
        // MIN e MAX com Comparator Lambda
        // -------------------------------------------------------
        System.out.println("\n[min/max] Melhor e pior aluno:");
        Optional<Aluno> melhor = alunos.stream()
                .max(Comparator.comparingDouble(Aluno::nota));
        Optional<Aluno> pior = alunos.stream()
                .min(Comparator.comparingDouble(Aluno::nota));

        melhor.ifPresent(a -> System.out.println("Melhor nota: " + a.nome() + " → " + a.nota()));
        pior.ifPresent(a -> System.out.println("Pior nota:   " + a.nome() + " → " + a.nota()));
    }
}
