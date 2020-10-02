package com.vandson.desafiomercadolivre.detalheProduto;

import com.vandson.desafiomercadolivre.perguntaProduto.PerguntaProduto;

/**
 * @author Vandson
 * @since 01/10/2020
 */
public class DetalhePergunta {

    private final String titulo;
    private final String descricao;
    private final String nomeAutor;

    public DetalhePergunta(PerguntaProduto perguntaProduto) {
        this.titulo = perguntaProduto.getTitulo();
        this.descricao = perguntaProduto.getDescricao();
        this.nomeAutor = perguntaProduto.getUsuario().getEmail();
    }


}
