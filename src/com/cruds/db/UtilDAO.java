package com.cruds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String getNextValForSeq(String seqname)
	{
		String sql3 = "select next value for " + seqname;
		String seqvalue = jdbcTemplate.queryForObject(
				sql3, new Object[] {}, String.class);
		return seqvalue;
		
	}
	
}
