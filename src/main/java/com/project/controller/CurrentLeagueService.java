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
		leagues.add("Synthesis");
		leagues.add("Synthesis HC");
		leagues.add("SSF Synthesis");
		leagues.add("SSF Synthesis HC");		
		return leagues;
	}

}
