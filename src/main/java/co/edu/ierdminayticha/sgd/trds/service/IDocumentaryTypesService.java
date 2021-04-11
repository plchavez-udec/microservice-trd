package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseListDto;

public interface IDocumentaryTypesService {

	DocumentaryTypesResponseDto create(DocumentaryTypesRequestDto dto);

	DocumentaryTypesResponseDto findById(Long id);

	List<DocumentaryTypesResponseListDto> findAll(Long idDocumentaryUnit);

	void update(Long id, DocumentaryTypesRequestDto dto);

	void delete(Long id);

}
