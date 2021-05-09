package co.edu.ierdminayticha.sgd.trds.api;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestCreateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestUpdateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieOutDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestion de tipos documentales", 
	 tags = "Api Microservicio para la gestion de tipos documentales")
public interface IApiDocumentaryType {
	
	@ApiOperation(value = "Crear tipo documental", 
				  response = SubSerieOutDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentaryTypeOutDto> create(
					@Validated(IRequestCreateValidation.class)
					@RequestBody DocumentaryTypeInDto request);
	
	@ApiOperation(value = "Obtener tipo documental por Id", 
			  response = DocumentaryTypeOutDto.class)
	@GetMapping(value = "{documentary-type-id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentaryTypeOutDto> findById(@PathVariable("documentary-type-id") Long id);
	
	@ApiOperation(value = "Obtener lista de tipos documentales", 
			  response = DocumentaryTypeOutDto.class)
	@GetMapping(value = "", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocumentaryTypeOutDto>> findAll(
			@QueryParam("tipoUnidadDocumental") String tipoUnidadDocumental,
			@QueryParam("valor") Long valor);
	
	@ApiOperation(value = "Actualziar tipo documental")
	@PutMapping(value = "{documentary-type-id}", 
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(
				@PathVariable("documentary-type-id") Long id,
				@Validated(IRequestUpdateValidation.class)
				@RequestBody DocumentaryTypeInDto request);
	
	@ApiOperation(value = "Eliminar tipo documental")
	@DeleteMapping(value = "{documentary-type-id}")
	public ResponseEntity<?> delete(@PathVariable("documentary-type-id") Long id);

}
