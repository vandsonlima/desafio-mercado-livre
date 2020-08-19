package com.vandson.desafiomercadolivre.novaCategoria;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 18/08/2020
 **/
//1
@Entity
@Table
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria_mae")
    private Categoria categoriaMae;

    @Deprecated
    protected Categoria(){

    }

    public Categoria(@NotBlank String nome) {
        Assert.hasLength(nome, "O nome não pode ser nulo");
        this.nome = nome;
    }

    public void addCategoriaMae(Categoria categoriaMae){
        Assert.notNull(categoriaMae, "Categoria mãe não pode ser nula");
        this.categoriaMae = categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }
}
