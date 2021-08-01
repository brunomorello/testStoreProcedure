package br.com.bmo.testStoreProcedure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CATS", schema = "PUBLIC")
@NamedStoredProcedureQuery(
	name = "Cat.getCatsOlderThan",
	procedureName = "PUBLIC.PUBLIC.FIND_CATS_OLDER_THAN",
	resultClasses = { Cat.class },
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "age_in", type = Integer.class)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private char gender;
	private Integer age;
	
}