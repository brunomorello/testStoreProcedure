package br.com.bmo.testStoreProcedure.model.dto;

import java.util.List;

import br.com.bmo.testStoreProcedure.model.Cat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class CatsResponseDTO {

	private List<Cat> cats;
}
