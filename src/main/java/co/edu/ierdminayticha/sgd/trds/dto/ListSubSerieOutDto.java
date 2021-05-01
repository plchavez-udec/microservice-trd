package co.edu.ierdminayticha.sgd.trds.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListSubSerieOutDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SubSerieOutDto> listSubSeries;

}
