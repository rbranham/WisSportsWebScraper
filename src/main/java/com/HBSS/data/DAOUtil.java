package com.HBSS.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility Class for writing SQL Queries
 * @author Roger Branham
 *
 */
public final class DAOUtil {

    // Constructors -------------------------------------------------------------------------------

    private DAOUtil() {
        // Utility class, hide constructor.
    }

    // Actions ------------------------------------------------------------------------------------
    //First three borrowed From: https://balusc.omnifaces.org/2008/07/dao-tutorial-data-layer.html 
    
    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given parameter values.
     * @param connection The Connection to create the PreparedStatement from.
     * @param sql The SQL query to construct the PreparedStatement with.
     * @param returnGeneratedKeys Set whether to return generated keys or not.
     * @param values The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during creating the PreparedStatement.
     */
    public static PreparedStatement prepareStatement
        (Connection connection, String sql, boolean returnGeneratedKeys, Object... values)
            throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(sql,
            returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    /**
     * Set the given parameter values in the given PreparedStatement.
     * @param connection The PreparedStatement to set the given parameter values in.
     * @param values The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during setting the PreparedStatement values.
     */
    public static void setValues(PreparedStatement statement, Object... values)
        throws SQLException
    {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    /**
     * Converts the given java.util.Date to java.sql.Date.
     * @param date The java.util.Date to be converted to java.sql.Date.
     * @return The converted java.sql.Date.
     */
    public static Date toSqlDate(java.util.Date date) {
     return (date != null) ? new Date(date.getTime()) : null;
    }
	
    // Rest of these @author Roger Branham
    
    /**
     * Generates a select all sql query for a table
     * @param tableString
     * @return String Select Query for a table, 
     */
    public static String generateSelectAllFromTable(String tableString) {
    	return "SELECT * FROM " + tableString + ";";
    }
    
    /**
     * Generates a select sql query for a table, by ID
     * @param tableString
     * @param idColumnString
     * @return String Select Query for an entry with an id in a table
     */
    public static String generateSelectFromTableById(String tableString, String idColumnString) {
    	return "SELECT * FROM " + tableString + " WHERE " + idColumnString + " = ?;"; 
    }
    
    /**
     * Generates a select sql query for a table, with multiple parameters
     * @param tableString
     * @param columnStrings
     * @return
     */
    public static String generateSelectFromTableByMultipleParams(String tableString, List<String> columnStrings) {
    	//TODO: Helper function for select by multiple equals parameters
    	return "Stub";
    }
    
    /**
     * Generates a delete query for a table based on a column
     * @param tableString
     * @param columnString
     * @return
     */
    public static String generateDeleteByOneColumn(String tableString, String columnString) {
    	return "DELETE FROM " + tableString + " WHERE " + columnString + " = ?;" ;
    }
    
    /**
     * Generates an insert string for a list of column names
     * @param tableString
     * @param columnNames
     * @return
     */
    public static String generateInsert(String tableString, ArrayList<String> columnNames) {
    	  	
    	StringBuilder sbColumnString = new StringBuilder(); 
    	StringBuilder sbValuesString = new StringBuilder(); 
    	
    	//Handle single value insert
    	if(columnNames.size() == 1) {
    		sbColumnString.append(columnNames.get(0));
    		sbValuesString.append("?");
    		
    	} else {
    		
	    	//Pull the last one off to add without a trailing comma
	    	String lastColumn = columnNames.remove(columnNames.size() - 1); 
	    	
	    	//Build the strings there should be a ? for every column so both strings can be built in the same loop
	    	for(String s : columnNames) {
	    		sbColumnString.append(s);
	    		sbColumnString.append(", ");
	    		
	    		sbValuesString.append("?, ");
	    	}
	    	
	    	sbColumnString.append(lastColumn); 
	    	sbValuesString.append("?"); 
    	}
    	
    	return "INSERT INTO " + tableString + " (" + sbColumnString.toString() + ") VALUES (" + sbValuesString.toString() + ");"; 
    	
    }
}


