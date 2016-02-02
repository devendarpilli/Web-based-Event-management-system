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
import com.eventz.events.login.model.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.setJdbcTemplate(new JdbcTemplate(dataSource));
    }
    
    public User findUser(String name, String password) {
    	
    	String sql = "select * from user where email = '" + name + "' limit 1";
    	if (password != null)
    		sql = "select * from user where email = '" + name + "' and password = '"+ password +"'  limit 1";
		List<User> users = getJdbcTemplate().query(sql, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setEmail(rs.getString("email"));
				user.setActive(true);
				return user;
			}
			
		});
		if (users != null && users.size() > 0)
			return users.get(0);
		return null;
	}
    
    public int addUser(User user) {
    	return getJdbcTemplate().update("insert into user(first_name, email, password) values(?,?,?)", new Object[] {
    			user.getFirstName(), user.getEmail(), user.getPassword()
    	});
    }

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
