package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DocumentaryTypeInDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull(groups = {IRequestCreateValidation.class})
	private String name;
	
}
