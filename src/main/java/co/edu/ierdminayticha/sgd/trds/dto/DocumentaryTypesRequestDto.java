package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentaryTypesRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idDocumentaryUnit;
	private String name;

}
