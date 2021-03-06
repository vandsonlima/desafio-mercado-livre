package com.vandson.desafiomercadolivre.novoUsuario;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//2
@RestController
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/usuario")
    @Transactional
    public String criar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        Usuario novoUsuario = usuarioRequest.toModel();
        entityManager.persist(novoUsuario);
        return novoUsuario.toString();
    }
}
