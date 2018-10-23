package com.csci360.electionapp.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ballet {
	
 	private final StringProperty balletName;
 	
 	
    public Ballet(){
        this(null);
    }
    public Ballet(String balletName) {
        this.balletName = new SimpleStringProperty(balletName);
    }
    public String getBalletName() {
        return balletName.get();
    }

    public void setBalletName(String balletName) {
        this.balletName.set(balletName);
    }
    
    public StringProperty balletNameProperty() {
        return balletName;
    }
}
