package com.vandson.desafiomercadolivre.novoProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novaCategoria.Categoria;
import com.vandson.desafiomercadolivre.novoProduto.imagens.ImagemProduto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import com.vandson.desafiomercadolivre.opiniaoProduto.OpiniaoProduto;
import com.vandson.desafiomercadolivre.perguntaProduto.PerguntaProduto;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produtoSelecionado")
    @OrderBy("titulo ASC")
    private Set<PerguntaProduto> perguntas = new HashSet<>();

    @OneToMany(mappedBy = "produtoSelecionado")
    private Set<OpiniaoProduto> opinioes = new HashSet<>();

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

    public boolean pertenceAoUsuarioLogado(UsuarioLogado usuarioLogado) {
        return this.proprietario.equals(usuarioLogado.get());
    }

    public void adicionaNovasImagens(@NotEmpty Set<String> novasImagens) {
        Set<ImagemProduto> links = novasImagens.stream()
                .map(imagem -> new ImagemProduto(this, imagem))
                .collect(Collectors.toSet());
    this.imagens.addAll(links);
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora){
        return imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> function) {
        return this.caracteristicas.stream().map(function).collect(Collectors.toSet());
    }

    public <T> Set<T> mapPerguntas(Function<PerguntaProduto, T> funcao) {
        return perguntas.stream().map(funcao).collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpinioes(Function<OpiniaoProduto, T> function) {
        return this.opinioes.stream().map(function).collect(Collectors.toSet());
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
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
                ", imagens=" + imagens +
                '}';
    }


    public BigDecimal mediaNotasOpinioes() {
        return BigDecimal.valueOf(this.opinioes.stream().mapToInt(OpiniaoProduto::getNota).average().orElse(0d));
    }

    /**
     * Verifica se o produto tem estoque suficiente
     * para efetivar um pedido com a quantidade informada
     *
      * @param quantidadePedido
     * @return true - se há estoque suficiente para a compra
     */
    public boolean temEstoque(@Positive int quantidadePedido){
        Assert.isTrue(quantidadePedido > 0, "a quantidade precisa ser maior que zero");
        return this.quantidade - quantidadePedido >=0;
    }

    public void abateEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");
        Assert.isTrue((this.quantidade - quantidade) > 0, "Não há quantidade em estoque suficiente para realizar a compra");

        this.quantidade -= quantidade;
    }
}
