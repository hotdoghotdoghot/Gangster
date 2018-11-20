package com.csci360.electionapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CandidateTest
{
    private Candidate testCandidate;
    private Candidate testCandidate2;
    @Before
    public void SetUp()
    {
        testCandidate = new Candidate("neil", "young",1);
        testCandidate2 = new Candidate("stevie", "wonder", 2);
    }
    @Test
    public void getFirstName() throws Exception
    {
        assertEquals(testCandidate.getFirstName(), "neil");
    }

    @Test
    public void setFirstName() throws Exception
    {
        testCandidate.setFirstName("Mambo");
        assertEquals(testCandidate.getFirstName(),"Mambo");
    }

    @Test
    public void getLastName() throws Exception
    {
        assertEquals(testCandidate.getLastName(), "young");
    }

    @Test
    public void setLastName() throws Exception
    {
        testCandidate.setLastName("glibglob");
        assertEquals(testCandidate.getLastName(),"glibglob");
    }

    @Test
    public void getCandidateID() throws Exception
    {
        assertEquals(testCandidate.getCandidateID(), 1);
    }

}