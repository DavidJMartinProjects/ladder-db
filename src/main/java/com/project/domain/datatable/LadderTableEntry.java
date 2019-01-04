package com.project.domain.datatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name="custom_league")
@Component
public class LadderTableEntry {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
	@Column(name = "characterId")
	private String characterId;

	@Column(name = "rank")
	private String rank;
	
	@Column(name = "rankDifference")
	private String rankDifference;
	
	@Column(name = "characterName")
	private String character;
	
	@Column(name = "dead")
	private String dead;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "theClass")
	private String theClass;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "challenges")
	private String challenges;
	
	@Column(name = "online")
	private String online;
	
	@Column(name = "twitch")
	private String twitch;
	
	@Column(name = "xph")
	private String xph;
	
	@Column(name = "xphDifference")
	private String xphDifference;
	
	@Column(name = "levelProgressBar")
	private String levelProgressBar;
	
	@Column(name = "timeStamp")
	private String timeStamp;
	
	@Column(name = "leagueName")
	private String leagueName;
	
	@Column(name = "leagueId")
	private String leagueId;

	public LadderTableEntry() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCharacter() {
		return character;
	}	

	public String getRankDifference() {
		return rankDifference;
	}

	public void setRankDifference(String rankDifference) {
		this.rankDifference = rankDifference;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTheClass() {
		return theClass;
	}

	public void setTheClass(String theClass) {
		this.theClass = theClass;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public String getDead() {
		return dead;
	}

	public void setDead(String dead) {
		this.dead = dead;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getTwitch() {
		return twitch;
	}

	public void setTwitch(String twitch) {
		this.twitch = twitch;
	}

	public String getXph() {
		return xph;
	}

	public void setXph(String xph) {
		this.xph = xph;
	}

	public String getXphDifference() {
		return xphDifference;
	}

	public void setXphDifference(String xphDifference) {
		this.xphDifference = xphDifference;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLevelProgressBar() {
		return levelProgressBar;
	}

	public void setLevelProgressBar(String levelProgressBar) {
		this.levelProgressBar = levelProgressBar;
	}
	
	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	@Override
	public String toString() {
		return "LadderTableEntry [id=" + id + ", characterId=" + characterId + ", rank=" + rank + ", rankDifference="
				+ rankDifference + ", character=" + character + ", dead=" + dead + ", account=" + account + ", level="
				+ level + ", theClass=" + theClass + ", experience=" + experience + ", challenges=" + challenges
				+ ", online=" + online + ", twitch=" + twitch + ", xph=" + xph + ", xphDifference=" + xphDifference
				+ ", levelProgressBar=" + levelProgressBar + ", timeStamp=" + timeStamp + ", leagueName=" + leagueName
				+ ", leagueId=" + leagueId + "]";
	}

}
