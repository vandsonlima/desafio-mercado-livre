package com.vandson.desafiomercadolivre.compartilhado.seguranca;

import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
public interface UserDetailsMapper {

    UserDetails map(Object usuario);
}
