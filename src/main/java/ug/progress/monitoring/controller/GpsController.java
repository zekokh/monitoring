package ug.progress.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ug.progress.monitoring.entity.LocationEntity;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.LocationService;
import ug.progress.monitoring.service.UserService;
import ug.progress.monitoring.service.impl.LocationServiceImpl;
import ug.progress.monitoring.service.impl.UserServiceImpl;
import ug.progress.monitoring.store.UserStore;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Ruslan Zekokh.
 */
@Controller
public class GpsController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Inject
    protected UserServiceImpl userStore;

    @Inject
    protected LocationServiceImpl locationStore;

    @RequestMapping(value = "/geolocation", method = RequestMethod.POST)
    public void acceptLocation(@RequestParam("longitude") String longitude,
                               @RequestParam("latitude") String latitude,
                               @RequestParam("appleId") String appleId,
                               @RequestParam("id") String id, HttpServletResponse response) {

        UserEntity user = null;
        if (id != null) {
            user = userStore.getUserById(Long.parseLong(id));
            if (user != null) {
                LocationEntity oldLocation = locationStore.getOneLocationsByUserId(id);
                if (oldLocation != null) {
                    oldLocation.setLongitude(longitude);
                    oldLocation.setLatitude(latitude);
                    oldLocation.setAppleId(appleId);
                    oldLocation.setDate(new Date());
                    locationStore.updateLocation(oldLocation);
                    try {
                        response.setContentType("text/xml; charset=UTF-8");
                        PrintWriter pw = response.getWriter();
                        pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>OK</status><description>Passed!</description></result>");
                    } catch (Exception e) {
                        logger.warn("Geolocation Service: response failed!");
                    }
                } else {
                    LocationEntity newLocation = new LocationEntity();
                    newLocation.setLongitude(longitude);
                    newLocation.setLatitude(latitude);
                    newLocation.setAppleId(appleId);
                    newLocation.setUserId(id);
                    newLocation.setDate(new Date());
                    boolean val = locationStore.saveLocation(newLocation);
                    try {
                        response.setContentType("text/xml; charset=UTF-8");
                        PrintWriter pw = response.getWriter();
                        pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>OK</status><description>Passed!</description></result>");
                    } catch (Exception e) {
                        logger.warn("Geolocation Service: response failed!");
                    }
                }

            } else {
                logger.warn("Geolocation Service: User not found!");
                try {
                    response.setContentType("text/xml; charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>1</status><description>User not found!</description></result>");
                } catch (Exception e) {
                    logger.warn("Geolocation Service: response failed!");
                }
            }
        } else {
            logger.warn("Geolocation Service: userId is empty");
            try {
                response.setContentType("text/xml; charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>2</status><description>User Id is empty!</description></result>");
            } catch (Exception e) {
                logger.warn("Geolocation Service: response failed!");
            }
        }
    }
}
