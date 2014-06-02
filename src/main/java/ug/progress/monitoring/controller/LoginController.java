package ug.progress.monitoring.controller;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.manager.UserManager;
import ug.progress.monitoring.service.impl.UserServiceImpl;
import ug.progress.monitoring.store.UserStore;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by ZR on 01.06.2014.
 */

@Controller
public class LoginController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    protected UserServiceImpl myAuthenticationManager;

    @Inject
    protected UserStore store = null;

    @Inject
    private PasswordEncoder passwordEncoder;

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


        UserEntity user = store.findByMail(mail);
        if(user == null) {
            logger.warn("Signin Service: User not found: " + mail);
            try {
                response.setContentType("text/xml; charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>error</status><description>User " + mail + " not found !</description></result>");
            } catch (Exception e) {
                logger.warn("Signin Service: response failed!");
            }
        }
        if(password==null){
            logger.warn("Signin Service: Received password is null for user "+ mail);
            try {
                response.setContentType("text/xml; charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>error</status><description> Received password is null for user "+ mail +"!</description></result>");
            } catch (Exception e) {
                logger.warn("Signin Service: response failed!");
            }
        }
        logger.debug("Signin Service:  Attempting to login: "+user.getMail()+":"+password+".");

        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("Signin Service:  Login successful.");
            try {
                response.setContentType("text/xml; charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>ok</status><description>"+ user.getId() +"</description></result>");
            } catch (Exception e) {
                logger.warn("Signin Service: response failed!");
            }
        }
        logger.debug("Signin Service:  Login failed.");
        try {
            response.setContentType("text/xml; charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><status>error</status><description> Login failed </description></result>");
        } catch (Exception e) {
            logger.warn("Signin Service: response failed!");
        }



    return "";
    }
}
