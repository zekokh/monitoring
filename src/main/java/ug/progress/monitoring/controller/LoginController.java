package ug.progress.monitoring.controller;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.impl.UserServiceImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZR on 01.06.2014.
 */

@Controller
public class LoginController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    protected UserServiceImpl myAuthenticationManager;

    /**
     * Точка входа для авторизации
     *
     * @return ссылка на страницу, которая реализует представление
     */
    @RequestMapping("/login")
    public String login() {

        return "Login";
    }

    @RequestMapping("/loginfail")
    public String loginfail(ModelMap model) {
        model.put("error", "Введенные логин и пароль не совпадают");
        return "Login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@RequestParam("mail") String mail,
                         @RequestParam("password") String password,
                         HttpServletResponse response){

        UserEntity user = null;


    return "";
    }
}
