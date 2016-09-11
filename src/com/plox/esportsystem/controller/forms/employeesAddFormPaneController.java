package com.plox.esportsystem.controller.forms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.plox.esportsystem.controller.ControllerPane;
import com.plox.esportsystem.model.entities.EmployeeManager;
import com.plox.esportsystem.model.entities.OfficeManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class employeesAddFormPaneController implements ControllerPane, Initializable {

	private final static employeesAddFormPaneController INSTANCE = new employeesAddFormPaneController();
	public static ControllerPane getInstance() {
		return INSTANCE;
	}
	
	private Pane newpane;
	
	private Label firstnameLabel = getLabelForm("Imię:",130,80);
	private TextField firstnameField = getTextfieldForm(200,80);
	private Label lastnameLabel = getLabelForm("Nazwisko:",130,110);
	private TextField lastnameField = getTextfieldForm(200,110);
	private Label emailLabel = getLabelForm("Email:",130,140);
	private TextField emailField = getTextfieldForm(200,140);
	private Label birthdateLabel = getLabelForm("Data ur:",130,170);
	private DatePicker birthdateField = getDatepickerForm(200,170);
	private Label phoneLabel = getLabelForm("Telefon:",130,200);
	private TextField phoneField = getTextfieldForm(200,200);
	private Label officenameLabel = getLabelForm("Stanowisko:",130,230);
	
	private Button submitButton = getSubmitButton(240,300);
	
	EmployeeManager employeemanager = new EmployeeManager();
	OfficeManager officemanager = new OfficeManager();
	
	private ComboBox<?> officenameCombo = getComboboxForm(officemanager.getAllName(),200,230);
	
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
        		firstnameLabel,
        		firstnameField,
        		lastnameLabel,
        		lastnameField,
        		emailLabel,
        		emailField,
        		birthdateLabel,
        		birthdateField,
        		phoneLabel,
        		phoneField,
        		officenameLabel,
        		officenameCombo,
        		submitButton);
        
        addEmployee();
        
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
	private DatePicker getDatepickerForm(int posx, int posy)
	{
		DatePicker datepicker = new DatePicker();
		
		datepicker.setLayoutX(posx);
		datepicker.setLayoutY(posy);
		
		return datepicker;
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
	
	private void addEmployee()
	{
		submitButton.setOnMouseClicked((e) -> {
			
			String firstname = firstnameField.getText();
			String lastname = lastnameField.getText();
			String email = emailField.getText();
			String birthdate = birthdateField.getValue().toString();
			String phone = phoneField.getText();
			String office = officenameCombo.getValue().toString();
			
			ArrayList<String> data = new ArrayList<String>();
			
			data.add(firstname);
			data.add(lastname);
			data.add(email);
			data.add(birthdate);
			data.add(phone);
			data.add(office);
			
			employeemanager.create(data);
			
		});
	}
}
