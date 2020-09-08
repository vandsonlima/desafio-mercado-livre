package com.vandson.desafiomercadolivre.novoProduto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/

public class CaracteristicasIguaisValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(NovoProdutoRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors())
            return;
        NovoProdutoRequest novoProduto = (NovoProdutoRequest) o;
        Set<String> nomesIguais = novoProduto.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty())
            errors.reject("Caracteristicas", "Você tem características iguais");
    }
}
