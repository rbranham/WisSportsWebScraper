package com.HBSS.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data class for tracking a team. 
 * 
 * <ul>
 * <li> id - unique id provided by database. Leave null when adding new team to database. 
 * <li> teamName - the name of the team as listed on Wissports
 * <li> town - string for name of the town to help differentiate teams with the same name.  
 * <ul>
 * @author Roger Branham
 */
@Getter @Setter @ToString
public class Team {

	private Integer id; 
	private String teamName; 
	private String town;
	
}
