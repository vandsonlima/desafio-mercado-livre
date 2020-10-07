package com.vandson.desafiomercadolivre.novaCompra;

import com.vandson.desafiomercadolivre.compartilhado.ExistsId;
import com.vandson.desafiomercadolivre.novoProduto.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 06/10/2020
 */
public class NovoPedidoRequest {


    @NotNull
    @ExistsId(domainClass = Produto.class)
    private Long idProdutoSelecionado;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gatewayPagamento;

    public NovoPedidoRequest(@NotNull Long idProdutoSelecionado, @NotNull @Positive Integer quantidade, @NotNull GatewayPagamento gatewayPagamento) {
        this.idProdutoSelecionado = idProdutoSelecionado;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
    }



    public Long getIdProdutoSelecionado() {
        return idProdutoSelecionado;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
