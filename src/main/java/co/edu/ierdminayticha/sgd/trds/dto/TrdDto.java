package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TrdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(groups = { IRequestCreateTrd.class, IRequestUpdateTrd.class })
	private String description;
	@NotNull(groups = { IRequestCreateTrd.class })
	private String version;
	private Date creationDate;
	private Date lastModifiedDate;

}
