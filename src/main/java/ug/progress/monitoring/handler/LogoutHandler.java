package ug.progress.monitoring.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ug.progress.monitoring.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ruslan Zekokh.
 */
public class LogoutHandler implements LogoutSuccessHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * Обработка действий после выхода
     *
     * @param request        http запрос
     * @param response       http ответ
     * @param authentication запись авторизации под которой пользователь работал
     * @throws java.io.IOException            вдруг где-то что-то случится
     * @throws javax.servlet.ServletException вдруг где-то что-то случится
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        try {
            Object object = authentication.getPrincipal();
            UserEntity user = null;
            if (object instanceof UserEntity) {
                user = (UserEntity) object;
            }
            if (user != null) {
                logger.info("User " + user.getMail() + " lost the system");
            }
            response.sendRedirect(request.getContextPath()+"/login");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}