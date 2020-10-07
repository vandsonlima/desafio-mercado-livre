package com.vandson.desafiomercadolivre.novaCompra;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 06/10/2020
 */
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    @ManyToOne
    @Valid
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @Valid
    private Produto produto;

    @NotNull
    @Enumerated
    private GatewayPagamento gatewayPagamento;


    /**
     * Um pedido só pode ser efetuado se houver estoque disponível.
     * Um idCompra deve ser gerada na criação do pedido
     * @param quantidade
     * @param usuario
     * @param produto
     * @param gatewayPagamento
     */
    public Pedido(@NotNull @Positive int quantidade, @NotNull UsuarioLogado usuario, @NotNull Produto produto, @NotNull GatewayPagamento gatewayPagamento) {
        Assert.isTrue(quantidade > 0, "A quantidade do pedido deve ser maior que zero");
        Assert.notNull(usuario, "dados do comprador não foram informados");
        Assert.notNull(produto, "dados do produto não foram informados");
        Assert.notNull(gatewayPagamento, "forma de pagamento não foi informada");

        produto.abateEstoque(quantidade);
        this.quantidade = quantidade;
        this.usuario = usuario.get();
        this.produto = produto;
        this.gatewayPagamento = gatewayPagamento;
    }

    @Deprecated
    public Pedido() {
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public String retornoPagamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.retornoUrl(this, uriComponentsBuilder);
    }

    public Long getId() {
        return this.id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

