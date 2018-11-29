package com.csci360.electionapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class BallotTest {
	private Ballot testBallot;
	private StringProperty ballotNameProp;

	@Before
	public void setUp() {
		int aaaahhhhhh;
		int numCandidates = 4;
		ArrayList<Candidate> testCandidate = new ArrayList<Candidate>(numCandidates);
		Candidate testCan1 = new Candidate("slime", "machine", 1);
		Candidate testCan2 = new Candidate("miles", "donB", 2);
		Candidate testCan3 = new Candidate("sad", "contest", 3);

		testCandidate.add(testCan1);
		testCandidate.add(testCan2);
		testCandidate.add(testCan3);

		testBallot = new Ballot("testBallotName", testCandidate, 1);
	}

	@Test
	public void getBallotName() throws Exception {
		assertEquals("testBallotName", testBallot.getBallotName());

	}

	@Test
	public void getBallotID() throws Exception {
		assertEquals(1, testBallot.getBallotID());
	}

	@Test
	public void setBallotName() throws Exception {
		testBallot.setBallotName("bigTest");
		assertEquals("bigTest", testBallot.getBallotName());
	}
}