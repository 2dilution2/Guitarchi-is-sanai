package org.pyriboo.gis_server.domain.playlist.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaylistRes {
	private String id;
	private String userId;
	private String title;
	private List<String> songIds;
}
