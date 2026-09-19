package com.tecnico.examen.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tecnico.examen.model.show;
import com.tecnico.examen.service.IShowsService;
import com.tecnico.examen.util.LectorJson;
import com.tecnico.examen.util.TvMazeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IShowsServiceImpl implements IShowsService {
	
	private final TvMazeService tvMazeService;
	private final LectorJson lectorJson;
		
	/**
	 * @param search_query
	 * @return List<show>
	 */
	@Override
	public List<show> obtenerShows(String search_query) {
		// Se crea la lista de shows
		List<show> listaShows = new ArrayList<>();
		
		// Se crea la cadena json
		String cadenaJson = "";
		
		// Se consulta los shows desde el API  TV Maze
		cadenaJson = tvMazeService.buscarShows(search_query);
		
		// Se llena la lista de shows con la cadena json
		listaShows = lectorJson.obtenerListaShows(cadenaJson); 
		
		return listaShows;
	}
	
	public show consultaShow(Long show_id) {
		// Se crea el objeto shows
		show show = new show();
		
		// Se crea la cadena json
		String cadenaJson = "";
		
		// Se consulta los shows desde el API  TV Maze
		cadenaJson = tvMazeService.consultarShow(show_id);
		
		try {
			// Se crea un objeto para mapear la respuesta
			ObjectMapper objectMapper = new ObjectMapper();
			
			// Conversión a un objeto individual
			show = objectMapper.readValue(cadenaJson, show.class);
		} catch (Exception e) {
			// TODO: handle exception
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la respuesta JSON: " + e.getMessage(), e);
		}
		
		return show;
	}
}
