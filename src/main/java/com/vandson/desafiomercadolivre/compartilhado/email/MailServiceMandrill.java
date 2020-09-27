package com.vandson.desafiomercadolivre.compartilhado.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vandson
 * @since 27/09/2020
 */
@Profile("prod")
@Service
public class MailServiceMandrill implements MailService{

    @Override
    public void send(String destinatario, String conteudo) {
        RestTemplate restTemplate = new RestTemplate();
        String mandrilMessage = ""; //seria o objeto que representa uma mensagem de email no domínio do MandrillMessage
        try {
            restTemplate.postForEntity("http://api.mandril.com/send.json", mandrilMessage, String.class);
        }catch (Exception e ){
            //tratamento de erro do envio de email
            e.printStackTrace();
        }
    }
}
