package com.vandson.desafiomercadolivre.novoUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//1
public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaLimpa(senha));
    }
}
