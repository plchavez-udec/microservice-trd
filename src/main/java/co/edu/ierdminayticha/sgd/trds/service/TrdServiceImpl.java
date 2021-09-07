package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.ITrdEntityRepository;
import co.edu.ierdminayticha.sgd.trds.util.ResponseCodeConstants;

@Service
public class TrdServiceImpl implements ITrdService {


	@Autowired
	private ITrdEntityRepository repository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TrdDto create(TrdDto dto) {
		validateExistenceOfTheResource(dto.getVersion());
		TrdEntity entity = toPersist(dto);
		return createSuccessfulResponse(entity);
	}

	@Override
	public TrdDto findById(Long id) {
		TrdEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new GeneralException(
						ResponseCodeConstants.ERROR_BUSINESS_TRD_NOT_EXIST));
		return createSuccessfulResponse(entity);
	}

	@Override
	public List<TrdOutDto> findAll() {
		Iterable<TrdEntity> entityList = this.repository.findAll();
		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, TrdDto dto) {}

	@Override
	public void delete(Long id) {}

	private void validateExistenceOfTheResource(String version) {
		TrdEntity entity = this.repository.findByVersion(version);
		if (entity != null) {
			throw new GeneralException(
					ResponseCodeConstants.ERROR_BUSINESS_TRD_ALREADY_EXIST);
		}
	}

	private TrdEntity toPersist(TrdDto dto) {
		TrdEntity entity = this.modelMapper.map(dto, TrdEntity.class);
		entity.setCreationDate(new Date());
		entity = this.repository.save(entity);
		return entity;
	}

	private TrdDto createSuccessfulResponse(TrdEntity entity) {
		return this.modelMapper.map(entity, TrdDto.class);

	}

	private List<TrdOutDto> createSuccessfulResponse(Iterable<TrdEntity> entityList) {
		return modelMapper.map(entityList, new TypeToken<List<TrdOutDto>>() {
		}.getType());

	}

}
