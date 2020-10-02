package com.vandson.desafiomercadolivre.novoProduto.imagens;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoProduto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 08/09/2020
 **/
@RestController
public class ImagemProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    private final UploadFile uploadFile;

    public ImagemProdutoController(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    @PostMapping("/produto/{id}/imagem")
    @Transactional
    public String criarNovaFoto(@PathVariable Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Produto produtoSelecionado = Optional.ofNullable(entityManager.find(Produto.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if(!produtoSelecionado.pertenceAoUsuarioLogado(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Produto selecionado não pertence ao usuário");
        }

        Set<String> novasImagens = uploadFile.sendFiles(imagemRequest.getImagens());
        produtoSelecionado.adicionaNovasImagens(novasImagens);
        entityManager.persist(produtoSelecionado);
        return produtoSelecionado.toString();
    }

}
