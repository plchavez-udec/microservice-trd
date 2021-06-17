package co.edu.ierdminayticha.sgd.trds.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SubSerieOutDto {

	private Long idTrdParent;
	private Long idSectionParent;
	private Long idSerieParent;
	private Long id;
	private FinalDisposalTypeDto finalDisposalType;
	private String code;
	private String name;
	private String process;
	private Long retentionTime;
	private Date creationDate;
	private Date lastModifiedDate;
	private List<DocumentaryTypeInDto> documentaryTypeList;

}
