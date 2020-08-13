package com.vandson.desafiomercadolivre.novoUsuario;

import com.vandson.desafiomercadolivre.compartilhado.HashService;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//3
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

    public Usuario toModel(HashService hashService) {
        try {
            return new Usuario(login, hashService.gerarHashHexaDecimal(senha));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Ocorreu um erro ao criptografar a senha do usuário", e);
        }
    }
}
