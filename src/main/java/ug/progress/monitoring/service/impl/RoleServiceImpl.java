package ug.progress.monitoring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.RoleEntity;
import ug.progress.monitoring.service.RoleService;
import ug.progress.monitoring.store.RoleStore;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ZR on 01.06.2014.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    RoleStore store;

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * @return список всех ролей
     */
    public List<RoleEntity> getAllRoles() {
        return store.findAll();
    }


    /**
     * @param roleName имя роли
     * @return роль с этим именем
     */
    public RoleEntity getRoleByName(String roleName) {
        RoleEntity role = null;
        try {
            role = store.findByRoleName(roleName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return role;
    }

    /**
     * @param description описание роли
     * @return роль с этим описанием
     */
    public RoleEntity getRoleByDescription(String description) {
        RoleEntity role = null;
        try {
            role = store.findByDescription(description);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return role;
    }
}
