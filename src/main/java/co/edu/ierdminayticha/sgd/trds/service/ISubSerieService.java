package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.SubSerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieOutDto;

public interface ISubSerieService {

	public SubSerieOutDto create(SubSerieInDto request);
	public SubSerieOutDto findById(Long id);
	public void update(Long id, SubSerieInDto request);
	List<SubSerieOutDto> findAllBySerie(Long idSerie);
}
