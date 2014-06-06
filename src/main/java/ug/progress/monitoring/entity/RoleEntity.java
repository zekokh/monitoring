package ug.progress.monitoring.entity;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ruslan Zekokh.
 */
@Entity
@Table(name = "role")
public class RoleEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_key_gen")
    @SequenceGenerator(sequenceName = "role_id_seq", allocationSize = 1, name = "role_id_key_gen")
    @Column(name = "id")
    protected Long id;

    @Column(name = "role_name", nullable = false)
    protected String roleName = null;

    @Column(name = "description", nullable = false)
    protected String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
