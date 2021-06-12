package co.edu.ierdminayticha.sgd.trds.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class DocumentaryTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqDocumentaryType")
	@SequenceGenerator(name = "SeqDocumentaryType", sequenceName = "\"SEQ_TIPOS_DOCUMENTALES_ID\"", allocationSize = 1)
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"NOMBRE\"")
	private String name;

	@Column(name = "\"ES_ELIMINADO\"")
	private Boolean isDeleted;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "\"ID_SERIE_FK\"")
	private SerieEntity serie;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "\"ID_SUB_SERIE_FK\"")
	private SubSerieEntity subSerie;

}
