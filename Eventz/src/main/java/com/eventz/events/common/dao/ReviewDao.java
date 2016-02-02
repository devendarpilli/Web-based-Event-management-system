package com.eventz.events.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eventz.events.common.model.City;
import com.eventz.events.common.model.Review;
import com.eventz.events.login.model.User;

@Repository
public class ReviewDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.setJdbcTemplate(new JdbcTemplate(dataSource));
    }
    
    public List<Review> findReviews(String id) {
    	
    	String sql = "select * from reviews where event_id = '" + id + "'";
    	
		return getJdbcTemplate().query(sql, new RowMapper<Review>() {

			public Review mapRow(ResultSet rs, int index) throws SQLException {
				Review review = new Review();
				review.setEventId(rs.getString("event_id"));
				review.setUserName(rs.getString("user_name"));
				review.setReviewText(rs.getString("review_text"));
				return review;
			}
			
		});
	}
    
    public int addReview(Review review) {
    	return getJdbcTemplate().update("insert into reviews(event_id, user_name, review_text) values(?,?,?)", new Object[] {
    			review.getEventId(), review.getUserName(), review.getReviewText()
    	});
    }

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
