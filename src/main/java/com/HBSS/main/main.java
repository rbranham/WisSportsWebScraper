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

public class main {

	private final static String DRIVER_PATH = "/Users/Roger/Documents/Tools/chromedriver.exe";
	private final static String INDIANHEAD_STRING = "5798944-indianhead";
	
	public static void main(String[] args) {
		
		//DAO manual testing
		DAOInterface db = MySQLDAO.getInstance();
		
		
//		Season s = new Season(); 
//		s.setSeasonString("2018-19");
		
//		Conference c = new Conference(); 
//		c.setConferenceName("Indianhead2");
		
		
		try {
						
			ConferencePage indianhead = setupConferencePageCode(db.getConference(1), db); //1 - Indianhead conference
			scrapeStatsForConference(indianhead, db); //Will read stats and print out results. 
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Web Scrapping manual testing
//		mainCode(); 	
		
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
		ConferencePage indianHead = new ConferencePage(driver, conference, db);
		indianHead.goTo(INDIANHEAD_STRING); //TODO: This string should probably be in conference database??
				
		//customSleep();
		
		return indianHead;
		
	}
	
	/** Runs code to read the stats */
	private static void scrapeStatsForConference(ConferencePage conferencePage, DAOInterface db) throws SQLException{
		
		ArrayList<Season> seasons = db.getAllSeasons(); 
		
		//Season startingSeason = seasons.remove(0);  //TODO: Change this to not require a starting season
		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp = conferencePage.getStatsForSeasonList(seasons); //, startingSeason
				
		displayStats(temp);
		
	}
	
	/** Displays the stats */
	private static void displayStats(ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp) {
		
		for(ArrayList<TeamConferenceSeasonQuickStats> seasonList : temp) {
		
			for(TeamConferenceSeasonQuickStats q : seasonList) {
				System.out.println(q);
			}
		
		}
		
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
		
	}

}
