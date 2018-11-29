package com.csci360.electionapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Candidate {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final int candidateID;

	public Candidate() {
		this(null, null, -1);
	}

	public Candidate(String firstName, String lastName, int candidateID) {

		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.candidateID = candidateID;
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

	public int getCandidateID() {
		return candidateID;
	}

}
