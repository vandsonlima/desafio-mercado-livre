package com.vandson.desafiomercadolivre.novoUsuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 13/08/2020
 **/
public class SenhaLimpa {

    @NotBlank
    @Length(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "Não pode ser vazia");
        Assert.isTrue(senha.length() >=6, "deve ter no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
