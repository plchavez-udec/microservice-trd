package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class FinalDisposalTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String initials;
	private String name;

}