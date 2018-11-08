package com.csci360.electionapp.controller;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.util.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateBalletController {
	
	
	//For adding a new ballet 
    @FXML
    private TextField balletNameField;
    @FXML
    private TextField fName1Field;
    @FXML
    private TextField fName2Field;
    @FXML
    private TextField fName3Field;
    @FXML
    private TextField fName4Field;
    @FXML
    private TextField fName5Field;
    @FXML
    private TextField lName1Field;
    @FXML
    private TextField lName2Field;
    @FXML
    private TextField lName3Field;
    @FXML
    private TextField lName4Field;
    @FXML
    private TextField lName5Field;
	
    @FXML
    private void initialize() {
    	balletNameField.setText("");
    	fName1Field.setText("");
    	fName2Field.setText("");
    	fName3Field.setText("");
    	fName4Field.setText("");
    	fName5Field.setText("");
    	lName1Field.setText("");
    	lName2Field.setText("");
    	lName3Field.setText("");
    	lName4Field.setText("");
    	lName5Field.setText("");
    }
	
	

    
    public void createBallet(ActionEvent event) throws Exception {
		
    	
		// Get fields from TextFields
    	String balletName = balletNameField.getText();
    	String[] fNameArray = {fName1Field.getText().toString(),fName2Field.getText().toString(),fName3Field.getText().toString(),fName4Field.getText().toString(),fName5Field.getText().toString()};
    	String[] lNameArray = {lName1Field.getText().toString(),lName2Field.getText().toString(),lName3Field.getText().toString(),lName4Field.getText().toString(),lName5Field.getText().toString()};
    	System.out.println(fNameArray[1]);
		//Connect to DB and Insert into user table. 
		try {
			
			DBConnection.addBalletQuery(fNameArray, lNameArray, balletName);
				
		}catch (Exception e) {
			throw e;
		}
		
		ConfigureBalletController.dialogStage.close();
		
		try {	
			
    		Parent configBalletParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuConfigBallet.fxml"));
    		Scene configBalletScene = new Scene(configBalletParent);
			BetterBallot.primaryStage.setScene(configBalletScene);
			BetterBallot.primaryStage.show();

			
		}catch (Exception e) {
			
			throw e;
		}
	}
    
    public void closeDialog() {
    	
    	ConfigureBalletController.dialogStage.close();
    }
		
		
}

