package com.HBSS.data;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HBSS.models.*;


/**
 * DAO Interface for interacting with database. 
 * Provides a contract for {@link Team}, {@link Conference}, {@link Season}, 
 * and {@link TeamConferenceSeasonQuickStats} data model classes. 
 *  
 * @author Roger branham
 *
 */
public interface DAOInterface extends Closeable{
	
	//CRUD for a season
	public void addSeason(Season s) throws IllegalArgumentException, SQLException;
	public Season getSeason(int id) throws SQLException;
	public Season getSeason(String seasonString); 
	public ArrayList<Season> getAllSeasons() throws SQLException; 
	public void deleteSeason(int id) throws SQLException; 
	public void updateSeason(Season s);
	
	//CRUD for a conference
	public void addConference(Conference c) throws SQLException;
	public Conference getConference(int id) throws SQLException; 
	public ArrayList<Conference> getAllConferences() throws SQLException;
	public void deleteConference(int id) throws SQLException; 
	
	//CRUD for Teams
	public void addTeam(Team t) throws SQLException; 
	public Team getTeam(int id) throws SQLException; 
	public Team getTeam(String teamName) throws SQLException;  
	public ArrayList<Team> getAllTeams() throws SQLException; 
	public void deleteTeam(int id) throws SQLException; 
	
	//CRUD for ConferenceSeasonStats entry
	public void addTeamRecord(TeamConferenceSeasonQuickStats stats) throws SQLException;
	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForSeason(int conferenceId, int seasonId);
	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForTeam(int conferenceId, int teamId) throws SQLException;
	public ArrayList<TeamConferenceSeasonQuickStats> getAllForConference(int conferenceId) throws SQLException; 
	
	//public void close(); 
}
