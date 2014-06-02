package ug.progress.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ug.progress.monitoring.entity.LocationEntity;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.LocationService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZR on 01.06.2014.
 */
@Controller
public class IndexController {

    @Inject
    LocationService locationService;

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/")
    public String index(ModelMap model, HttpSession session) {
        try{
            UserEntity users = (UserEntity) session.getAttribute("user");
            Long userId = users.getId();
            List<LocationEntity> locations = locationService.getAllLocations();
            if (locations == null)
                model.addAttribute("error", "Нет данных о пользователях");
            else model.addAttribute("loc", locations.get(0));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return "Index";
    }
}
