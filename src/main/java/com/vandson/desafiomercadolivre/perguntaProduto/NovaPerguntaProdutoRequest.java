package com.vandson.desafiomercadolivre.perguntaProduto;

import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 25/09/2020
 */
public class NovaPerguntaProdutoRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    public NovaPerguntaProdutoRequest(@NotBlank String titulo, @NotBlank String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public PerguntaProduto toModel(@NotNull @Valid Produto produtoSelecionado,@NotNull @Valid Usuario usuario) {
        return new PerguntaProduto(titulo, descricao, produtoSelecionado, usuario);
    }
}
