package com.project.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface UserRepository extends JpaRepository<LadderTableEntryEntity, Integer> {
	
	@Query(value = "SELECT * FROM top_ten_ladders u WHERE u.league_name = :theLeagueName ORDER BY u.id ASC", nativeQuery = true)
	List<LadderTableEntryEntity> fetchTopTenLadderHC(@Param("theLeagueName") String theLeagueName);

	@Query(value = "SELECT * FROM top_ten_ladders ORDER BY id ASC", nativeQuery = true)
	List<LadderTableEntryEntity> fetchFilteredTopTenLadder();

	@Query(value = "SELECT * FROM top_ten_ladders u WHERE u.league_name = 'SSF Betrayal' ORDER BY u.id ASC", nativeQuery = true)
	List<LadderTableEntryEntity> fetchTopLadderSFF();

	@Query(value = "SELECT * FROM top_ten_ladders u WHERE u.league_name = 'Betrayal' ORDER BY u.id ASC", nativeQuery = true)
	List<LadderTableEntryEntity> fetchTopLadderStd();

}
	