package com.csci360.electionapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Canidate {
 	private final StringProperty firstName;
    private final StringProperty lastName;
    
    
    public Canidate() {
        this(null, null);
    } 
    
    public Canidate(String firstName, String lastName) {
    	
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
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
}
