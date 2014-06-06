package ug.progress.monitoring.service;

import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.RoleEntity;

import java.util.List;

/**
 * Created by Ruslan Zekokh.
 */
@Service
public interface RoleService {

    public List<RoleEntity> getAllRoles();

    public RoleEntity getRoleByDescription(String description);

    public RoleEntity getRoleByName(String roleName);
}
