package com.vandson.desafiomercadolivre.novaCategoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 18/08/2020
 **/
//2
@RestController
public class NovaCategoriaController {

    private final CategoriaRepository categoriaRepository;

    public NovaCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/categoria")
    public String criar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria novaCategoria = novaCategoriaRequest.model(categoriaRepository);
        categoriaRepository.save(novaCategoria);
        return novaCategoria.toString();
    }
}
