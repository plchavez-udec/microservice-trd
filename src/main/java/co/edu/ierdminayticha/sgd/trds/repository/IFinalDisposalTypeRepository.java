package co.edu.ierdminayticha.sgd.trds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.FinalDisposalTypeEntity;

@Repository
public interface IFinalDisposalTypeRepository extends CrudRepository<FinalDisposalTypeEntity, Long> {

}
