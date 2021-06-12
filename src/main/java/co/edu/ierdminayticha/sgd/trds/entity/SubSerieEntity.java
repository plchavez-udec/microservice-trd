package co.edu.ierdminayticha.sgd.trds.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"SUBSERIES\"")
public class SubSerieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqSubSerieId")
	@SequenceGenerator(name = "SeqSubSerieId", allocationSize = 1, sequenceName = "\"SEQ_SUB_SERIE_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "\"ID_TIPO_DISPOSICION_FK\"")
	private FinalDisposalTypeEntity finalDisposalType;

	@Column(name = "\"CODIGO\"")
	private String code;

	@Column(name = "\"NOMBRE\"")
	private String name;

	@Column(name = "\"PROCEDIMIENTO\"")
	private String process;

	@Column(name = "\"TIEMPO_RETENCION\"")
	private Long retentionTime;

	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;

	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "\"ID_SERIE_FK\"")
	private SerieEntity serie;

	@OneToMany(mappedBy = "subSerie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DocumentaryTypeEntity> documentaryTypeList;
	
	public void addDocumentaryType(DocumentaryTypeEntity dmt) {
		if (this.documentaryTypeList == null)
			this.documentaryTypeList = new ArrayList<>();
		this.documentaryTypeList.add(dmt);
		dmt.setSubSerie(this);
	}

}
