package ug.progress.monitoring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.LocationEntity;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.LocationService;
import ug.progress.monitoring.store.LocationStore;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ZR on 02.06.2014.
 */
@Service
public class LocationServiceImpl implements LocationService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    LocationStore store;

    @Override
    public List<LocationEntity> getAllLocations() {
        return store.findAll();
    }

    @Override
    public List<LocationEntity> getLocationsByUserId(Long id) {
        return store.findAllByUserId(id);
    }

    @Override
    public boolean saveLocation(LocationEntity location) {
        if (location == null) {
            return false;
        }else {
            try {
                store.save(location);
                return true;
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }
        }
    }
}
