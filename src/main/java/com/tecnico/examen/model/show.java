package com.tecnico.examen.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "shows")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class show {
	
	@Id
	private Long id;
	private String name;
	private String channel;
	private String summary;
	private List<String> genres;
	
	private String status;
	private String url;
    private String type;
    private String language;
    private Integer runtime;
    private Integer averageRuntime;
    private String premiered;
    private String ended;
    private String officialSite;
    private Map<String, Object> schedule;
    private Rating rating;
    private Integer weight;
    private Network network;
    private WebChannel webChannel;
    private Object dvdCountry;
    private Map<String, Object> externals;
    private Image image;
    private Long updated;
    private Map<String, Object> _links;	
	
}
