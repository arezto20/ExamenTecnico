package com.tecnico.examen.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tecnico.examen.model.ShowComment;
import com.tecnico.examen.model.dto.CommentRequestDto;
import com.tecnico.examen.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {
	
	private final CommentRepository commentRepository;
	
	public void guardarComentario(CommentRequestDto request) {
        // Validación opcional de rating (0 a 5)
        if (request.getRating() < 0 || request.getRating() > 5) {
        	// Si no está dentro del rango lanzará un error
            throw new IllegalArgumentException("El rating debe estar entre 0 y 5.");
        }
        
        // Se crea el nuevo objeto de comentarios.
        ShowComment nuevoComentario = new ShowComment(request.getShowId(), request.getComment(), request.getRating());

        commentRepository.save(nuevoComentario);
    }
}
