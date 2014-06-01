package ug.progress.monitoring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.UserEntity;

import java.util.List;

/**
 * Created by ZR on 01.06.2014.
 */

@Service
public interface UserService extends UserDetailsService {
    public List<UserEntity> getAllUser();
    public UserEntity getUserById(Long id);
    public UserEntity getUserByMail(String mail);
    public void deleteUser(Long userId);
    public UserEntity saveUser(UserEntity user);
    public boolean updateUser(UserEntity user);
    public boolean changePassword(UserEntity user, String password);
    public boolean isMailExists(String mail, long ignoreId);
    public boolean isMailUnique(String login, long ignoreId);
}
