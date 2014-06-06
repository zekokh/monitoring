package ug.progress.monitoring.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.UserService;
import ug.progress.monitoring.store.UserStore;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ruslan Zekokh.
 */

@Service
public class UserServiceImpl implements UserService, AuthenticationProvider {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    protected UserStore store;

    @Override
    public List<UserEntity> getAllUser() {
        return store.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return store.findById(id);
    }

    @Override
    public UserEntity getUserByMail(String mail) {
        return store.findByMail(mail);
    }

    /**
     * Метод ищет пользователя по переданному идентификатору и удаляет его
     *
     * @param userId идентификатор пользователя для удаления
     */
    public void deleteUser(Long userId) {
        try {
            UserEntity deletedUser = store.findOne(userId);
            if (deletedUser == null) logger.error("User not found");
            store.delete(deletedUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Метод получает пользователя для сохранения, шифрует его пароль и сохраняет пользователя
     *
     * @param user пользователь, которого сохраняем
     * @return сохраненного пользователя
     */
    public UserEntity saveUser(UserEntity user) {
        UserEntity savedUser=null;
        if (user == null) return null;
        if (user.getPassword() == null) {
            logger.debug("Received password is null for user" + user.getMail());
            return null;
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            savedUser= store.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return savedUser;
    }

    @Override
    public boolean updateUser(UserEntity editUser) {
        try {
            UserEntity oldUser = store.findOne(editUser.getId());
            oldUser.setFirstName(editUser.getFirstName());
            oldUser.setLastName(editUser.getLastName());
            oldUser.setMail(editUser.getMail());
            oldUser.setUserRoles(editUser.getUserRoles());
            UserEntity savedUser = store.save(oldUser);
            return savedUser != null;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean changePassword(UserEntity user, String password) {
        try {
            UserEntity oldUser = getUserById(user.getId());
            if (oldUser != null) {
                oldUser.setPassword(passwordEncoder.encode(password));
                UserEntity savedUser = store.save(oldUser);
                return savedUser != null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isMailExists(String mail, long ignoreId) {
        try {
            UserEntity user = (UserEntity) loadUserByUsername(mail);
            return (user.getId() != ignoreId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isMailUnique(String login, long ignoreId) {
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        try {
            UserEntity user = store.findByMail(mail);
            if (user == null)
                throw new UsernameNotFoundException("User with name " + mail + " not found!");
            return user;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UsernameNotFoundException("User with name " + mail + " not found!");
        }

}

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {

        if (checkPassword((String) ObjectUtils.defaultIfNull(a.getPrincipal(), ""), (String) ObjectUtils.defaultIfNull(a.getCredentials(), ""))) {
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

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean checkPassword(String login, String password) {
        UserEntity user = store.findByMail(login);
        if (user == null) {
            logger.warn("User not found: " + login);
            return false;
        }
        if (password == null) {
            logger.warn("Received password is null for user " + login);
            return false;
        }
        logger.debug("Attempting to login: " + user.getMail() + ":" + password + ".");

        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("Login successful.");
            return true;
        }
        logger.debug("Login failed.");
        return false;
    }
}
