package ug.progress.monitoring.handler;

import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZR on 01.06.2014.
 */
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    protected String redirectUrl;

    public AccessDeniedHandler() {
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        try {
            response.sendRedirect(redirectUrl);
        } catch (Exception ignore) {
        }
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}