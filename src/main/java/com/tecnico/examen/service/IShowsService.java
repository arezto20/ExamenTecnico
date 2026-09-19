package com.tecnico.examen.service;

import java.util.List;

import com.tecnico.examen.model.show;

public interface IShowsService {
	/**
	 * @param search_query
	 * @return List<show>
	 */
	List<show> obtenerShows(String search_query);
	
	show consultaShow(Long show_id);
}
