package ug.progress.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZR on 01.06.2014.
 */
@Controller
public class IndexController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/")
    public String index() {
        return "Index";
    }

    @RequestMapping("/version")
    public String version() {
        return "Version";
    }

    @RequestMapping("/accessdenied")
    public String accessDenied() {
        return "AccessDenied";
    }
}
