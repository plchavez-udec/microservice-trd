package co.edu.ierdminayticha.sgd.trds.api;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.ierdminayticha.sgd.trds.dto.IRequestCreateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestUpdateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.ListSerieOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestion de series documentales", 
	 tags = "Api Microservicio para la gestion de series documentales")
public interface IApiSerie {
	
	@ApiOperation(value = "Crear serie", 
				  response = SerieOutDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SerieOutDto> create(@Validated(IRequestCreateValidation.class) 
										      @RequestBody SerieInDto request);

	@ApiOperation(value = "Obtener serie por Id", 
				  response = SerieOutDto.class)
	@GetMapping(value = "/{serie-id}", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SerieOutDto> findById(@PathVariable("serie-id") Long id);

	
	@ApiOperation(value = "Obtener lista de series", 
				  response = ListSerieOutDto.class)
	@GetMapping(value = "", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SerieOutDto>> findAll(@QueryParam("idTrd") Long idTrd);


	@ApiOperation(value = "Actualización de una serie", 
				  response = SerieOutDto.class)
	@PutMapping(value = "/{serie-id}", 
				  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("serie-id") Long id,
										      @Validated(IRequestUpdateValidation.class)
											  @RequestBody SerieInDto request);

	/*
	@ApiOperation(value = "Eliminar usuario", 
				  response = UserRequestDto.class)
	@DeleteMapping(value = "{usuario-id}")
	public ResponseEntity<?> delete(Long id);
	*/

}
