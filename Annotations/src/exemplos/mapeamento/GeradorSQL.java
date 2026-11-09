package exemplos.mapeamento;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstração prática de como ORMs (Hibernate / JPA) geram SQL automaticamente
 * inspecionando anotações como @Table e @Column via Reflection.
 */
public class GeradorSQL {

    public static String gerarInsert(Object objeto) {
        Class<?> clazz = objeto.getClass();

        // 1. Verifica se a classe tem a anotação @Tabela
        if (!clazz.isAnnotationPresent(Tabela.class)) {
            throw new IllegalArgumentException("A classe " + clazz.getSimpleName() + " não possui a anotação @Tabela!");
        }

        Tabela anotacaoTabela = clazz.getAnnotation(Tabela.class);
        String nomeTabela = anotacaoTabela.nome();

        List<String> colunas = new ArrayList<>();
        List<String> valores = new ArrayList<>();

        // 2. Percorre os campos anotados com @Coluna
        for (Field campo : clazz.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Coluna.class)) {
                campo.setAccessible(true);
                Coluna anotacaoColuna = campo.getAnnotation(Coluna.class);
                colunas.add(anotacaoColuna.nome());

                try {
                    Object valor = campo.get(objeto);
                    if (valor == null) {
                        valores.add("NULL");
                    } else if (valor instanceof String) {
                        valores.add("'" + valor + "'");
                    } else {
                        valores.add(valor.toString());
                    }
                } catch (IllegalAccessException e) {
                    valores.add("NULL");
                }
            }
        }

        return String.format(
            "INSERT INTO %s (%s) VALUES (%s);",
            nomeTabela,
            String.join(", ", colunas),
            String.join(", ", valores)
        );
    }
}
