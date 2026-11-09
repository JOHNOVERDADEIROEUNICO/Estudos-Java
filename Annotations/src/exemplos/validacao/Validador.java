package exemplos.validacao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Motor de validação que utiliza Reflection para inspecionar os campos
 * de qualquer objeto e aplicar as regras definidas nas Anotações.
 */
public class Validador {

    public static List<String> validar(Object objeto) {
        List<String> erros = new ArrayList<>();
        if (objeto == null) {
            erros.add("O objeto a ser validado é nulo.");
            return erros;
        }

        // Obtém os metadados da classe em tempo de execução
        Class<?> clazz = objeto.getClass();

        // Percorre todos os campos declarados (inclusive privados)
        for (Field campo : clazz.getDeclaredFields()) {
            // Permite acesso ao campo mesmo se for privado
            campo.setAccessible(true);

            try {
                Object valor = campo.get(objeto);

                // 1. Valida anotação @CampoObrigatorio
                if (campo.isAnnotationPresent(CampoObrigatorio.class)) {
                    CampoObrigatorio anotacao = campo.getAnnotation(CampoObrigatorio.class);
                    if (valor == null || (valor instanceof String str && str.trim().isEmpty())) {
                        erros.add("Campo [" + campo.getName() + "]: " + anotacao.mensagem());
                    }
                }

                // 2. Valida anotação @GarantirMaioridade
                if (campo.isAnnotationPresent(GarantirMaioridade.class)) {
                    GarantirMaioridade anotacao = campo.getAnnotation(GarantirMaioridade.class);
                    if (valor instanceof Number num) {
                        int idade = num.intValue();
                        if (idade < anotacao.idadeMinima()) {
                            erros.add("Campo [" + campo.getName() + "] valor (" + idade + "): " + anotacao.mensagem());
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                erros.add("Erro de acesso ao campo " + campo.getName() + ": " + e.getMessage());
            }
        }

        return erros;
    }
}
