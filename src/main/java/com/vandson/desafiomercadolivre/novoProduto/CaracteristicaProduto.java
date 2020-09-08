package com.vandson.desafiomercadolivre.novoProduto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@Entity
@Table
public class CaracteristicaProduto {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;


    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "CaracteristicaProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
