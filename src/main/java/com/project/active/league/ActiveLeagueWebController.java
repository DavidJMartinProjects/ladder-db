package com.project.active.league;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/active-leagues")
public class ActiveLeagueWebController {
	
	@Autowired
	ActiveLeagueService activeLeagueService;
	
	@RequestMapping(value = "/redundant", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	public void getDelveLadderData(@RequestBody Map<String, String> redundantLeagues) throws InterruptedException {
		System.out.println("Processing request to /active-leagues/redundant");		
		System.out.println("redundantLeagues : " +redundantLeagues);
		activeLeagueService.removeRedundantLeaguesFromDb(redundantLeagues);
	}

}
