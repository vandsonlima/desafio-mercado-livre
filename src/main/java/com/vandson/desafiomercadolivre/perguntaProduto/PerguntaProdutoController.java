package com.vandson.desafiomercadolivre.perguntaProduto;

import com.vandson.desafiomercadolivre.compartilhado.UsuarioLogado;
import com.vandson.desafiomercadolivre.compartilhado.email.MailService;
import com.vandson.desafiomercadolivre.compartilhado.notificacoes.NotificacaoService;
import com.vandson.desafiomercadolivre.novoProduto.Produto;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//5
@RestController
public class PerguntaProdutoController {

    @PersistenceContext
    private EntityManager entityManager;
    //1
    private final NotificacaoService notificacaoService;

    public PerguntaProdutoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping("/produto/{id}/perguntas")
    @Transactional
    //4
    public ResponseEntity criar(@RequestBody @Valid NovaPerguntaProdutoRequest novaPerguntaProdutoRequest, //1
                                @PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado, //1
                                UriComponentsBuilder uriComponentsBuilder) {
        //2
        Produto produtoSelecionado = Optional.ofNullable(entityManager.find(Produto.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        PerguntaProduto novaPergunta = novaPerguntaProdutoRequest.toModel(produtoSelecionado, usuarioLogado.get());
        entityManager.persist(novaPergunta);

        notificacaoService.novaPerunta(novaPergunta);

        return ResponseEntity.ok(novaPergunta.toString());
    }
}
