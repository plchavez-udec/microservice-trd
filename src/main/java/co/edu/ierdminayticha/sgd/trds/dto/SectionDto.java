package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SectionDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;

}