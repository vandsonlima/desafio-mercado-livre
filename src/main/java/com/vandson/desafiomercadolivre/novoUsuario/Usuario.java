package com.vandson.desafiomercadolivre.novoUsuario;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 12/08/2020
 **/
@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @PastOrPresent
    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();


    /**
     * A senha já deve vir criptografada. Para isso, utilize o {@link com.vandson.desafiomercadolivre.compartilhado.HashService}
     *
     * @param login
     * @param senha
     */
    Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(login, "Login não pode ser vazio");
        Assert.hasLength(senha, "A senha não pode ser vazia");
        this.login = login;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
