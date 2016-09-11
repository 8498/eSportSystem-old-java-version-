package com.plox.esportsystem.controller.forms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.plox.esportsystem.controller.ControllerPane;
import com.plox.esportsystem.model.entities.OfficeManager;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class officesAddFormPane implements ControllerPane, Initializable {

	private final static officesAddFormPane INSTANCE = new officesAddFormPane();
	
	public static ControllerPane getInstance() {
		return INSTANCE;
	}

	private Pane newpane;
	
	private Label nameLabel = getLabelForm("Nazwa:",130,80);
	private TextField nameField = getTextfieldForm(200,80);
	
	private Button submitButton = getSubmitButton(240,300);
	
	OfficeManager officemanager = new OfficeManager();
	
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
        		nameLabel,
        		nameField,
        		submitButton);
        
        addOffice();
        
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
	
	private Button getSubmitButton(int posx, int posy) 
	{
		Button button = new Button("Dodaj");
		
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		
		return button;
	}
	
	private void addOffice()
	{
		submitButton.setOnMouseClicked((e) -> {
			
			String name = nameField.getText();
			
			ArrayList<String> data = new ArrayList<String>();
			data.add(name);
			
			officemanager.create(data);
			
		});
	}

}
