package com.vandson.desafiomercadolivre.opiniaoProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 25/09/2020
 */
//5
@RestController
public class OpiniaoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("produto/{id}/opiniao")
    @Transactional
    public ResponseEntity<String> cadastrarNovaOpiniao(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest novaOpiniaoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produtoSelecionado = Optional.ofNullable(entityManager.find(Produto.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        OpiniaoProduto novaOpiniao = novaOpiniaoRequest.toModel(produtoSelecionado, usuarioLogado);
        entityManager.persist(novaOpiniao);
        return ResponseEntity.ok(novaOpiniao.toString());
    }
}
