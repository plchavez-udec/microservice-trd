package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.IDocumentaryTypesApi;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseListDto;
import co.edu.ierdminayticha.sgd.trds.service.IDocumentaryTypesService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RefreshScope
@RestController
@RequestMapping(value = "documentary-type/v1/documentary-type")
public class DocumentaryTypesController implements IDocumentaryTypesApi {

	@Autowired
	private IDocumentaryTypesService service;

	@Override
	public ResponseEntity<DocumentaryTypesResponseDto> create(DocumentaryTypesRequestDto request) {
		
		log.info("DocumentaryTypesController : create - Creando recurso {}", request);

		DocumentaryTypesResponseDto response = service.create(request);

		log.info("DocumentaryTypesController : create - Transacción exitosa, recurso creado: "
				+ "{}", response);
		
		return buildCreationResponse(response);
	}

	@Override
	public ResponseEntity<DocumentaryTypesResponseDto> findById(Long id) {
		
		log.info("DocumentaryTypesController : findById - Consultando recurso con id "
				+ "{}", id);

		DocumentaryTypesResponseDto response = this.service.findById(id);
		
		log.info("DocumentaryTypesController : findById - Transacción exitosa, recurso: "
				+ "{}", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<DocumentaryTypesResponseListDto>> findAll(Long idDocumentaryUnit) {
		
		log.info("DocumentaryTypesController : findAll - Consultando todos los registros");

		List<DocumentaryTypesResponseListDto> response = service.findAll(idDocumentaryUnit);
		
		log.info("DocumentaryTypesController : findAll - Transacción exitosa, registros "
				+ "consultados: ", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, DocumentaryTypesRequestDto request) {
		
		log.info("DocumentaryTypesController : update - Actualizando recurso con id {}, "
				+ "nuevos valores: {}", id,  request);
		
		this.service.update(id, request);
		
		log.info("DocumentaryTypesController : update - Transacción exitosa, registro "
				+ "actualizado");
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		
		log.info("DocumentaryTypesController : delete - Eliminando recurso con id {}", id);
		
		this.service.delete(id);
		
		log.info("DocumentaryTypesController : delete - Transacción exitosa, recurso "
				+ "eliminado");
		
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<DocumentaryTypesResponseDto> buildCreationResponse(DocumentaryTypesResponseDto response) {

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{referencia-id}")
				.buildAndExpand(response.getId())
				.toUri();

		return ResponseEntity.created(uri).body(response);

	}

}
