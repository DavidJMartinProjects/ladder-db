package com.project.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface LeagueIdDetailsRepository extends JpaRepository<LeagueIdDetails, Integer> {

	@Query(value = "SELECT DISTINCT league_id, league_name FROM custom_league", nativeQuery = true)
	List<LeagueIdDetails> fetchCustomLadderDetailsFromDb();

}

