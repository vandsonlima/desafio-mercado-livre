package com.vandson.desafiomercadolivre.novoUsuario;

import com.vandson.desafiomercadolivre.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//1
public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
