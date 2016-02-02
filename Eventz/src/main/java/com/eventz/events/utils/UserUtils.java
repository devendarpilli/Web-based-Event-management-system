package com.eventz.events.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eventz.events.login.model.User;


public class UserUtils {

	public final static String USER_STR = "user";
	
	public static boolean isAuthorized(HttpSession httpSession) {
		if (httpSession.getAttribute(USER_STR) != null)
			return true;
		else
			return false;
	}
	
	public static void addUserToSession(HttpSession httpSession, User user) {
		httpSession.setAttribute(USER_STR, user);
	}
	
	public static User getUserFromSession(HttpSession httpSession) {
		return (User) httpSession.getAttribute(USER_STR);
	}
	
	public static User getUserFromRequest(HttpServletRequest req) {
		User user = new User();
		user.setFirstName(req.getParameter("fname"));
		user.setEmail(req.getParameter("email"));
		user.setActive(true);
		user.setCreatedOn(new Date());
		user.setPassword(req.getParameter("password"));
		return user;
	}
}
