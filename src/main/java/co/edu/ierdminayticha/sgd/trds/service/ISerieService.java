package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.FinalDisposalTypeDto;
import co.edu.ierdminayticha.sgd.trds.dto.SectionDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;

public interface ISerieService {
	public SerieOutDto create(SerieInDto request);
	public SerieOutDto findById(Long id);
	public List<SerieOutDto> findAll(Long idTrd, Long idSection);
	public void update(Long id, SerieInDto request);
	public List<FinalDisposalTypeDto> findAllFinalDisposalType();
	public List<SectionDto> findAllSeccions();

}
