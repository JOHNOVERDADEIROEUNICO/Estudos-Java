package exemplos.mapeamento;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para mapear atributos de uma classe a colunas do banco de dados (simulando @Column do JPA).
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Coluna {
    String nome();
    boolean chavePrimaria() default false;
}
