package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.services.Authentication;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class ChangePasswordPaneController implements ControllerPane {

	private final static ChangePasswordPaneController INSTANCE = new ChangePasswordPaneController();
	
	public static ControllerPane getInstance() {
		return INSTANCE;
	}

	UserManager userManager = new UserManager();
	Authentication auth = new Authentication();
	
	private int userID;
	private Pane newpane;
	
	@FXML
	private Button submitButton;
	@FXML
	private PasswordField passwordField;
	
	public void changePassword()
	{
		submitButton.setOnAction((e) -> {
			userManager.edit(userID, "password", auth.hash(passwordField.getText()));
		});
	}
	@Override
	public void generatePane(Pane pane, String fxml, String style, int userID) {
		this.userID = userID;
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxml));
        fxmlloader.setController(this);
        
        try {
			newpane = (Pane) fxmlloader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        newpane.getStylesheets().add(style);
        
        changePassword();
        
        pane.getChildren().add(newpane);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
