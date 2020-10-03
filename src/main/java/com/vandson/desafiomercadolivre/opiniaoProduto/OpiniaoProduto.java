package com.vandson.desafiomercadolivre.opiniaoProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 25/09/2020
 */
@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @Positive
    @Min(value = 1)
    @Max(value = 5)
    private int nota;

    @NotNull
    @ManyToOne
    private Produto produtoSelecionado;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(@NotBlank String titulo, @NotBlank String descricao, @NotNull @Min(value = 1) @Max(value = 5) int nota, @NotNull @Valid Produto produtoSelecionado, @NotNull @Valid UsuarioLogado usuarioLogado) {
        Assert.notNull(produtoSelecionado, "O produto não pode ser nulo");
        Assert.notNull(usuarioLogado, "O usuário logado não pode ser nulo");


        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.produtoSelecionado = produtoSelecionado;
        this.usuario = usuarioLogado.get();
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

    @Override
    public String toString() {
        return "OpiniaoProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", produtoSelecionado=" + produtoSelecionado +
                ", usuario=" + usuario +
                '}';
    }
}
