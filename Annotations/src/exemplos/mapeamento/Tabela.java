package exemplos.mapeamento;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para mapear uma classe a uma tabela do banco de dados (simulando @Table do JPA).
 * - @Target(ElementType.TYPE): Aplicável apenas a classes ou interfaces.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {
    String nome();
}
