package com.project.custom.league;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.controller.CustomLeagueRepository;
import com.project.controller.LeagueIdDetails;
import com.project.controller.LeagueIdDetailsRepository;
import com.project.domain.datatable.LadderTableEntry;

@Component
public class UpdateCustomLeagueService {
	
	@Autowired
	CustomLeagueRepository leagueRepository;
	
	@Autowired
	CustomLeagueRepository customLeagueRepository;
	
	@Autowired
	CustomLeagueRequestService customLeagueRequestService;
	
	@Autowired
	LeagueIdDetailsRepository leagueIdDetailsRepository;
	
	@Autowired
	LeagueComparisionService leagueComparisionService;
	
	List<LadderTableEntry> oldLeagueData;
	List<LadderTableEntry> newLeagueData;
	
	public void updateCustomLeagues() throws InterruptedException {
		List<LeagueIdDetails> currentLeagueIds = leagueIdDetailsRepository.fetchCustomLadderDetailsFromDb();
		System.out.println("updateCustomLeagues() called. Current Active leagues IDs : ");
		currentLeagueIds.forEach(e -> System.out.println(e.getLeague_id()));
		for(LeagueIdDetails leagueIdDetails : currentLeagueIds) {
			oldLeagueData = new ArrayList<>();
			newLeagueData = new ArrayList<>();
			List<LadderTableEntry> oldLeagueData = customLeagueRepository.getCustomLeagueFromDb(leagueIdDetails.getLeague_name());
			List<LadderTableEntry> newLeagueData = customLeagueRequestService.getCustomLeagueData(leagueIdDetails.getLeague_id(), leagueIdDetails.getLeague_name());
			List<LadderTableEntry> calculatedLadder = leagueComparisionService.compareLeagues(oldLeagueData, newLeagueData);
			customLeagueRepository.deleteRedundantLeagueFromDb(leagueIdDetails.getLeague_name());
			if(!calculatedLadder.isEmpty()) {
				leagueRepository.saveAll(calculatedLadder);
				leagueRepository.flush();
				System.out.println("------- Active League "+ leagueIdDetails.getLeague_name()+" Successfully Updated. ---------");
			}
		}
	}

}
