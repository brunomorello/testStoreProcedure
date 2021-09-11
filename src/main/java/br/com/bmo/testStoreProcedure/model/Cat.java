package br.com.bmo.testStoreProcedure.model;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CATS", schema = "PUBLIC")
@SqlResultSetMapping(
	name = "CatsResults",
	entities = {
		@EntityResult(entityClass = br.com.bmo.testStoreProcedure.model.Cat.class, 
				fields = {
						@FieldResult(name = "id", column = "ID"),
						@FieldResult(name = "name", column = "NAME"),
						@FieldResult(name = "gender", column = "GENDER"),
						@FieldResult(name = "age", column = "AGE")
		})
	}
)
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "Cat.getCatsOlderThan",
			procedureName = "FIND_CATS_OLDER_THAN",
			resultSetMappings = { "CatsResults" },
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "age_in", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = Cat.class)
			})
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