package co.edu.ierdminayticha.sgd.trds.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseListDto;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestCreateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestUpdateValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestión de unidades documentales", 
     tags = "Api Microservicio para la gestión de unidades documentales")
public interface IDocumentaryUnitApi {
	
	@ApiOperation(value = "Crear unidad documental",
		          response = DocumentaryUnitResponseDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DocumentaryUnitResponseDto> create( @Validated(IRequestCreateValidation.class)
							  @RequestBody 
							  DocumentaryUnitRequestDto request);

	@ApiOperation(value = "Obtener unidad documental por Id",
	              response = DocumentaryUnitResponseDto.class)
	@GetMapping(value = "/{documentary-unit-id}", 
			    produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DocumentaryUnitResponseDto> findById(@PathVariable("documentary-unit-id") Long id);

	@ApiOperation(value = "Obtener lista de unidades documentales",
            	  response = DocumentaryUnitResponseListDto.class)
	@GetMapping(value = "", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<DocumentaryUnitResponseListDto>> findAll(
			@NotNull @RequestParam(name = "trdId") Long trdId,
			@NotNull @RequestParam(name = "documentaryUnitType") Long documentaryUnitType);

	@ApiOperation(value = "Actualización parcial de un unidad documental",
      	  		  response = DocumentaryUnitRequestDto.class)
	@PatchMapping(value = "{documentary-unit-id}", 
			 	  consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(
							 @PathVariable("documentary-unit-id")
							 Long id,
							 @Validated(IRequestUpdateValidation.class)
					  		 @RequestBody
							 DocumentaryUnitRequestDto request);

	@ApiOperation(value = "Eliminar unidad documental",
	  		  response = DocumentaryUnitRequestDto.class)
	@DeleteMapping(value = "{documentary-unit-id}")
	ResponseEntity<?> delete(Long id);

}
