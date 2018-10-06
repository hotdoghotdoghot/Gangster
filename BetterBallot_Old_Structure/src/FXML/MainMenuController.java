package FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {
	
	/*
	 * Switch scene to Login Screen. (Used for Logging out)
	 */
    public void goToLoginScreen(ActionEvent event)throws Exception {
		try {		
    			Parent loginParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
    			Scene loginScene = new Scene(loginParent);
			Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			loginStage.setScene(loginScene);
			loginStage.show();
			
		}catch (Exception e) {
			
			throw e;
		} 
    }
}
