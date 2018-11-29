package com.csci360.electionapp.controller;

import com.csci360.electionapp.BetterBallot;
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

public class CreateBallotController {

	// For adding a new ballot
	@FXML
	private TextField ballotNameField;
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
	private boolean okClicked = false;

	@FXML
	private void initialize() {
		ballotNameField.setText("");
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

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void createBallot(ActionEvent event) throws Exception {

		// Get fields from TextFields
		String ballotName = ballotNameField.getText();
		String[] fNameArray = { fName1Field.getText().toString(), fName2Field.getText().toString(),
				fName3Field.getText().toString(), fName4Field.getText().toString(), fName5Field.getText().toString() };
		String[] lNameArray = { lName1Field.getText().toString(), lName2Field.getText().toString(),
				lName3Field.getText().toString(), lName4Field.getText().toString(), lName5Field.getText().toString() };

		// Connect to DB and Insert into user table.
		try {

			DBConnection.addBallotQuery(fNameArray, lNameArray, ballotName);

		} catch (Exception e) {
			throw e;
		}

		ConfigureBallotController.createDialogStage.close();
	}

	public void closeDialog() {

		ConfigureBallotController.createDialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
