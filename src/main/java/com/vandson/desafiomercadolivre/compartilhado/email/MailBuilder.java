package com.vandson.desafiomercadolivre.compartilhado.email;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * @author Vandson
 * @since 26/09/2020
 */
public class MailBuilder {

    public static MailBuilder builder(){
        return new MailBuilder();
    }

    public SimpleMailMessage simpleMailMessage(String destinatario, String conteudo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(conteudo);
        simpleMailMessage.setTo(destinatario);
        return simpleMailMessage;
    }
}
