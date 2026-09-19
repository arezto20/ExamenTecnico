package com.tecnico.examen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CommentDetail {
	
	private String comment;
    private Integer rating;
    
    // Constructor con parámetros
    public CommentDetail(String comment, Integer rating) {
        this.comment = comment;
        this.rating = rating;
    }
}
