package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SerieInDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(groups = {IRequestCreateValidation.class})
	private Long idTrd;
	private Long finalDisposalType;
	@NotNull(groups = {IRequestCreateValidation.class, 
					   IRequestUpdateValidation.class})
	private String code;
	@NotNull(groups = {IRequestCreateValidation.class,
					   IRequestUpdateValidation.class})
	private String name;
	@NotNull(groups = {IRequestCreateValidation.class, 
					   IRequestUpdateValidation.class})
	private String process;
	@Valid
	@NotNull(groups = {IRequestCreateValidation.class})
	private Long section;
	private Long retentionTime;
}
