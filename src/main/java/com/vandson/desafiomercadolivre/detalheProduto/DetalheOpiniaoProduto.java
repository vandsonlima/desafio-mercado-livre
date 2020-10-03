package com.vandson.desafiomercadolivre.detalheProduto;

import com.vandson.desafiomercadolivre.opiniaoProduto.OpiniaoProduto;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 03/10/2020
 */
public class DetalheOpiniaoProduto {

    private String titulo;

    private String descricao;

    private int nota;

    public DetalheOpiniaoProduto(OpiniaoProduto opiniaoProduto) {
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
        this.nota = opiniaoProduto.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}
