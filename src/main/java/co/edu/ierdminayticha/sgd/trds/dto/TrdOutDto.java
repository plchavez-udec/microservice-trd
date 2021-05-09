package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TrdOutDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String version;
	private Date creationDate;
	private Date lastModifiedDate;

}