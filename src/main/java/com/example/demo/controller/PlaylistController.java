package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongsService;

@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistService pserve;
	
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		
		//fetching the songs using song service 
		List<Songs> songList = sserv.getSongs();
		
		//adding the songs in the model
		model.addAttribute("songList", songList);
		
		//sending createplaylist
		return "createplaylist";
	}
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		
		//adding playlist
		pserve.addPlaylist(playlist);
		
		//updating songs
		List<Songs> songslist = playlist.getSongs();
		for(Songs song : songslist) {
			song.getPlaylist().add(playlist);
			sserv.updateSongs(song);
		}
		return "playlistsuccess";
	}
	@GetMapping("/map-viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playlist = pserve.fetchPlaylist();
		model.addAttribute("playlist", playlist);
		return "viewplaylists";
	}

}
