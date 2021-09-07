package co.edu.ierdminayticha.sgd.trds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.trds.entity.MessageEntity;

@Repository
public interface IMessageRepository extends CrudRepository<MessageEntity, String> {

}
