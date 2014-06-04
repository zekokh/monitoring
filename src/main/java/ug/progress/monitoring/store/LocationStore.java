package ug.progress.monitoring.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import ug.progress.monitoring.entity.LocationEntity;

import java.util.List;

/**
 * Created by ZR on 02.06.2014.
 */
public interface LocationStore extends JpaRepository<LocationEntity, Long>, QueryDslPredicateExecutor<LocationEntity> {

    public List<LocationEntity> findAll();
    public List<LocationEntity> findAllByUserId (String id);
    public LocationEntity findByUserId (String userId);

}
