package ug.progress.monitoring.store;

import org.springframework.data.jpa.repository.JpaRepository;
import ug.progress.monitoring.entity.RoleEntity;

/**
 * Created by ZR on 01.06.2014.
 */
public interface RoleStore extends JpaRepository<RoleEntity, Long> {

    public RoleEntity findByRoleName(String roleName);

    public RoleEntity findByDescription(String description);
}