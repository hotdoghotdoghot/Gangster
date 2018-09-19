package FXML;

import java.io.IOException;

import easyVote.App;
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
import util.DBConnection;

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
    				
            		Parent mainMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
			
    			Parent createAccountParent = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
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