package com.project.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.datatable.LadderTableEntry;

@Transactional
public interface CustomLeagueRepository extends JpaRepository<LadderTableEntry, Integer> {
	
	@Query(value = "SELECT * FROM custom_league WHERE league_name=:theLeagueName", nativeQuery = true)
	List<LadderTableEntry> getCustomLeagueFromDb(@Param("theLeagueName") String theLeagueName);
	
	@Modifying
	@Query(value = "DELETE FROM custom_league WHERE league_name=:theLeagueName", nativeQuery = true)
	void deleteRedundantLeagueFromDb(@Param("theLeagueName") String theLeagueName);
}




	