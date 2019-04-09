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
		leagues.add("Synthesis Event (IRE001)");
		leagues.add("Synthesis Event HC (IRE002)");
		leagues.add("SSF Synthesis Event (IRE003)");
		leagues.add("SSF Synthesis Event HC (IRE004)");		
		return leagues;
	}

}
