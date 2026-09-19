package com.tecnico.examen.service;

import com.tecnico.examen.model.dto.CommentRequestDto;

public interface ICommentService {
	
	void guardarComentario(CommentRequestDto request);
	
}
