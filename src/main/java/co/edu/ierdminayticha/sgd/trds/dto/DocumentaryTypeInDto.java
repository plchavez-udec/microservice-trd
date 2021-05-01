package co.edu.ierdminayticha.sgd.trds.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DocumentaryTypeInDto {

	@NotNull(groups = {IRequestCreateValidation.class, 
					   IRequestUpdateValidation.class})
	private String name;
	private Long idSerie;
	private Long idSubSerie;
}
