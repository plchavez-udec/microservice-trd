package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DocumentaryTypesResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private DocumentaryTypeOutDto documentanyUnit;
	private Boolean isDeleted;
	private Date creationDate;
	private Date lastModifiedDate;

}
