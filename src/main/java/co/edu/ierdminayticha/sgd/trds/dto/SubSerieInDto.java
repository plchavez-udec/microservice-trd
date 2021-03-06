package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubSerieInDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(groups = IRequestCreateValidation.class)
	private Long idSerie;
	@NotNull(groups = {IRequestCreateValidation.class,
					   IRequestUpdateValidation.class})
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
	@NotNull(groups = {IRequestCreateValidation.class,
			   		   IRequestUpdateValidation.class})
	private Long retentionTime;
	@Valid
	@NotNull(groups = {IRequestCreateValidation.class})
	private List<DocumentaryTypeInDto> documentaryTypeList;
}
