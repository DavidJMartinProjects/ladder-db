package com.project.topten.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.topten.pojo.TopTenLadderResponse;
import com.project.topten.pojo.TopTenLeague;

@Component
public class TopTenDbService {
	
	@Autowired
	TopTenLeague topTenLeague;
			
	@Autowired
	TopTenDaoImpl topTenDaoImpl;
	
	public TopTenLadderResponse getTopTenLadder() {
		return topTenDaoImpl.fetchTopTenDelveLadder();
	}
	
	// a method here for each league
}
