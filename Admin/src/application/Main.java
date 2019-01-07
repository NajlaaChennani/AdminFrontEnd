package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			  System.out.println(getClass().getResource("Login.fxml").getPath());
	            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

	            primaryStage.setTitle("Bienvenue admin !");
	            root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            primaryStage.setScene(new Scene(root));
	            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
