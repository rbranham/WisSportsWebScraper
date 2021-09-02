package com.HBSS.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.HBSS.data.DAOInterface;
import com.HBSS.models.Conference;
import com.HBSS.models.Season;
import com.HBSS.models.Team;
import com.HBSS.models.TeamConferenceSeasonQuickStats;
import com.HBSS.pages.PageSuper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * This class provides functionality to interact with a conference webpage. 
 * 
 * @author Roger Branham
 *
 */
public class ConferencePage extends PageSuper {
	
	//Constants
	final private String BASE_URL = "https://www.wissports.net"; //This value could be refactored into wissports superclass
	final private String CONFERENCE_PAGE_POINT = "/page/show";
	final private String FULL_URL = BASE_URL + CONFERENCE_PAGE_POINT; 
	
	//Useful By locators for finding important WebElements
	final private By seasonDropDown = By.id("megaDropDown-season");
	final private By seasonCallout = By.id("megaDropDown-season-callout");
	final private By statTable = By.className("statTable");
	
	private Conference conference;
	private ArrayList<Team> teamsCache;
	private DAOInterface db; //Wish we could decouple this long term, but need for looking up team info 
	
	public ConferencePage(WebDriver driver, Conference conference, DAOInterface db) {
		super(driver);
		this.conference = conference;
		this.teamsCache = new ArrayList<Team>(); //Initialize Team Cache
		this.db = db;
	}
	
	
	//Conference value is actually for each season for each conference. But only need to get onto one page and then can navigate to each season through clicks on dropdown
	/**
	 * Goes to webpage at endpoint specified by string.
	 * Will be {@value BASE_URL} + {@value CONFERENCE_PAGE_POINT} + parameter conferenceValue
	 * 
	 * @param conferenceValue
	 */
	public void goTo(String conferenceValue) {
		driver.get(FULL_URL + "/" + conferenceValue);    
	}
	
	
	/**
	 * Provides functionality to read multiple seasons for a conference. 
	 * 
	 * @param seasons
	 * @param currentPageSeason
	 * @return 2d Array List, each row is a season, each season has a list of teams and their standings. 
	 */
	public ArrayList<ArrayList<TeamConferenceSeasonQuickStats>>  getStatsForSeasonList(ArrayList<Season> seasons, Season currentPageSeason){
		
		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> masterList = new ArrayList<ArrayList<TeamConferenceSeasonQuickStats>>();
		
		//Should check if current page matches one in the string array and delete it if so
		masterList.add(readStatsTable(currentPageSeason.getId()));
		
		//Go through each season and get stats
		for(Season s: seasons) {
			
			changeSeasonTo(s.getSeasonString());
			
			masterList.add(
					readStatsTable(s.getId())
					);
			
		}
		
		return masterList;
		
	}
	
	
	/**
	 * While one a conference page, this function will switch pages to the year that matches parameter seasonText
	 * @param seasonText
	 */
	public void changeSeasonTo(String seasonText) {
		
		openSeasonCallout();
		
		WebElement dropdown = driver.findElement(seasonCallout).findElement(By.xpath(".//div[2]/select"));

		String s = "//optgroup[@label='"
				+ seasonText
				+ "']/option";
		
		dropdown.findElement(By.xpath(s))
				.click(); //Should open page
		
	}
	
	
	/** This function opens the season switching call out on a conference page*/
	private void openSeasonCallout() {
		driver.findElement(seasonDropDown).click();
	}
	
	
	/**
	 * While on a conference standings page, will read in the standings and return a list of standing data models
	 * 
	 * @param seasonId - Is passed through to for creating datamodel
	 * @return ArrayList<TeamConferenceSeasonQuickStats>
	 */
	public ArrayList<TeamConferenceSeasonQuickStats> readStatsTable(int seasonId){
		ArrayList<TeamConferenceSeasonQuickStats> tableContents = new ArrayList<TeamConferenceSeasonQuickStats>();
		
		List<WebElement> items = driver.findElement(statTable)
				.findElements(By.xpath(".//tbody/tr"));
		
		for(WebElement i : items) {
			
			List<WebElement> e = i.findElements(By.xpath(".//td"));

			//-- Construct a stat line --------------------
			TeamConferenceSeasonQuickStats temp = new TeamConferenceSeasonQuickStats();
			temp.setSeasonId(seasonId); 						//private int seasonId; 
			temp.setConferenceId(conference.getId());			//private int conferenceId; 
																//private int teamId;  		//TODO: need to use text to find team id
			temp.setWins(Integer.parseInt(e.get(2).getText()));	//private int wins;
			temp.setWins(Integer.parseInt(e.get(3).getText()));	//private int losses; 
			temp.setStreak(e.get(4).getText());					//private String streak; 	//WARNING: For seasons before 18-19, there are more columns so this will not be correct.
			temp.setOverall(e.get(5).getText());				//private String overall;	//TODO: Solutions is to write methods to grab these values, can then implement dynamic solution		
			
		}
		
		return tableContents;
	}
	
	/**
	 * This function is a lookup function for matching a team name to a team id. 
	 * will look up in cache first, then database. If not found in either will then 
	 * create new team to add to database. 
	 * @param teamName
	 * @return teamId
	 */
	private int findMatchingTeamId(String teamName) {
		
		//Wish we could decouple needing database, but can't think of way to. 
		
		try {  
			//Check cache ---
			ArrayList<Team> teamsThatMatch = new ArrayList<Team>(
					teamsCache.stream()
					.filter( t -> (t.getTeamName().equals(teamName)))
					.collect(Collectors.toList())
					);
			
			if(teamsThatMatch.size() >= 2) {
				System.out.println("Multiple matches in cache!"); //Probably should throw error up here. 
				return -1; //Could just return first one found for now??
			}
			else if(teamsThatMatch.size() == 1) { //Found in cache
				return teamsThatMatch.get(0).getId();
			}
		
				
			//Check database ---
			
			
			
			
			//Create new and add to database. And Cache  ---
			
			Team team = new Team();
			team.setTeamName(teamName); 
			//Town??? //TODO: Maybe remove town as every team in state appears to have a different name. 
			
			db.addTeam(team); //This function by reference will update the id field
			
			
			if(team.getId() != null) {
				teamsCache.add(team); //Add to cache
				return team.getId(); 
			} else {
				System.out.println("New team came back with null id");
				return -1; //Could also throw error
			}
			
		}
		catch (Exception e){
			
		}
		
		return -1;
	}
	
    
}
