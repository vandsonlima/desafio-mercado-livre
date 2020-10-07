package com.vandson.desafiomercadolivre.novaCompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAYPAL {
        @Override
        String retornoUrl(Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(pedido.getId()).toString();

            return "paypal.com/" + pedido.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    }, PAGSEGURO {
        @Override
        String retornoUrl(Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(pedido.getId()).toString();

            return "pagseguro.com/" + pedido.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    };



    abstract String retornoUrl(Pedido pedido, UriComponentsBuilder uriComponentsBuilder);
}
