package ug.progress.monitoring.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by ZR on 02.06.2014.
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