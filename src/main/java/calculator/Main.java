package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml_css/CalculatorFX.fxml"));
		StackPane gridPane = loader.load();
		Scene scene = new Scene(gridPane,200,290);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kalkulator");
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}

}
