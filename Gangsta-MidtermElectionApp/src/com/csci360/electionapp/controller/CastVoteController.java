package com.csci360.electionapp.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.Candidate;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CastVoteController {

	// For viewing the current ballots and ballot candidates
	@FXML
	public TableView<Ballot> ballotTable;
	@FXML
	private TableColumn<Ballot, String> ballotNameColumn;
	@FXML
	public TableView<Candidate> candidateTable;
	@FXML
	private TableColumn<Candidate, String> candidateFirstNameColumn;
	@FXML
	private TableColumn<Candidate, String> candidateLastNameColumn;

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
	 * Show available ballots that a voter are eligible to vote for
	 */
	public void updateDetails() throws Exception {

		int userID = LoginScreenController.getUserLogedIn().getVoterID();
		ballotTable.setItems(DBConnection.getVotersAvailableBallots(userID));
		ballotNameColumn.setCellValueFactory(cellData -> cellData.getValue().ballotNameProperty());

		// Listen for selection changes and show the person details when changed.
		ballotTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBallotDetails(newValue));
	}

	/*
	 * Show the ballot candidates
	 */
	public void showBallotDetails(Ballot ballot) {

		if (ballot != null) {

			ArrayList<Candidate> newArray = new ArrayList<Candidate>(ballot.getCandidates());
			ObservableList<Candidate> candidates = FXCollections.observableArrayList(newArray);
			candidateTable.setItems(candidates);

			candidateFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
			candidateLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		} else {
			candidateTable.setItems(null);
		}
	}

	/*
	 * Cast vote for what candidate is selected, then delete the ballot from the
	 * table so user can't vote more then once. Insert into DB
	 */
	public void castVote() {

		int selectedIndex = candidateTable.getSelectionModel().getSelectedIndex();
		Candidate candidate = candidateTable.getSelectionModel().getSelectedItem();
		Ballot ballot = ballotTable.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {

			int candidateID = candidate.getCandidateID();
			int ballotID = ballot.getBallotID();
			LoginScreenController.getUserLogedIn();
			int userID = User.getVoterID();

			try {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(BetterBallot.getPrimaryStage());
				alert.setTitle("Vote Confirmation");
				alert.setHeaderText("Please Confirm Your Selection");
				alert.setContentText("Are you sure you want to vote for " + candidate.getFirstName() + " "
						+ candidate.getLastName() + " for " + ballot.getBallotName() + "?");
				ButtonType buttonTypeVote = new ButtonType("Vote");
				ButtonType buttonTypeBack = new ButtonType("Back");
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets()
						.add(getClass().getResource("/com/csci360/electionapp/view/Style.css").toExternalForm());
				dialogPane.getStyleClass().add("alert");
				alert.getButtonTypes().setAll(buttonTypeVote, buttonTypeBack);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == buttonTypeVote) {
					DBConnection.castVoteQuery(userID, ballotID, candidateID);
					showBallotDetails(null);
					updateDetails();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(BetterBallot.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Candidate Selected");
			alert.setContentText("Please select a candidate to cast vote!");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets()
					.add(getClass().getResource("/com/csci360/electionapp/view/Style.css").toExternalForm());
			dialogPane.getStyleClass().add("alert");
			alert.showAndWait();
		}
	}

}
