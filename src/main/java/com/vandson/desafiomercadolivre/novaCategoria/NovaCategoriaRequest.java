package com.vandson.desafiomercadolivre.novaCategoria;

import com.vandson.desafiomercadolivre.compartilhado.ExistsId;
import com.vandson.desafiomercadolivre.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 18/08/2020
 **/
public class NovaCategoriaRequest {

    @NotBlank
    //@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    //@ExistsId(domainClass = Categoria.class, fieldName = "idCategoriaMae")
    private Long idCategoriaMae;

    public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria model(CategoriaRepository categoriaRepository) {
        Categoria novaCategoria = new Categoria(nome);
        if(Objects.nonNull(idCategoriaMae))
            novaCategoria.addCategoriaMae(categoriaRepository.findById(idCategoriaMae).orElseThrow());
        return novaCategoria;
    }
}
