package com.HBSS.data;

import com.HBSS.models.TeamConferenceSeasonQuickStats;

public interface DAOInterface {
	
	
	//CRUD for a season
	
	
	//Set team record
	public void setTeamRecord(TeamConferenceSeasonQuickStats stats);

	//looks for existing team by teamName and town. Every school should be uniquily identifiable by these two attributes. 
	//This method should return a -1 or some indicator that team was not found
	public int findTeam(String teamName, String conferenceName);
	
	//This method adds a new team to the team table. Should check first before 
	public void addTeam(); 
	
	public void addConference(String name); 
	public int getConference(String name); 
	
	
}
