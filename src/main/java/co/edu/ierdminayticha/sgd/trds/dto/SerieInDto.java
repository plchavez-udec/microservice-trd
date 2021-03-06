package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.List;

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
	private String process;
	@Valid
	@NotNull(groups = {IRequestCreateValidation.class})
	private Long section;
	private Long retentionTime;
	
	@Valid
	private List<DocumentaryTypeInDto> documentaryTypeList;
}
