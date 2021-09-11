package br.com.bmo.testStoreProcedure.mapper;

import java.util.Map;

import br.com.bmo.testStoreProcedure.model.Cat;

public class CatsMapper {

	public static Cat parseFromResultSetRow(Map<String, Object> input) {		
		return new Cat(
			Long.valueOf((Integer) input.get("id")),
			(String) input.get("name"),
			String.valueOf(input.get("gender")).charAt(0),
			(Integer) input.get("age")
		);
	}
}
