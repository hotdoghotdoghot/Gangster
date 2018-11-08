package com.csci360.electionapp.controller;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballet;
import com.csci360.electionapp.util.DBConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigureBalletController {
	
	
	//For viewing the current ballets 
	@FXML
	private TableView<Ballet> balletTable;
	@FXML
	private TableColumn<Ballet, String> balletNameColumn;
	
	public static Stage dialogStage = new Stage(); 

	
	public void initialize() {
		try {
			
			balletNameColumn.setCellValueFactory(cellData -> cellData.getValue().balletNameProperty());
			balletTable.setItems(DBConnection.getAllBallets());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	   }

	/*
	 * Switch scene to Login Screen. (Used for Logging out)
	 */
    public void goToLoginScreen(ActionEvent event)throws Exception {
		try {		
    		Parent loginParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/LoginScreen.fxml"));
    		Scene loginScene = new Scene(loginParent);
			Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			loginStage.setScene(loginScene);
			loginStage.show();
			
		}catch (Exception e) {
			
			throw e;
		} 
    }
    
	/*
	 * Administration Button Clicked. (Used for ballet administration)
	 */
    public void adminView(ActionEvent event)throws Exception {
		try {		
    		Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenu.fxml"));
    		Scene mainMenuScene = new Scene(mainMenuParent);
			Stage mainMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			mainMenuStage.setScene(mainMenuScene);
			mainMenuStage.show();
			
		}catch (Exception e) {
			
			throw e;
		} 
    }
    
	/*
	 * Stats Button Clicked. (Used for viewing stats)
	 */
    public void statsView(ActionEvent event)throws Exception {
		try {		
    		Parent statsParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuStats.fxml"));
    		Scene statsScene = new Scene(statsParent);
			Stage statsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			statsStage.setScene(statsScene);
			statsStage.show();
			
		}catch (Exception e) {
			
			throw e;
		} 
    }    
	/*
	 * Setting Button Clicked. (Used for viewing account settings)
	 */
    public void settingsView(ActionEvent event)throws Exception {
		try {		
    		Parent settingsParent = FXMLLoader.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuSettings.fxml"));
    		Scene settingsScene = new Scene(settingsParent);
			Stage settingsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			settingsStage.setScene(settingsScene);
			settingsStage.show();
			
		}catch (Exception e) {
			
			throw e;
		} 
    }
    public void showCreateBallet(ActionEvent event)throws Exception {
    	
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/AddBalletDialog.fxml"));
    	AnchorPane mainMenu = (AnchorPane) loader.load();               
    	dialogStage.setTitle("Create Ballet");
    	dialogStage.initModality(Modality.WINDOW_MODAL);
    	Scene Dialogscene = new Scene(mainMenu);
    	dialogStage.setScene(Dialogscene);
    	dialogStage.show();
        
    }

}
