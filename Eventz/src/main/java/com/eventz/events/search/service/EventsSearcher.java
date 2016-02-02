package com.eventz.events.search.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eventz.events.search.model.SearchRequest;
import com.eventz.events.search.model.SearchResponse;
import com.eventz.events.utils.SearchUtils;

@Service
public class EventsSearcher {

	private static final Logger logger = LoggerFactory.getLogger(EventsSearcher.class);
	
	final String APP_KEY = "Vpdxjp7ZX8JHHkJr";
	
	final String FUTURE = "Future";
	
	final String EVENTS = "events";
	
	final String EVENT = "event";
	
	final String PAGE_NO = "page_number";
	
	final String PAGE_SIZE = "page_size";
	
	final String TOTAL_ITEMS = "total_items";
	
	final String CATEGORY = "category";

	public JSONArray getRootCategories() throws IOException {
		StringBuilder url = new StringBuilder();
		url.append("http://api.evdb.com/json/categories/list?app_key="); 
		url.append(APP_KEY);
		logger.info("Url: " + url);
		
		return JSONObject.fromObject(stringOfUrl(url.toString())).getJSONArray(CATEGORY);
	}
	
	public JSONObject getEvent(String id) throws IOException {
		StringBuilder url = new StringBuilder();
		url.append("http://api.evdb.com/json/events/get?app_key="); 
		url.append(APP_KEY + "&&id=" );
		url.append(id);
		
		return JSONObject.fromObject(stringOfUrl(url.toString()));
	}
	
	public SearchResponse searchEventFul(SearchRequest request) throws MalformedURLException, IOException {
		StringBuilder url = new StringBuilder();
		url.append("http://api.eventful.com/json/events/search?app_key="); 
		url.append(APP_KEY + "&keywords=" );
		url.append(	URLEncoder.encode(request.getSearchTerm(), "UTF-8") + "&location=" );
		url.append(	URLEncoder.encode(request.getCity(), "UTF-8"));	
		
		addOptionalParameters(url, request);
		return getEvents(stringOfUrl(url.toString()), request);
	}
	
	private void addOptionalParameters(StringBuilder url, SearchRequest request) {
		if (request.getPage() != null)
			url.append("&page_number=" + request.getPage());
		if (request.getDistance() > 0)
			url.append("&within=" + request.getDistance());
		if (request.getDate() != null)
			url.append("&date=" + getDateString(request.getDate()));
		else
			url.append("&date=" + FUTURE);
		if (request.getCategory() != null)
			url.append("&category=" + request.getCategory());
		logger.info("Url: " + url);
	}

	private String getDateString(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(SearchUtils.DATE_FORMAT);
		return sdf.format(new Date()) + "00-" + date + "00";
	}

	public static String stringOfUrl(String addr) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URL url = new URL(addr);
        IOUtils.copy(url.openStream(), output);
        return output.toString();
    }
	
	public SearchResponse getEvents(String string, SearchRequest request) {
		
		SearchResponse response = new SearchResponse();
		response.setSearchRequest(request);
		JSONObject root = JSONObject.fromObject(string);
		
		if (JSONUtils.isNull(root.get(EVENTS))) {
			response.setResults(new JSONArray());
			return response;
		}
		JSONObject obj = (JSONObject) root.get(EVENTS);
		if (JSONUtils.isArray(obj.get(EVENT))) {
			response.setResults(obj.getJSONArray(EVENT));
		} else {
			JSONArray array = new JSONArray();
			array.add(obj.getJSONObject(EVENT));
			response.setResults(array);
		}
			
		response.setPage(root.getString(PAGE_NO));
		response.setPageSize(root.getString(PAGE_SIZE));
		response.setTotalItemsCount(root.getString(TOTAL_ITEMS));
		
		return response;
	}
	
}
