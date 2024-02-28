package org.pyriboo.gis_server.domain.song.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "hashtags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hashtag {

	@Id
	private Long id;

	private String name;

}
