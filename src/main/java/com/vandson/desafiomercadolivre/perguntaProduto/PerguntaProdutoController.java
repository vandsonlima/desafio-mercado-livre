package com.vandson.desafiomercadolivre.perguntaProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//2
@RestController
public class PerguntaProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/produto/{id}/perguntas")
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid NovaPerguntaProdutoRequest novaPerguntaProdutoRequest, @PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder) {
        Produto produtoSelecionado = Optional.ofNullable(entityManager.find(Produto.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        PerguntaProduto novaPergunta = novaPerguntaProdutoRequest.toModel(produtoSelecionado, usuarioLogado.get());
        entityManager.persist(novaPergunta);
        final URI uri = uriComponentsBuilder.path("/produto/{id}/perguntas").buildAndExpand(produtoSelecionado.getId()).toUri();
        novaPergunta.notificarVendedor(uri.getPath());
        return ResponseEntity.created(uri).build();
    }
}
