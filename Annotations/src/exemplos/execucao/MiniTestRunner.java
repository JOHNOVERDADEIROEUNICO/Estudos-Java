package exemplos.execucao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Runner simples que demonstra como ferramentas como JUnit ou Spring Test funcionam:
 * 1. Carrega a classe via Reflection
 * 2. Procura métodos que possuem a anotação @TesteUnitario
 * 3. Executa cada método dinamicamente com method.invoke(...)
 */
public class MiniTestRunner {

    public static void executarTestes(Class<?> classeDeTestes) {
        System.out.println("\n--- 3. MOTOR DE TESTES COM ANOTAÇÃO EM MÉTODOS (@TesteUnitario) ---");
        System.out.println("Inspecionando a classe: " + classeDeTestes.getSimpleName());

        int total = 0;
        int passados = 0;
        int falhados = 0;
        int ignorados = 0;

        try {
            // Cria uma instância da classe de teste
            Object instancia = classeDeTestes.getDeclaredConstructor().newInstance();

            for (Method metodo : classeDeTestes.getDeclaredMethods()) {
                // Verifica se o método tem a anotação @TesteUnitario
                if (metodo.isAnnotationPresent(TesteUnitario.class)) {
                    total++;
                    TesteUnitario info = metodo.getAnnotation(TesteUnitario.class);

                    if (info.ignorar()) {
                        System.out.println("   [-] IGNORADO: " + metodo.getName() + " -> " + info.descricao());
                        ignorados++;
                        continue;
                    }

                    try {
                        // Invoca o método dinamicamente
                        metodo.invoke(instancia);
                        System.out.println("   [+] PASSOU:   " + metodo.getName() + " -> " + info.descricao());
                        passados++;
                    } catch (InvocationTargetException e) {
                        // A exceção original lançada dentro do método fica dentro de TargetException
                        Throwable causa = e.getCause();
                        System.out.println("   [X] FALHOU:   " + metodo.getName() + " -> Motivo: " + causa.getMessage());
                        falhados++;
                    }
                }
            }

            System.out.printf("Resultado: Total=%d | Passou=%d | Falhou=%d | Ignorado=%d\n",
                    total, passados, falhados, ignorados);

        } catch (Exception e) {
            System.err.println("Erro ao instanciar ou executar a suíte de testes: " + e.getMessage());
        }
    }
}
