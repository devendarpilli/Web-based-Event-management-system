package com.eventz.events.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LocationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("city") == null && !request.getRequestURL().toString().contains("change/city")
				&& !request.getRequestURL().toString().contains("/resources") && !request.getRequestURL().toString().contains("/complete")
				&& !request.getRequestURL().toString().contains("/api/")) {
			response.sendRedirect( request.getContextPath() + "/change/city");
			return false;
		}
		return true;
	}
	
}
