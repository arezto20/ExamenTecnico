package com.tecnico.examen.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tecnico.examen.model.show;
import com.tecnico.examen.repository.showsRepository;
import com.tecnico.examen.service.IShowsService;
import com.tecnico.examen.util.LectorJson;
import com.tecnico.examen.util.TvMazeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IShowsServiceImpl implements IShowsService {
	
	private final TvMazeService tvMazeService;
	private final showsRepository showRepository;
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
		
		// Se valida si el id ya se encuentra en registrado en MongoDB 
		Optional<show> showEnCache = showRepository.findById(show_id);
		
		if (showEnCache.isPresent()) {
			show = showEnCache.get();
			return show; 
		}
		
		// Se crea la cadena json
		String cadenaJson = "";
		
		// Se consulta los shows desde el API  TV Maze
		cadenaJson = tvMazeService.consultarShow(show_id);
		
		try {
			// Se crea un objeto para mapear la respuesta
			ObjectMapper objectMapper = new ObjectMapper();
			
			// Conversión a un objeto individual
			show = objectMapper.readValue(cadenaJson, show.class);
			
			// 4. GUARDAR EL RESULTADO EN MONGO ANTES DE RETORNAR
	        showRepository.save(show);
		} catch (Exception e) {
			// TODO: handle exception
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la respuesta JSON: " + e.getMessage(), e);
		}
		
		return show;
	}
}
