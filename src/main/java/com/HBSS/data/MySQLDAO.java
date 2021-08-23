package com.HBSS.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HBSS.models.Conference;
import com.HBSS.models.Season;
import com.HBSS.models.Team;
import com.HBSS.models.TeamConferenceSeasonQuickStats;

/**
 * @author Roger Branham
 */
public class MySQLDAO implements DAOInterface {
	
	//Conneciton Settings Strings -------------------------------
	//TODO: Find a way to read this in from a configuration files to keep off of repository
	//Currently just using my local test database
	final private String connectionString = "jdbc:mysql://127.0.0.1:3306/farm_managment_database"; 
	final private String userString = "root";
	final private String password = "Admin"; 
	
	//Constants ------------------------------------------------
	final private String DATABASE_STRING = "farm_managment_database"; //currently just local test database
	
	//Team Table
	final private String TEAM_TABLE = "teams"; 
	final private String TEAM_ID = "idteams";
	final private String TEAM_NAME = "team_name";
	final private String TEAM_TOWN = "team_town"; 
	
	//Season Stats Table 
	final private String SEASON_TABLE = "basektball_seasons"; 
	final private String SEASON_ID = "";
	final private String SEASON_STRING = "season_string";
	
	//Conference Table
	final private String CONFERENCE_TABLE = "conferences"; 
	final private String CONFERENCE_ID = "idconferences"; 
	final private String CONFERENCE_NAME = "conference_name"; 
	
	//TeamSeasonConference Stats table
	final private String STATS_TABLE = "conference_stats";
	final private String STATS_TEAM_ID = "team_id";
	final private String STATS_CONF_ID = "conference_id";
	final private String STATS_SEAS_ID = "season_id";
	final private String STATS_WINS = "wins";
	final private String STATS_LOSSES = "losses"; 
	final private String STATS_STREAK = "streak";
	final private String STATS_OVERALL = "overall"; 
	
	//Initialization Code --------------------------------------------
	private Connection conn = null;
	
	public static DAOInterface getInstance() {
		MySQLDAO db = new MySQLDAO();
		db.connect();	
		return db;		
	}
	
	private void connect() {
		
        try {
        	
        	conn = DriverManager.getConnection(
                    connectionString, userString, password);
        	
            if (conn != null) {
                System.out.println("Connected to the database!");
                
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	
	//Implemented methods ----------------------------------------------
	
	
	public void addSeason(Season s) {
		// TODO Auto-generated method stub
		
	}

	public Season getSeason(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Season getSeason(String seasonString) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Season> getAllSeasons() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSeason(int id) {
		// TODO Auto-generated method stub
		
	}

	public void updateSeason(Season s) {
		// TODO Auto-generated method stub
		
	}

	public void addConference(Conference c) {
		// TODO Auto-generated method stub
		
	}

	public Conference getConference(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Conference> getAllConferences() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteConference(int id) {
		// TODO Auto-generated method stub
		
	}

	public void addTeam(Team t) {
		// TODO Auto-generated method stub
		
	}

	public Team getTeam(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Team getTeam(String teamName, String town) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteTeam(int id) {
		// TODO Auto-generated method stub
		
	}

	public void addTeamRecord(TeamConferenceSeasonQuickStats stats) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForSeason(int conferenceId, int seasonId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStatsForTeam(int conferenceId, int teamId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
