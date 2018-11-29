package com.csci360.electionapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
	private User testUser;

	@Before
	public void SetUp() {
		User testUser = new User("alex32Billion", "alex", "ross", 32, "admin");
	}

	@Test
	public void getFirstName() throws Exception {
		String strAlex = new String("alex");
		assertEquals(strAlex.hashCode(), (testUser.getFirstName()).hashCode());
	}

	@Test
	public void setFirstName() throws Exception {
		testUser.setFirstName("stinky");
		assertEquals(testUser.getFirstName(), "stinky");
	}

	@Test
	public void getLastName() throws Exception {
		assertEquals(testUser.getLastName(), "ross");
	}

	@Test
	public void setLastName() throws Exception {
		testUser.setLastName("stinky");
		assertEquals(testUser.getLastName(), "stinky");
	}

	@Test
	public void getUserName() throws Exception {
		assertEquals(testUser.getUserName(), "alex32Billion");
	}

	@Test
	public void setUserName() throws Exception {
		testUser.setUserName("bobbyBigBoy");
		assertEquals(testUser.getUserName(), "bobbyBigBoy");
	}

	@Test
	public void getUserType() throws Exception {
		assertEquals(testUser.getUserType(), "admin");
	}

	@Test
	public void getVoterID() throws Exception {
		assertEquals(testUser.getVoterID(), 32);
	}

}