package com.tecnico.examen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tecnico.examen.model.ShowComment;

@Repository
public interface CommentRepository extends MongoRepository<ShowComment, String> {
	// Se pueden agregar consultas personalizadas si en un futuro se requieren
	List<ShowComment> findByShowId(Long showId);
}
