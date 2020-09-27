package com.vandson.desafiomercadolivre.compartilhado.email;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Vandson
 * @since 26/09/2020
 */
public interface MailService {
    /**
     * envia um email via sistema
     * os dados estão simplificados para um ambiente de treinamento
     * 
     * @param destinatario
     * @param conteudo
     */
    public void send(String destinatario, String conteudo);
}
