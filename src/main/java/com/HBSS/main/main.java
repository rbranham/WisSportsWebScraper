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
		
		
		Season s = new Season(); 
		s.setSeasonString("2018-19");
		
//		Conference c = new Conference(); 
//		c.setConferenceName("Indianhead2");
		
		
		try {
			//db.addSeason(s);
			//db.deleteSeason(2);
			
			//db.getAllSeasons().stream().forEach(System.out::println);
			
//			System.out.println(db.getSeason(1)); 
			
			//Conference Manual prototype testing
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
			System.out.println(db.getTeam("Bayfield")); 
			//db.deleteTeam(2);
			
			//db.getAllTeams().stream().forEach(System.out :: println);
			
			//ConferencePage indianhead = setupConferencePageCode(db.getConference(1), db); //1 - Indianhead conference
			
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
	
	
	private static ConferencePage setupConferencePageCode(Conference conference, DAOInterface db) {
		
		//Setup code
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.       
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH );  //C:\Users\Roger\Documents\Tools 
		WebDriver driver = new ChromeDriver(); 	
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		ConferencePage indianHead = new ConferencePage(driver, conference, db);
		indianHead.goTo(INDIANHEAD_STRING); //TODO: This string should probably be in conference database??
				
		customSleep();
		
		return indianHead;
		
	}
	
	private static void scrapeStatsForConference(ConferencePage conference, DAOInterface db) throws SQLException{
		
		ArrayList<Season> seasons = db.getAllSeasons(); 
		
		Season startingSeason = seasons.remove(0); 
		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp = conference.getStatsForSeasonList(seasons, startingSeason);
				
		displayStats(temp);
		
	}
	
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
	
	

}
