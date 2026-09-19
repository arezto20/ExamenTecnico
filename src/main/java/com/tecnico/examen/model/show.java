package com.tecnico.examen.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class show {
	
	private Long id;
	private String name;
	private String channel;
	private String summary;
	private List<String> genres;
	private String status;
	
}
