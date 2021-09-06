package com.HBSS.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.HBSS.data.DAOInterface;
import com.HBSS.data.DAOUtil;
import com.HBSS.data.MySQLDAO;
import com.HBSS.models.*;
import com.HBSS.pages.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.DbDoc;

public class main {

	private final static String DRIVER_PATH = "/Users/Roger/Documents/Tools/chromedriver.exe";
	
	public static void main(String[] args) {
		
		//DAO manual testing
		DAOInterface db = MySQLDAO.getInstance();	
		
		try {
			
//			Conference c = new Conference(); 
//			c.setConferenceName("Big Eight");
//			c.setEndpoint("554103-big-eight");
//			
//			db.addConference(c);
//			db.getAllConferences().stream().forEach(System.out::println);
			
			// Main code to run scraper ----------------------------------------------------------
			ConferencePage confPage = setupConferencePageCode(db.getConference(3), db); //1 - Indianhead conference, 3 - Big Eight
			ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> bigList = scrapeStatsForConference(confPage, db); //Will read stats 
			writeStats(bigList, db); //Write into database
			bigList.stream().forEach(System.out::println);
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param conference  Conference that we are checking
	 * @param db
	 * @return
	 */
	private static ConferencePage setupConferencePageCode(Conference conference, DAOInterface db) {
		
		//Initiate web driver
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH );  //C:\Users\Roger\Documents\Tools 
		WebDriver driver = new ChromeDriver(); 	
		
		//Set waits/timeouts
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Prep conference page
		ConferencePage confPage = new ConferencePage(driver, conference, db);
		confPage.goTo(conference.getEndpoint()); 
		
		driver.manage().window().maximize(); //Full screen
		System.out.println("Conference Page Loaded");
		customSleep();
		
		
		return confPage;
		
	}
	
	/** Runs code to read the stats */
	private static ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> scrapeStatsForConference(ConferencePage conferencePage, DAOInterface db) throws SQLException{
		
		ArrayList<Season> seasons = db.getAllSeasons(); 
		//System.out.println("Starting read, passing in seasons: ");
//		
//		//"2020-21"
//		//"2018-19"
//		
//		ArrayList<Integer> indexes = new ArrayList<Integer>(); 
//		
//		for(int i = 0; i < seasons.size(); i++) {
//			if(seasons.get(i).getSeasonString().equals("2019-20") || seasons.get(i).getSeasonString().equals("2020-21") || seasons.get(i).getSeasonString().equals("2018-19")) {
//				indexes.add(i);
//			}
//		}
		
		
		seasons.stream().forEach(System.out::println);
		System.out.println("-----------------------");

		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp = conferencePage.getStatsForSeasonList(seasons); 
		
		System.out.println("Scrape finished, displaying results: ");
		displayStats(temp);
		return temp;
		
	}
	
	/** Displays the stats */
	private static void displayStats(ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp) {
		
		for(ArrayList<TeamConferenceSeasonQuickStats> seasonList : temp) {
		
			for(TeamConferenceSeasonQuickStats q : seasonList) {
				System.out.println(q);
			}
		
		}
		
		System.out.println("Done displaying results");
		
	}
	
	/** Write the stats to database */
	private static void writeStats(ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp, DAOInterface db) {
		
		for(ArrayList<TeamConferenceSeasonQuickStats> seasonList : temp) {
		
			for(TeamConferenceSeasonQuickStats q : seasonList) {

				try {
					db.addTeamRecord(q);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		}
		
		System.out.println("Done Inputing results");
		
	}
	
	/** Converts stats list to json string */
	private static String createJson(ArrayList<TeamConferenceSeasonQuickStats> list) {
		try {
			//JSON conversion test
			ObjectMapper mapper = new ObjectMapper();
			
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
			
			return jsonString;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void customSleep() {
		try {
			System.out.println("Sleeping");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Let the user actually see something! 
	}
	
	private static void miscDbCode() {
		
//		Season s = new Season(); 
//		s.setSeasonString("2018-19");
		
//		Conference c = new Conference(); 
//		c.setConferenceName("Indianhead2");
		
		
		//db.addSeason(s);
		//db.deleteSeason(2);
		
		//db.getAllSeasons().stream().forEach(System.out::println);
		
//		System.out.println(db.getSeason(1)); 
		

		
		//db.addConference(c);
		//System.out.println(db.getConference(1));
		
		//db.deleteConference(2);
		
		//ArrayList<Conference> confs = db.getAllConferences();
		
		//confs.stream().forEach(System.out :: println);
		//db.getAllConferences().stream().forEach(System.out :: println);
		
		
	//Team manual test
		//Team t = new Team();
		//t.setTeamName("Bayfield");
		//t.setTown("Port Wing");
		
		//db.addTeam(t);
//		Team t = db.getTeam("Mellen");
//		
//		if(t == null) {
//			System.out.println("not found");
//		} else {
//			System.out.println("Not null for some reason? ");
//		}
		
		//System.out.println(db.getTeam("Mellen")); 
		//db.deleteTeam(2);
		
		//db.getAllTeams().stream().forEach(System.out :: println);
		
		
//		TeamConferenceSeasonQuickStats t = new TeamConferenceSeasonQuickStats();
//		t.setConferenceId(1);
//		t.setSeasonId(1);
//		t.setTeamId(7);
//		t.setWins(14);
//		t.setLosses(2);
//		t.setOverall("19-6-0");
		
		//db.addTeamRecord(t);
		
		
	}

}
