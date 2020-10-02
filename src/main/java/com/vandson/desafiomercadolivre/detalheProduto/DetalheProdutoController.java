package com.vandson.desafiomercadolivre.detalheProduto;

import com.vandson.desafiomercadolivre.novoProduto.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vandson
 * @since 01/10/2020
 */
@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produto/{id}")
    public DetalheProduto get(@PathVariable Long id){
        Produto produto = manager.find(Produto.class, id);
        return new DetalheProduto(produto);
    }
}
