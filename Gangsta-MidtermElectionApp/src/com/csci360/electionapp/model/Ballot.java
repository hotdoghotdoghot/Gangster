package com.csci360.electionapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ballot {

	private final StringProperty ballotName;
	private final int ballotId;
	private final ArrayList<Candidate> candidates;

	public Ballot() {
		this(null, null, -1);
	}

	public Ballot(String ballotName, ArrayList<Candidate> candidates, int ballotId) {
		this.ballotName = new SimpleStringProperty(ballotName);
		this.candidates = new ArrayList<Candidate>(candidates);
		this.ballotId = ballotId;
	}

	public String getBallotName() {
		return ballotName.get();
	}

	public int getBallotID() {
		return ballotId;
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public void setBallotName(String ballotName) {
		this.ballotName.set(ballotName);
	}

	public void addCandidate(String fName, String lName, int candidateID) {
		Candidate candidate = new Candidate(fName, lName, candidateID);
		this.candidates.add(candidate);
	}

	public StringProperty ballotNameProperty() {
		return ballotName;
	}
}
