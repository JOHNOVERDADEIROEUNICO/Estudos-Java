package exemplos.execucao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para marcar métodos como testes executáveis (estilo JUnit).
 * - @Target(ElementType.METHOD): Aplicável apenas a métodos.
 * - @Retention(RetentionPolicy.RUNTIME): Disponível para Reflection executar.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TesteUnitario {
    String descricao() default "Sem descrição";
    boolean ignorar() default false;
}
