package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.IDocumentaryUnitApi;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseListDto;
import co.edu.ierdminayticha.sgd.trds.service.IDocumentaryUnitService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RefreshScope
@RestController
@RequestMapping(value = "documentary-unit/v1/documentary-unit")
public class DocumentaryUnitController implements IDocumentaryUnitApi {

	@Autowired
	private IDocumentaryUnitService service;

	@Override
	public ResponseEntity<DocumentaryUnitResponseDto> create(DocumentaryUnitRequestDto request) {
		
		log.info("IDocumentaryUnitController : create - Creando recurso {}", request);

		DocumentaryUnitResponseDto response = service.create(request);

		log.info("IDocumentaryUnitController : create - Transacción exitosa, recurso creado: "
				+ "{}", response);
		
		return buildCreationResponse(response);
	}

	@Override
	public ResponseEntity<DocumentaryUnitResponseDto> findById(Long id) {
		
		log.info("IDocumentaryUnitController : findById - Consultando recurso con id "
				+ "{}", id);

		DocumentaryUnitResponseDto response = this.service.findById(id);
		
		log.info("IDocumentaryUnitController : findById - Transacción exitosa, recurso: "
				+ "{}", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<DocumentaryUnitResponseListDto>> findAll(Long trdId, Long documentaryUnitType) {
		
		log.info("IDocumentaryUnitController : findAll - Consultando todos los registros");

		List<DocumentaryUnitResponseListDto> response = service.findAll(trdId, documentaryUnitType);
		
		log.info("IDocumentaryUnitController : findAll - Transacción exitosa, registros "
				+ "consultados: ", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, DocumentaryUnitRequestDto request) {
		
		log.info("IDocumentaryUnitController : update - Actualizando recurso con id {}, "
				+ "nuevos valores: {}", id,  request);
		
		service.update(id, request);
		
		log.info("IDocumentaryUnitController : update - Transacción exitosa, registro "
				+ "actualizado");
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		
		log.info("IDocumentaryUnitController : delete - Eliminando recurso con id {}", id);
		
		log.info("IDocumentaryUnitController : delete - Transacción exitosa, recurso "
				+ "eliminado");
		
		return null;
	}

	private ResponseEntity<DocumentaryUnitResponseDto> buildCreationResponse(DocumentaryUnitResponseDto response) {

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{referencia-id}")
				.buildAndExpand(response.getId())
				.toUri();

		return ResponseEntity.created(uri).body(response);

	}

}
