package com.csci360.electionapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.csci360.electionapp.model.Ballet;
import com.csci360.electionapp.model.Canidate;
import com.csci360.electionapp.model.Voter;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * All DB Queries, and connections
 */
public class DBConnection {
	
    private static Connection connect = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private static ResultSet nextResultSet = null;

    
    
    /* 
     * For Connecting to MySQL Database
     */
	private static void mySqlConnection() throws Exception {
						
				try {
				// Setup the connection with the DB
				Class.forName("com.mysql.cj.jdbc.Driver");
		        String host = "jdbc:mysql://evoting.c09tob7ay9ht.us-east-1.rds.amazonaws.com/evote?autoReconnect=true&useSSL=false";
		        String user = "root";
		        String password = "adminroot";
		        connect = DriverManager.getConnection(host, user, password );

				} catch (Exception e) {
					throw e;				
				} 		
	}
    /* 
     * For Closing the connection to MySQL Database
     */
	private static void close() {
	    try {
	    		if (resultSet != null) {
	    			resultSet.close();
	    		}

	    		if (statement != null) {
	    			statement.close();
	    		}
	    		
	    		if (connect != null) {
	    			connect.close();
	    		}
	    		} catch (Exception e) {

	    		}
	  }
	
    /* 
     * For logging into the ez-vote
     */
	public static String loginQuery(String userid, String password) throws Exception {
		
		
		try{
			
			mySqlConnection();		
			statement = connect.prepareStatement("SELECT User_Type FROM evote.users WHERE USERID = ? AND PASSWORD = ?");
			statement.setString(1, userid);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			int userFound = 0;
			String userType = null;
			while (resultSet.next()) {
				userFound = userFound+1;
				userType = resultSet.getString(1);
				
			}
			//writeResultSet(resultSet);
			close();
			return userType;     
			
		
		} catch (Exception e) {
			throw e;
			}
	}
	
    /* 
     * Adding Account to Better Ballot
     */
	public static void addAccountQuery(String userid, String password, String firstName, String lastName) throws Exception {
		
		try{

			mySqlConnection();	
			statement = connect.prepareStatement("INSERT INTO users(USERID,PASSWORD,User_Type, First_Name,Last_Name) VALUES (?, ?, 'voter', ?,?)");
			statement.setString(1, userid);
			statement.setString(2, password);
			statement.setString(3, firstName);
			statement.setString(4, lastName);
			statement.executeUpdate();
			close();
		
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static void addBalletQuery(String[] fNameArray, String[] lNameArray, String balletName) throws Exception {
		
		String id = null;
		try{

			mySqlConnection();	
			statement = connect.prepareStatement("INSERT INTO ballets(name)VALUES(?)");
			statement.setString(1, balletName);
			statement.executeUpdate();
			
			statement = connect.prepareStatement("SELECT ID FROM ballets WHERE NAME = ?");
			statement.setString(1, balletName);
			resultSet = statement.executeQuery();
			
	        while (resultSet.next()) {
	        	id = resultSet.getString(1);     
	        }
	        System.out.print(fNameArray.length);
	        int i=0;
			while(fNameArray[i].compareTo("") != 0) {
				
				statement = connect.prepareStatement("INSERT INTO canidates(ballet_id,first_name,last_name) VALUES(?,?,?)");
				statement.setString(1, id);
				statement.setString(2, fNameArray[i]);
				statement.setString(3, lNameArray[i]);
				statement.executeUpdate();
				i++;
			}
			close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static void deleteAllCanidatesQuery(int balletId) throws Exception {
		
		try{
			mySqlConnection();
			statement = connect.prepareStatement("DELETE FROM canidates WHERE ballet_id = ?");
			statement.setInt(1, balletId);
			statement.executeUpdate();
			close();
			
		}catch (Exception e) {
			throw e;
		}
		
	}
	public static void deleteBallet(int balletId) throws Exception {
		
		try{
			deleteAllCanidatesQuery(balletId);
			mySqlConnection();
			
			statement = connect.prepareStatement("DELETE FROM ballets WHERE id = ?");
			statement.setInt(1, balletId);
			statement.executeUpdate();
			close();
			
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	
	public static void updataBalletQuery(String[] fNameArray, String[] lNameArray, int balletID) throws Exception {
		
		try{
			deleteAllCanidatesQuery(balletID);
			mySqlConnection();
	        int i=0;
			while(fNameArray[i].compareTo("") != 0) {
				
				statement = connect.prepareStatement("INSERT INTO canidates(ballet_id,first_name,last_name) VALUES(?,?,?)");
				statement.setInt(1, balletID);
				statement.setString(2, fNameArray[i]);
				statement.setString(3, lNameArray[i]);
				statement.executeUpdate();
				i++;
			}
			close();
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	
    /* 
     * For logging into the better ballot
     */
	public static ObservableList<Ballet> getAllBallets() throws Exception {
		
		
		try{
			
			mySqlConnection();
			String fName;
			String lName;
			
			statement = connect.prepareStatement("SELECT name, id FROM evote.ballets");
			resultSet = statement.executeQuery();
			ObservableList<Ballet> balletsData = FXCollections.observableArrayList();
			
			while (resultSet.next()) {
				
				ArrayList<Canidate> canidates = new ArrayList<Canidate>();
				System.out.println(resultSet.getInt(2));
				statement = connect.prepareStatement("SELECT first_name,last_name FROM evote.canidates WHERE ballet_id = ?");
				statement.setInt(1, resultSet.getInt(2));
				nextResultSet = statement.executeQuery();
			
				while (nextResultSet.next()){
					fName = nextResultSet.getString(1);
					lName = nextResultSet.getString(2);
					Canidate canidate = new Canidate(fName,lName);
					canidates.add(canidate);
				}
				balletsData.add(new Ballet(resultSet.getString(1),canidates,resultSet.getInt(2)));
				
			}			
			close();
			
			return balletsData;     
			
		
		} catch (Exception e) {
			throw e;
			}
	}
	
	
	public static ObservableList<Voter> getAllUsers() throws Exception {
		
		
		try{
			
			mySqlConnection();		
			statement = connect.prepareStatement("SELECT USERID,First_Name,Last_Name FROM evote.users");
			resultSet = statement.executeQuery();
			ObservableList<Voter> voterData = FXCollections.observableArrayList();
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
				voterData.add(new Voter(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
				
			}
			//writeResultSet(resultSet);
			close();
			return voterData;     
			
		
		} catch (Exception e) {
			throw e;
			}
	}
	/*
	 * For using when you want to see what the query results.. For Debugging
	 */
	private static void writeResultSet(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {

            String id = resultSet.getString(1)+ " " + resultSet.getString(2);
            
            System.out.println(id); 
        }
	}
}
