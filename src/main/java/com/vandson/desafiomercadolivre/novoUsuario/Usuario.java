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
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @PastOrPresent
    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();


    Usuario(@NotBlank @Email String email, @Valid SenhaLimpa senhaLimpa) {
        Assert.hasLength(email, "Login não pode ser vazio");
        Assert.notNull(senhaLimpa, "Senha não pode ser nula");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
