package com.project.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.domain.ladder.Ladder;

@Component
public class DatasetService {

	@Autowired
	UserRepository userRepository;	
	@Autowired
	ProgressBarService progressBarService;
	@Autowired
	LadderTableEntry ladderTableEntry;

	private static CurrentLeagueService currentLeagueService = new CurrentLeagueService();
	private static List<String> leagues = new ArrayList<>();
	private static List<List<LadderTableEntry>> currentDataset = new ArrayList<>();
	private static List<List<LadderTableEntry>> latestDataset = new ArrayList<>();
	private static List<List<LadderTableEntry>> newDataset = new ArrayList<>();
	private static DecimalFormat formatter = new DecimalFormat("#,###");
	private static double amount;
	private static String number;
	private static String latest;
	private static String current;
	private static String difference;
	private static String rankDifference;
	private static String xpPerHour;
	private static String theExperience;
	private static String timeStamp;
	private static String latestRank;
	private static String currentRank;
	private static Long newXPPH, oldXPPH;
	private static Long newRank, oldRank;
	private static String levelProgressBar;
	private static ResponseEntity<Ladder> response;
	private static LadderTableEntry entry;
	private static RestTemplate restTemplate;
	private static List<LadderTableEntry> tableEntries = new ArrayList<>();

	public DatasetService() throws InterruptedException {
	}

	public List<List<LadderTableEntry>> getLatestDataSet() throws InterruptedException {
		leagues = currentLeagueService.getLeagues();
		newDataset = new ArrayList<>();
		int i = 0;
		for (; i < 4; i++) {
			tableEntries = new ArrayList<>();
			String url = "http://api.pathofexile.com/ladders/" + leagues.get(i) + "?limit=200";
			restTemplate = new RestTemplate();
			response = restTemplate.getForEntity(url, Ladder.class);

			int j = 0;
			int length = 200;// response.getBody().getEntries().length;
			for (; j < length; j++) {
				entry = new LadderTableEntry();
				entry.setRank(response.getBody().getEntries()[j].getRank());
				entry.setOnline(response.getBody().getEntries()[j].getOnline());
				entry.setCharacter(response.getBody().getEntries()[j].getCharacter().getName());
				entry.setDead(response.getBody().getEntries()[j].getDead());
				entry.setAccount(response.getBody().getEntries()[j].getAccount().getName());
				entry.setLevel(response.getBody().getEntries()[j].getCharacter().getLevel());
				entry.setTheClass(response.getBody().getEntries()[j].getCharacter().getTheClass());
				entry.setChallenges(response.getBody().getEntries()[j].getAccount().getChallenges().getTotal());
				entry.setExperience(response.getBody().getEntries()[j].getCharacter().getExperience());
				if (response.getBody().getEntries()[j].getAccount().getTwitch() != null) {
					entry.setTwitch(response.getBody().getEntries()[j].getAccount().getTwitch().getName());
				} else {
					entry.setTwitch("");
				}
				tableEntries.add(entry);
			}
			newDataset.add(tableEntries);
			Thread.sleep(500);
		}
		if (currentDataset.size() == 0) {
			currentDataset = newDataset;
			latestDataset = newDataset;
		}
		return newDataset;
	}

	public void calculateDataSet() throws InterruptedException {
		// copy latest to current dataset
		currentDataset = latestDataset;
		// get the latest dataset
		newDataset = getLatestDataSet();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 200; j++) {
				for (int k = 0; k < 200; k++) {
					if (newDataset.get(i).get(j).getCharacter().equals(currentDataset.get(i).get(k).getCharacter())) {
						// character match then calculate xph

						latest = (newDataset.get(i).get(j).getExperience()).replaceAll(",", "");
						current = (currentDataset.get(i).get(k).getExperience()).replaceAll(",", "");

						if (latest.equals("")) {
							newXPPH = new Long(0);
						} else {
							newXPPH = Long.parseLong(latest);
						}

						if (current.equals("")) {
							oldXPPH = new Long(0);
						} else {
							oldXPPH = Long.parseLong(current);
						}

						latestRank = (newDataset.get(i).get(j).getRank()).replaceAll(",", "");
						currentRank = (currentDataset.get(i).get(k).getRank()).replaceAll(",", "");

						if (latestRank.equals("")) {
							newRank = new Long(0);
						} else {
							newRank = Long.parseLong(latestRank);
						}

						if (currentRank.equals("")) {
							oldRank = new Long(0);
						} else {
							oldRank = Long.parseLong(currentRank);
						}

						difference = String.valueOf(newXPPH - oldXPPH);
						rankDifference = String.valueOf(oldRank - newRank);
						xpPerHour = String.valueOf((newXPPH - oldXPPH) * 12);
						theExperience = formatXp(newDataset.get(i).get(k).getExperience());
						difference = formatNumber(difference);
						xpPerHour = formatNumber(xpPerHour);
						
						levelProgressBar = progressBarService.getProgressPercentage(Integer.parseInt(newDataset.get(i).get(j).getLevel()), newDataset.get(i).get(j).getExperience().replaceAll(",", ""));


						newDataset.get(i).get(j).setXph(xpPerHour);
						newDataset.get(i).get(j).setXphDifference(difference);
						newDataset.get(i).get(j).setLevelProgressBar(levelProgressBar);
						newDataset.get(i).get(j).setRankDifference(rankDifference);
						newDataset.get(i).get(j).setExperience(theExperience);
						// set polling timestamp for current time
						timeStamp = new SimpleDateFormat(" MMM d hh:mm a").format(new Date());
						newDataset.get(i).get(j).setTimeStamp(timeStamp);
					
					}
				}
			}
		}

		latestDataset = newDataset;
		System.out.println("latestDataset size : " + latestDataset.size() + " " +latestDataset.get(1).size());
		System.out.println("Start SQL Transfer.");
		
		for (int i = 0; i < 4; i++) {
			String theLeague = leagues.get(i);
			System.out.println("theLeague : " +theLeague);
			for (int j = 0; j < 200; j++) {				
				CharacterInfo characterInfo = mapToCharacterInfo(theLeague, newDataset.get(i).get(j));
				userRepository.save(characterInfo);			
			}
		}
		System.out.println("SQL Transfer Complete.");
	}

	private CharacterInfo mapToCharacterInfo(String league, LadderTableEntry entry) {
		CharacterInfo characterInfo = new CharacterInfo();
		characterInfo.setLeague(league);
		characterInfo.setAccount(entry.getAccount());
		characterInfo.setChallenges(entry.getChallenges());
		characterInfo.setCharacter(entry.getCharacter());
		characterInfo.setDead(entry.getDead());
		characterInfo.setExperience(entry.getExperience());
		characterInfo.setLevel(entry.getLevel());
		characterInfo.setLevelProgressBar(entry.getLevelProgressBar());
		characterInfo.setOnline(entry.getOnline());
		characterInfo.setRank(entry.getRank());
		characterInfo.setRankDifference(entry.getRankDifference());
		characterInfo.setTheClass(entry.getTheClass());
		characterInfo.setTimeStamp(entry.getTimeStamp());
		characterInfo.setTwitch(entry.getTwitch());
		characterInfo.setXph(entry.getXph());
		characterInfo.setXphDifference(entry.getXphDifference());
		return characterInfo;
	}

	public List<LadderTableEntry> getCalculatedDataset(String selectedLeague) {
		Runtime.getRuntime().gc();
		switch (selectedLeague) {
			case "Incursion Event (IRE001)": {
				System.out.println("getCalculatedDataset() : Incursion Event (IRE001)");
				Runtime.getRuntime().gc();
				return latestDataset.get(0);
			}
			case "Incursion Event HC (IRE002)": {
				System.out.println("getCalculatedDataset() : Incursion Event HC (IRE002)");
				Runtime.getRuntime().gc();
				return latestDataset.get(1);
			}
			case "SSF Incursion Event (IRE003)": {
				System.out.println("getCalculatedDataset() : SSF Incursion Event (IRE003)");
				Runtime.getRuntime().gc();
				return latestDataset.get(2);
			}
			case "SSF Incursion Event HC (IRE004)": {
				System.out.println("getCalculatedDataset() : SSF Incursion Event HC (IRE004)");
				Runtime.getRuntime().gc();
				return latestDataset.get(3);
			}
	
			default: {
				return new ArrayList<LadderTableEntry>();
			}
		}
	}

	public String formatNumber(String theNumber) {
		number = theNumber;
		number = number.replaceAll(",", "");
		amount = Double.parseDouble(number);
		return formatter.format(amount).replaceAll(",", "");
	}

	public String formatXp(String theNumber) {
		number = theNumber;
		number = number.replaceAll(",", "");
		amount = Double.parseDouble(number);
		return formatter.format(amount);
	}

}
