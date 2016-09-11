package com.plox.esportsystem.controller.forms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.plox.esportsystem.controller.ControllerPane;
import com.plox.esportsystem.model.entities.RoleManager;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class usersAddFormPaneController implements Initializable, ControllerPane {

	private final static usersAddFormPaneController INSTANCE = new usersAddFormPaneController();
	
	public static ControllerPane getInstance() {
		return INSTANCE;
	}
	
	private Pane newpane;
	
	private Label loginLabel = getLabelForm("Login:",130,80);
	private TextField loginField = getTextfieldForm(200,80);
	private Label emailLabel = getLabelForm("Email:",130,110);
	private TextField emailField = getTextfieldForm(200,110);
	private Label passwordLabel = getLabelForm("Hasło:",130,140);
	private PasswordField passwordField = getPasswordfieldForm(200,140);
	private Label roleLabel = getLabelForm("Rola:",130,170);
	
	private Button submitButton = getSubmitButton(240,300);
	
	UserManager usermanager = new UserManager();
	RoleManager rolemanager = new RoleManager();
	
	private ComboBox<?> roleCombo = getComboboxForm(rolemanager.getAllName(),200,170);
	
	@Override
	public void generatePane(Pane pane, String fxml, String style, int userID) {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxml));
        fxmlloader.setController(this);
        
        try {
			newpane = (Pane) fxmlloader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        newpane.getStylesheets().add(style);
        
        newpane.getChildren().addAll(
        		loginLabel,
        		loginField,
        		emailLabel,
        		emailField,
        		passwordLabel,
        		passwordField,
        		roleLabel,
        		roleCombo,
        		submitButton);
        
        addUser();
        
        pane.getChildren().add(newpane);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	private Label getLabelForm(String text, int posx, int posy) 
	{
		Label label = new Label(text);
		
		label.setLayoutX(posx);
		label.setLayoutY(posy);
		
		return label;
	}
	
	private TextField getTextfieldForm(int posx, int posy)
	{
		TextField textfield = new TextField();
		
		textfield.setLayoutX(posx);
		textfield.setLayoutY(posy);
		
		return textfield;
	}
	
	private PasswordField getPasswordfieldForm(int posx, int posy)
	{
		PasswordField passwordfield = new PasswordField();
		
		passwordfield.setLayoutX(posx);
		passwordfield.setLayoutY(posy);
		
		return passwordfield;
	}
	
	private ComboBox<String> getComboboxForm(ArrayList<?> arraylist, int posx, int posy)
	{
		ComboBox<String> combobox = new ComboBox<String>();
		
		ObservableList<String> bbservablelist = (ObservableList<String>) FXCollections.observableArrayList(arraylist);
		
		combobox.setItems(bbservablelist);
		combobox.setLayoutX(posx);
		combobox.setLayoutY(posy);
		
		return combobox;
	}
	
	private Button getSubmitButton(int posx, int posy) 
	{
		Button button = new Button("Dodaj");
		
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		
		return button;
	}
	
	private void addUser()
	{
		submitButton.setOnMouseClicked((e) -> {
			
			String login = loginField.getText();
			String email = emailField.getText();
			String password = passwordField.getText();
			String role = roleCombo.getValue().toString();
			
			ArrayList<String> data = new ArrayList<String>();
			
			data.add(login);
			data.add(email);
			data.add(password);
			data.add(role);
			
			usermanager.create(data);
		});
	}

}
