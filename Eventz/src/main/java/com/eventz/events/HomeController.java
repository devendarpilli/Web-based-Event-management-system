package com.eventz.events;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sqlite.JDBC;

import com.eventz.events.common.dao.CityDao;
import com.eventz.events.common.dao.ReviewDao;
import com.eventz.events.common.dao.UserDao;
import com.eventz.events.common.model.City;
import com.eventz.events.login.model.User;
import com.eventz.events.search.model.SearchRequest;
import com.eventz.events.search.service.EventsSearcher;
import com.eventz.events.utils.ReviewUtils;
import com.eventz.events.utils.SearchUtils;
import com.eventz.events.utils.UserUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private EventsSearcher eventsSearcher;
	
	@Autowired
	CityDao cityDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	ServletContext context;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@PostConstruct
	public void cacheCategories() throws IOException {
		logger.info("Caching categories ...!");
		context.setAttribute("categories", getEventsSearcher().getRootCategories());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, HttpSession session, Model model) {
		session.invalidate();
		return "home";
	}
	
  @RequestMapping(value = "/api/login", method = {RequestMethod.POST, RequestMethod.GET})
  public @ResponseBody
  Map<String,Object> loginApi(Locale locale, HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model)
      throws IOException {
    
    Map<String,Object> map = new HashMap<String,Object>();
    
    User user = userDao.findUser(request.getParameter("email"),
        request.getParameter("password"));
    if (user != null) {
      map.put("user", user);
    } else {
      map.put("error", "Username or Password is wrong");
    }
    return map;
  }
	
	@RequestMapping(value = "/account", method = {RequestMethod.POST, RequestMethod.GET})
	public String login(Locale locale, HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		
		String submit = request.getParameter("submit");
		List<String> errors = new ArrayList<String>();
		
		if ("Login".equalsIgnoreCase(submit)) {
			User user = userDao.findUser(request.getParameter("email"), request.getParameter("password"));
			if (user != null) {
				UserUtils.addUserToSession(session, user);
				response.sendRedirect(request.getContextPath());
				return null;
			} else {
				errors.add("Username or Password is wrong");
			}
		} else if ("Sign up".equalsIgnoreCase(submit)) {
			User user = UserUtils.getUserFromRequest(request);
			model.addAttribute("user", user);
			if (userDao.findUser(request.getParameter("email"), null) != null) {
				errors.add("Username already exists");
			} else {
				userDao.addUser(user);
				UserUtils.addUserToSession(session, user);
				response.sendRedirect(request.getContextPath());
				return null;
			}
		}
		model.addAttribute("errors", errors);
		return "login";
	}
	
	@RequestMapping(value = "/change/city", method = RequestMethod.GET)
	public String changeCity(Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String city = null;
		if ("Save".equalsIgnoreCase(request.getParameter("submit")))
			city = request.getParameter("city2");
		else
			city = request.getParameter("city");
		if (city != null) {
			session.setAttribute("city", city);
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "change-city";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, HttpServletRequest request, Model model) throws MalformedURLException, IOException, ParseException {
		SearchRequest searchRequest = SearchUtils.getSearchRequest(request);
		model.addAttribute("results", getEventsSearcher().searchEventFul(searchRequest));
		return "search";
	}
	
	@RequestMapping(value = "/browse/{id}", method = RequestMethod.GET)
	public String browse(Locale locale, @PathVariable String id, HttpServletRequest request, Model model) throws MalformedURLException, IOException, ParseException {
		SearchRequest searchRequest = SearchUtils.getBrowseRequest(request, id);
		model.addAttribute("results", getEventsSearcher().searchEventFul(searchRequest));
		return "browse";
	}
	
	@RequestMapping(value = "/view/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String view(Locale locale, @PathVariable String id, HttpServletRequest request, Model model) throws MalformedURLException, IOException {
		String submit = request.getParameter("submit");
		if ("Post".equalsIgnoreCase(submit))
			reviewDao.addReview(ReviewUtils.getReviewFromRequest(request, id));
		model.addAttribute("event", getEventsSearcher().getEvent(id));
		model.addAttribute("reviews", reviewDao.findReviews(id));
		return "view";
	}
	
	@RequestMapping(value="/complete", method=RequestMethod.GET)
	public @ResponseBody List<City> getSuggestions(@RequestParam String term) {
	    return cityDao.findCity(term);
	}

	
	

	public EventsSearcher getEventsSearcher() {
		return eventsSearcher;
	}

	public void setEventsSearcher(EventsSearcher eventsSearcher) {
		this.eventsSearcher = eventsSearcher;
	}
	
}
