package com.project.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Component
public class LeagueIdDetails {
	
    @Id
	@Column(name = "league_name")
	private String league_name;
	
	@Column(name = "league_id")
	private String league_id;
	
	public LeagueIdDetails() {
	}

	public String getLeague_name() {
		return league_name;
	}

	public void setLeague_name(String league_name) {
		this.league_name = league_name;
	}

	public String getLeague_id() {
		return league_id;
	}

	public void setLeague_id(String league_id) {
		this.league_id = league_id;
	}
	
	@Override
	public String toString() {
		return "LeagueIdDetails [league_name=" + league_name + ", league_id=" + league_id + "]";
	}

	
}
