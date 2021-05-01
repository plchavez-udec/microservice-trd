package co.edu.ierdminayticha.sgd.trds.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"TDRS\"")
public class TrdEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_TDRS_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_TDRS_ID\"")
	@Column(name = "\"ID\"")
	private Long id;
	@Column(name = "\"DESCRIPCION\"")
	private String description;
	@Column(name = "\"VERSION\"")
	private String version;
	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;
	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;
	@OneToMany(mappedBy = "trd")
	private List<SerieEntity> series;

}
