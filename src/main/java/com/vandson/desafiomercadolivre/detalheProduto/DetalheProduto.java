package com.vandson.desafiomercadolivre.detalheProduto;

import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoProduto.imagens.ImagemProduto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Vandson
 * @since 01/10/2020
 */
//8
public class DetalheProduto {

    private final String nome;
    private final BigDecimal valor;
    private final String descricao;
    private final Set<String> imagensLinks;
    //1
    private final Set<DetalheCaracteristica> detalhesCaracteristicas;
    //1
    private final Set<DetalhePergunta> detalhesPerguntas;
    //1
    private final Set<DetalheOpiniaoProduto> detalhesOpinioes;
    private final BigDecimal mediaNotas;
    private final Integer totalNotas;

    //5
    public DetalheProduto(@NotNull Produto produto) {
        //1
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        //1
        this.imagensLinks = produto.mapImagens(ImagemProduto::getUrl);
        //1
        this.detalhesCaracteristicas = produto.mapCaracteristicas(DetalheCaracteristica::new);
        //1
        this.detalhesPerguntas = produto.mapPerguntas(DetalhePergunta::new);
        //1
        this.detalhesOpinioes = produto.mapOpinioes(DetalheOpiniaoProduto::new);
        this.mediaNotas = produto.mediaNotasOpinioes();
        this.totalNotas = detalhesOpinioes.size();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<String> getImagensLinks() {
        return imagensLinks;
    }

    public Set<DetalheCaracteristica> getDetalhesCaracteristicas() {
        return detalhesCaracteristicas;
    }

    public Set<DetalhePergunta> getDetalhesPerguntas() {
        return detalhesPerguntas;
    }

    public Set<DetalheOpiniaoProduto> getDetalhesOpinioes() {
        return detalhesOpinioes;
    }

    public BigDecimal getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }
}
