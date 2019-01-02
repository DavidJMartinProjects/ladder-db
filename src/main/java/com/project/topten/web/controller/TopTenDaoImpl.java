package com.project.topten.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.controller.LadderTableEntryEntity;
import com.project.controller.UserRepository;
import com.project.domain.datatable.LadderTableEntry;
import com.project.topten.pojo.TopTenLadderResponse;
import com.project.topten.pojo.TopTenLeague;

@Component
public class TopTenDaoImpl {

	@Autowired
	UserRepository userRepository;

	List<LadderTableEntryEntity> delve;
	List<LadderTableEntryEntity> lab;
	List<LadderTableEntryEntity> raceTo100;

	public TopTenLadderResponse fetchTopTenDelveLadder() {
		System.out.println("fetchTopTenDelveLadderSSFHC()");	
		TopTenLadderResponse topTenLadderResponse = new TopTenLadderResponse();
		List<LadderTableEntryEntity> topTenLadder= userRepository.findAll();
		topTenLadderResponse.addToLeagueHC(buildTopTenResponse(topTenLadder, "Hardcore Betrayal"));
		topTenLadderResponse.addToLeagueHCSFF(buildTopTenResponse(topTenLadder, "SSF Betrayal HC"));
		topTenLadderResponse.addToLeagueSFF(buildTopTenResponse(topTenLadder, "SSF Betrayal"));
		topTenLadderResponse.addToLeagueStd(buildTopTenResponse(topTenLadder, "Betrayal"));
		return topTenLadderResponse;
	}

	private TopTenLeague buildTopTenResponse(List<LadderTableEntryEntity> list, String league) {
		System.out.println("buildTopTenResponse() : Size " + list.size());		
		delve = new ArrayList<>();
		lab = new ArrayList<>();
		raceTo100 = new ArrayList<>();

		for (LadderTableEntryEntity entry : list) {
			System.out.println("entry.getLeagueName() : " + entry.getLeagueName());
			if (entry.getLeagueDifficulty().equals(league)) {
				if (entry.getLeagueName().equals("TableDataDelveTopTen")) {
					System.out.println("Delve Entry found.");
					delve.add(entry);
				} else if (entry.getLeagueName().equals("TableDataUberLabTopTen")) {
					System.out.println("Lab Entry found.");
					lab.add(entry);
				} else if (entry.getLeagueName().equals("TableDataRaceTo100")) {
					System.out.println("RaceTo100 Entry found.");
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
