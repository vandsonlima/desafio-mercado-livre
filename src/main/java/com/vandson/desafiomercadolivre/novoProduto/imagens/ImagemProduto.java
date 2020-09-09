package com.vandson.desafiomercadolivre.novoProduto.imagens;

import com.vandson.desafiomercadolivre.novoProduto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/
@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotBlank
    private String url;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull Produto produto, @NotBlank String url) {
        this.produto = produto;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }
}
