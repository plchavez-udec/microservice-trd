package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeOutDto;

public interface IDocumentaryTypeService {
	public DocumentaryTypeOutDto create(DocumentaryTypeInDto request);
	public DocumentaryTypeOutDto findById(Long id);
	public List<DocumentaryTypeOutDto> findAllBySerie(Long idSerie);
	public List<DocumentaryTypeOutDto> findAllBySubSerie(Long idSubSerie);
	public void update(Long id, DocumentaryTypeInDto request);
	public void delete(Long id);
}
