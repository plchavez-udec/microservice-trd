package co.edu.ierdminayticha.sgd.trds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.TrdEntity;

@Repository
public interface ITrdEntityRepository extends CrudRepository<TrdEntity, Long> {
	
	TrdEntity findByVersion(String version);

}
