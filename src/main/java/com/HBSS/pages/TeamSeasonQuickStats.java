package com.HBSS.pages;

public class TeamSeasonQuickStats {
	
	private String name; 
	private int wins;
	private int losses; 
	private String streak; 
	private String overall;
	
	public TeamSeasonQuickStats(String name, int wins, int losts, String streak, String overall) {
		this.name = name;
		this.wins = wins;
		this.losses = losts;
		this.streak = streak;
		this.overall = overall;
	}
	
	@Override
	public String toString() {
		return "TeamSeasonQuickStats [name=" + name + ", wins=" + wins + ", losts=" + losses + ", streak=" + streak
				+ ", overall=" + overall + "]";
	}
	
	
}
