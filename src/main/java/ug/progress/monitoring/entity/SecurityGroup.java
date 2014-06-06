package ug.progress.monitoring.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Ruslan Zekokh.
 */
public class SecurityGroup implements GrantedAuthority {


    public SecurityGroup() {
        super();
    }

    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }
}