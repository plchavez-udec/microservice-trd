package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentaryUnitResponseListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private DocumentaryUnitTypeDto documentaryUnitType;
	private String code;
	private String name;
	private String process;

}
