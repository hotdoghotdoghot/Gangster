package com.csci360.electionapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ballet {
	
 	private final StringProperty balletName;
 	private final int balletId;
 	private final ArrayList<Canidate> canidates;
 	
 	
    public Ballet(){
        this(null,null, -1);
    }   

    public Ballet(String balletName, ArrayList<Canidate> canidates, int balletId) {
        this.balletName = new SimpleStringProperty(balletName);
        this.canidates =  new ArrayList<Canidate>(canidates);
        this.balletId =  balletId;
    }
    public String getBalletName() {
        return balletName.get();
    }
    public int getBalletID() {
        return balletId;
    }
    public ArrayList<Canidate> getCanidates() {
        return canidates;
    }

    public void setBalletName(String balletName) {
        this.balletName.set(balletName);
    } 
    
    public void addCanidate(String fName, String lName, int canidateID) {
    	Canidate canidate = new Canidate(fName,lName, canidateID);
        this.canidates.add(canidate);
    } 
    
    public StringProperty balletNameProperty() {
        return balletName;
    }
}
