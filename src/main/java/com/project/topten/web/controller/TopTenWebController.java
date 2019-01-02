package com.project.topten.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.topten.pojo.TopTenLadderResponse;

@CrossOrigin
@RestController
@RequestMapping(value = "/top-ten")
public class TopTenWebController {
	@Autowired
	TopTenDbService topTenDbService;

		
	@RequestMapping(value = "/delve", method = RequestMethod.GET, produces = { "application/json" })
	public TopTenLadderResponse getDelveLadderData() throws InterruptedException {
		System.out.println("Processing request to to-ten data");
		System.out.println("Web Controller Response : ");
		return topTenDbService.getTopTenLadder();
	}

}
	