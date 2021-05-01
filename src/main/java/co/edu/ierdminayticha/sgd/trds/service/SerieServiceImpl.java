package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.SerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SerieOutDto;
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
				.orElseThrow(() -> new NoSuchElementException("La serie a la cual hace referencia no existe"));
		return this.createSuccessfulResponse(serieEntityOut);
	}

	@Override
	public List<SerieOutDto> findAll(Long idTrd) {
		TrdEntity trdEntity = this.trdRepository.findById(idTrd)
				.orElseThrow(() -> new NoSuchElementException("La trd a la cual hace referencia no existe"));
		List<SerieEntity> listSerieEntityOut = this.serieRepository.findAllByTrd(trdEntity);
		return this.createSuccessfulResponse(listSerieEntityOut);
	}

	@Override
	public void update(Long id, SerieInDto request) {
		SerieEntity serieEntity = this.serieRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("La serie a la cual hace referencia no existe"));
		FinalDisposalTypeEntity finalDisposalType = null;
		if (request.getFinalDisposalType() != null) {
			finalDisposalType = this.finalDisposalTypeRepository.findById(request.getFinalDisposalType())
					.orElseThrow(() -> new NoSuchElementException(
							"El tipo de disposición final al cual hace referencia no existe"));
		}
		serieEntity.setFinalDisposalType(finalDisposalType);
		serieEntity.setCode(request.getCode());
		serieEntity.setName(request.getName());
		serieEntity.setProcess(request.getProcess());
		if (request.getRetentionTime() != null) {
			serieEntity.setRetentionTime(request.getRetentionTime());
		}
		serieEntity.setLastModifiedDate(new Date());
		log.info("SerieServiceImpl :: update - Serie a actualizar: ", serieEntity);
		this.serieRepository.save(serieEntity);
	}

	private void validateExistenceOfTheResource(Long idTrd, String seriesName) {
		TrdEntity trdEntity = this.trdRepository.findById(idTrd)
				.orElseThrow(() -> new NoSuchElementException("La trd a la cual hace referencia no existe"));
		SerieEntity entity = this.serieRepository.findByNameAndTrd(seriesName, trdEntity);
		if (entity != null) {
			throw new GeneralException(String.format("Actualmente ya existe la serie con el nombre {} para la trd {}",
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
		log.info("SerieServiceImpl :: toPersist - Serie a persistir: ", serieEntity);
		SerieEntity serieEntityOut = this.serieRepository.save(serieEntity);
		return serieEntityOut;
	}

	private SerieOutDto createSuccessfulResponse(SerieEntity serieEntityOut) {
		SerieOutDto serieDtoOut = modelMapper.map(serieEntityOut, new TypeToken<SerieOutDto>() {
		}.getType());
		return serieDtoOut;
	}

	private List<SerieOutDto> createSuccessfulResponse(List<SerieEntity> listSerieEntityOut) {
		List<SerieOutDto> listSerieDtoOut = modelMapper.map(listSerieEntityOut, new TypeToken<List<SerieOutDto>>() {
		}.getType());
		return listSerieDtoOut;
	}
}
