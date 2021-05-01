package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SerieOutDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private FinalDisposalTypeDto finalDisposalType;
	private String code;
	private String name;
	private String process;
	private SectionDto section;
	private Long retentionTime;
	private Date creationDate;
	private Date lastModifiedDate;
	private List<SubSerieOutDto> subSeries;
	private List<DocumentaryTypeOutDto> documentaryTypeList;

}
