package com.csci360.electionapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class BalletTest
{
    private Ballet testBallet;
    private StringProperty balletNameProp;

    @Before
    public void setUp()
    {
        int canidates = 4;
        ArrayList<Canidate> testCanidate = new ArrayList<Canidate>(canidates);
        Canidate testCan1 = new Canidate("slime", "machine", 1);
        Canidate testCan2 = new Canidate("miles", "donB", 2);
        Canidate testCan3 = new Canidate("sad", "contest", 3);

        testCanidate.add(testCan1);
        testCanidate.add(testCan2);
        testCanidate.add(testCan3);

        testBallet = new Ballet("testBallotName",testCanidate,1);
    }
    @Test
    public void getBalletName() throws Exception
    {
        assertEquals("testBallotName",testBallet.getBalletName());

    }
    @Test
    public void getBalletID() throws Exception
    {
        assertEquals(1,testBallet.getBalletID());
    }
/*
    @Test
    public void getCanidates() throws Exception
    {
        ArrayList canidateList = new ArrayList();
        Canidate testCan1 = new Canidate("slime", "machine", 1);
        Canidate testCan2 = new Canidate("miles", "donB", 2);
        Canidate testCan3 = new Canidate("sad", "contest", 3);

        canidateList.add(testCan1);
        canidateList.add(testCan2);
        canidateList.add(testCan3);

        assertEquals(canidateList,testBallet.getCanidates());
    }
*/
    @Test
    public void setBalletName() throws Exception
    {
        testBallet.setBalletName("bigTest");
        assertEquals("bigTest", testBallet.getBalletName());
    }
    @Test
    public void addCanidate() throws Exception
    {
        Canidate testCan4 = new Canidate("douglas", "falcon", 4);
    }
    @Test
    public void balletNameProperty() throws Exception
    {
        balletNameProp.set("testBallotName");
        assertEquals(testBallet.balletNameProperty().toString(),balletNameProp.toString());
    }

}