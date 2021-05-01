package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;

public interface ITrdService {

	TrdDto create(TrdDto dto);

	TrdDto findById(Long id);

	List<TrdOutDto> findAll();

	void update(Long id, TrdDto dto);

	void delete(Long id);

}
