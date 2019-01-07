package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button btn_login;

	@FXML
	private TextField txt_log;
	
	@FXML
	private PasswordField txt_mdp;
	
	@FXML
	private void loginAction(ActionEvent event) {
		if(txt_log.getText() != null && txt_mdp.getText() != null)
		{
		try {
			URL url = new URL("http://localhost:8080/api/banking/loginAdmin/" + txt_log.getText() + "/" + txt_mdp.getText());
			HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
	
			if(in.readLine().equalsIgnoreCase("success")) {
				System.out.println("jhgjhghj");

				Stage stage = new Stage();
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
					root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				} catch(Exception e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setAlwaysOnTop(true);
				stage.setIconified(false);
				stage.centerOnScreen();
				stage.setResizable(false);
				stage.setMaximized(false);
				stage.show();
				
				Stage stagecourant = (Stage) btn_login.getScene().getWindow();
		
			    stagecourant.close();
			}	
			else
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Vous n'avez pas réussi !");
			    alert.setHeaderText("Veuillez réessayer");
			    alert.setContentText("Accéder à cet espace ne vous est pas permi !");
			    alert.showAndWait();	
			}
			}catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Vous n'avez pas réussi !");
		    alert.setHeaderText("Veuillez réessayer");
		    alert.setContentText("Tous les champs doivent être remplis");
		    alert.showAndWait();	
		}
	}
}
