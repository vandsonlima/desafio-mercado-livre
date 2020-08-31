package com.vandson.desafiomercadolivre.compartilhado.seguranca;

import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Value("${security.username-query}")
    private String query;
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        List<?> objects = entityManager.createQuery(query)
                .setParameter("username", username).getResultList();
        Assert.isTrue(objects.size() <= 1,
                "[BUG] mais de um autenticável tem o mesmo username. "
                        + username);

        if (objects.isEmpty()) {
            throw new UsernameNotFoundException(
                    "Não foi possível encontrar usuário com email: "
                            + username);
        }

        return userDetailsMapper.map(objects.get(0));
    }
}
