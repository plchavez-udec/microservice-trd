package co.edu.ierdminayticha.sgd.trds.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;
import co.edu.ierdminayticha.sgd.trds.exception.GeneralException;
import co.edu.ierdminayticha.sgd.trds.repository.ITrdEntityRepository;

@Service
public class TrdServiceImpl implements ITrdService {

	private static final String EXISTING_RESOURCE_MESSAGE = "El recurso con version (%s) ya existe ";

	private static final String NO_EXISTEN_RESOURCE_MESSAGE = "No existe el recurso con id (%s) ";

	private static final String NO_EXISTEN_INFO_MESSAGE = "No existe información para mostrar";

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
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		return createSuccessfulResponse(entity);
	}

	@Override
	public List<TrdOutDto> findAll() {

		Iterable<TrdEntity> entityList = this.repository.findAll();

		if (entityList == null) {
			throw new NoSuchElementException(NO_EXISTEN_INFO_MESSAGE);
		}

		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, TrdDto dto) {

		TrdEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		this.modelMapper.map(dto, entity);

		entity.setLastModifiedDate(new Date());

		this.repository.save(entity);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	private void validateExistenceOfTheResource(String version) {

		TrdEntity entity = this.repository.findByVersion(version);

		if (entity != null) {
			throw new GeneralException(String.format(EXISTING_RESOURCE_MESSAGE, version));
		}

	}

	private TrdEntity toPersist(TrdDto dto) {

		TrdEntity entity = this.modelMapper.map(dto, TrdEntity.class);

		entity.setCreationDate(new Date());

		entity = this.repository.save(entity);

		return entity;

	}

	private TrdDto createSuccessfulResponse(TrdEntity entity) {

		TrdDto dto = this.modelMapper.map(entity, TrdDto.class);

		return dto;

	}

	private List<TrdOutDto> createSuccessfulResponse(Iterable<TrdEntity> entityList) {

		List<TrdOutDto> dtoList = modelMapper.map(entityList, new TypeToken<List<TrdOutDto>>() {
		}.getType());

		return dtoList;

	}

}
