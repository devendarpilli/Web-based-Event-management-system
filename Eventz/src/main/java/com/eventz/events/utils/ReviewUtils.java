package com.eventz.events.utils;

import javax.servlet.http.HttpServletRequest;

import com.eventz.events.common.model.Review;
import com.eventz.events.login.model.User;


public class ReviewUtils {

	public static Review getReviewFromRequest(HttpServletRequest req, String eventId) {
		Review review = new Review();
		review.setEventId(eventId);
		User user = UserUtils.getUserFromSession(req.getSession());
		if (user != null)
			review.setUserName(user.getFirstName());
		else
			review.setUserName("anonymous");
		review.setReviewText(req.getParameter("review_text"));
		return review;
	}
}
