package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.ITrdApi;
import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;
import co.edu.ierdminayticha.sgd.trds.service.ITrdService;

@RefreshScope
@RestController
@RequestMapping(value = "v1/trd")
public class ITrdController implements ITrdApi {

	@Autowired
	private ITrdService service;

	@Override
	public ResponseEntity<?> create(TrdDto dto) {

		TrdDto response = service.create(dto);

		return buildCreationResponse(response);
	}

	@Override
	public ResponseEntity<?> findById(Long id) {

		TrdDto response = service.findById(id);

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<TrdOutDto>> findAll() {

		List<TrdOutDto> response = service.findAll();

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, TrdDto dto) {

		service.update(id, dto);

		return ResponseEntity.noContent().build();
	}

	private ResponseEntity<?> buildCreationResponse(TrdDto response) {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{trd-id}").buildAndExpand(response.getId())
				.toUri();

		return ResponseEntity.created(uri).body(response);

	}

}
