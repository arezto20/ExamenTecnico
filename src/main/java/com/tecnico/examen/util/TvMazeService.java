package com.tecnico.examen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TvMazeService {
	
	// Si  el valor del application.properties automáticamente
    @Value("${tvmaze.api.url}")
    private String apiUrl;

	public String buscarShows(String query) {
		// Construimos la URL con el parámetro de búsqueda dinámico
        String url = apiUrl + "?q=" + query;

        RestTemplate restTemplate = new RestTemplate();

        // Hacemos la petición GET y obtenemos la respuesta en formato JSON (como String)
        String respuestaJson = restTemplate.getForObject(url, String.class);

        return respuestaJson;
	}
}
