package co.edu.ierdminayticha.sgd.trds.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.DocumentaryTypeInDto;
import co.edu.ierdminayticha.sgd.trds.dto.FinalDisposalTypeDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieInDto;
import co.edu.ierdminayticha.sgd.trds.dto.SubSerieOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.FinalDisposalTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SubSerieEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.IFinalDisposalTypeRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISerieRepository;
import co.edu.ierdminayticha.sgd.trds.repository.ISubSerieRepository;
import co.edu.ierdminayticha.sgd.trds.util.ResponseCodeConstants;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SubSerieServiceImpl implements ISubSerieService {

	@Autowired
	private ISerieRepository serieRepository;
	@Autowired
	private ISubSerieRepository subSerieRepository;
	@Autowired
	private IFinalDisposalTypeRepository finalDisposalTypeRepository;

	@Override
	public SubSerieOutDto create(SubSerieInDto request) {
		this.validateExistenceOfResource(request.getIdSerie(), request.getName());
		SubSerieEntity subSerieEntityOut = this.toPersist(request);
		return this.createSuccessfulResponse(subSerieEntityOut);
	}

	@Override
	public SubSerieOutDto findById(Long id) {
		SubSerieEntity subSerieEntityOut = this.subSerieRepository.findById(id)
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_SSUBERIE_NOT_EXIST));
		return this.createSuccessfulResponse(subSerieEntityOut);
	}

	@Override
	public List<SubSerieOutDto> findAllBySerie(Long idSerie) {
		
		SerieEntity serieEntity = this.serieRepository.findById(idSerie)
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_SERIE_NOT_EXIST));
		
		List<SubSerieEntity> listSubSerie = this.subSerieRepository
				.findAllBySerie(serieEntity);
		
		return this.createSuccessfulResponse(listSubSerie);
	}

	@Override
	public void update(Long id, SubSerieInDto request) {
		SubSerieEntity entity = this.subSerieRepository.findById(id)
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_SSUBERIE_NOT_EXIST));
		
		entity.setFinalDisposalType(this.finalDisposalTypeRepository.findById(request.getFinalDisposalType())
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_FINAL_DISPOSITION_TYPE_NOT_EXIST)));
		
		entity.setCode(request.getCode());
		entity.setName(request.getName());
		entity.setProcess(request.getProcess());
		entity.setRetentionTime(request.getRetentionTime());
		entity.setLastModifiedDate(new Date());
		for (DocumentaryTypeInDto item : request.getDocumentaryTypeList()) {
			DocumentaryTypeEntity dmt = new DocumentaryTypeEntity();
			if (item.getId()!=null) {
				dmt.setId(item.getId());
			}
			dmt.setName(item.getName());
			dmt.setIsDeleted(Boolean.FALSE);
			entity.addDocumentaryType(dmt);
		}
		this.subSerieRepository.save(entity);
	}

	private void validateExistenceOfResource(Long idSerie, String subSerieName) {
		
		SerieEntity serieEntity = this.serieRepository.findById(idSerie)
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_SERIE_NOT_EXIST));
		
		SerieEntity entity = this.subSerieRepository
				.findByNameAndSerie(subSerieName, serieEntity);
		
		if (entity != null) {
			throw new GeneralException(
					ResponseCodeConstants.ERROR_BUSINESS_SUBSERIE_ALREADY_EXIST);
		}
	}

	private SubSerieEntity toPersist(SubSerieInDto request) {
		
		FinalDisposalTypeEntity dinalDisposalTypeEntity = this.finalDisposalTypeRepository
				.findById(request.getFinalDisposalType())
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_FINAL_DISPOSITION_TYPE_NOT_EXIST));
		
		SerieEntity serieEntityIn = this.serieRepository.findById(request.getIdSerie())
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_SERIE_NOT_EXIST));
		
		SubSerieEntity subSerieEntity = new SubSerieEntity();
		subSerieEntity.setFinalDisposalType(dinalDisposalTypeEntity);
		subSerieEntity.setSerie(serieEntityIn);
		subSerieEntity.setCode(request.getCode());
		subSerieEntity.setName(request.getName());
		subSerieEntity.setProcess(request.getProcess());
		subSerieEntity.setRetentionTime(request.getRetentionTime());
		subSerieEntity.setCreationDate(new Date());
		for (DocumentaryTypeInDto item : request.getDocumentaryTypeList()) {
			DocumentaryTypeEntity dmt = new DocumentaryTypeEntity();
			dmt.setName(item.getName());
			dmt.setIsDeleted(Boolean.FALSE);
			subSerieEntity.addDocumentaryType(dmt);
		}
		return this.subSerieRepository.save(subSerieEntity);
	}

	private SubSerieOutDto createSuccessfulResponse(SubSerieEntity subSerieEntityOut) {
		SubSerieOutDto response = new SubSerieOutDto();
		response.setIdTrdParent(subSerieEntityOut.getSerie().getTrd().getId());
		response.setIdSectionParent(subSerieEntityOut.getSerie().getSection().getId());
		response.setIdSerieParent(subSerieEntityOut.getSerie().getId());
		response.setId(subSerieEntityOut.getId());
		response.setCode(subSerieEntityOut.getCode());
		response.setName(subSerieEntityOut.getName());
		response.setProcess(subSerieEntityOut.getProcess());
		response.setRetentionTime(subSerieEntityOut.getRetentionTime());
		response.setCreationDate(subSerieEntityOut.getCreationDate());
		response.setLastModifiedDate(subSerieEntityOut.getLastModifiedDate());
		response.setFinalDisposalType(new FinalDisposalTypeDto());
		response.getFinalDisposalType().setId(subSerieEntityOut.getFinalDisposalType().getId());
		response.getFinalDisposalType().setName(subSerieEntityOut.getFinalDisposalType().getName());
		response.getFinalDisposalType().setInitials(subSerieEntityOut.getFinalDisposalType().getInitials());
		response.setDocumentaryTypeList(new ArrayList<>());
		for (DocumentaryTypeEntity item : subSerieEntityOut.getDocumentaryTypeList()) {
			if (Boolean.FALSE.equals(item.getIsDeleted())) {
				DocumentaryTypeInDto itemDto = new DocumentaryTypeInDto();
				itemDto.setId(item.getId());
				itemDto.setName(item.getName());
				response.getDocumentaryTypeList().add(itemDto);
			}
		}
		return response;
	}

	private List<SubSerieOutDto> createSuccessfulResponse(List<SubSerieEntity> listSubSerie) {
		List<SubSerieOutDto> response = new ArrayList<>();
		listSubSerie.forEach(s -> {
			SubSerieOutDto dto = this.createSuccessfulResponse(s);
			response.add(dto);
		});
		return response;
	}
}
