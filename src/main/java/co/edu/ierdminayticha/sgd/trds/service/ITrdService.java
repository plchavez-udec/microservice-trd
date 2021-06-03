package co.edu.ierdminayticha.sgd.trds.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.trds.dto.TrdDto;
import co.edu.ierdminayticha.sgd.trds.dto.TrdOutDto;

public interface ITrdService {

	public TrdDto create(TrdDto dto);

	public TrdDto findById(Long id);

	public List<TrdOutDto> findAll();

	public void update(Long id, TrdDto dto);

	public void delete(Long id);

}
