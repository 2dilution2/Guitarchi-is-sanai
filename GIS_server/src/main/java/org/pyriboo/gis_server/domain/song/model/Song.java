package org.pyriboo.gis_server.domain.song.model;

import java.util.List;

import org.pyriboo.gis_server.global.entity.BaseTime;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "songs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song extends BaseTime {

	@Id
	private String id;

	private String title;

	private String artist;

	private List<String> hashtags;

	private List<LyricsAndChords> lyricsAndChords;

}
