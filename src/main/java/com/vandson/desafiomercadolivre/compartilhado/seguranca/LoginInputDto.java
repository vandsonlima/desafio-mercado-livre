package com.vandson.desafiomercadolivre.compartilhado.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
public class LoginInputDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public LoginInputDto(@NotBlank String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }


    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
