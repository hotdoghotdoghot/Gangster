package com.csci360.electionapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

	private final StringProperty userName;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty userType;
	private static int voterID;

	public User() {
		this(null, null, null, -1, null);
	}

	public User(String userName, String firstName, String lastName, int voterID, String userType) {
		this.userName = new SimpleStringProperty(userName);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.voterID = voterID;
		this.userType = new SimpleStringProperty(userType);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName.set(userName);
	}

	public StringProperty userNameProperty() {
		return userName;
	}

	public StringProperty voterIDProperty() {
		return userName;
	}

	public String getUserType() {
		return userType.get();
	}

	public StringProperty userType() {
		return userType;
	}

	public static int getVoterID() {
		return voterID;
	}

}
