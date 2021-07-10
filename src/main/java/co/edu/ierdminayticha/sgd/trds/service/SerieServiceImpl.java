package co.edu.ierdminayticha.sgd.trds.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.FinalDisposalTypeDto;
import co.edu.ierdminayticha.sgd.trds.dto.SectionDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.FinalDisposalTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SectionEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.IFinalDisposalTypeRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISectionRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISerieRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ITrdEntityRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SerieServiceImpl implements ISerieService {

	@Autowired
	private ISerieRepository serieRepository;
	@Autowired
	private ITrdEntityRepository trdRepository;
	@Autowired
	private IFinalDisposalTypeRepository finalDisposalTypeRepository;
	@Autowired
	private ISectionRepository sectionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SerieOutDto create(SerieInDto request) {
		this.validateExistenceOfTheResource(request.getIdTrd(), request.getName());
		SerieEntity serieEntityOut = this.toPersist(request);
		return this.createSuccessfulResponse(serieEntityOut);
	}

	@Override
	public SerieOutDto findById(Long id) {
		SerieEntity serieEntityOut = this.serieRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(
						"La serie a la cual hace referencia no existe"));
		return this.createSuccessfulResponse(serieEntityOut);
	}

	@Override
	public List<SerieOutDto> findAll(Long idTrd, Long idSection) {
		List<SerieEntity> listSerieEntityOut = new ArrayList<>();
		TrdEntity trdEntity = this.trdRepository.findById(idTrd)
				.orElseThrow(() -> new NoSuchElementException(
						"La trd a la cual hace referencia no existe"));
		if (idSection!=null) {
			listSerieEntityOut = this.serieRepository
					.findAllByTrdAndSectionOrderByCodeAsc(trdEntity,
					this.sectionRepository.findById(idSection).get());
		}else {
			listSerieEntityOut = this.serieRepository
					.findAllByTrdOrderByCodeAsc(trdEntity);
		}
		return this.createSuccessfulResponse(listSerieEntityOut);
	}

	@Override
	public void update(Long id, SerieInDto request) {
		SerieEntity serieEntity = this.serieRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(
						"La serie a la cual hace referencia no existe"));
		FinalDisposalTypeEntity finalDisposalType = null;
		if (request.getFinalDisposalType() != null) {
			finalDisposalType = this.finalDisposalTypeRepository
					.findById(request.getFinalDisposalType()).orElseThrow(
					() -> new NoSuchElementException(
					"El tipo de disposición final al cual hace referencia no existe"));
		}
		serieEntity.setFinalDisposalType(finalDisposalType);
		serieEntity.setCode(request.getCode());
		serieEntity.setName(request.getName());
		serieEntity.setProcess(request.getProcess());
		serieEntity.setRetentionTime(request.getRetentionTime());
		serieEntity.setLastModifiedDate(new Date());
		if (!request.getDocumentaryTypeList().isEmpty()) {
			for (DocumentaryTypeInDto item : request.getDocumentaryTypeList()) {
				DocumentaryTypeEntity dmt = new DocumentaryTypeEntity();
				if (item.getId()!=null) {
					dmt.setId(item.getId());
				}
				dmt.setName(item.getName());
				dmt.setIsDeleted(Boolean.FALSE);
				serieEntity.addDocumentaryType(dmt);
			}
		}
		this.serieRepository.save(serieEntity);
	}

	private void validateExistenceOfTheResource(Long idTrd, String seriesName) {
		TrdEntity trdEntity = this.trdRepository.findById(idTrd)
				.orElseThrow(() -> new NoSuchElementException("La trd a la cual hace referencia no existe"));
		SerieEntity entity = this.serieRepository.findByNameAndTrd(seriesName, trdEntity);
		if (entity != null) {
			throw new GeneralException(String.format("Actualmente ya existe la serie con el nombre %s para la trd %s",
					seriesName, trdEntity.getVersion()));
		}
	}

	private SerieEntity toPersist(SerieInDto request) {
		FinalDisposalTypeEntity dinalDisposalTypeEntity = null;
		if (request.getFinalDisposalType() != null) {
			dinalDisposalTypeEntity = this.finalDisposalTypeRepository.findById(request.getFinalDisposalType())
					.orElseThrow(() -> new NoSuchElementException(
							"El tipo de disposición final al cual hace referencia no existe"));
		}
		SectionEntity sectionEntity = this.sectionRepository.findById(request.getSection()).orElseThrow(
				() -> new NoSuchElementException("El tipo de disposición final al cual hace referencia no existe"));
		TrdEntity trdEntity = this.trdRepository.findById(request.getIdTrd())
				.orElseThrow(() -> new NoSuchElementException("La trd a la cual hace referencia no existe"));
		SerieEntity serieEntity = new SerieEntity();
		serieEntity.setFinalDisposalType(dinalDisposalTypeEntity);
		serieEntity.setTrd(trdEntity);
		serieEntity.setSection(sectionEntity);
		serieEntity.setCode(request.getCode());
		serieEntity.setName(request.getName());
		serieEntity.setProcess(request.getProcess());
		if (request.getRetentionTime() != null) {
			serieEntity.setRetentionTime(request.getRetentionTime());
		}
		serieEntity.setCreationDate(new Date());
		if (!request.getDocumentaryTypeList().isEmpty()) {
			for (DocumentaryTypeInDto item : request.getDocumentaryTypeList()) {
				DocumentaryTypeEntity dmt = new DocumentaryTypeEntity();
				dmt.setName(item.getName());
				dmt.setIsDeleted(Boolean.FALSE);
				serieEntity.addDocumentaryType(dmt);
			}
		}
		return this.serieRepository.save(serieEntity);
	}

	private SerieOutDto createSuccessfulResponse(SerieEntity serieEntityOut) {
		SerieOutDto response =  new SerieOutDto();
		response.setId(serieEntityOut.getId());
		response.setIdTrdParent(serieEntityOut.getTrd().getId());
		response.setCode(serieEntityOut.getCode());
		response.setName(serieEntityOut.getName());
		response.setProcess(serieEntityOut.getProcess());
		if (serieEntityOut.getRetentionTime()!=null) {
			response.setRetentionTime(serieEntityOut.getRetentionTime());
		}
		response.setCreationDate(serieEntityOut.getCreationDate());
		response.setLastModifiedDate(serieEntityOut.getLastModifiedDate());
		response.setSection(new SectionDto());
		response.getSection().setId(serieEntityOut.getSection().getId());
		response.getSection().setName(serieEntityOut.getSection().getName());
		response.getSection().setCode(serieEntityOut.getSection().getCode());
		if (serieEntityOut.getFinalDisposalType()!=null) {
			response.setFinalDisposalType(new FinalDisposalTypeDto());
			response.getFinalDisposalType().setId(serieEntityOut.getFinalDisposalType().getId());
			response.getFinalDisposalType().setName(serieEntityOut.getFinalDisposalType().getName());
			response.getFinalDisposalType().setInitials(serieEntityOut.getFinalDisposalType().getInitials());
		}		
		if (serieEntityOut.getDocumentaryTypeList() !=null && !serieEntityOut.getDocumentaryTypeList().isEmpty()) {
			response.setDocumentaryTypeList(new ArrayList<>());
			for (DocumentaryTypeEntity item : serieEntityOut.getDocumentaryTypeList()) {				
				if (Boolean.FALSE.equals(item.getIsDeleted())) {
					DocumentaryTypeInDto itemDto= new DocumentaryTypeInDto();
					itemDto.setId(item.getId());
					itemDto.setName(item.getName());
					response.getDocumentaryTypeList().add(itemDto);
				}
			}
		}
		return response;
	}

	private List<SerieOutDto> createSuccessfulResponse(List<SerieEntity> listSerieEntityOut) {
		List<SerieOutDto> response= new ArrayList<>();
		listSerieEntityOut.forEach(s ->{
			SerieOutDto dto = this.createSuccessfulResponse(s);
			response.add(dto);
		});
		return response;
	}

	@Override
	public List<FinalDisposalTypeDto> findAllFinalDisposalType() {
		List<FinalDisposalTypeEntity> entityList = (List<FinalDisposalTypeEntity>) this.finalDisposalTypeRepository
				.findAll();
		return modelMapper.map(entityList, new TypeToken<List<FinalDisposalTypeDto>>() {
		}.getType());
	}

	@Override
	public List<SectionDto> findAllSeccions() {
		List<SectionEntity> entityList = (List<SectionEntity>) this.sectionRepository.findAll();
		return modelMapper.map(entityList, new TypeToken<List<SectionDto>>() {
		}.getType());
	}
}
