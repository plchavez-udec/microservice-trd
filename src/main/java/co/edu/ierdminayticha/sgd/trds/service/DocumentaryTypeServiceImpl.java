package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SubSerieEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.IDocumentaryTypeRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISerieRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISubSerieRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DocumentaryTypeServiceImpl implements IDocumentaryTypeService {

	@Autowired
	private ISerieRepository serieRepository;
	@Autowired
	private ISubSerieRepository subSerieRepository;
	@Autowired
	private IDocumentaryTypeRepository documentaryTypeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DocumentaryTypeOutDto create(DocumentaryTypeInDto request) {
		log.info("crear tipo documental {}", request);
		/*
		 * if (request.getIdSerie() != null && request.getIdSubSerie() == null) {
		 * this.validateExistenceOfResourceinSerie(request.getIdSerie(),
		 * request.getName()); } else if (request.getIdSerie() == null &&
		 * request.getIdSubSerie() != null) {
		 * this.validateExistenceOfResourceinSubSerie(request.getIdSubSerie(),
		 * request.getName()); } DocumentaryTypeEntity documentaryTypeEntityOut =
		 * this.toPersist(request); return
		 * this.createSuccessfulResponse(documentaryTypeEntityOut);
		 */
		return null;
	}

	@Override
	public DocumentaryTypeOutDto findById(Long id) {
		DocumentaryTypeEntity documentaryTypeEntity = this.documentaryTypeRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("El tipo documental a la cual hace referencia no existe"));
		return this.createSuccessfulResponse(documentaryTypeEntity);
	}

	@Override
	public List<DocumentaryTypeOutDto> findAllBySerie(Long idSerie) {
		SerieEntity serieEntity = this.serieRepository.findById(idSerie)
				.orElseThrow(() -> new NoSuchElementException("La serie a la cual hace referencia no existe"));
		List<DocumentaryTypeEntity> listDocumentaryTypeEntity = this.documentaryTypeRepository
				.findAllBySerieAndIsDeleted(serieEntity, false);
		return createSuccessfulResponse(listDocumentaryTypeEntity);
	}

	@Override
	public List<DocumentaryTypeOutDto> findAllBySubSerie(Long idSubSerie) {
		SubSerieEntity subSerieEntity = this.subSerieRepository.findById(idSubSerie)
				.orElseThrow(() -> new NoSuchElementException("La sub serie a la cual hace referencia no existe"));
		List<DocumentaryTypeEntity> listDocumentaryTypeEntity = this.documentaryTypeRepository
				.findAllBySubSerieAndIsDeleted(subSerieEntity, false);
		return createSuccessfulResponse(listDocumentaryTypeEntity);
	}

	@Override
	public void update(Long id, DocumentaryTypeInDto request) {
		DocumentaryTypeEntity documentaryTypeEntity = this.documentaryTypeRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("El tipo documental a la cual hace referencia no existe"));
		documentaryTypeEntity.setName(request.getName());
		this.documentaryTypeRepository.save(documentaryTypeEntity);
	}

	@Override
	public void delete(Long id) {
		DocumentaryTypeEntity documentaryTypeEntity = this.documentaryTypeRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("El tipo documental a la cual hace referencia no existe"));
		documentaryTypeEntity.setIsDeleted(true);
		this.documentaryTypeRepository.save(documentaryTypeEntity);
	}

	private void validateExistenceOfResourceinSerie(Long idSerie, String documentaryTypeName) {
		SerieEntity serieEntity = this.serieRepository.findById(idSerie)
				.orElseThrow(() -> new NoSuchElementException("La serie a la cual hace referencia no existe"));
		DocumentaryTypeEntity documentaryTypeEntity = this.documentaryTypeRepository.findBySerieAndName(serieEntity,
				documentaryTypeName);
		if (documentaryTypeEntity != null) {
			throw new GeneralException(
					String.format("Actualmente ya existe el tipo documental con el nombre %s para la serie %s",
							documentaryTypeName, serieEntity.getName()));
		}
	}

	private void validateExistenceOfResourceinSubSerie(Long idSubSerie, String documentaryTypeName) {
		SubSerieEntity serieEntity = this.subSerieRepository.findById(idSubSerie)
				.orElseThrow(() -> new NoSuchElementException("La sub serie a la cual hace referencia no existe"));
		DocumentaryTypeEntity documentaryTypeEntity = this.documentaryTypeRepository.findBySubSerieAndName(serieEntity,
				documentaryTypeName);
		if (documentaryTypeEntity != null) {
			throw new GeneralException(
					String.format("Actualmente ya existe el tipo documental con el nombre %s para la sub serie %s",
							documentaryTypeName, serieEntity.getName()));
		}
	}

	private DocumentaryTypeEntity toPersist(DocumentaryTypeInDto request) {
		/*DocumentaryTypeEntity documentaryTypeEntity = new DocumentaryTypeEntity();
		documentaryTypeEntity.setName(request.getName());
		if (request.getIdSerie() != null && request.getIdSubSerie() == null) {
			documentaryTypeEntity.setSerie(this.serieRepository.findById(request.getIdSerie())
					.orElseThrow(() -> new NoSuchElementException("La serie a la cual hace referencia no existe")));
		} else if (request.getIdSerie() == null && request.getIdSubSerie() != null) {
			documentaryTypeEntity.setSubSerie(this.subSerieRepository.findById(request.getIdSubSerie())
					.orElseThrow(() -> new NoSuchElementException("La sub serie a la cual hace referencia no existe")));
		}
		documentaryTypeEntity.setIsDeleted(false);
		documentaryTypeEntity = this.documentaryTypeRepository.save(documentaryTypeEntity);
		return documentaryTypeEntity;*/
		return null;
	}

	private DocumentaryTypeOutDto createSuccessfulResponse(DocumentaryTypeEntity documentaryTypeEntityOut) {
		DocumentaryTypeOutDto documentaryTypeOutDto = modelMapper.map(documentaryTypeEntityOut,
				new TypeToken<DocumentaryTypeOutDto>() {
				}.getType());
		return documentaryTypeOutDto;
	}

	private List<DocumentaryTypeOutDto> createSuccessfulResponse(
			List<DocumentaryTypeEntity> listDocumentaryTypeEntity) {
		List<DocumentaryTypeOutDto> listSerieDtoOut = modelMapper.map(listDocumentaryTypeEntity,
				new TypeToken<List<DocumentaryTypeOutDto>>() {
				}.getType());
		return listSerieDtoOut;
	}
}
