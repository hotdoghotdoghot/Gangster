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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditBalletController {
	
	
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
    
    private Stage dialogStage;
    private Ballet ballet;
	
    @FXML
    private void initialize() {
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setBallet(Ballet ballet){
    	
    	this.ballet = ballet;
    	int canidateCount = ballet.getCanidates().size();
    	
    	if (canidateCount == 5) {
    		
    		balletNameField.setText(ballet.getBalletName()); 
    		fName1Field.setText(ballet.getCanidates().get(0).getFirstName());     
    		fName2Field.setText(ballet.getCanidates().get(1).getFirstName());       
    		fName3Field.setText(ballet.getCanidates().get(2).getFirstName());      
    		fName4Field.setText(ballet.getCanidates().get(4).getFirstName());      
    		fName5Field.setText(ballet.getCanidates().get(5).getFirstName());    
    		lName1Field.setText(ballet.getCanidates().get(0).getLastName());                 
    		lName2Field.setText(ballet.getCanidates().get(1).getLastName());              
    		lName3Field.setText(ballet.getCanidates().get(2).getLastName());            
    		lName4Field.setText(ballet.getCanidates().get(4).getLastName());             
    		lName5Field.setText(ballet.getCanidates().get(5).getLastName());    
    		
    	}
    	else if (canidateCount == 4) {
    		
    		balletNameField.setText(ballet.getBalletName()); 
    		fName1Field.setText(ballet.getCanidates().get(0).getFirstName());     
    		fName2Field.setText(ballet.getCanidates().get(1).getFirstName());       
    		fName3Field.setText(ballet.getCanidates().get(2).getFirstName());      
    		fName4Field.setText(ballet.getCanidates().get(4).getFirstName());      
    		fName5Field.setText("");   
    		lName1Field.setText(ballet.getCanidates().get(0).getLastName());                 
    		lName2Field.setText(ballet.getCanidates().get(1).getLastName());              
    		lName3Field.setText(ballet.getCanidates().get(2).getLastName());            
    		lName4Field.setText(ballet.getCanidates().get(4).getLastName());             
    		lName5Field.setText("");    
    		
    	}
    	else if (canidateCount == 3) {
    		
    		balletNameField.setText(ballet.getBalletName()); 
    		fName1Field.setText(ballet.getCanidates().get(0).getFirstName());     
    		fName2Field.setText(ballet.getCanidates().get(1).getFirstName());       
    		fName3Field.setText(ballet.getCanidates().get(2).getFirstName());      
    		fName4Field.setText("");   
    		fName5Field.setText("");   
    		lName1Field.setText(ballet.getCanidates().get(0).getLastName());                 
    		lName2Field.setText(ballet.getCanidates().get(1).getLastName());              
    		lName3Field.setText(ballet.getCanidates().get(2).getLastName());            
    		lName4Field.setText("");             
    		lName5Field.setText("");    
    		
    	}
    	else if (canidateCount == 2) {
    		
    		balletNameField.setText(ballet.getBalletName()); 
    		fName1Field.setText(ballet.getCanidates().get(0).getFirstName());     
    		fName2Field.setText(ballet.getCanidates().get(1).getFirstName());       
    		fName3Field.setText("");  
    		fName4Field.setText("");   
    		fName5Field.setText("");   
    		lName1Field.setText(ballet.getCanidates().get(0).getLastName());                 
    		lName2Field.setText(ballet.getCanidates().get(1).getLastName());              
    		lName3Field.setText("");            
    		lName4Field.setText("");             
    		lName5Field.setText("");    
    		
    	}
    	else if (canidateCount == 1) {
    		
    		balletNameField.setText(ballet.getBalletName()); 
    		fName1Field.setText(ballet.getCanidates().get(0).getFirstName());     
    		fName2Field.setText("");       
    		fName3Field.setText("");  
    		fName4Field.setText("");   
    		fName5Field.setText("");   
    		lName1Field.setText(ballet.getCanidates().get(0).getLastName());                 
    		lName2Field.setText("");              
    		lName3Field.setText("");            
    		lName4Field.setText("");             
    		lName5Field.setText("");    
    		
    	}
    	else {
    		
    		balletNameField.setText(ballet.getBalletName()); 
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
    }
    
    public void updateBallet(ActionEvent event) throws Exception {
    	int balletId = ballet.getBalletID();
    	
    	String[] fNameArray = {fName1Field.getText().toString(),fName2Field.getText().toString(),fName3Field.getText().toString(),fName4Field.getText().toString(),fName5Field.getText().toString()};
    	String[] lNameArray = {lName1Field.getText().toString(),lName2Field.getText().toString(),lName3Field.getText().toString(),lName4Field.getText().toString(),lName5Field.getText().toString()};
		//Connect to DB and Insert into user table. 
		try {
			
			DBConnection.updataBalletQuery(fNameArray, lNameArray, balletId);
			ConfigureBalletController.editDialogStage.close();
			

				
		}catch (Exception e) {
			throw e;
		}
		
	}
    	

    public void closeDialog() {
    	
    	ConfigureBalletController.editDialogStage.close();
    }
		
		
}

