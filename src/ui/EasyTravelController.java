package ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
import structures.AdjacencyListGraph;

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
	private Pane paneConfiguration;
	@FXML
	private Pane pointsOfInterest;
	@FXML
	private Pane unconnectedZones;
	@FXML
	private Pane PaneCalculateTime;
	@FXML
	private TextField tfVelocity;
	@FXML
	private TextField tfTime;
	@FXML
	private TextField tfDistance;
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
		try {
			String path = tfPath1.getText();
			points = easyTravel.load(path);
			String info = "";
			for (int i = 0; i < points.size(); i++) {
				info += points.get(i).toString() + "\n";
				names.add(points.get(i).getName());
			}
			txtInfo1.setText(info);
			cbDepartureCity.getItems().addAll(names);
			cbDepartureCityTime.getItems().addAll(names);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A ocurrido un error, intentalo de nuevo", "Ups!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	int velocity = 0;

	@FXML
	void showMapCalculateTime(ActionEvent event) {

		if (!tfVelocity.getText().equals("")) {
			try {
				velocity = Integer.parseInt(tfVelocity.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Debes ingresar una velocidad válida", "Ha ocurrido un error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (velocity != 0) {
				TravelMap map = new TravelMap();
				map.makeMap();
				LoadMap lm = new LoadMap(this, map, "la ruta ");
				lm.setDaemon(true);
				lm.start();
			} else {
				JOptionPane.showMessageDialog(null, "La velocidad no puede ser 0", "Ha ocurrido un error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "¡Debes ingresar una velociad!", "Ha ocurrido un error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void generatePathToCalculateTime(TravelMap map) {
		int idOne = searchByName(cbDepartureCityTime.getSelectionModel().getSelectedItem());
		int idTwo = searchByName(cbArrivalCityTime.getSelectionModel().getSelectedItem());
		AdjacencyListGraph<Point> adjacencyListGraph = easyTravel.getList();
		adjacencyListGraph.WeightedMatrix();
		String p = adjacencyListGraph.floydWarshall2(points.get(idOne), idOne, idTwo);
		String[] path = p.split(",");
		int distance = adjacencyListGraph.floydWarshall3(points.get(idOne), idOne, idTwo);
		for (int i = 0; i < path.length - 1; i++) {
			Point point = adjacencyListGraph.getVertex(Integer.parseInt(path[i]));
			if (point != null) {
				LatLng one = new LatLng(point.getLatitude(), point.getLongitude());
				Point p2 = adjacencyListGraph.getVertex(Integer.parseInt(path[i + 1]));
				LatLng two = new LatLng(p2.getLatitude(), p2.getLongitude());
				map.generateArea(one, 400.0);
				map.generateArea(two, 400.0);
				map.generateSimplePath(one, two, false);
			}
		}
		tfDistance.setText(distance + " km");
		double d = (double)distance / velocity;
		DecimalFormat df = new DecimalFormat("#.00");
		tfTime.setText(df.format(d)+" horas");
	}

	@FXML
	void showMapPointsOfInterest(ActionEvent event) {
		TravelMap map = new TravelMap();
		map.makeMap();
		LoadMap lm = new LoadMap(this, map, "el mapa ");
		lm.setDaemon(true);
		lm.start();
	}

	public void drawPoints(TravelMap map) {
		for (int i = 0; i < points.size(); i++) {
			LatLng one = new LatLng(points.get(i).getLatitude(), points.get(i).getLongitude());
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.generateArea(one, 400.0);
		}
		AdjacencyListGraph<Point> adjacencyListGraph = easyTravel.getList();
		for (int i = 0; i < points.size(); i++) {
			for (int j = 0; j < points.size(); j++) {
				boolean connected = adjacencyListGraph.areConnected(points.get(i), points.get(j));
				if (connected) {
					LatLng one = new LatLng(points.get(i).getLatitude(), points.get(i).getLongitude());
					LatLng two = new LatLng(points.get(j).getLatitude(), points.get(j).getLongitude());
					map.generateSimplePath(one, two, false);
				}
			}
		}
	}

	@FXML
	void showMapTravel(ActionEvent event) {
		TravelMap map = new TravelMap();
		map.makeMap();
		LoadMap lm = new LoadMap(this, map, "la mejor ruta ");
		lm.setDaemon(true);
		lm.start();
	}

	public void generatePath(TravelMap map) {

		int idOne = searchByName(cbDepartureCity.getSelectionModel().getSelectedItem());
		int idTwo = searchByName(cbArrivalCity.getSelectionModel().getSelectedItem());

		AdjacencyListGraph<Point> adjacencyListGraph = easyTravel.getList();
		adjacencyListGraph.WeightedMatrix();
		String p = adjacencyListGraph.floydWarshall2(points.get(idOne), idOne, idTwo);
		String[] path = p.split(",");
		for (int i = 0; i < path.length - 1; i++) {
			Point point = adjacencyListGraph.getVertex(Integer.parseInt(path[i]));
			if (p != null) {
				LatLng one = new LatLng(point.getLatitude(), point.getLongitude());
				Point p2 = adjacencyListGraph.getVertex(Integer.parseInt(path[i + 1]));
				LatLng two = new LatLng(p2.getLatitude(), p2.getLongitude());
				map.generateArea(one, 400.0);
				map.generateArea(two, 400.0);
				map.generateSimplePath(one, two, false);
			}
		}
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

	public String getMessage() {
		return lbMessage.getText();
	}

	@FXML
	void showMapUnconnectedZones(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "¡Estás de suerte!" + "\nNo hay zonas inconexas en esta zona",
				"EasyTravel: Colombia", JOptionPane.PLAIN_MESSAGE);

	}

	public int searchByName(String name) {
		int id = -1;
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).getName().equals(name)) {
				id = points.get(i).getId();
			}
		}
		return id;
	}

	@FXML
	void ActivateCredits(ActionEvent event) {
		credits.setVisible(true);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
		paneConfiguration.setVisible(false);
	}

	@FXML
	void ActivateInstructions(ActionEvent event) {
		credits.setVisible(true);
		instructions.setVisible(true);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
		paneConfiguration.setVisible(false);
	}

	@FXML
	void ActivateCalculateTime(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(true);
		paneConfiguration.setVisible(true);
	}

	@FXML
	void ActivateEasyTravel(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(true);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
		paneConfiguration.setVisible(true);
	}

	@FXML
	void ActivatePointsOfInterest(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(true);
		unconnectedZones.setVisible(false);
		PaneCalculateTime.setVisible(false);
		paneConfiguration.setVisible(true);
	}

	@FXML
	void ActivateUnconnectedZones(ActionEvent event) {
		credits.setVisible(false);
		instructions.setVisible(false);
		EasyTravel.setVisible(false);
		pointsOfInterest.setVisible(false);
		unconnectedZones.setVisible(true);
		PaneCalculateTime.setVisible(false);
		paneConfiguration.setVisible(true);
	}

	@FXML
	void Exit(ActionEvent event) {
		System.exit(0);
	}

}
