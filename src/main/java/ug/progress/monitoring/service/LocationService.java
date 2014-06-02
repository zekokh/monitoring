package ug.progress.monitoring.service;

import org.springframework.stereotype.Service;
import ug.progress.monitoring.entity.LocationEntity;

import java.util.List;

/**
 * Created by ZR on 02.06.2014.
 */
@Service
public interface LocationService {

    public List<LocationEntity> getAllLocations();
    public List<LocationEntity> getLocationsByUserId(Long id);
    public boolean saveLocation(LocationEntity location);

}
