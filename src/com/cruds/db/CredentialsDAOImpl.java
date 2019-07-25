package com.cruds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cruds.entity.Credentials;

@Repository
public class CredentialsDAOImpl implements CredentialsDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String loginCheck(Credentials c) {
		String sql = "select userType from loginCheck where userId LIKE (?) AND password LIKE (?)";
		String userType = "";
		try {
			userType = (String)getJdbcTemplate().queryForObject(
					sql, new Object[] {c.getUserId(),c.getPassword()  }, String.class);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userType;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
