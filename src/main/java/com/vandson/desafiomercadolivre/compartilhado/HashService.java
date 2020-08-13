package com.vandson.desafiomercadolivre.compartilhado;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

/**
 * Classe que auxilia na geração de dados criptografados
 *
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//1
@Service
@Validated
public class HashService {

    private static final String DEFAULT_ALGORITHM = "SHA-256";

    private static byte[] gerarHash(@NotNull String frase) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(DEFAULT_ALGORITHM);
        md.update(frase.getBytes());
        return md.digest();
    }

    public String gerarHashHexaDecimal(@NotNull String frase) throws NoSuchAlgorithmException {
        byte[] bytes = gerarHash(frase);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02X", 0xFF & b));
        }
        return stringBuilder.toString();
    }
}
