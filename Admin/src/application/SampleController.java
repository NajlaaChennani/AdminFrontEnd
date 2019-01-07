package application;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jfoenix.controls.JFXButton;

import Models.Agence;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SampleController implements Initializable  {

@FXML
private Pane pnl_allag,pnl_allus,pnl_addag,pnl_addus;

@FXML
private JFXButton btn_allag,btn_allus,btn_addag,btn_addus;

@FXML
private Button ajou_ag,ajou_us,supp_us,supp_ad;

@FXML
private TextField name, adresse;

@FXML
private TableView<Agence> allagences;

@FXML
private TableColumn<Agence, Long> nomagence, adagence;

@FXML
private TableView<User> allagents;

@FXML
private TableColumn<User, Long> nameuser, username, phone, aduser, age, soltel;

@FXML
private TextField us_nom ,us_username,us_phone,us_addr,us_password, us_soldetel, us_age;

@FXML 
private TextField txt_log,txt_mdp;

@FXML 
private Button btn_login, btn_upagence;

@FXML
private void handleButtonAction(ActionEvent event) {
	if(event.getSource()==btn_allag) {
	    this.reloadAgences();
		pnl_allag.toFront();
	}
	else if(event.getSource()==btn_allus) {
		reloadAgents();
		pnl_allus.toFront();	
	}
	else if(event.getSource()==btn_addag) {
		pnl_addag.toFront();	
	}
	else if(event.getSource()==btn_addus) {
		pnl_addus.toFront();	
	}
	
		
}



@FXML
private void ajoutagence(ActionEvent event) {
	JSONObject json = new JSONObject();
	json.put("name", name.getText());
	json.put("adresse", adresse.getText());
	try {
		URL url = new URL("http://localhost:8080/api/banking/addAgence");
		HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		wr.flush();
		wr.close();
		conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
	}catch (Exception ex)
	{
		ex.printStackTrace();
	}
	name.clear();
	adresse.clear();
}


@FXML
private void ajoutagent(ActionEvent event) {
	JSONObject json = new JSONObject();
	json.put("name", us_nom.getText());
	json.put("username", us_username.getText());
	json.put("password", us_password.getText());
	json.put("phone", us_phone.getText());
	json.put("address", us_addr.getText());
	json.put("soldetelephonique", us_soldetel.getText());
	json.put("age", us_age.getText());
	try {
		URL url = new URL("http://localhost:8080/api/banking/addAgent");
		HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		wr.flush();
		wr.close();
		conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
	}catch (Exception ex)
	{
		ex.printStackTrace();
	}
	us_nom.clear();
	us_age.clear();
	us_password.clear();
	us_soldetel.clear();
	us_username.clear();
	us_phone.clear();
	us_addr.clear();
}

@FXML
private void deleteAgent(ActionEvent event) {
	try {
	User agent = allagents.getSelectionModel().getSelectedItem();
	System.out.println(agent.getUsername());
	URL url = new URL("http://localhost:8080/api/banking/deleteAgent/" + agent.getUsername());
	HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
	conn.setUseCaches(false);
	conn.setDoInput(true);
	conn.setDoOutput(true);
	conn.setRequestMethod("DELETE");
	conn.getInputStream();
	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 reloadAgents();
	}catch (Exception ex)
	{
		ex.printStackTrace();
	}
}

@FXML
private void deleteAgence(ActionEvent event) {
	try {
		Agence agence = allagences.getSelectionModel().getSelectedItem();
		System.out.println(agence.getName());
		URL url = new URL("http://localhost:8080/api/banking/deleteAgence/" + agence.getName());
		HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("DELETE");
		conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		 reloadAgences();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}


public void reloadAgences() {
	try {
		URL url = new URL("http://localhost:8080/api/banking/allagences");
		HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(in.readLine());
		
		ObservableList<Agence> agencedata = FXCollections.observableArrayList();
		
			for(Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) parser.parse(object.toString());
				System.out.println(jsonObject.toJSONString());
				agencedata.add(new Agence(jsonObject.get("name").toString(), jsonObject.get("adresse").toString()));
				nomagence.setCellValueFactory(new PropertyValueFactory<Agence, Long>("name"));
				adagence.setCellValueFactory(new PropertyValueFactory<Agence, Long>("adresse"));
			}		
			allagences.setItems(agencedata);
		}catch(Exception ex)
	{
		ex.printStackTrace();
	}
}



public void reloadAgents() {
	try {
		URL url = new URL("http://localhost:8080/api/banking/allagents");
		HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(in.readLine());
		
		ObservableList<User> agentdata = FXCollections.observableArrayList();
		
			for(Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) parser.parse(object.toString());
				System.out.println(jsonObject.toJSONString());
				agentdata.add(new User(jsonObject.get("name").toString(), jsonObject.get("username").toString(), 
						Integer.parseInt(jsonObject.get("age").toString()), jsonObject.get("password").toString(), jsonObject.get("phone").toString()
						,jsonObject.get("address").toString(), Double.parseDouble(jsonObject.get("soldetelephonique").toString())));
				nameuser.setCellValueFactory(new PropertyValueFactory<User, Long>("name"));
				username.setCellValueFactory(new PropertyValueFactory<User, Long>("username"));
				age.setCellValueFactory(new PropertyValueFactory<User, Long>("age"));
				aduser.setCellValueFactory(new PropertyValueFactory<User, Long>("address"));
				phone.setCellValueFactory(new PropertyValueFactory<User, Long>("phone"));
				soltel.setCellValueFactory(new PropertyValueFactory<User, Long>("soldetelephonique"));
			}		
			allagents.setItems(agentdata);
		}catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
}
