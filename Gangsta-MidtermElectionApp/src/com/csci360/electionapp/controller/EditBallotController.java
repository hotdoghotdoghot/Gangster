package com.csci360.electionapp.controller;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballot;
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

public class EditBallotController {

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
	private Ballot ballot;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/*
	 * Fill out dialog stage with current ballot candidates for editing
	 */
	public void setBallot(Ballot ballot) {

		this.ballot = ballot;
		int candidateCount = ballot.getCandidates().size();

		if (candidateCount == 5) {

			ballotNameField.setText(ballot.getBallotName());
			fName1Field.setText(ballot.getCandidates().get(0).getFirstName());
			fName2Field.setText(ballot.getCandidates().get(1).getFirstName());
			fName3Field.setText(ballot.getCandidates().get(2).getFirstName());
			fName4Field.setText(ballot.getCandidates().get(3).getFirstName());
			fName5Field.setText(ballot.getCandidates().get(3).getFirstName());
			lName1Field.setText(ballot.getCandidates().get(0).getLastName());
			lName2Field.setText(ballot.getCandidates().get(1).getLastName());
			lName3Field.setText(ballot.getCandidates().get(2).getLastName());
			lName4Field.setText(ballot.getCandidates().get(3).getLastName());
			lName5Field.setText(ballot.getCandidates().get(4).getLastName());

		} else if (candidateCount == 4) {

			ballotNameField.setText(ballot.getBallotName());
			fName1Field.setText(ballot.getCandidates().get(0).getFirstName());
			fName2Field.setText(ballot.getCandidates().get(1).getFirstName());
			fName3Field.setText(ballot.getCandidates().get(2).getFirstName());
			fName4Field.setText(ballot.getCandidates().get(3).getFirstName());
			fName5Field.setText("");
			lName1Field.setText(ballot.getCandidates().get(0).getLastName());
			lName2Field.setText(ballot.getCandidates().get(1).getLastName());
			lName3Field.setText(ballot.getCandidates().get(2).getLastName());
			lName4Field.setText(ballot.getCandidates().get(3).getLastName());
			lName5Field.setText("");

		} else if (candidateCount == 3) {

			ballotNameField.setText(ballot.getBallotName());
			fName1Field.setText(ballot.getCandidates().get(0).getFirstName());
			fName2Field.setText(ballot.getCandidates().get(1).getFirstName());
			fName3Field.setText(ballot.getCandidates().get(2).getFirstName());
			fName4Field.setText("");
			fName5Field.setText("");
			lName1Field.setText(ballot.getCandidates().get(0).getLastName());
			lName2Field.setText(ballot.getCandidates().get(1).getLastName());
			lName3Field.setText(ballot.getCandidates().get(2).getLastName());
			lName4Field.setText("");
			lName5Field.setText("");

		} else if (candidateCount == 2) {

			ballotNameField.setText(ballot.getBallotName());
			fName1Field.setText(ballot.getCandidates().get(0).getFirstName());
			fName2Field.setText(ballot.getCandidates().get(1).getFirstName());
			fName3Field.setText("");
			fName4Field.setText("");
			fName5Field.setText("");
			lName1Field.setText(ballot.getCandidates().get(0).getLastName());
			lName2Field.setText(ballot.getCandidates().get(1).getLastName());
			lName3Field.setText("");
			lName4Field.setText("");
			lName5Field.setText("");

		} else if (candidateCount == 1) {

			ballotNameField.setText(ballot.getBallotName());
			fName1Field.setText(ballot.getCandidates().get(0).getFirstName());
			fName2Field.setText("");
			fName3Field.setText("");
			fName4Field.setText("");
			fName5Field.setText("");
			lName1Field.setText(ballot.getCandidates().get(0).getLastName());
			lName2Field.setText("");
			lName3Field.setText("");
			lName4Field.setText("");
			lName5Field.setText("");

		} else {

			ballotNameField.setText(ballot.getBallotName());
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

	/*
	 * Get updates and update DB
	 */
	public void updateBallot(ActionEvent event) throws Exception {
		int ballotId = ballot.getBallotID();

		String[] fNameArray = { fName1Field.getText().toString(), fName2Field.getText().toString(),
				fName3Field.getText().toString(), fName4Field.getText().toString(), fName5Field.getText().toString() };
		String[] lNameArray = { lName1Field.getText().toString(), lName2Field.getText().toString(),
				lName3Field.getText().toString(), lName4Field.getText().toString(), lName5Field.getText().toString() };
		// Connect to DB and Insert into user table.
		try {

			DBConnection.updateBallotQuery(fNameArray, lNameArray, ballotId);
			ConfigureBallotController.editDialogStage.close();

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * Close dialog stage.
	 */
	public void closeDialog() {

		ConfigureBallotController.editDialogStage.close();
	}

}
