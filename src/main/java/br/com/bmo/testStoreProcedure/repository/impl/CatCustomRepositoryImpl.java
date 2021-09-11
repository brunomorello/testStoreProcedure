package br.com.bmo.testStoreProcedure.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import br.com.bmo.testStoreProcedure.mapper.CatsMapper;
import br.com.bmo.testStoreProcedure.model.Cat;
import br.com.bmo.testStoreProcedure.repository.CatCustomRepository;

public class CatCustomRepositoryImpl implements CatCustomRepository{

	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public List<Cat> getCatsOlderThan(Integer age) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(datasource).withSchemaName("catsys").withProcedureName("FIND_CATS_OLDER_THAN");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("age_in", age);
		Map<String, Object> out = jdbcCall.execute(in);
		
		System.out.println(out.get("#result-set-1"));
		
		List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("#result-set-1");
		
		return result.stream()
			.map(CatsMapper::parseFromResultSetRow)
			.collect(Collectors.toList());
		
	}


}
