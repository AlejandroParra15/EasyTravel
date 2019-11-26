package ui;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.teamdev.jxmaps.LatLng;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.*;
import threads.LoadMap;

public class EasyTravelController {

	@FXML
	private Pane credits;
	@FXML
	private Label lbMessage;
	@FXML
	private Pane instructions;
	@FXML
	private Pane EasyTravel;
	@FXML
	private ComboBox<String> cbDepartureCity;
	@FXML
	private ComboBox<String> cbArrivalCity;
	@FXML
	private Pane pointsOfInterest;
	@FXML
	private Pane unconnectedZones;
	@FXML
	private Pane PaneCalculateTime;
	@FXML
	private TextField tfVelocity;
	@FXML
	private ComboBox<String> cbDepartureCityTime;
	@FXML
	private ComboBox<String> cbArrivalCityTime;
	@FXML
	private TextField tfPath1;
	@FXML
	private TextArea txtInfo1;

	ArrayList<Point> points;
	ObservableList<String> names;
	EasyTravel easyTravel;

	public void initialize() {
		points = new ArrayList<>();
		easyTravel = new EasyTravel();
		cbDepartureCity.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				cbArrivalCity.getItems().clear();
				cbArrivalCity.getItems().addAll(names);
				cbArrivalCity.getItems().remove(newValue);
			}
		});
		cbDepartureCityTime.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				cbArrivalCityTime.getItems().clear();
				cbArrivalCityTime.getItems().addAll(names);
				cbArrivalCityTime.getItems().remove(newValue);
			}
		});
	}

	@FXML
	void SearchFileData(ActionEvent event) {
		names = FXCollections.observableArrayList();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			tfPath1.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	@FXML
	void loadFileData(ActionEvent event) {
		String path = tfPath1.getText();
		points = easyTravel.load(path);
		String info = "";
		for (int i = 0; i < points.size(); i++) {
			info += points.get(i).toString() + "\n";
			names.add(points.get(i).getName());
		}
		txtInfo1.setText(info);
		cbDepartureCity.getItems().addAll(names);
	}

	@FXML
	void showMapCalculateTime(ActionEvent event) {

	}

	@FXML
	void showMapPointsOfInterest(ActionEvent event) {

	}

	@FXML
	void showMapTravel(ActionEvent event) {
		TravelMap map = new TravelMap("Colombia");
		LoadMap lm = new LoadMap(this, map);
		lm.start();
		lm.setDaemon(true);
	}
	
	public void generatePath(TravelMap map) {
		int idOne = cbDepartureCity.getSelectionModel().getSelectedIndex();
		int idTwo = cbArrivalCity.getSelectionModel().getSelectedIndex() + 1;
		LatLng one = new LatLng(points.get(idOne).getLatitude(), points.get(idOne).getLongitude());
		LatLng two = new LatLng(points.get(idTwo).getLatitude(), points.get(idTwo).getLongitude());
		map.generateMarker(one);
		map.generateArea(one, 400.0);
		map.generateArea(two, 400.0);
		map.generateSimplePath(one, two, false);
	}

	public void addMessage(String msg) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				lbMessage.setText(lbMessage.getText() + msg);
			}
		});
	}
	
	public void setMessage(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lbMessage.setText(msg);
			}
		});
	}

	@FXML
	void showMapUnconnectedZones(ActionEvent event) {

	}

	@FXML
	void ActivateCredits(ActionEvent event) {
		credits.setVisible(true);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
	}

	@FXML
	void ActivateInstructions(ActionEvent event) {
		credits.setVisible(true);
		instructions.setVisible(true);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
	}

	@FXML
	void ActivateCalculateTime(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(true);
	}

	@FXML
	void ActivateEasyTravel(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(true);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
	}

	@FXML
	void ActivatePointsOfInterest(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(true);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
	}

	@FXML
	void ActivateUnconnectedZones(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(true);
		PaneCalculateTime.setVisible(false);
	}

	@FXML
	void Exit(ActionEvent event) {
		System.exit(0);
	}

}
