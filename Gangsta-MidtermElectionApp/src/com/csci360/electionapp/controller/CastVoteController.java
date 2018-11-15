package com.csci360.electionapp.controller;


import java.util.ArrayList;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballet;
import com.csci360.electionapp.model.Canidate;
import com.csci360.electionapp.model.User;
import com.csci360.electionapp.util.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CastVoteController {
	
	
	//For viewing the current ballets 
	@FXML
	public TableView<Ballet> balletTable;
	
	@FXML
	private TableColumn<Ballet, String> balletNameColumn;
	
	@FXML
	public TableView<Canidate> canidateTable;
	
	@FXML
	private TableColumn<Canidate, String> canidateFirstNameColumn;
	
	@FXML
	private TableColumn<Canidate, String> canidateLastNameColumn;
	
	@FXML
	
	
	public void initialize() {
		try {
			updateDetails();
			
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

    public void updateDetails() throws Exception {
    	
		balletNameColumn.setCellValueFactory(cellData -> cellData.getValue().balletNameProperty());
		balletTable.setItems(DBConnection.getAllBallets());
		

        // Listen for selection changes and show the person details when changed.
        balletTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBalletDetails(newValue));

    }
    
    public void showBalletDetails(Ballet ballet) {
    	
    	if(ballet != null){
    		
    		ArrayList<Canidate> newArray= new ArrayList<Canidate>(ballet.getCanidates());
    		ObservableList<Canidate> canidates = FXCollections.observableArrayList(newArray);
    		canidateTable.setItems(canidates);
    		
            canidateFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
            canidateLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
            

    	}
    	else {
    		
    		canidateTable.setItems(null);
    	}
    }

    
    public void castVote() {
    	
    	
    	int selectedIndex = canidateTable.getSelectionModel().getSelectedIndex();  
    	Canidate canidate = canidateTable.getSelectionModel().getSelectedItem();
    	Ballet ballet = balletTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedIndex >= 0){
    		
    		int canidateID = canidate.getCanidateID();
    		int balletID = ballet.getBalletID();
    		int userID = LoginScreenController.getUserLogedIn().getVoterID();
    		
    		try {
    			
				DBConnection.castVoteQuery(userID,balletID, canidateID);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
            

    	}
    	else {
    		
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(BetterBallot.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Canidate Selected");
            alert.setContentText("Please select a canidate to cast vote!");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/com/csci360/electionapp/view/Style.css").toExternalForm());
            dialogPane.getStyleClass().add("alert");
            alert.showAndWait();
    	}
    }
 
}
    

