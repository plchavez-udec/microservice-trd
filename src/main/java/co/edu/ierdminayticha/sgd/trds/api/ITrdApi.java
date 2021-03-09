package co.edu.ierdminayticha.sgd.trds.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.ierdminayticha.sgd.trds.dto.IRequestCreateTrd;
import co.edu.ierdminayticha.sgd.trds.dto.IRequestUpdateTrd;
import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import io.swagger.annotations.Api;

@Api
public interface ITrdApi {

	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> create( @Validated(IRequestCreateTrd.class)
							  @RequestBody 
							  TrdDto dto);

	@GetMapping(value = "/{trd-id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> findById(@PathVariable("trd-id") Long id);

	@GetMapping(value = "", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> findAll();

	@PatchMapping(value = "/{trd-id}", 
			 	  consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(
							 @PathVariable("trd-id")
							 Long id,
							 @Validated(IRequestUpdateTrd.class)
					  		 @RequestBody
							 TrdDto dto);

	ResponseEntity<?> delete(Long id);

}
