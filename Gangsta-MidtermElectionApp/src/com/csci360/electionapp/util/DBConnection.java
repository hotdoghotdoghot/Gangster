package com.csci360.electionapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.Candidate;
import com.csci360.electionapp.model.User;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
			connect = DriverManager.getConnection(host, user, password);

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
	public static User loginQuery(String userid, String password) throws Exception {

		try {
			// Connect to MySQL and Query DB
			mySqlConnection();
			statement = connect.prepareStatement(
					"SELECT ID, USERID, User_Type, First_Name, Last_Name, COUNT(*) FROM evote.users WHERE USERID = ? AND PASSWORD = SHA1(?)");
			statement.setString(1, userid);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			// User authentication
			int userFound = 0;
			int id;
			String userID = null;
			String userType = null;
			String userFirstName = null;
			String userLastName = null;
			User userLogedIn = null;

			// Get user information and create user object.
			while (resultSet.next()) {
				userFound = resultSet.getInt(6);
				if (userFound == 1) {
					id = resultSet.getInt(1);
					userID = resultSet.getString(2);
					userType = resultSet.getString(3);
					userFirstName = resultSet.getString(4);
					userLastName = resultSet.getString(5);
					userLogedIn = new User(userID, userFirstName, userLastName, id, userType);
				}
				break;
			}
			close();
			return userLogedIn;

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Adding Account to Better Ballot
	 */
	public static void addAccountQuery(String userid, String password, String firstName, String lastName)
			throws Exception {

		try {

			mySqlConnection();
			statement = connect.prepareStatement(
					"INSERT INTO users(USERID,PASSWORD,User_Type, First_Name,Last_Name) VALUES (?,SHA1(?), 'voter', ?,?)");
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

	/*
	 * Add Ballot to MySQL
	 */
	public static void addBallotQuery(String[] fNameArray, String[] lNameArray, String ballotName) throws Exception {

		String id = null;
		try {

			mySqlConnection();
			statement = connect.prepareStatement("INSERT INTO ballots(name)VALUES(?)");
			statement.setString(1, ballotName);
			statement.executeUpdate();

			statement = connect.prepareStatement("SELECT ID FROM ballots WHERE NAME = ?");
			statement.setString(1, ballotName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getString(1);
			}
			System.out.print(fNameArray.length);
			int i = 0;
			while (fNameArray[i].compareTo("") != 0) {

				statement = connect
						.prepareStatement("INSERT INTO candidates(ballot_id,first_name,last_name) VALUES(?,?,?)");
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

	/*
	 * Delete ballot candidates to MySQL DB
	 */
	public static void deleteAllCandidatesQuery(int ballotId) throws Exception {

		try {
			mySqlConnection();
			statement = connect.prepareStatement("DELETE FROM candidates WHERE ballot_id = ?");
			statement.setInt(1, ballotId);
			statement.executeUpdate();
			close();

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * Delete Ballot from MySQL DB
	 */
	public static void deleteBallot(int ballotId) throws Exception {

		try {
			deleteAllCandidatesQuery(ballotId);
			mySqlConnection();

			statement = connect.prepareStatement("DELETE FROM ballots WHERE id = ?");
			statement.setInt(1, ballotId);
			statement.executeUpdate();
			close();

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * Update Ballot candidates in MySQL DB
	 */
	public static void updateBallotQuery(String[] fNameArray, String[] lNameArray, int ballotID) throws Exception {

		try {
			deleteAllCandidatesQuery(ballotID);
			mySqlConnection();
			int i = 0;
			while (fNameArray[i].compareTo("") != 0) {

				statement = connect
						.prepareStatement("INSERT INTO candidates(ballot_id,first_name,last_name) VALUES(?,?,?)");
				statement.setInt(1, ballotID);
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

	/*
	 * Get all ballots in MySQL DB
	 */
	public static ObservableList<Ballot> getAllBallots() throws Exception {

		try {
			mySqlConnection();
			String fName;
			String lName;
			int candidateID;

			statement = connect.prepareStatement("SELECT name, id FROM evote.ballots");
			resultSet = statement.executeQuery();
			ObservableList<Ballot> ballotsData = FXCollections.observableArrayList();

			while (resultSet.next()) {

				ArrayList<Candidate> candidates = new ArrayList<Candidate>();
				statement = connect
						.prepareStatement("SELECT first_name,last_name, id FROM evote.candidates WHERE ballot_id = ?");
				statement.setInt(1, resultSet.getInt(2));
				nextResultSet = statement.executeQuery();

				while (nextResultSet.next()) {
					fName = nextResultSet.getString(1);
					lName = nextResultSet.getString(2);
					candidateID = nextResultSet.getInt(3);

					Candidate candidate = new Candidate(fName, lName, candidateID);
					candidates.add(candidate);
				}
				ballotsData.add(new Ballot(resultSet.getString(1), candidates, resultSet.getInt(2)));
			}
			close();

			return ballotsData;

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Get all users in MySQL DB
	 */
	public static ObservableList<User> getAllUsers() throws Exception {

		try {

			mySqlConnection();
			statement = connect
					.prepareStatement("SELECT ID, USERID, User_Type, First_Name, Last_Name FROM evote.users");
			resultSet = statement.executeQuery();
			ObservableList<User> voterData = FXCollections.observableArrayList();
			int id;
			String userID = null;
			String userType = null;
			String userFirstName = null;
			String userLastName = null;

			while (resultSet.next()) {

				id = resultSet.getInt(1);
				userID = resultSet.getString(2);
				userType = resultSet.getString(3);
				userFirstName = resultSet.getString(4);
				userLastName = resultSet.getString(5);
				voterData.add(new User(userID, userFirstName, userLastName, id, userType));

			}
			// writeResultSet(resultSet);
			close();
			return voterData;

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Insert users vote in MySQL DB
	 */
	public static void castVoteQuery(int voterID, int ballotID, int candidateID) throws Exception {

		try {

			mySqlConnection();
			statement = connect.prepareStatement("INSERT INTO votes(voter_id, ballot_id,candidate_id) VALUES(?,?,?)");
			statement.setInt(1, voterID);
			statement.setInt(2, ballotID);
			statement.setInt(3, candidateID);
			statement.executeUpdate();
			close();

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Get all the ballets that are available for the user to vote for
	 */
	public static ObservableList<Ballot> getVotersAvailableBallots(int userID) throws Exception {

		try {

			mySqlConnection();
			String fName;
			String lName;
			int candidateID;

			statement = connect.prepareStatement(
					"SELECT name, id FROM ballots WHERE id NOT IN (SELECT DISTINCT ballot_id FROM v_votes WHERE user_id = ?)");
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();
			ObservableList<Ballot> ballotsData = FXCollections.observableArrayList();

			while (resultSet.next()) {

				ArrayList<Candidate> candidates = new ArrayList<Candidate>();
				statement = connect
						.prepareStatement("SELECT first_name,last_name, id FROM evote.candidates WHERE ballot_id = ?");
				statement.setInt(1, resultSet.getInt(2));
				nextResultSet = statement.executeQuery();

				while (nextResultSet.next()) {
					fName = nextResultSet.getString(1);
					lName = nextResultSet.getString(2);
					candidateID = nextResultSet.getInt(3);

					Candidate candidate = new Candidate(fName, lName, candidateID);
					candidates.add(candidate);
				}
				ballotsData.add(new Ballot(resultSet.getString(1), candidates, resultSet.getInt(2)));

			}
			close();

			return ballotsData;

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Get pie chart data
	 */
	public static ObservableList<PieChart.Data> getCurrentStandingsPieChart(int ballotID) throws Exception {

		try {

			mySqlConnection();
			statement = connect.prepareStatement("SELECT * FROM v_ballot_count WHERE ballot_id = ?");
			statement.setInt(1, ballotID);
			resultSet = statement.executeQuery();
			ObservableList<PieChart.Data> ballotsData = FXCollections.observableArrayList();

			while (resultSet.next()) {

				ballotsData.add(new PieChart.Data(resultSet.getString(3)+" "+resultSet.getString(4) , resultSet.getInt(5)));
			}
			close();

			return ballotsData;

		} catch (Exception e) {
			throw e;
		}
	}

	public static Map<String, Integer> getCurrentStandings(int ballotID) throws Exception {

		try {

			mySqlConnection();
			statement = connect.prepareStatement("SELECT * FROM v_ballot_count WHERE ballot_id = ?");
			statement.setInt(1, ballotID);
			resultSet = statement.executeQuery();
			Map<String, Integer> ballotData = new HashMap<String, Integer>();
			while (resultSet.next()) {

				ballotData.put(resultSet.getString(3)+" "+resultSet.getString(4), resultSet.getInt(5));
			}
			close();

			return ballotData;

		} catch (Exception e) {
			throw e;
		}
	}
	/*
	 * For using when you want to see what the query results.. For Debugging
	 */
	private static void writeResultSet(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {

			String id = resultSet.getString(1) + " " + resultSet.getString(2);

			System.out.println(id);
		}
	}

}
