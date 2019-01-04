package com.project.custom.league;

import static com.project.business.Constants.currentRank;
import static com.project.business.Constants.formatter;
import static com.project.business.Constants.latestRank;
import static com.project.business.Constants.timeStamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.controller.ProgressBarService;
import com.project.domain.datatable.LadderTableEntry;

@Component
public class LeagueComparisionService {

	@Autowired
	ProgressBarService progressBarService;

	List<LadderTableEntry> currentDataset;
	List<LadderTableEntry> newDataset;

	public List<LadderTableEntry> compareLeagues(List<LadderTableEntry> oldLadder, List<LadderTableEntry> newLadder)
			throws InterruptedException {
		return calculateDataSet(new ArrayList<>(oldLadder), new ArrayList<>(newLadder));
	}

	public List<LadderTableEntry> calculateDataSet(List<LadderTableEntry> oldLadder, List<LadderTableEntry> newLadder)
			throws InterruptedException {
		currentDataset = new ArrayList<>(oldLadder);
		newDataset = new ArrayList<>(newLadder);

		String levelProgressBar = new String();
		String difference = new String();
		String rankDifference = new String();
		String xpPerHour = new String();
		String theExperience = new String();
		String theDifference = new String();
		String theXpPerHour = new String();
		String latest = new String();
		String current = new String();

		if (currentDataset == null || currentDataset.isEmpty()) {
			currentDataset = new ArrayList<>(newLadder);
		}

		// copy latest to current dataset
		// currentDataset = oldLadder;
		// newDataset = newLadder;
		
		for (LadderTableEntry newDatasetEntry : newDataset) {
		for (LadderTableEntry currentDatasetEntry : currentDataset) {
	
				if (newDatasetEntry.getCharacterId().equals(currentDatasetEntry.getCharacterId())) {
					LadderTableEntry latestMatchingEnty = new LadderTableEntry();
					System.out.println("+++++++ Match Found ++++++++++");

					// character match then calculate xph

					latest = new String(newDatasetEntry.getExperience());
					latest.replaceAll(",", "");
					current = new String(currentDatasetEntry.getExperience());
					current.replaceAll(",", "");
					System.out.println("latest : " + latest);
					System.out.println("current : " + current);

					Long newXPPH, oldXPPH, newRank, oldRank;
					if (latest.isEmpty() || latest == null) {
						newXPPH = new Long(0);
					} else {
						newXPPH = Long.parseLong(latest.replaceAll(",", ""));
					}
					System.out.println("newXPPH : " + newXPPH);

					if (current.isEmpty() || current == null) {
						oldXPPH = new Long(0);
					} else {
						oldXPPH = Long.parseLong(current.replaceAll(",", ""));
					}

					latestRank = (newDatasetEntry.getRank()).replaceAll(",", "");
					currentRank = (currentDatasetEntry.getRank()).replaceAll(",", "");

					if (latestRank.isEmpty() || latestRank == null) {
						newRank = new Long(0);
					} else {
						newRank = Long.parseLong(latestRank.replaceAll(",", ""));
					}

					if (currentRank.isEmpty() || currentRank == null) {
						oldRank = new Long(0);
					} else {
						oldRank = Long.parseLong(currentRank.replaceAll(",", ""));
					}

					difference = String.valueOf(newXPPH - oldXPPH);
					rankDifference = String.valueOf(oldRank - newRank);
					xpPerHour = String.valueOf((newXPPH - oldXPPH) * 12);
					theExperience = formatXp(newDatasetEntry.getExperience());
					theDifference = formatNumber(difference);
					theXpPerHour = formatNumber(xpPerHour);
					levelProgressBar = progressBarService.getProgressPercentage(
							Integer.parseInt(newDatasetEntry.getLevel()),
							newDatasetEntry.getExperience().replaceAll(",", ""));
					newDatasetEntry.setXph(theXpPerHour);
					newDatasetEntry.setXphDifference(theDifference);
					newDatasetEntry.setRankDifference(rankDifference);
					newDatasetEntry.setExperience(theExperience);
					newDatasetEntry.setLevelProgressBar(levelProgressBar);
					// set polling timestamp for current time
					timeStamp = new SimpleDateFormat(" MMM d hh:mm a").format(new Date());
					newDatasetEntry.setTimeStamp(timeStamp);
				}
			}
		}
		return newDataset;

	}

	public static String formatNumber(String theNumber) {
		String number = new String();
		number = theNumber.replaceAll(",", "");
		double amount = 0.0;
		amount = Double.parseDouble(number);
		return formatter.format(amount).replaceAll(",", "");
	}

	public static String formatXp(String theNumber) {
		String number = new String();
		number = theNumber;
		number = number.replaceAll(",", "");
		double amount = 0.0;
		amount = Double.parseDouble(number);
		return formatter.format(amount);
	}

}
