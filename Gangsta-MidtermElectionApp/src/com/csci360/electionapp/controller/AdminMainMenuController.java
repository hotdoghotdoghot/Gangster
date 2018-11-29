package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.util.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdminMainMenuController {

	/*
	 * Switch scene to Login Screen. (Used for Logging out)
	 */
	public void goToLoginScreen(ActionEvent event) throws Exception {
		try {
			Parent loginParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/LoginScreen.fxml"));
			Scene loginScene = new Scene(loginParent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setScene(loginScene);
			loginStage.show();

		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * Administration Button Clicked. (Used for ballot administration)
	 */
	public void adminView(ActionEvent event) throws Exception {
		try {
			Parent mainMenuParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenu.fxml"));
			Scene mainMenuScene = new Scene(mainMenuParent);
			Stage mainMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			mainMenuStage.setScene(mainMenuScene);
			mainMenuStage.show();

		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * Stats Button Clicked. (Used for viewing stats)
	 */
	public void statsView(ActionEvent event) throws Exception {
		try {
			Parent statsParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuStats.fxml"));
			Scene statsScene = new Scene(statsParent);
			Stage statsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			statsStage.setScene(statsScene);
			statsStage.show();

		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * Setting Button Clicked. (Used for viewing account settings)
	 */
	public void settingsView(ActionEvent event) throws Exception {

		try {
			Parent settingsParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuSettings.fxml"));
			Scene settingsScene = new Scene(settingsParent);
			Stage settingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			settingsStage.setScene(settingsScene);
			settingsStage.show();

		} catch (Exception e) {

			throw e;
		}
	}

	/*
	 * Configure Ballots Button Clicked. (Used for adding/editing/deleting ballots
	 * for a admin user)
	 */
	public void configureBallots(ActionEvent event) throws Exception {

		try {

			Parent configBallotParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuConfigBallot.fxml"));
			Scene configBallotScene = new Scene(configBallotParent);
			Stage configBallotStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			configBallotStage.setScene(configBallotScene);
			configBallotStage.show();

		} catch (Exception e) {

			throw e;
		}

	}

	/*
	 * Configure Users Button Clicked. (Used for adding/editing/deleting users for a
	 * admin user)
	 */
	public void configureUsers(ActionEvent event) throws Exception {

		try {

			Parent configUsersParent = FXMLLoader
					.load(getClass().getResource("/com/csci360/electionapp/view/AdminMainMenuConfigUser.fxml"));
			Scene configUsersScene = new Scene(configUsersParent);
			Stage configUsersStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			configUsersStage.setScene(configUsersScene);
			configUsersStage.show();

		} catch (Exception e) {

			throw e;
		}

	}

}
