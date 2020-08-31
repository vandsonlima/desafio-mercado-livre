package com.vandson.desafiomercadolivre.compartilhado.seguranca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/08/2020
 **/
@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    public static final Logger logger = LoggerFactory.getLogger(UserAuthenticationController.class);

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody LoginInputDto loginInputDto){
        UsernamePasswordAuthenticationToken authenticationToken = loginInputDto.build();
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            String jwt = tokenManager.generateToken(authentication);
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            logger.error("[Autenticacao] {}",e);
            return ResponseEntity.badRequest().build();
        }
    }
}
