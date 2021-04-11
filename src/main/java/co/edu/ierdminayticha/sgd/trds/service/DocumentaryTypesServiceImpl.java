package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypesResponseListDto;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypesEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.IDocumentaryTypesRepository;
import co.edu.ierdminayticha.sgd.trds.repository.IDocumentaryUnitRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DocumentaryTypesServiceImpl implements IDocumentaryTypesService {

	private static final String EXISTING_RESOURCE_MESSAGE = "El recurso con nombre (%s) ya existe ";
	private static final String NO_EXISTEN_RESOURCE_MESSAGE = "No existe el recurso con id (%s) ";
	private static final String NO_EXISTEN_INFO_MESSAGE = "No existe información para mostrar";

	@Autowired
	private IDocumentaryTypesRepository repository;

	@Autowired
	private IDocumentaryUnitRepository documentaryUnitrepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DocumentaryTypesResponseDto create(DocumentaryTypesRequestDto request) {

		log.info("DocumentaryTypesServiceImpl : create - Creando recurso");

		validateExistenceOfTheResource(request.getName());

		DocumentaryTypesEntity entity = toPersist(request);

		return createSuccessfulResponse(entity);

	}

	@Override
	public DocumentaryTypesResponseDto findById(Long id) {

		log.info("DocumentaryTypesServiceImpl : findById - Consultando recurso por Id");

		DocumentaryTypesEntity entity = this.repository.findByIdAndIsDeleted(id, false)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		return createSuccessfulResponse(entity);
	}

	@Override
	public List<DocumentaryTypesResponseListDto> findAll(Long idDocumentaryUnit) {

		log.info("DocumentaryTypesServiceImpl : findAll - Consultando lista de  recursos");

		Iterable<DocumentaryTypesEntity> entityList = this.repository
				.findAllByDocumentaryUnitAndIsDeleted(
						this.documentaryUnitrepository.findById(idDocumentaryUnit)
								.orElseThrow(() -> new NoSuchElementException(String
										.format("No existe la unidad documental informada (%s) ", idDocumentaryUnit))),
						false);

		if (entityList == null) {
			throw new NoSuchElementException(NO_EXISTEN_INFO_MESSAGE);
		}

		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, DocumentaryTypesRequestDto request) {

		log.info("DocumentaryTypesServiceImpl : update - Actualizando recurso");

		DocumentaryTypesEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		this.modelMapper.map(request, entity);

		entity.setLastModifiedDate(new Date());

		this.repository.save(entity);

	}

	@Override
	public void delete(Long id) {

		log.info("DocumentaryTypesServiceImpl : delete - Eliminando recurso");

		DocumentaryTypesEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		entity.setIsDeleted(true);

		this.repository.save(entity);

	}

	private void validateExistenceOfTheResource(String name) {

		DocumentaryTypesEntity entity = this.repository.findByName(name);

		if (entity != null) {

			log.info("DocumentaryTypesServiceImpl : validateExistenceOfTheResource - "
					+ "el recurso con nombre ({}) ya existe", name);

			throw new GeneralException(String.format(EXISTING_RESOURCE_MESSAGE, name));
		}

	}

	private DocumentaryTypesEntity toPersist(DocumentaryTypesRequestDto request) {

		DocumentaryTypesEntity entity = new DocumentaryTypesEntity();
		entity.setDocumentaryUnit(this.documentaryUnitrepository.findById(request.getIdDocumentaryUnit())
				.orElseThrow(() -> new NoSuchElementException(String
						.format("No existe la unidad documental informada (%s) ", request.getIdDocumentaryUnit()))));
		entity.setName(request.getName());
		entity.setIsDeleted(false);
		entity.setCreationDate(new Date());

		log.info("DocumentaryTypesServiceImpl : toPersist - " + "recurso a persistir: ", entity);

		entity = this.repository.save(entity);

		return entity;

	}

	private DocumentaryTypesResponseDto createSuccessfulResponse(DocumentaryTypesEntity entity) {

		DocumentaryTypesResponseDto response = this.modelMapper.map(entity, DocumentaryTypesResponseDto.class);
		response.setDocumentanyUnit(new DocumentaryTypeOutDto());

		return response;

	}

	private List<DocumentaryTypesResponseListDto> createSuccessfulResponse(
			Iterable<DocumentaryTypesEntity> entityList) {

		List<DocumentaryTypesResponseListDto> dtoList = modelMapper.map(entityList,
				new TypeToken<List<DocumentaryTypesResponseListDto>>() {
				}.getType());

		return dtoList;

	}

}
