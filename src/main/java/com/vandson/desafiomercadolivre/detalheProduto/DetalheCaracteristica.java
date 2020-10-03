package com.vandson.desafiomercadolivre.detalheProduto;

import com.vandson.desafiomercadolivre.novoProduto.CaracteristicaProduto;

/**
 * @author Vandson
 * @since 01/10/2020
 */
public class DetalheCaracteristica {
    String nome;
    String descricao;

    public DetalheCaracteristica(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
