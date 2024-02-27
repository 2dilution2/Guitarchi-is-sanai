package org.pyriboo.gis_server.domain.song.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongReq {
	private String title;
	private String artist;
	private List<String> hashtags;
}
