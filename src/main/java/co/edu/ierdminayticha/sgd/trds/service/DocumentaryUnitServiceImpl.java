package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitRequestDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryUnitResponseListDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryUnitEntity;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryUnitTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.IDocumentaryUnitRepository;
import co.edu.ierdminayticha.sgd.trds.repository.IDocumentaryUnitTypeRepository;
import co.edu.ierdminayticha.sgd.trds.repository.IFinalDisposalTypeRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISectionRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ITrdEntityRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DocumentaryUnitServiceImpl implements IDocumentaryUnitService {

	private static final String EXISTING_RESOURCE_MESSAGE = "El recurso con codigo (%s) ya existe ";
	private static final String NO_EXISTEN_RESOURCE_MESSAGE = "No existe el recurso con id (%s) ";
	private static final String NO_EXISTEN_INFO_MESSAGE = "No existe información para mostrar";

	@Autowired
	private IDocumentaryUnitRepository repository;

	@Autowired
	private IDocumentaryUnitTypeRepository documentaryUnitTypeRepository;

	@Autowired
	private IFinalDisposalTypeRepository finalDisposalTypeRepository;

	@Autowired
	private ISectionRepository sectionRepository;

	@Autowired
	private ITrdEntityRepository trdRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DocumentaryUnitResponseDto create(DocumentaryUnitRequestDto request) {

		log.info("DocumentaryUnitServiceImpl : create - Creando unidad documental (serie -subserie)");

		validateExistenceOfTheResource(request.getCode());

		DocumentaryUnitEntity entity = toPersist(request);

		return createSuccessfulResponse(entity);

	}

	@Override
	public DocumentaryUnitResponseDto findById(Long id) {

		log.info("DocumentaryUnitServiceImpl : findById - Consultando unidad documental por Id");

		DocumentaryUnitEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		return createSuccessfulResponse(entity);
	}

	@Override
	public List<DocumentaryUnitResponseListDto> findAll(Long trdId, Long idDocumentaryUnitType) {

		log.info("DocumentaryUnitServiceImpl : findAll - Consultando lista de  unidades documentales");

		DocumentaryUnitTypeEntity documentaryUnitType = this.documentaryUnitTypeRepository
				.findById(idDocumentaryUnitType)
				.orElseThrow(() -> new NoSuchElementException("No existe el tipo de unidad documental informado"));
		
		TrdEntity trd = this.trdRepository.findById(trdId).orElseThrow(() -> new NoSuchElementException(
				String.format("No existe el id (%s) para la trd informada ", trdId)));

		Iterable<DocumentaryUnitEntity> entityList = this.repository.findAllByTrdAndDocumentaryUnitType(trd,
				documentaryUnitType);

		if (entityList == null) {
			throw new NoSuchElementException(NO_EXISTEN_INFO_MESSAGE);
		}

		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, DocumentaryUnitRequestDto request) {

		log.info("DocumentaryUnitServiceImpl : update - Actualizando recurso");

		DocumentaryUnitEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		if (request.getFinalDisposalType() != null)
			entity.setFinalDisposalType(this.finalDisposalTypeRepository.findById(request.getFinalDisposalType())
					.orElseThrow(() -> new NoSuchElementException("No existe el tipo de disposición final informado")));

		if (request.getProcess() != null)
			entity.setProcess(request.getProcess());

		if (request.getRetentionTime() != null)
			entity.setRetentionTime(request.getRetentionTime());

		entity.setName(request.getName());
		entity.setLastModifiedDate(new Date());

		this.repository.save(entity);

	}

	@Override
	public void delete(Long id) {

		log.info("DocumentaryUnitServiceImpl : delete - Eliminando recurso");

		DocumentaryUnitEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		repository.delete(entity);

	}

	private void validateExistenceOfTheResource(String code) {

		DocumentaryUnitEntity entity = this.repository.findByCode(code);

		if (entity != null) {

			log.info("DocumentaryUnitServiceImpl : validateExistenceOfTheResource - "
					+ "el recurso (serie -subserie) con codigo ({}) ya existe", code);

			throw new GeneralException(String.format(EXISTING_RESOURCE_MESSAGE, code));
		}

	}

	private DocumentaryUnitEntity toPersist(DocumentaryUnitRequestDto request) {

		DocumentaryUnitEntity entity = new DocumentaryUnitEntity();

		entity.setDocumentaryUnitType(this.documentaryUnitTypeRepository.findById(request.getDocumentaryUnitType())
				.orElseThrow(() -> new NoSuchElementException("No existe el tipo de unidad documental informado")));

		if (request.getFatherDocumentaryUnit() != null) {

			entity.setFatherDocumentaryUnit(this.repository.findById(request.getFatherDocumentaryUnit()).orElseThrow(
					() -> new NoSuchElementException("No existe el la unidad documental (padre) informada")));
		}

		if (request.getFinalDisposalType() != null) {

			entity.setFinalDisposalType(this.finalDisposalTypeRepository.findById(request.getFinalDisposalType())
					.orElseThrow(() -> new NoSuchElementException("No existe el tipo de disposición final informado")));
		}

		if (request.getSection() != null) {

			entity.setSection(this.sectionRepository.findById(request.getSection())
					.orElseThrow(() -> new NoSuchElementException("No existe la seccion informada")));
		}
		if (request.getRetentionTime() != null) {
			entity.setRetentionTime(request.getRetentionTime());
		}

		entity.setTrd(this.trdRepository.findById(request.getTrd()).orElseThrow(() -> new NoSuchElementException(
				String.format("No existe el id (%s) para la trd informada ", request.getTrd()))));
		entity.setCode(request.getCode());
		entity.setName(request.getName());
		entity.setProcess(request.getProcess());
		entity.setCreationDate(new Date());

		log.info("DocumentaryUnitServiceImpl : toPersist - unidad documental a persistir: ", entity);

		entity = this.repository.save(entity);

		return entity;

	}

	private DocumentaryUnitResponseDto createSuccessfulResponse(DocumentaryUnitEntity entity) {

		DocumentaryUnitResponseDto dto = this.modelMapper.map(entity, DocumentaryUnitResponseDto.class);
		dto.setTrd(new TrdOutDto());
		dto.getTrd().setId(entity.getTrd().getId());
		dto.getTrd().setVersion(entity.getTrd().getVersion());

		return dto;

	}

	private List<DocumentaryUnitResponseListDto> createSuccessfulResponse(Iterable<DocumentaryUnitEntity> entityList) {

		List<DocumentaryUnitResponseListDto> dtoList = modelMapper.map(entityList,
				new TypeToken<List<DocumentaryUnitResponseListDto>>() {
				}.getType());

		return dtoList;

	}

}
