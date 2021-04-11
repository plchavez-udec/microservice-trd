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
@Table(name = "\"TIPOS_DISPOSICION_FINAL\"")
public class FinalDisposalTypeEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_TIPOS_DISPOSICION_FINAL_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_TIPOS_DISPOSICION_FINAL_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"INICIALES\"")
	private String initials;

	@Column(name = "\"NOMBRE\"")
	private String name;

}