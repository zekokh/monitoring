package ug.progress.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.impl.UserServiceImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZR on 02.06.2014.
 */
@Controller
public class AddUserController {

    @Inject
    protected UserServiceImpl userService;

    @RequestMapping("/add")
    public void addUsers(@RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("mail") String mail,
                               @RequestParam("password") String password, HttpServletResponse response){

        UserEntity a = null;
        a.setFirstName("Ruslan");
        a.setLastName("Zekokh");
        a.setMail("ruslan@mail.ru");
        a.setPassword("123");
        userService.saveUser(a);
    }

}
