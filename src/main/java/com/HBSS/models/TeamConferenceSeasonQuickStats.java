package com.HBSS.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class TeamConferenceSeasonQuickStats {
	
	//TODO: Change what the model holds to match database table. 
	private String season; 
	private String teamName; 
	private int wins;
	private int losses; 
	private String streak; 
	private String overall;
	
	public TeamConferenceSeasonQuickStats() {
		
	}
	
	//TODO: Depreciate
	public TeamConferenceSeasonQuickStats(String name, int wins, int losts, String streak, String overall, String season) {
		this.teamName = name;
		this.wins = wins;
		this.losses = losts;
		this.streak = streak;
		this.overall = overall;
		this.season = season;
	}

	
	@Override
	public String toString() {
		return "TeamConferenceSeasonQuickStats [season=" + season + ", teamName=" + teamName + ", wins=" + wins
				+ ", losses=" + losses + ", streak=" + streak + ", overall=" + overall + "]";
	}
	

}
