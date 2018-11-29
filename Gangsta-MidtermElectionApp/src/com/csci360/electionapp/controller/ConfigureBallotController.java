package com.csci360.electionapp.controller;

import java.util.ArrayList;

import com.csci360.electionapp.BetterBallot;
import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.Candidate;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigureBallotController {

	// For viewing the current ballots
	@FXML
	public TableView<Ballot> ballotTable;
	@FXML
	public TableView<Candidate> candidateTable;
	@FXML
	private TableColumn<Ballot, String> ballotNameColumn;
	@FXML
	private TableColumn<Candidate, String> candidateFirstNameColumn;
	@FXML
	private TableColumn<Candidate, String> candidateLastNameColumn;

	public static Stage createDialogStage = new Stage();
	public static Stage editDialogStage = new Stage();
	private FXMLLoader editLoader = new FXMLLoader();
	private FXMLLoader createLoader = new FXMLLoader();

	@FXML
	public void initialize() {
		try {

			// Create Ballot Dialog Scene but doesn't show yet.
			createLoader
					.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/AddBallotDialog.fxml"));
			AnchorPane createDialogPane = (AnchorPane) createLoader.load();
			createDialogStage.setTitle("Create Ballot");
			createDialogStage.initModality(Modality.WINDOW_MODAL);
			createDialogStage.initOwner(BetterBallot.primaryStage);
			Scene createDialogScene = new Scene(createDialogPane);
			createDialogStage.setScene(createDialogScene);

			// Create Edit Dialog Scene but doesn't show yet.
			editLoader
					.setLocation(BetterBallot.class.getResource("/com/csci360/electionapp/view/EditBallotDialog.fxml"));
			AnchorPane editDialogPane = (AnchorPane) editLoader.load();
			Scene editDialogScene = new Scene(editDialogPane);
			editDialogStage.setScene(editDialogScene);
			editDialogStage.setTitle("Edit Ballot");
			editDialogStage.initModality(Modality.WINDOW_MODAL);
			editDialogStage.initOwner(BetterBallot.primaryStage);

			// Fill table with available ballots
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
	 * Show the create ballot dialog with "Add" is selected
	 */
	public void showCreateBallot(ActionEvent event) throws Exception {

		CreateBallotController controller = createLoader.getController();
		controller.setDialogStage(createDialogStage);
		createDialogStage.showAndWait();
		showBallotDetails(null);
		updateDetails();

	}

	/*
	 * Show the edit ballot dialog with "edit" is selected
	 */
	public void showEditBallot(ActionEvent event) throws Exception {

		Ballot selectedBallot = ballotTable.getSelectionModel().getSelectedItem();
		EditBallotController controller = editLoader.getController();
		controller.setDialogStage(editDialogStage);
		controller.setBallot(selectedBallot);
		editDialogStage.showAndWait();
		showBallotDetails(null);
		updateDetails();

	}

	/*
	 * Show all candidates for the ballot that is selected
	 */
	public void showBallotDetails(Ballot ballot) {

		if (ballot != null) {
			ArrayList<Candidate> newArray = new ArrayList<Candidate>(ballot.getCandidates());
			ObservableList<Candidate> candidates = FXCollections.observableArrayList(newArray);
			candidateTable.setItems(candidates);
		} else {

			candidateTable.setItems(null);
		}
	}

	/*
	 * Used for updated ballot table when a change occurs.
	 */
	public void updateDetails() throws Exception {

		ballotNameColumn.setCellValueFactory(cellData -> cellData.getValue().ballotNameProperty());
		ballotTable.setItems(DBConnection.getAllBallots());

		candidateFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		candidateLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		// Listen for selection changes and show the person details when changed.
		ballotTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBallotDetails(newValue));
	}

	/*
	 * Delete ballot that is selected.
	 */
	public void deleteBallot(ActionEvent event) throws Exception {

		// Delete Ballot from DB
		int selectedIndex = ballotTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Ballot selectedBallot = ballotTable.getSelectionModel().getSelectedItem();
			ballotTable.getItems().remove(selectedIndex);
			DBConnection.deleteBallot(selectedBallot.getBallotID());
		}

		else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(BetterBallot.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Ballot Selected");
			alert.setContentText("Please select a ballot to delete..");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets()
					.add(getClass().getResource("/com/csci360/electionapp/view/Style.css").toExternalForm());
			dialogPane.getStyleClass().add("alert");
			alert.showAndWait();
		}
		showBallotDetails(null);
		updateDetails();
	}

}
