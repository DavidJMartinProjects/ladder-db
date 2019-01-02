package com.project.topten.web.controller;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.project.controller.LadderTableEntryEntity;

public interface TopTenDao extends Repository<List<LadderTableEntryEntity>, Long>{
	
	    @Query(value = "SELECT * FROM top_ten_ladders u WHERE u.dtype = TableDataDelveTopTen and u.leagueName = SSF Betrayal HC", nativeQuery = true)
	    List<LadderTableEntryEntity> fetchTopTenDelveLadderSSFHC();
	    
	    @Query(value = "SELECT * FROM top_ten_ladders u WHERE u.dtype = TableDataUberLabTopTen and u.leagueName = SSF Betrayal HC", nativeQuery = true)
	    List<LadderTableEntryEntity> fetchTopTenUberLabLadderSFFHC();
	    
	    @Query(value = "SELECT * FROM top_ten_ladders u WHERE u.dtype = TableDataRaceTo100 and u.leagueName = SSF Betrayal HC", nativeQuery = true)
	    List<LadderTableEntryEntity> fetchTopTenRaceTo100LadderSSFHC();
	    
	    @Query(value = "DELETE * FROM top_ten_ladders", nativeQuery = true)
	    void deleteAllTopTenLadder();
    
}
