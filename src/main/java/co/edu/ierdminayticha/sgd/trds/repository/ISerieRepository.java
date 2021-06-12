package co.edu.ierdminayticha.sgd.trds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.SectionEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;

@Repository
public interface ISerieRepository extends JpaRepository<SerieEntity, Long> {

	SerieEntity findByNameAndTrd(String name, TrdEntity trd);
	List<SerieEntity> findAllByTrdOrderByCodeAsc(TrdEntity trd);
	List<SerieEntity> findAllByTrdAndSectionOrderByCodeAsc(TrdEntity trd, SectionEntity section);

}
