package com.vandson.desafiomercadolivre.novoProduto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(@Valid Produto produto) {
        return new CaracteristicaProduto(this.nome, this.descricao, produto);
    }
}


