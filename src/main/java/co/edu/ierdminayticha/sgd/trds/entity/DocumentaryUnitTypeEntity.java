package co.edu.ierdminayticha.sgd.trds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"TIPOS_UNIDADES_DOCUMENTALES\"")
public class DocumentaryUnitTypeEntity {

	@Id
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"NOMBRE\"")
	private String name;

}