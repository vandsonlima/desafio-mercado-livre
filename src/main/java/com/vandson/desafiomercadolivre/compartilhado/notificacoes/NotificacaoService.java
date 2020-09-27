package com.vandson.desafiomercadolivre.compartilhado.notificacoes;

import com.vandson.desafiomercadolivre.compartilhado.email.MailService;
import com.vandson.desafiomercadolivre.perguntaProduto.PerguntaProduto;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * @author Vandson
 * @since 27/09/2020
 */
@Service
public class NotificacaoService {

    private final MailService mailer;

    public NotificacaoService(MailService mailer) {
        this.mailer = mailer;
    }

    public void novaPerunta(PerguntaProduto perguntaProduto){

        String mensagem = String.format(" Nova Pergunta registrada em %s. Pergunta: %s mensagem: %s",
                perguntaProduto.getInstant().format(DateTimeFormatter.BASIC_ISO_DATE),
                perguntaProduto.getTitulo(), perguntaProduto.getDescricao());

        mailer.send(perguntaProduto.getProdutoSelecionado().getProprietario().getEmail(), mensagem );
    }
}
