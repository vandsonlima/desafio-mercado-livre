package com.vandson.desafiomercadolivre.compartilhado.email;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Vandson
 * @since 26/09/2020
 */
@Service
public class MailServiceJavaMail implements MailService {

    private final JavaMailSender javaMailSender;

    public MailServiceJavaMail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void send(String destinatario, String conteudo) {
        javaMailSender.send(MailBuilder.builder().simpleMailMessage(destinatario, conteudo));
    }
}
