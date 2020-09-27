package com.vandson.desafiomercadolivre.compartilhado.email;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Vandson
 * @since 27/09/2020
 */
@Primary
@Profile("!prod")
@Service
public class FakeMailer implements  MailService{

    @Override
    public void send(String destinatario, String conteudo) {
        System.out.println("enviando email...");
        System.out.println("destinatario "+destinatario);
        System.out.println("mensagem: " + conteudo);
    }
}
