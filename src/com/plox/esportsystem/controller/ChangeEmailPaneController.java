package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.model.entities.UserManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ChangeEmailPaneController implements ControllerPane {

	private final static ChangeEmailPaneController INSTANCE = new ChangeEmailPaneController();
	
	public static ControllerPane getInstance() {
		return INSTANCE;
	}

	UserManager userManager = new UserManager();
	
	Pane newpane;
	private int userID;
	
	@FXML
	Button submitButton;
	@FXML
	TextField emailField;
	public void changeEmail()
	{
		submitButton.setOnAction((e) -> {
			userManager.edit(userID, "email", emailField.getText());
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
        
        changeEmail();
        
        pane.getChildren().add(newpane);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
