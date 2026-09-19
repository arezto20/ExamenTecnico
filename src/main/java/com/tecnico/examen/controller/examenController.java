/**
 * 
 */
package com.tecnico.examen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.examen.constant.pathsConstants;
import com.tecnico.examen.model.dto.CommentRequestDto;
import com.tecnico.examen.service.IShowsService;
import com.tecnico.examen.service.impl.CommentServiceImpl;

import lombok.RequiredArgsConstructor;

/*
 * 
 */
@RestController
@RequestMapping("${basePath}")
@RequiredArgsConstructor
public class examenController {
	
	private final IShowsService iShowsService;
	private final CommentServiceImpl commentService;
	
	@GetMapping(pathsConstants.PATH_SEARCH_SHOWS)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity obtenerShows(@RequestParam("q") String search_query) {
		//Se inicia el recurso de búsqueda
		return new ResponseEntity(iShowsService.obtenerShows(search_query), HttpStatus.OK);
	}
	
	@GetMapping(pathsConstants.PATH_SHOWS)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity consultaShow(@PathVariable("show_id") Long show_id) {
		//Se inicia el recurso de búsqueda
		return new ResponseEntity(iShowsService.consultaShow(show_id), HttpStatus.OK);
	}
	
	@PostMapping(pathsConstants.PATH_COMMENTS)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> registrarComentario(@RequestBody CommentRequestDto request) {
		try {
			//Se inicia el recurso de comentarios
			commentService.guardarComentario(request);
			return ResponseEntity.status(HttpStatus.CREATED).body("Calificación y comentario guardados correctamente.");
		} catch (IllegalArgumentException e) {
            // Si hay un error de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: " + e.getMessage());
            
        } catch (Exception e) {
            // Para cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR: No se pudo guardar el comentario.");
        }
	}
	
	
}
