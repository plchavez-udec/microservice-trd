package co.edu.ierdminayticha.sgd.trds.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DocumentaryTypeOutDto {

	private Long id;
	private String name;
	private Date creationDate;
	private Date lastModifiedDate;

}
