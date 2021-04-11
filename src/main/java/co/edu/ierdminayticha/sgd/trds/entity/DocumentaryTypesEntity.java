package co.edu.ierdminayticha.sgd.trds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"TIPOS_DOCUMENTALES\"")
public class DocumentaryTypesEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_REFERENCIA_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_REFERENCIA_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "\"ID_UNIDAD_DOCUMENTAL_FK\"")
	private DocumentaryUnitEntity documentaryUnit;

	@Column(name = "\"ES_ELIMINADO\"")
	private Boolean isDeleted;

	@Column(name = "\"NOMBRE\"")
	private String name;

	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;
	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;

}
