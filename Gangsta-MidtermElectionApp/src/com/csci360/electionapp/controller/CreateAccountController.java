package com.csci360.electionapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.csci360.electionapp.util.DBConnection;

public class CreateAccountController {

	@FXML
	private TextField userIdField;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField fistNameField;

	@FXML
	private TextField lastNameField;

	/*
	 * Switch scene to Login Screen.
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
	 * Adds a new account for the better ballet
	 */
	public void createAccount(ActionEvent event) throws Exception {

		// Get fields from TextFields
		String userId = userIdField.getText();
		String password = passwordField.getText();
		String firstName = fistNameField.getText();
		String lastName = lastNameField.getText();

		// Connect to DB and Insert into user table.
		try {

			DBConnection.addAccountQuery(userId, password, firstName, lastName);
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
}
