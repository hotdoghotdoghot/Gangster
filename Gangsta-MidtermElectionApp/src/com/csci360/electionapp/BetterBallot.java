package com.csci360.electionapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.csci360.electionapp.model.*;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.csci360.electionapp.util.DBConnection;


public class BetterBallot extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;    
    
	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Better Ballot");

       initRootLayout();
       showLogin();
      //showMainMenu();
	}
	
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/MainScreen.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //For Initial Startup
    public void showLogin() {
        try {
            //Show login screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/LoginScreen.fxml"));
            AnchorPane login = (AnchorPane) loader.load();            
            rootLayout.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //For Debugging and Developing 
    public void showMainMenu() {
        try {
            //Show login screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/MainMenu.fxml"));
            AnchorPane mainMenu = (AnchorPane) loader.load();            
            Scene scene = new Scene(mainMenu);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 
	public static void main(String[] args){
		launch(args);
	}
	
	public static Window getPrimaryStage() {
		// TODO Auto-generated method stub
		return null;
	}
}

