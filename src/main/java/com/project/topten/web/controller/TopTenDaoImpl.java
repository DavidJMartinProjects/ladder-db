package com.project.topten.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.controller.TopTenLadderTableEntryEntity;
import com.project.controller.LeagueRepository;
import com.project.topten.pojo.TopTenLadderResponse;
import com.project.topten.pojo.TopTenLeague;

@Component
public class TopTenDaoImpl {

	@Autowired
	LeagueRepository userRepository;
	
	List<TopTenLadderTableEntryEntity> delve;
	List<TopTenLadderTableEntryEntity> lab;
	List<TopTenLadderTableEntryEntity> raceTo100;

	public TopTenLadderResponse fetchTopTenDelveLadder() {
		System.out.println("fetchTopTenDelveLadderSSFHC()");	
		TopTenLadderResponse topTenLadderResponse = new TopTenLadderResponse();
//		List<LadderTableEntryEntity> topTenLadder= userRepository.findAll();
//		topTenLadderResponse.addToLeagueHC(buildTopTenResponse(topTenLadder, "Hardcore Betrayal"));
//		topTenLadderResponse.addToLeagueHCSFF(buildTopTenResponse(topTenLadder, "SSF Betrayal HC"));
//		topTenLadderResponse.addToLeagueSFF(buildTopTenResponse(topTenLadder, "SSF Betrayal"));
//		topTenLadderResponse.addToLeagueStd(buildTopTenResponse(topTenLadder, "Betrayal"));
		
		
		List<TopTenLadderTableEntryEntity> test = userRepository.fetchFilteredTopTenLadder();
		
		for(TopTenLadderTableEntryEntity ladderTableEntryEntity : test) {
			System.out.println(ladderTableEntryEntity);
		}

//		List<LadderTableEntryEntity> topTenLadder= userRepository.findAll();
		topTenLadderResponse.addToLeagueHC(buildTopTenResponse(userRepository.fetchFilteredTopTenLadder(), "Hardcore Synthesis"));
		topTenLadderResponse.addToLeagueHCSFF(buildTopTenResponse(userRepository.fetchFilteredTopTenLadder(), "SSF Synthesis HC"));
		topTenLadderResponse.addToLeagueSFF(buildTopTenResponse(userRepository.fetchFilteredTopTenLadder(), "SSF Synthesis"));
		topTenLadderResponse.addToLeagueStd(buildTopTenResponse(userRepository.fetchFilteredTopTenLadder(), "Synthesis"));
//		System.out.println("================= Print Standard League Delve ladder ========================");
//		topTenLadderResponse.getLeagueStd().getTableDataDelve().forEach(System.out::println);
		return topTenLadderResponse;
	}

	private TopTenLeague buildTopTenResponse(List<TopTenLadderTableEntryEntity> list, String league) {
		delve = new ArrayList<>();
		lab = new ArrayList<>();
		raceTo100 = new ArrayList<>();

		for (TopTenLadderTableEntryEntity entry : list) {
			if (entry.getLeagueDifficulty().equals(league)) {
				if (entry.getLeagueName().equals("TableDataDelveTopTen")) {
					delve.add(entry);
				} else if (entry.getLeagueName().equals("TableDataUberLabTopTen")) {
					lab.add(entry);
				} else if (entry.getLeagueName().equals("TableDataRaceTo100")) {
					raceTo100.add(entry);
				}
			}
		}
		
		TopTenLeague topTenLeague = new TopTenLeague();
		topTenLeague.setTableDataDelve(delve);
		topTenLeague.setTableDataUberLabTopTen(lab);
		topTenLeague.setTableDataRaceTo100(raceTo100);
		return topTenLeague;
	}

}
