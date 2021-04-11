package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseListDto;

public interface IDocumentaryUnitService {

	DocumentaryUnitResponseDto create(DocumentaryUnitRequestDto dto);

	DocumentaryUnitResponseDto findById(Long id);

	List<DocumentaryUnitResponseListDto> findAll(Long trdId, Long documentaryUnitType);

	void update(Long id, DocumentaryUnitRequestDto dto);

	void delete(Long id);

}
