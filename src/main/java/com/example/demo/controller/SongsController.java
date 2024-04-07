package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entities.Songs;
import com.example.demo.services.SongsService;

@Controller
public class SongsController {
	
	@Autowired
	SongsService songserve;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song) {
		boolean songStatus = songserve.songNameExists(song.getName());
		if(songStatus == false) {
		songserve.addSongs(song);
		return "songsuccess";	
	}
		else {
			return "songfail";
		}
	}
	@GetMapping("/map-viewsongs")
	public String getSongs(Model model) {
		List<Songs> songList = songserve.getSongs();
		model.addAttribute("songList", songList);
		return "displaysongs";
	}
	@GetMapping("/viewsongs")
	public String getCustomerSongs(Model model) {
		boolean primeCustomerStatus = true;
		if(primeCustomerStatus == true) {
			List<Songs> songList = songserve.getSongs();
			model.addAttribute("songList", songList);
			return "displaysongs";
		}
		else {
		return "makepayment";
		}
	}
}
