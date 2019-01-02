package com.project.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.project.domain.datatable.LadderTableEntry;

@Component
public interface CustomLeagueRepository extends JpaRepository<LadderTableEntry, Integer> {

}




	