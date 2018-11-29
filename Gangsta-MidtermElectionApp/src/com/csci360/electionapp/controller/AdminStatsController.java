package com.csci360.electionapp.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminStatsController {

	@FXML
	public TableView<Ballot> ballotTable;
	@FXML
	private TableColumn<Ballot, String> ballotNameColumn;
	@FXML
	private PieChart pieChart;
	@FXML
	private GridPane  gridPane;
	


	@FXML
	public void initialize() {
		try {
			// Fill table with availble ballots
			updateDetails();
			showBallotStats(null);

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
	 * Show all candidates for the ballot that is selected
	 */
	public void showBallotStats(Ballot ballot) {
		gridPane.getChildren().clear();
		if (ballot != null) {

			try {
				
				pieChart.setTitle(ballot.getBallotName() + " Standings");
				pieChart.resize(1000, 1000);
				pieChart.setOpacity(100);
				pieChart.setData(DBConnection.getCurrentStandingsPieChart((ballot.getBallotID())));
				
				Map<String, Integer> ballotData = DBConnection.getCurrentStandings(ballot.getBallotID());
				Set<String> keySet = ballotData.keySet();
				Iterator<String> keySetIterator = keySet.iterator();
				int i = 1;
				
				while(keySetIterator.hasNext()) {
					Label tempNameLabel = new Label();
					Label tempCountLabel = new Label();
					String key = keySetIterator.next();
					tempNameLabel.setText(key);
					tempCountLabel.setText(ballotData.get(key).toString());
					tempNameLabel.setTextFill(Color.web("#9ba5ac"));
					tempCountLabel.setTextFill(Color.web("#9ba5ac"));

					gridPane.add(tempNameLabel , 0, i);
					gridPane.add(tempCountLabel, 1, i);
					i++;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pieChart.setOpacity(0);
		}
	}

	/*
	 * Used for updated ballot table when a change occurs.
	 */
	public void updateDetails() throws Exception {

		ballotNameColumn.setCellValueFactory(cellData -> cellData.getValue().ballotNameProperty());
		ballotTable.setItems(DBConnection.getAllBallots());

		// Listen for selection changes and show the person details when changed.
		ballotTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBallotStats(newValue));
	}

}
