package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/*
 * All DB Queries, and connections
 */
public class DBConnection {
	
    private static Connection connect = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    
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
	public static boolean loginQuery(String userid, String password) throws Exception {
		
		
		try{
			
			mySqlConnection();		
			statement = connect.prepareStatement("SELECT COUNT(*) FROM evote.users WHERE USERID = ? AND PASSWORD = ?");
			statement.setString(1, userid);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			int userFound = -1;
			while (resultSet.next()) {
				userFound = Integer.parseInt(resultSet.getString(1));
			}
			//writeResultSet(resultSet);
			if(userFound == 0) {
				close();
        		 	return false;     
			}
			else {
				close();
				return true;
			}
		
		} catch (Exception e) {
			throw e;
			}
	}
	
    /* 
     * Adding Account to EZ-Vote
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
		
		} catch (Exception e) {
			throw e;
		}
	}
	/*
	 * For using when you want to see what the query results.. For Debugging
	 */
	private static void writeResultSet(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {

        		String id = resultSet.getString(1);
            String user = resultSet.getString(2);
            String password = resultSet.getString(3);
            System.out.println("User: " + user);
            System.out.println("password: " + password);

        }
	}
}
