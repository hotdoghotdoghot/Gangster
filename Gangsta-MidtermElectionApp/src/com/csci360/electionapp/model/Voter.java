package com.csci360.electionapp.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Voter {
	
		private final StringProperty userName;
	 	private final StringProperty firstName;
	    private final StringProperty lastName;


	    public Voter() {
	        this(null, null, null);
	    }
	    
	
	    public Voter( String userName, String firstName, String lastName) {
	    	this.userName = new SimpleStringProperty(userName);
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
	    public String getUserName() {
	        return userName.get();
	    }

	    public void setUserName(String lastName) {
	        this.userName.set(lastName);
	    }
	    
	    public StringProperty userNameProperty() {
	        return userName;
	    }


}
