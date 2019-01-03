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
		System.out.println("updateCustomLeagues() called. Current leagues : ");
		for(LeagueIdDetails leagueIdDetails : currentLeagueIds) {
			oldLeagueData = new ArrayList<>();
			newLeagueData = new ArrayList<>();
			System.out.println("Current League Details" +leagueIdDetails.getLeague_id() +" "+ leagueIdDetails.getLeague_name());
			List<LadderTableEntry> oldLeagueData = customLeagueRepository.getCustomLeagueFromDb(leagueIdDetails.getLeague_name());
			System.out.println("league id : " +leagueIdDetails.getLeague_id()+ " oldLeagueData size : " +oldLeagueData.size());
			oldLeagueData.forEach(System.out::println);
			List<LadderTableEntry> newLeagueData = customLeagueRequestService.getCustomLeagueData(leagueIdDetails.getLeague_id(), leagueIdDetails.getLeague_name());
			System.out.println("league id : " +leagueIdDetails.getLeague_id()+ " newLeagueData size : " +newLeagueData.size());
			newLeagueData.forEach(System.out::println);
			List<LadderTableEntry> calculatedLadder = leagueComparisionService.compareLeagues(oldLeagueData, newLeagueData);
			customLeagueRepository.deleteRedundantLeagueFromDb(leagueIdDetails.getLeague_name());
			System.out.println("------- Old League Deleted. ---------");
			System.out.println("calculatedLadder.size() before saving to sql : "+calculatedLadder.size());
			
			if(!calculatedLadder.isEmpty()) {
				leagueRepository.saveAll(calculatedLadder);
				leagueRepository.flush();
				System.out.println("------- New League Saved. ---------");
			}
		}
	}

}
