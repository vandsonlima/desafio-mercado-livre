package com.vandson.desafiomercadolivre.compartilhado;

import com.vandson.desafiomercadolivre.compartilhado.seguranca.UserDetailsMapper;
import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@Component
public class AppUserDetailsMapper implements UserDetailsMapper {
    @Override
    public UserDetails map(Object usuario) {
        return new UsuarioLogado((Usuario) usuario);
    }
}
