package co.edu.ierdminayticha.sgd.trds.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypesEntity;
import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryUnitEntity;

@Repository
public interface IDocumentaryTypesRepository extends CrudRepository<DocumentaryTypesEntity, Long> {
	
	DocumentaryTypesEntity findByName(String name);

	Iterable<DocumentaryTypesEntity> findAllByDocumentaryUnitAndIsDeleted(DocumentaryUnitEntity documentaryUnit, Boolean isDeleted);
	
	Optional<DocumentaryTypesEntity> findByIdAndIsDeleted(Long id, Boolean IsDeleted);

}
