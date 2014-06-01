package ug.progress.monitoring.manager;


import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.store.UserStore;

import javax.inject.Inject;

/**
 * Created by ZR on 01.06.2014.
 */
@Service
public class UserManager implements AuthenticationProvider, UserDetailsService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    protected UserStore store = null;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {

        if (checkPassword((String) ObjectUtils.defaultIfNull(a.getPrincipal(), ""), (String)ObjectUtils.defaultIfNull(a.getCredentials(), ""))) {
            UserEntity user = (UserEntity) loadUserByUsername(a.getPrincipal().toString());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    a.getPrincipal(),
                    a.getCredentials(),
                    user.getAuthorities());
            //token.setAuthenticated(true);
            token.setDetails(user);
            return token;
        } else {
            a.setAuthenticated(false);
        }
        return a;
    }

    private boolean checkPassword(String mail, String password) {
        UserEntity user = store.findByMail(mail);
        if(user == null) {
            logger.warn("User not found: " + mail);
            return false;
        }
        if(password==null){
            logger.warn("Received password is null for user "+ mail);
            return false;
        }
        logger.debug("Attempting to login: "+user.getMail()+":"+password+".");

        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("Login successful.");
            return true;
        }
        logger.debug("Login failed.");
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = store.findByMail(username);
            if (user==null)
                throw new UsernameNotFoundException("User with name "+username+" not found!");
            return user;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UsernameNotFoundException("User with name "+username+" not found!");
        }
    }
}
