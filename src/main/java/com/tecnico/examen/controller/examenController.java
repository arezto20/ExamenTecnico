/**
 * 
 */
package com.tecnico.examen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.examen.constant.pathsConstants;
import com.tecnico.examen.service.IShowsService;

import lombok.RequiredArgsConstructor;

/*
 * 
 */
@RestController
@RequestMapping("${basePath}")
@RequiredArgsConstructor
public class examenController {
	
	private final IShowsService iShowsService;
	
	@GetMapping(pathsConstants.PATH_SEARCH_SHOWS)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity obtenerShows(@RequestParam("q") String search_query) {
		//Se inicia el recurso de búsqueda
		return new ResponseEntity(iShowsService.obtenerShows(search_query), HttpStatus.OK);
	}
	
}
