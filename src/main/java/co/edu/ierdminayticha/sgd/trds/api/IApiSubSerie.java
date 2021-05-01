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
import co.edu.ierdminayticha.sgd.trds.dto.ListSubSerieOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieOutDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestion de subseries documentales", 
	 tags = "Api Microservicio para la gestion de subseries documentales")
public interface IApiSubSerie {
	
	@ApiOperation(value = "Crear subserie", 
				  response = SubSerieOutDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubSerieOutDto> create(
					@Validated(IRequestCreateValidation.class)
					@RequestBody SubSerieInDto request);
	
	@ApiOperation(value = "Obtener subserie por Id", 
			  response = SerieOutDto.class)
	@GetMapping(value = "{sub-serie-id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubSerieOutDto> findById(@PathVariable("sub-serie-id") Long id);
	
	@ApiOperation(value = "Obtener lista de subserie", 
			  response = ListSubSerieOutDto.class)
	@GetMapping(value = "", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubSerieOutDto>> findAllBySerie(@QueryParam("idSerie") Long idSerie);	
	
	@ApiOperation(value = "Actualización de una sub serie", 
			  response = SerieOutDto.class)
	@PutMapping(value = "{sub-serie-id}", 
			  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("sub-serie-id") Long id,
									      @Validated(IRequestUpdateValidation.class)
										  @RequestBody SubSerieInDto request);
	
	/*
	@ApiOperation(value = "Actualización parcial del usuario", 
				  response = UserRequestDto.class)
	@PatchMapping(value = "{usuario-id}", 
				  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("usuario-id") Long id,
			@Validated(IRequestUpdateValidation.class) @RequestBody UserRequestDto request);

	@ApiOperation(value = "Eliminar usuario", 
				  response = UserRequestDto.class)
	@DeleteMapping(value = "{usuario-id}")
	public ResponseEntity<?> delete(Long id);
	*/

}
