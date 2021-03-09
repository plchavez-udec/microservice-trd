package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;

public interface ITrdEntityService {

	TrdDto create(TrdDto dto);

	TrdDto findById(Long id);

	List<TrdDto> findAll();

	void update(Long id, TrdDto dto);

	void delete(Long id);

}
