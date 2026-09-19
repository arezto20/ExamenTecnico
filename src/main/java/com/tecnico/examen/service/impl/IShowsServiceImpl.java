package com.tecnico.examen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}
