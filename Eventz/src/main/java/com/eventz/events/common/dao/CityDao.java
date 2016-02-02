package com.eventz.events.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eventz.events.common.model.City;

@Repository
public class CityDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.setJdbcTemplate(new JdbcTemplate(dataSource));
    }
    
    public List<City> findCity(String q) {
		return getJdbcTemplate().query("select * from City where Name like '" + q + "%' limit 10", new RowMapper<City>() {

			public City mapRow(ResultSet rs, int index) throws SQLException {
				City city = new City();
				city.setLabel( rs.getString("Name") + " , " + rs.getString("County"));
				city.setValue( rs.getString("Name"));
				city.setDistrict(rs.getString("district"));
				return city;
			}
			
		});
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
