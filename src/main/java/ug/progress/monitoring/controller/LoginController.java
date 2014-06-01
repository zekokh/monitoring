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

    /**
     * Непосредственно обработка авторизации
     *
     * @param mail     имя пользователя
     * @param password пароль пользователя
     * @param response http response
     * @param model    модель данных
     * @return ссылка на страницу, которая реализует представление
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String check(@RequestParam("j_username") String mail, @RequestParam("j_password") String password,
                        @RequestHeader(value = "referer", defaultValue = "/") String headerReferer,
                        @RequestParam(value = "referer", defaultValue = "") String requestReferer,
                        HttpServletResponse response, ModelMap model) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(mail, password);
        String referer = StringUtils.defaultIfEmpty(requestReferer, headerReferer);
        if (referer.contains("/login")) referer = "/";
        try {
            Authentication auth = myAuthenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            //sessionRegistry.registerNewSession(UUID.randomUUID().toString(), auth.getPrincipal());
            if (!auth.isAuthenticated()) {
                model.put("referer", referer);
                model.put("error", "Введенные логин и пароль не совпадают");
            } else {
                response.sendRedirect(referer);
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            model.put("error", "Во время авторизации произошла ошибка");
        }
        return "Login";
    }
}
