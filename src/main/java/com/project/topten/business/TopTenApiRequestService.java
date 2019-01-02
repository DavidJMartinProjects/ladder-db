package com.project.topten.business;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.project.controller.UserRepository;
import com.project.topten.pojo.TopTenLadderResponse;
import com.project.topten.pojo.TopTenLeague;
import com.project.topten.util.TopTenUtil;

@Component
public class TopTenApiRequestService {

	@Autowired
	TopTenLaddersService topTenLaddersService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TopTenUtil topTenUtil;

	public static TopTenLadderResponse topTenLadderResponse = new TopTenLadderResponse();

	static TopTenLeague topTenLeague;

	public TopTenLadderResponse getTopTenLaddersDataSet() throws InterruptedException {
		return topTenLadderResponse;
	}

	public void getTopTenLadderData() throws InterruptedException {
		List<String> leaguesList = topTenUtil.getLeagueNamesAsList();
		leaguesList.forEach(System.out::println);

		userRepository.deleteAll();
		for (String league : leaguesList) {
			TopTenLeague topTenLeague = topTenLaddersService.getLeagueTopTenData(league);
			saveToMySQL(topTenLeague);
		}
	}

	public void saveToMySQL(TopTenLeague topTenLeague) {
		System.out.println("saving to Delve Data to SQL database : " + topTenLeague.getTableDataDelve());
		System.out.println("Start of SQL Transfer.");
		// calculate xph and rank difference here from sql database
		userRepository.saveAll(topTenLeague.getTableDataDelve());
		userRepository.saveAll(topTenLeague.getTableDataUberLabTopTen());
		userRepository.saveAll(topTenLeague.getTableDataRaceTo100());
		userRepository.flush();
		System.out.println("SQL Transfer complete.");

	}

}
