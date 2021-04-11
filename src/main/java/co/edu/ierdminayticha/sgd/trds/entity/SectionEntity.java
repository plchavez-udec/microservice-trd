package co.edu.ierdminayticha.sgd.trds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"SECCION\"")
public class SectionEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_SECCION_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_SECCION_ID\"") 
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"CODIGO\"")
	private String code;
	
	@Column(name = "\"NOMBRE\"")
	private String name;

}