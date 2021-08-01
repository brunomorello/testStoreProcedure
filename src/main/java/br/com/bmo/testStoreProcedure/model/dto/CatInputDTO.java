package br.com.bmo.testStoreProcedure.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bmo.testStoreProcedure.model.Cat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatInputDTO {

	@NotBlank @NotNull
	private String name;
	
	@NotNull
	private char gender;
	
	@Min(1)
	private Integer age;
	
	public Cat parse() { 
		Cat cat = new Cat();
		cat.setName(name);
		cat.setAge(age);
		cat.setGender(gender);
		return cat;
	}
}
