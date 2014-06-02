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

/**
 * Created by ZR on 01.06.2014.
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
                                 @RequestParam("id") String id, HttpServletResponse response){

        UserEntity user = null;
        if(id != null) {
           user =  userStore.getUserById(Long.parseLong(id));
            if(user != null) {
                LocationEntity newLocation = null;
                newLocation.setLongitude(Double.parseDouble(longitude));
                newLocation.setLatitude(Double.parseDouble(latitude));
                newLocation.setAppleId(appleId);
                locationStore.saveLocation(newLocation);
                try{
                    response.setContentType("text/xml; charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>OK</status><description>Data added!</description></result>");
                }catch (Exception e){
                    logger.warn("Failed get ");
                }

            }
        }else  {
            try{
                response.setContentType("text/xml; charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>3</status><description>Error!</description></result>");
            }catch (Exception e){
                logger.warn("Failed get 2 ");
            }
        }
    }
}
