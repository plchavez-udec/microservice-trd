package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.IApiDocumentaryType;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeOutDto;
import co.edu.ierdminayticha.sgd.trds.service.IDocumentaryTypeService;

@RefreshScope
@RestController
@RequestMapping(value = "v1/documentary-type")
public class DocumentaryTypeController implements IApiDocumentaryType {

	@Autowired
	private IDocumentaryTypeService service;

	@Override
	public ResponseEntity<DocumentaryTypeOutDto> create(DocumentaryTypeInDto request) {
		return this.buildCreationResponse(this.service.create(request));
	}

	@Override
	public ResponseEntity<DocumentaryTypeOutDto> findById(Long id) {
		DocumentaryTypeOutDto response = this.service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<DocumentaryTypeOutDto>> findAllBySubserie(Long idSubSerie) {
		List<DocumentaryTypeOutDto> response = this.service.findAllBySubSerie(idSubSerie);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, DocumentaryTypeInDto request) {
		this.service.update(id, request);
		return ResponseEntity.accepted().build();
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<DocumentaryTypeOutDto> buildCreationResponse(DocumentaryTypeOutDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{documentary-type-id}")
				.buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);

	}
}
