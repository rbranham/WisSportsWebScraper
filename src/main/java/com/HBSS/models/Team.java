package com.HBSS.models;

public class Team {

	private int id; 
	private String teamName; 
	private String town;
	
	
	public Team(String teamName, String town) {
		super();
		this.teamName = teamName;
		this.town = town;
	}


	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", town=" + town + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTeamName() {
		return teamName;
	}


	public String getTown() {
		return town;
	} 
	
	
	
	
}
