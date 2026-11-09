package exemplos.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para validar idade mínima em tempo de execução.
 * - @Target(ElementType.FIELD): Só pode ser aplicada em variáveis/atributos.
 * - @Retention(RetentionPolicy.RUNTIME): Fica salva no bytecode e pode ser lida via Reflection.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GarantirMaioridade {
    int idadeMinima() default 18;
    String mensagem() default "A idade mínima permitida é 18 anos!";
}
