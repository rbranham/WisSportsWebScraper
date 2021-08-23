package com.HBSS.data;

import java.util.ArrayList;

import com.HBSS.models.*;


/**
 * DAO Interface for interacting with database. 
 * Provides a contract for {@link Team}, {@link Conference}, {@link Season}, 
 * and {@link TeamConferenceSeasonQuickStats} data model classes. 
 *  
 * @author RogerB
 *
 */
public interface DAOInterface {
	
	//CRUD for a season
	public void addSeason(Season s);
	public Season getSeason(int id);
	public Season getSeason(String seasonString); 
	public ArrayList<Season> getAllSeasons(); 
	public void deleteSeason(int id); 
	public void updateSeason(Season s);
	
	//CRUD for a conference
	public void addConference(Conference c);
	public Conference getConference(int id); 
	public ArrayList<Conference> getAllConferences();
	public void deleteConference(int id); 
	
	//CRUD for Teams
	public void addTeam(Team t); 
	public Team getTeam(int id); 
	public Team getTeam(String teamName, String town); //Assumes that name/town combination will always be unique. 
	public ArrayList<Team> getAllTeams(); 
	public void deleteTeam(int id); 
	
	//CRUD for ConferenceSeasonStats entry
	public void addTeamRecord(TeamConferenceSeasonQuickStats stats);
	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForSeason(int conferenceId, int seasonId);
	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForTeam(int conferenceId, int teamId);
	
}
