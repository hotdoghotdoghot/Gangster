package easyVote;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

import FXML.*;
import dataModel.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import util.DBConnection;


public class App extends Application {
	
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
            loader.setLocation(App.class.getResource("/FXML/MainScreen.fxml"));
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
            loader.setLocation(App.class.getResource("/FXML/LoginScreen.fxml"));
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
            loader.setLocation(App.class.getResource("/FXML/MainMenu.fxml"));
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
