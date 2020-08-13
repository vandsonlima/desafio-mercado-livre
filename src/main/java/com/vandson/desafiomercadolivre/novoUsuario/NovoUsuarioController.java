package com.vandson.desafiomercadolivre.novoUsuario;

import com.vandson.desafiomercadolivre.compartilhado.HashService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//3
@RestController
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    private final HashService hashService;

    public NovoUsuarioController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping("/usuario")
    @Transactional
    public String criar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        Usuario novoUsuario = usuarioRequest.toModel(hashService);
        entityManager.persist(novoUsuario);
        return novoUsuario.toString();
    }
}
