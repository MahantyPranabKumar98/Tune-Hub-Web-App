package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Songs;

public interface SongsService {
	public String addSongs(Songs song);
	public boolean songNameExists(String name);
	public List<Songs> getSongs();
	public void updateSongs(Songs song);

}
