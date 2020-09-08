package com.vandson.desafiomercadolivre.novoProduto;

import com.vandson.desafiomercadolivre.novaCategoria.Categoria;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@Table
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    private final LocalDateTime instantCriacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario proprietario;

    @Deprecated
    public Produto(){

    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Integer quantidade,
                   @NotBlank @Length Collection<CaracteristicaRequest> caracteristicas, @NotBlank @Length String descricao, Categoria categoria, @NotNull Usuario proprietario){
        Assert.hasLength(nome, "O nome não pode ser vazio");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "O valor deve ser maior que zero");
        Assert.isTrue(quantidade >=0, "quantodade deve vir preenchida");
        Assert.notNull(caracteristicas, "Caracteristicas não pode ser nulo");
        Assert.isTrue(caracteristicas.size() >=3, "Características devem ter no mínimo 3 itens");
        Assert.hasLength(descricao, "Descrição não pode ser vazio");
        Assert.isTrue(descricao.length() <=1000, "Descrição deve conter entre 0 e 1000 caracteres");
        Assert.notNull(categoria, "categoria não pode ser nulo");
        Assert.notNull(proprietario, "Proprietário do produto não pode ser nulo");

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristicaRequest -> caracteristicaRequest.toModel(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instantCriacao=" + instantCriacao +
                ", proprietario=" + proprietario +
                '}';
    }
}
