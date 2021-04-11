package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DocumentaryUnitResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private DocumentaryUnitTypeDto documentaryUnitType;
	private FatherDocumentaryUnitDto fatherDocumentaryUnit;
	private FinalDisposalTypeDto finalDisposalType;
	private TrdOutDto trd;
	private String code;
	private String name;
	private String process;
	private Long retentionTime;
	private Date creationDate;
	private Date lastModifiedDate;

}
