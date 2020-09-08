package com.vandson.desafiomercadolivre.novoProduto;

import com.vandson.desafiomercadolivre.compartilhado.ExistsId;
import com.vandson.desafiomercadolivre.compartilhado.UniqueValue;
import com.vandson.desafiomercadolivre.novaCategoria.Categoria;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
public class NovoProdutoRequest {
    @UniqueValue(fieldName = "nome", domainClass = Produto.class)
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @ExistsId(domainClass = Categoria.class)
    private Long idCategoria;


    public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Integer quantidade,
                              @Size(min = 3) List<CaracteristicaRequest> caracteristicas, @NotBlank @Length(max = 1000) String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(EntityManager entityManager, @NotNull Usuario proprietario) {
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        return new Produto(this.nome, this.valor, this.quantidade, caracteristicas, this.descricao, categoria, proprietario);
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> tentativaResultado = new HashSet<>();
        for (CaracteristicaRequest caracteristicaRequest: caracteristicas) {
            if(!tentativaResultado.add(caracteristicaRequest.getNome()))
                nomesIguais.add(caracteristicaRequest.getNome());
        }
        return nomesIguais;
    }
}
