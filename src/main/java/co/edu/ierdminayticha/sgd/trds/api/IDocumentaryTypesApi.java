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

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseListDto;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestCreateValidation;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestUpdateValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestión de tipos documentals", 
     tags = "Api Microservicio para la gestión de tipos documentals")
public interface IDocumentaryTypesApi {
	
	@ApiOperation(value = "Crear tipo documental",
		          response = DocumentaryTypesResponseDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DocumentaryTypesResponseDto> create( 
			@Validated(IRequestCreateValidation.class)
			@RequestBody DocumentaryTypesRequestDto request);

	@ApiOperation(value = "Obtener tipo documental por Id",
	              response = DocumentaryTypesRequestDto.class)
	@GetMapping(value = "/{documentary-type-id}", 
			    produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DocumentaryTypesResponseDto> findById(@PathVariable("documentary-type-id") Long id);

	@ApiOperation(value = "Obtener lista de tipos documentales",
            	  response = DocumentaryTypesRequestDto.class)
	@GetMapping(value = "", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<DocumentaryTypesResponseListDto>> findAll(
			@NotNull @RequestParam("idDocumentaryUnit") Long idDocumentaryUnit);

	@ApiOperation(value = "Actualización parcial del tipo documental",
      	  		  response = DocumentaryTypesRequestDto.class)
	@PatchMapping(value = "{documentary-type-id}", 
			 	  consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(
							 @PathVariable("documentary-type-id")
							 Long id,
							 @Validated(IRequestUpdateValidation.class)
					  		 @RequestBody
							 DocumentaryTypesRequestDto request);

	@ApiOperation(value = "Eliminación logica de un tipo documental",
	  		  response = DocumentaryTypesRequestDto.class)
	@DeleteMapping(value = "{documentary-type-id}")
	ResponseEntity<?> delete(@PathVariable("documentary-type-id") Long id);

}
