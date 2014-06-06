package ug.progress.monitoring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.LocationEntity;
import ug.progress.monitoring.service.LocationService;
import ug.progress.monitoring.store.LocationStore;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ruslan Zekokh.
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
    public List<LocationEntity> getLocationsByUserId(String id) {
        return store.findAllByUserId(id);
    }

    @Override
    public LocationEntity getOneLocationsByUserId(String id) {
            return store.findByUserId(id);
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

    @Override
    public boolean updateLocation(LocationEntity location) {
        try{
            LocationEntity oldLocation = store.findByUserId(location.getUserId());
            oldLocation.setLatitude(location.getLatitude());
            oldLocation.setLongitude(location.getLongitude());
            oldLocation.setAppleId(location.getAppleId());
            oldLocation.setDate(location.getDate());
            LocationEntity savedLocation = store.save(oldLocation);
            return savedLocation != null;
        } catch (Exception e) {
        logger.error(e.getMessage());
    }
        return false;
    }
}
