package co.edu.ierdminayticha.sgd.trds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryUnitEntity;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryUnitTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;

@Repository
public interface IDocumentaryUnitRepository extends CrudRepository<DocumentaryUnitEntity, Long> {

	DocumentaryUnitEntity findByCode(String code);

	Iterable<DocumentaryUnitEntity> findAllByTrdAndDocumentaryUnitType(TrdEntity trd,
			DocumentaryUnitTypeEntity documentaryUnitType);

}
