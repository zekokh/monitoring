package ug.progress.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZR on 01.06.2014.
 */
@Controller
public class GpsController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/geolocation")
    public void acceptLocation(@RequestParam("longitude") String longitude,
                                 @RequestParam("latitude") String latitude,
                                 @RequestParam("id") String id, HttpServletResponse response){
    UserEntiry user
    }
}
