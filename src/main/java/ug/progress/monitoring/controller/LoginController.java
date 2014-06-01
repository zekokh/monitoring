package ug.progress.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ZR on 01.06.2014.
 */

@Controller
public class LoginController {

    /**
     * Точка входа для авторизации
     *
     * @return ссылка на страницу, которая реализует представление
     */
    @RequestMapping("/login")
    public String login(){ return "Login";}

    /**
     * Непосредственно обработка авторизации
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param response http response
     * @param model    модель данных
     * @return ссылка на страницу, которая реализует представление
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String check(@RequestParam("mail") String mail, @RequestParam("password") String password){

    }
}
