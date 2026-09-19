package com.tecnico.examen.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "show_comments")
public class ShowComment {
	@Id
    private String id;
    private Long showId;
    private String comment;
    private Integer rating;
    
    // Constructor con parámetros
    public ShowComment(Long showId, String comment, Integer rating) {
        this.showId = showId;
        this.comment = comment;
        this.rating = rating;
    }
}
