package com.vandson.desafiomercadolivre.novoUsuario;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
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


    Usuario(@NotBlank @Email String login, @Valid SenhaLimpa senhaLimpa) {
        Assert.hasLength(login, "Login não pode ser vazio");
        Assert.notNull(senhaLimpa, "Senha não pode ser nula");

        this.login = login;
        this.senha = senhaLimpa.hash();
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
