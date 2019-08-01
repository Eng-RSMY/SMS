package com.dream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dream.model.User;
import com.dream.service.UserService;

/**
 * The GlobalController Class
 *
 * @author Dileep
 * @version 1.0 
 * Date 20/05/2019.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GlobalController {

	@Autowired
	private UserService userService;

	private User loginUser;

	public User getLoginUser() {
		if (loginUser == null) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			loginUser = userService.findByName(auth.getName());
		}
		return loginUser;
	}
}
