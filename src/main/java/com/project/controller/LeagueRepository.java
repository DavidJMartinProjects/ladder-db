package com.project.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface LeagueRepository extends JpaRepository<TopTenLadderTableEntryEntity, Integer> {
	
	@Query(value = "SELECT * FROM top_ten_ladders ORDER BY id ASC", nativeQuery = true)
	List<TopTenLadderTableEntryEntity> fetchFilteredTopTenLadder();
	
	@Query(value = "DELETE * FROM custom_league_ladders WHERE league_name=:theLeagueName", nativeQuery = true)
	List<TopTenLadderTableEntryEntity> deleteRedundantLeagueFromDb(@Param("theLeagueName") String theLeagueName);

}
	