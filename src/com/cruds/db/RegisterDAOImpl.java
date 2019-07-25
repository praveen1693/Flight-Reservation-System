package com.cruds.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cruds.entity.User;


@Repository
public class RegisterDAOImpl implements RegisterDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(User user) {
		
		String sql3 = "select next value for frs_seq_userid";
		String seqUserId = (String)getJdbcTemplate().queryForObject(
				sql3, new Object[] {}, String.class);
		String subStr = user.getFirstName().substring(0, 2);
		String dummyUserID = subStr+seqUserId;
		user.setUserId(dummyUserID);
		
		
		String sql1 = "insert into logincheck values(?,?,?,?)";
		jdbcTemplate.update(sql1, new Object[]{user.getUserId(),user.getPassword(),user.getUserType(),0});
		
		String sql2 = "insert into registration values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql2, new Object[]{user.getUserId(),user.getFirstName(),user.getLastName(),user.getDOB(),
							user.getGender(),user.getStreet(),user.getLocation(),user.getCity(),user.getState(),user.getPincode(),
							user.getPhNo(),user.getEmailId(),user.getPassword()});
		
	}
	
}
