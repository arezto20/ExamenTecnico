package com.tecnico.examen.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnico.examen.model.show;

@Component
public class LectorJson {
	
	public static List<show> obtenerListaShows(String jsonString) {
		
		List<show> listaShows = new ArrayList<>();
		
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // Se verifica que sea un arreglo JSON válido
            if (!rootNode.isArray()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "La respuesta de la API no tiene el formato de lista esperado.");
            }

            // Se recorre cada elemento del array principal
            for (JsonNode nodoElemento : rootNode) {
                // Entra a la propiedad "show"
                JsonNode showNode = nodoElemento.get("show");

                if (showNode != null && showNode.has("id")) {
                	show show = new show();

                	/*
                	 * Se busca de cada elemento las siguientes propiedades: id, name, channel, summary y genres
                	 */
                	
                    // 1. id
                    show.setId(showNode.get("id").asLong());

                    // 2. name
                    if (showNode.has("name") && !showNode.get("name").isNull()) {
                        show.setName(showNode.get("name").asText());
                    }

                    // 3. channel (Viene dentro del objeto "network" -> propiedad "name")
                    JsonNode networkNode = showNode.get("network");
                    if (networkNode != null && networkNode.has("name") && !networkNode.get("name").isNull()) {
                        show.setChannel(networkNode.get("name").asText());
                    } else {
                        show.setChannel("Desconocido"); // Valor por defecto si no tiene canal (ej. web channels)
                    }

                    // 4. summary
                    if (showNode.has("summary") && !showNode.get("summary").isNull()) {
                        show.setSummary(showNode.get("summary").asText());
                    }

                    // 5. genres (Es un array de strings en el JSON)
                    List<String> genresList = new ArrayList<>();
                    JsonNode genresNode = showNode.get("genres");
                    if (genresNode != null && genresNode.isArray()) {
                        for (JsonNode genre : genresNode) {
                            genresList.add(genre.asText());
                        }
                    }
                    show.setGenres(genresList);

                    // Se agrega la lista llena
                    listaShows.add(show);
                }
            }

            // Si la lista quedó vacía pero el JSON era válido, se lanza un error 204
            if (listaShows.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, 
                    "No se encontraron shows válidos para procesar en el JSON."
                );
            }

            return listaShows;

        } catch (ResponseStatusException rse) {
            // Re-lanzamos la excepción HTTP 500 configurada explícitamente
            throw rse;
        } catch (Exception e) {
            // Captura cualquier otro error de sintaxis del JSON y lanza el error 500
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la respuesta JSON: " + e.getMessage(), e);
        }
    }
	
}
