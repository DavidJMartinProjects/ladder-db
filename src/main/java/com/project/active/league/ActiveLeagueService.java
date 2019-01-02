package com.project.active.league;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.controller.LeagueRepository;

@Component
public class ActiveLeagueService {
	
	@Autowired
	LeagueRepository leagueRepository;
	
	public void removeRedundantLeaguesFromDb(Map<String, String> redundantLeagues) {
		for(Map.Entry<String, String> redundantCustomLeague : redundantLeagues.entrySet()) {
			System.out.println("removing redundant league with id : " +redundantCustomLeague.getKey());
			leagueRepository.deleteRedundantLeagueFromDb(redundantCustomLeague.getKey());
		}
	}

}
