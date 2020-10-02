package com.vandson.desafiomercadolivre.perguntaProduto;

import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 25/09/2020
 */
@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    @ManyToOne
    private Produto produtoSelecionado;
    @NotNull
    @ManyToOne
    private Usuario usuario;

    private final LocalDateTime instant = LocalDateTime.now();

    @Deprecated
    public PerguntaProduto() {
    }

    public PerguntaProduto(@NotBlank String titulo, @NotBlank String descricao, @NotNull @Valid Produto produtoSelecionado, @NotNull @Valid Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoSelecionado = produtoSelecionado;
        this.usuario = usuario;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "PerguntaProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produtoSelecionado=" + produtoSelecionado +
                ", usuario=" + usuario +
                ", instant=" + instant +
                '}';
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaProduto that = (PerguntaProduto) o;
        return titulo.equals(that.titulo) &&
                produtoSelecionado.equals(that.produtoSelecionado) &&
                usuario.equals(that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produtoSelecionado, usuario);
    }
}
