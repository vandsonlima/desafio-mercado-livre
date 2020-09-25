package com.vandson.desafiomercadolivre.opiniaoProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;

import javax.validation.constraints.*;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 25/09/2020
 */
public class NovaOpiniaoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int nota;

    public NovaOpiniaoRequest(@NotBlank String titulo, @NotBlank String descricao, @NotNull @Min(value = 1) @Max(value = 5) int nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public OpiniaoProduto toModel(Produto produtoSelecionado, UsuarioLogado usuarioLogado) {

        return new OpiniaoProduto(titulo, descricao, nota, produtoSelecionado, usuarioLogado);
    }
}
