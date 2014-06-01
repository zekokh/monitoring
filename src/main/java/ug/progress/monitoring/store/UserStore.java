package ug.progress.monitoring.store;

import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import ug.progress.monitoring.entity.UserEntity;

import java.util.List;

/**
 * Created by ZR on 01.06.2014.
 */
public interface UserStore extends JpaRepository<UserEntity, Long>, QueryDslPredicateExecutor<UserEntity> {
    public List<UserEntity> findAll();
    public UserEntity findById (Long id);
    public UserEntity findByMail (String mail);
}
