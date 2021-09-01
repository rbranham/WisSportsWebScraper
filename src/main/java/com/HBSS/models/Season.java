package com.HBSS.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Season data class for a single sports season
 * <ul> 
 * <li> id - Unique Id for keeping track of in database. Id provided by database
 * <li> seasonString - A string representing the season as seen on wissports. For example: "2019-20"
 * <ul>
 * @author Roger Branham
 */
@Getter @Setter @ToString
public class Season {

	private Integer id; 
	private String seasonString; 
	
}
