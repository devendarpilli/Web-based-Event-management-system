package com.eventz.events.utils;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.eventz.events.search.model.SearchRequest;

public class SearchUtils {
	
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static SearchRequest getSearchRequest(HttpServletRequest request) throws ParseException {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setSearchTerm(request.getParameter("q"));
		searchRequest.setCity(request.getSession().getAttribute("city").toString());
		searchRequest.setPage(request.getParameter("page"));
		String submit = request.getParameter("submit");
		if ("Apply".equalsIgnoreCase(submit)) {
			if (request.getParameter("date") != null) {
				searchRequest.setDate(request.getParameter("date"));
			}
			if (request.getParameter("distance") != null && request.getParameter("distance").length() > 0)
				searchRequest.setDistance(Integer.parseInt(request.getParameter("distance")));
		}
		
		return searchRequest;
	}

	public static SearchRequest getBrowseRequest(HttpServletRequest request, String id) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setPage(request.getParameter("page"));
		searchRequest.setCategory(id);
		searchRequest.setSearchTerm("");
		searchRequest.setCity(request.getSession().getAttribute("city").toString());
		return searchRequest;
	}
}
