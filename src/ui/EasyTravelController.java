package ui;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.*;

public class EasyTravelController {


    @FXML
    private Pane credits;
    @FXML
    private Pane instructions;
    @FXML
    private Pane EasyTravel;
    @FXML
    private ComboBox<?> cbDepartureCity;
    @FXML
    private ComboBox<?> cbArrivalCity;
    @FXML
    private Pane pointsOfInterest;
    @FXML
    private Pane unconnectedZones;
    @FXML
    private Pane PaneCalculateTime;
    @FXML
    private TextField tfVelocity;
    @FXML
    private ComboBox<?> cbDepartureCityTime;
    @FXML
    private ComboBox<?> cbArrivalCityTime;
    @FXML
    private TextField tfPath1;
    @FXML
    private TextArea txtInfo1; 
    
    ArrayList<Point> points;
    EasyTravel easyTravel;
    
    public void initialize() {
    	points = new ArrayList<>();
    	easyTravel = new EasyTravel();
    }

    @FXML
    void SearchFileData(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	tfPath1.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    @FXML
    void loadFileData(ActionEvent event) {
    	String path = tfPath1.getText();
    	points = easyTravel.load(path);
    	String info = txtInfo1.getText();
    	for (int i = 0; i < points.size(); i++) {
			info+=points.get(i).toString()+"\n";
		}
    	txtInfo1.setText(info);
    }

    @FXML
    void showMapCalculateTime(ActionEvent event) {
    	
    }

    @FXML
    void showMapPointsOfInterest(ActionEvent event) {
    	
    }

    @FXML
    void showMapTravel(ActionEvent event) {
    	TravelMap example = new TravelMap("Colombia");
		example.generateMarker(example.darMap().getCenter());
		example.generateArea(example.darMap().getCenter(), 400.0);
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
