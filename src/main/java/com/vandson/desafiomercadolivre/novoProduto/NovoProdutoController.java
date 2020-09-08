package com.vandson.desafiomercadolivre.novoProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@RestController
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;


    @InitBinder
    private void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new CaracteristicasIguaisValidator());
    }

    @PostMapping("/novoProduto")
    @Transactional
    public String criar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Produto novoProduto = novoProdutoRequest.toModel(entityManager, usuarioLogado.get());
        entityManager.persist(novoProduto);
        return novoProduto.toString();
    }
}
