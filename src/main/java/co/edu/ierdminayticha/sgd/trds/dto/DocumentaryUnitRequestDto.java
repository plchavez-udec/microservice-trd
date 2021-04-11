package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DocumentaryUnitRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(groups = IRequestCreateValidation.class)
	private Long documentaryUnitType;
	private Long fatherDocumentaryUnit;
	private Long finalDisposalType;
	private Long section;
	private Long retentionTime; 
	@NotNull(groups = IRequestCreateValidation.class)
	private Long trd;
	@NotNull(groups = IRequestCreateValidation.class)
	private String code;
	@NotNull(groups = {IRequestCreateValidation.class, IRequestUpdateValidation.class})
	private String name;
	private String process;

}
