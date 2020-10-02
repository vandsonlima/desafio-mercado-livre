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
public class DetalheProduto {

    private final String nome;
    private final BigDecimal valor;
    private final String descricao;
    private final Set<String> imagensLinks;
    private final Set<DetalheCaracteristica> detalhesCaracteristicas;
    private final Set<DetalhePergunta> detalhesPerguntas;
//    private final set<DetalheOpiniaoProduto> detalheOpiniaoProduto;
//    private final BigDecimal mediaNotas;
//    private final Integer totalNotas;

    public DetalheProduto(@NotNull Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.imagensLinks = produto.mapImagens(ImagemProduto::getUrl);
        this.detalhesCaracteristicas = produto.mapCaracteristicas(DetalheCaracteristica::new);
        this.detalhesPerguntas = produto.mapPerguntas(DetalhePergunta::new);
        //opinioes
        //media de notas
        //numero total de notas
    }
}
