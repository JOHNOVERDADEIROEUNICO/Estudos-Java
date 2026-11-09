package exemplos.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para validar se campos String ou objetos não estão nulos/vazios.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CampoObrigatorio {
    String mensagem() default "Este campo é obrigatório e não pode ser nulo ou vazio!";
}
