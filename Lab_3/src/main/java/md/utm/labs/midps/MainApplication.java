package md.utm.labs.midps;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class MainApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//stage.setResizable(false);
		FXMLLoader loader = new FXMLLoader();
		loader.setController(new Controller());
		Parent root = (Parent) loader.load(getClass().getResourceAsStream("/metadata.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Scientific calculator v1.0");
		stage.show();
	}
}
