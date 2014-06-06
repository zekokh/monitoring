package ug.progress.monitoring.interceptor;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ug.progress.monitoring.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Created by Ruslan Zekokh.
 */

/**
 * Этот интерцептор будет перехватывать все обращения к контроллерам и вставлять в
 * сессию пользователей авторизованного пользователя
 */
public class UserInterceptor implements HandlerInterceptor {

    /**
     * Обработка запроса до того как он придет к контроллеру
     *
     * @param request  http-запрос
     * @param response hgttp-ответ
     * @param handler  непонятное что-то
     * @return
     * @throws Exception вдруг что-то произошло
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication();
        try {
            Object objectDetails = authentication.getDetails();
            Object objectPrincipal = authentication.getPrincipal();
            if (request.getSession().getAttribute("user") == null) {
                if (objectDetails instanceof UserEntity) {
                    request.getSession().setAttribute("user", objectDetails);
                } else if (objectPrincipal instanceof  UserEntity) {
                    request.getSession().setAttribute("user", objectPrincipal);
                }
            }
        } catch (Exception ignored) {
            //нам не нужно тут ничего обрабатывать. Эта ошибка произойдет только в непонятном случае, когда у запроса
            // нет сессии. Такая ошибка бывает, но мы ее проигнорируем :)
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
}
