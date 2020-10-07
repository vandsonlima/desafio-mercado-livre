package com.vandson.desafiomercadolivre.novaCompra;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.compartilhado.notificacoes.NotificacaoService;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//4
@RestController
public class NovaCompraController {

    @PersistenceContext
    private EntityManager entityManager;

    private final NotificacaoService email;

    public NovaCompraController(NotificacaoService email) {
        this.email = email;
    }

    @PostMapping("/pedido/novo")
    @Transactional
    public String criar(@RequestBody @Valid NovoPedidoRequest novoPedidoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder) throws BindException {
       //1
        Produto produto = entityManager.find(Produto.class, novoPedidoRequest.getIdProdutoSelecionado());
        //1
        if(produto.temEstoque(novoPedidoRequest.getQuantidade())) {
            //1
            Pedido novoPedido = new Pedido(novoPedidoRequest.getQuantidade(), usuarioLogado, produto, novoPedidoRequest.getGatewayPagamento());
            entityManager.persist(novoPedido);
            email.novaCompra(novoPedido);
            return  novoPedido.retornoPagamento(uriComponentsBuilder);// uriComponentsBuilder.path(novoPedido.getGatewayPagamento().getUrl()).buildAndExpand(novoPedido.getIdCompraGerada()).toString();
        }
        BindException problemaComEstoque = new BindException(novoPedidoRequest, "novoPedidoRequest");
        problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");
        throw problemaComEstoque;
    }
}
