package com.tecnico.examen.repository;

import com.tecnico.examen.model.show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface showsRepository extends MongoRepository<show, Long> {
	// MongoRepository ya incluye métodos como findById() y save() listos para usar
}
