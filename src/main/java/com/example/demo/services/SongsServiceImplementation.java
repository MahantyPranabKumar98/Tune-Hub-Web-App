package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;
import com.example.demo.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService{
	
	@Autowired
    SongsRepository srepo;
	
	@Override
	public String addSongs(Songs song) {
		srepo.save(song);
		return "song is added";
	}
	public boolean songNameExists(String name) {
		if(srepo.findByName(name) == null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public List<Songs> getSongs() {
		List<Songs> songList = srepo.findAll();
		return songList;
	}
	@Override
	public void updateSongs(Songs song) {
		srepo.save(song);
		
	}

}
