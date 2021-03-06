package ug.progress.monitoring.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ug.progress.monitoring.customValidation.LoginConstraint;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Zekokh.
 */
@Entity
@Table(name = "user", schema = "public")
@LoginConstraint
public class UserEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = -1017971023631083571L;
    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_key_gen")
    @SequenceGenerator(sequenceName = "user_id_seq", allocationSize = 1, name = "user_id_key_gen")
    private Long id;

    /**
     * Почта пользователя
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Пароль пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * Дата регистрации пользователя
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_registration")
    private Date dateRegistration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @NotEmpty(message = "Должна быть задана хотя бы одна роль")
    private List<RoleEntity> userRoles = new ArrayList<RoleEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = getGrantedAuthorities(getRolesName(getUserRoles()));
        return list;
    }


    public List<String> getRolesName(List<RoleEntity> roles) {
        List<String> rolesName = new ArrayList<String>();
        for (RoleEntity role : roles) {
            rolesName.add(role.getRoleName());
        }
        return rolesName;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public List<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
