package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("EasyTravel.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Easy Traveler");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
