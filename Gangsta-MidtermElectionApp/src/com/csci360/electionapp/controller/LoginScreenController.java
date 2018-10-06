package com.csci360.electionapp.controller;

import java.io.IOException;
import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.util.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginScreenController {

    @FXML
    private TextField userIdField;

    @FXML
    private Label loginFailed;

    @FXML
    private TextField passwordField;
     
    @FXML
    private void initialize() {
    		loginFailed.setText("");
    }
    
    /*
     * Changes scence to Main Menu after login success
     */
    public void userLogin(ActionEvent event) throws Exception {
    	
    		//Get username and password from TextFields
		String userId =userIdField.getText();
		String password = passwordField.getText();
		
		//User Verification
    		try {
    			
    			boolean loginVerification = DBConnection.loginQuery(userId, password);
    		
    			if(loginVerification == true) {  
    				
            		Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenu.fxml"));
            		Scene mainMenuScene = new Scene(mainMenuParent);
    				Stage mainMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
    				mainMenuStage.setScene(mainMenuScene);
    				mainMenuStage.show();
    			}
    			else {
    				loginFailed.setText("Invalid Username or Password");
    			}
    				
    		}catch (Exception e) {
				throw e;
			} 	
    		
    }
    /*
     * Switches to create user scene
     */
    public void goToCreateUser(ActionEvent event)throws Exception {
		try {
			
    			Parent createAccountParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/CreateAccount.fxml"));
    			Scene createAccountScene = new Scene(createAccountParent);
			Stage createAccountStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			createAccountStage.setScene(createAccountScene);
			createAccountStage.show();
		}catch (Exception e) {
			throw e;
		} 
    }
    
    //Closes Application
    public void cancelLogin(ActionEvent event)throws Exception {
    		try {
    			Platform.exit();  		
    		}catch (Exception e) {
    			throw e;
    		} 
    }
    
    
}