package com.dream.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The MyAuthenticationSuccessHandler class
 *
 * @author Dileep
 * @version 1.0
 * Date 20/05/2019.
 */
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        System.out.println("handle: " + targetUrl);

        if (response.isCommitted()) {
            logger.warn("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        logger.info("determineTargetUrl: " + authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> users = new ArrayList<>();
        for (GrantedAuthority a : authorities) {
            logger.info("Authority: " + a.getAuthority());
            users.add(a.getAuthority());
            
        }

        if (isAdminOrHM(users)) {
            return "/admin";
        } else if (isAttender(users) || isTeacher(users) || isParent(users) || isStudent(users)) {
            return "/home";
        } else {
            return "/login?error";
        }
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isAdminOrHM(List<String> users) {
        return users.contains("HM");
    }

    private boolean isTeacher(List<String> users) {
        return users.contains("Teacher");
    }
    
    private boolean isAttender(List<String> users) {
        return users.contains("Attender");
    }

    private boolean isParent(List<String> users) {
        return users.contains("Parent");
    }
    
    private boolean isStudent(List<String> users) {
        return users.contains("Student");
    }

}
