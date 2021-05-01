package co.edu.ierdminayticha.sgd.trds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.SerieEntity;
import co.edu.ierdminayticha.sgd.trds.entity.SubSerieEntity;

@Repository
public interface ISubSerieRepository extends JpaRepository<SubSerieEntity, Long> {

	SerieEntity findByNameAndSerie(String name, SerieEntity serie);
	List<SubSerieEntity> findAllBySerie(SerieEntity serie);
}
