package com.eventz.events.mappings.login;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eventz.events.login.model.User;

public interface UserMapper {

	final String SELECT_USER = "SELECT * FROM user WHERE id = #{userId}";

	@Select(SELECT_USER)
	User getUser(@Param("id") String userId);

	void updateUser(User user);
}
