package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.IApiSubSerie;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieOutDto;
import co.edu.ierdminayticha.sgd.trds.service.ISubSerieService;

@RefreshScope
@RestController
@RequestMapping(value = "v1/sub-serie")
public class SubSerieController implements IApiSubSerie {

	@Autowired
	private ISubSerieService service;

	@Override
	public ResponseEntity<SubSerieOutDto> create(SubSerieInDto request) {
		return this.buildCreationResponse(this.service.create(request));
	}

	@Override
	public ResponseEntity<SubSerieOutDto> findById(Long id) {
		SubSerieOutDto subSerieOutDto = this.service.findById(id);
		return ResponseEntity.ok(subSerieOutDto);
	}

	@Override
	public ResponseEntity<List<SubSerieOutDto>> findAllBySerie(Long idSerie) {
		List<SubSerieOutDto> listSubSerieOutDto = this.service.findAllBySerie(idSerie);
		return ResponseEntity.ok(listSubSerieOutDto);
	}

	@Override
	public ResponseEntity<?> update(Long id, SubSerieInDto request) {
		this.service.update(id, request);
		return ResponseEntity.accepted().build();
	}

	private ResponseEntity<SubSerieOutDto> buildCreationResponse(SubSerieOutDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{serie-id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);

	}
}
