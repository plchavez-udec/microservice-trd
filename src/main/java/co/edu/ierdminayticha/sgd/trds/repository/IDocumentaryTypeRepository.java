package co.edu.ierdminayticha.sgd.trds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.DocumentaryTypeEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SubSerieEntity;

@Repository
public interface IDocumentaryTypeRepository extends JpaRepository<DocumentaryTypeEntity, Long> {
	DocumentaryTypeEntity findBySerieAndName(SerieEntity serie, String name);
	DocumentaryTypeEntity findBySubSerieAndName(SubSerieEntity subSerie, String name);
	List<DocumentaryTypeEntity> findAllBySubSerieAndIsDeleted(SubSerieEntity subSerie, Boolean isDeleted);
}
