package co.edu.ierdminayticha.sgd.trds.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.trds.api.IApiSerie;
import co.edu.ierdminayticha.sgd.trds.dto.SerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;
import co.edu.ierdminayticha.sgd.trds.service.ISerieService;

@RefreshScope
@RestController
@RequestMapping(value = "v1/series")
public class SerieController implements IApiSerie {

	@Autowired
	private ISerieService service;

	@Override
	public ResponseEntity<SerieOutDto> create(SerieInDto request) {
		return this.buildCreationResponse(this.service.create(request));
	}

	@Override
	public ResponseEntity<SerieOutDto> findById(Long id) {
		SerieOutDto serieOutDto = this.service.findById(id);
		return ResponseEntity.ok(serieOutDto);
	}

	@Override
	public ResponseEntity<List<SerieOutDto>> findAll(Long idTrd) {
		List<SerieOutDto> ListSerieOutDto = this.service.findAll(idTrd);
		return ResponseEntity.ok(ListSerieOutDto);
	}

	@Override
	public ResponseEntity<?> update(Long id, SerieInDto request) {
		this.service.update(id, request);
		return ResponseEntity.accepted().build();
	}

	private ResponseEntity<SerieOutDto> buildCreationResponse(SerieOutDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{serie-id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);

	}
}
