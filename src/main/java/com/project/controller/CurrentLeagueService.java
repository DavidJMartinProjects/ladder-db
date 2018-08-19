package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CurrentLeagueService {

	private List<String> leagues = new ArrayList<>();

	public CurrentLeagueService() {
	}

	public List getLeagues() {
		leagues.add("Incursion Event (IRE001)");
		leagues.add("Incursion Event HC (IRE002)");
		leagues.add("SSF Incursion Event (IRE003)");
		leagues.add("SSF Incursion Event HC (IRE004)");		
		return leagues;
	}

}
