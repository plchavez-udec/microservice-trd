package co.edu.ierdminayticha.sgd.trds.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "\"UNIDADES_DOCUMENTALES\"")
public class DocumentaryUnitEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_REFERENCIA_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_REFERENCIA_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "\"ID_TIPO_UNIDAD_DOC_FK\"")
	private DocumentaryUnitTypeEntity documentaryUnitType;

	@ManyToOne
	@JoinColumn(name = "\"ID_UNI_DOC_PADRE_FK\"")
	private DocumentaryUnitEntity fatherDocumentaryUnit;

	@ManyToOne
	@JoinColumn(name = "\"ID_TIPO_DISPOSICION_FK\"")
	private FinalDisposalTypeEntity finalDisposalType;

	@ManyToOne
	@JoinColumn(name = "\"ID_SECCION_FK\"")
	private SectionEntity section;

	@Column(name = "\"TIEMPO_RETENCION\"")
	private Long retentionTime;

	@ManyToOne
	@JoinColumn(name = "\"ID_TRD_FK\"")
	private TrdEntity trd;

	@Column(name = "\"CODIGO\"")
	private String code;

	@Column(name = "\"NOMBRE\"")
	private String name;

	@Column(name = "\"PROCEDIMIENTO\"")
	private String process;

	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;

	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;

	@OneToMany(mappedBy = "fatherDocumentaryUnit")
	private List<DocumentaryUnitEntity> documentaryUnitList;

	@OneToMany(mappedBy = "documentaryUnit")
	private List<DocumentaryTypesEntity> documentaryTypeList;

}
