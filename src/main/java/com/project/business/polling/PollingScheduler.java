
package com.project.business.polling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.project.custom.league.UpdateCustomLeagueService;
import com.project.topten.business.TopTenApiRequestService;


@Configuration
@EnableScheduling
class PollingScheduler {
	
	@Autowired
	TopTenApiRequestService topTenApiRequestService;
	
	@Autowired
	UpdateCustomLeagueService updateCustomLeagueService;
	
	@Scheduled(initialDelay = 10000, fixedRate = 300000)
	public void pollDataFromApi() {
		try {
			Runtime.getRuntime().gc();
			Date date1 = new Date();
			System.out.println("****************************************");
			System.out.println("======== Poll Request Recieved : Updating top-ten ladders ========");
			topTenApiRequestService.getTopTenLadderData();
			Date date2 = new Date();
			computeDiff(date1, date2);
			System.out.println("======== Poll Request Complete : top-ten ladders successfully updated ========");
			System.out.println("Processing Time : " + computeDiff(date1, date2).toString());
			System.out.println("****************************************");
			java.lang.Thread.sleep(10000);
			Runtime.getRuntime().gc();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(initialDelay = 3000, fixedRate = 300000)
	public void updateCustomLeagues() throws InterruptedException {
		Runtime.getRuntime().gc();
		Date date1 = new Date();
		System.out.println("======== Poll Request Recieved : Updating active league ladders ladders ========");
		updateCustomLeagueService.updateCustomLeagues();
		Date date2 = new Date();
		computeDiff(date1, date2);
		System.out.println("======== Poll Request Complete : active league ladders successfully updated ========");
		System.out.println("Processing Time : " + computeDiff(date1, date2).toString());
		System.out.println("****************************************");
	}

	public static Map<TimeUnit, Long> computeDiff(Date date1, Date date2) {
		long diffInMillies = date2.getTime() - date1.getTime();
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);
		Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
		long milliesRest = diffInMillies;
		for (TimeUnit unit : units) {
			long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;
			result.put(unit, diff);
		}
		return result;
	}

}
