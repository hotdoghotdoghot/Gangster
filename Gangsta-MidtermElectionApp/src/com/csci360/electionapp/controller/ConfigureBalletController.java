package com.csci360.electionapp.controller;

import java.util.ArrayList;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballet;
import com.csci360.electionapp.model.Canidate;
import com.csci360.electionapp.util.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigureBalletController {
	
	
	//For viewing the current ballets 
	@FXML
	public TableView<Ballet> balletTable;
	@FXML
	public TableView<Canidate> canidateTable;
	@FXML
	private TableColumn<Ballet, String> balletNameColumn;
	@FXML
	private TableColumn<Canidate, String> canidateFirstNameColumn;
	@FXML
	private TableColumn<Canidate, String> canidateLastNameColumn;



	public static Stage createDialogStage = new Stage(); 
	public static Stage editDialogStage = new Stage(); 
	private FXMLLoader editLoader = new FXMLLoader();
	private FXMLLoader createLoader = new FXMLLoader();
	

	@FXML
	public void initialize() {
		try {
	    	
	    	createLoader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/AddBalletDialog.fxml"));
	    	AnchorPane createDialogPane = (AnchorPane) createLoader.load();               
	    	createDialogStage.setTitle("Create Ballet");
	    	createDialogStage.initModality(Modality.WINDOW_MODAL);
	    	createDialogStage.initOwner(BetterBallot.primaryStage);
	    	Scene createDialogScene = new Scene(createDialogPane);
	    	createDialogStage.setScene(createDialogScene);
			
	    	
	    	editLoader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/EditBalletDialog.fxml"));
	    	AnchorPane editDialogPane = (AnchorPane) editLoader.load();   
	    	
	    	
	    	editDialogStage.setTitle("Edit Ballet");
	    	editDialogStage.initModality(Modality.WINDOW_MODAL);
	    	editDialogStage.initOwner(BetterBallot.primaryStage);
	    	Scene editDialogScene = new Scene(editDialogPane);
	    	editDialogStage.setScene(editDialogScene);
	    	
	    	
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
    
    public boolean showCreateBallet(ActionEvent event)throws Exception {
    	
    	CreateBalletController controller = createLoader.getController();
    	controller.setDialogStage(createDialogStage);
    	createDialogStage.showAndWait();
    	showBalletDetails(null);
    	updateDetails();
    	return controller.isOkClicked();
        
    }
    
    public void showEditBallet(ActionEvent event)throws Exception {
    	
    	Ballet selectedBallet = balletTable.getSelectionModel().getSelectedItem();	
    	EditBalletController controller = editLoader.getController();
    	controller.setDialogStage(editDialogStage);
    	controller.setBallet(selectedBallet);   	
    	editDialogStage.showAndWait();
    	showBalletDetails(null);
    	updateDetails();
        
    }
    
    public void showBalletDetails(Ballet ballet) {
    	
    	if(ballet != null){
    		ArrayList<Canidate> newArray= new ArrayList<Canidate>(ballet.getCanidates());
    		ObservableList<Canidate> canidates = FXCollections.observableArrayList(newArray);
    		canidateTable.setItems(canidates);
    	}
    	else {
    		
    		canidateTable.setItems(null);
    	}
    }
    public void updateDetails() throws Exception {
    	
		balletNameColumn.setCellValueFactory(cellData -> cellData.getValue().balletNameProperty());
		balletTable.setItems(DBConnection.getAllBallets());
		
        canidateFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        canidateLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        

        // Listen for selection changes and show the person details when changed.
        balletTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBalletDetails(newValue));
    }
  public void deleteBallot(ActionEvent event)throws Exception {
    	
    	Ballet selectedBallet = balletTable.getSelectionModel().getSelectedItem();	
    	DBConnection.deleteBallet(selectedBallet.getBalletID());   	
    	showBalletDetails(null);
    	updateDetails();
        
    }

    
 
}
    

