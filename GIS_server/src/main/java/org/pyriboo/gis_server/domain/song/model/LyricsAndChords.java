package org.pyriboo.gis_server.domain.song.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// subDocument
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LyricsAndChords {

	private String lyrics;

	private String chords;

}
