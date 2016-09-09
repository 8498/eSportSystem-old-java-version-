package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.model.entities.Employee;
import com.plox.esportsystem.model.entities.EmployeeManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class employeesTablePaneController implements ControllerPane, Initializable{

	private final static employeesTablePaneController INSTANCE = new employeesTablePaneController();
	
	public static ControllerPane getInstance() {
		return INSTANCE;
	}
	
	private Pane newpane;
	
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
        
        newpane.getChildren().add(getTableView());
        
        pane.getChildren().add(newpane);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public TableView<?> getTableView()
	{
		EmployeeManager employeeManager = new EmployeeManager();
		
		TableView<Employee> employeesTable = new TableView<Employee>();
		ObservableList<Employee> employees = FXCollections.observableArrayList(employeeManager.getAll());
		
		TableColumn<Employee, String> idCol = new TableColumn<Employee, String>("Id");
	    idCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("id")
	    );
	    TableColumn<Employee, String> firstnameCol = new TableColumn<Employee, String>("Imie");
	    firstnameCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("firstname")
	    );
	    TableColumn<Employee, String> lastnameCol = new TableColumn<Employee, String>("Nazwisko");
	    lastnameCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("lastname")
	    );
	    TableColumn<Employee, String> emailCol = new TableColumn<Employee, String>("Email");
	    emailCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("email")
	    );
	    TableColumn<Employee, String> birthdateCol = new TableColumn<Employee, String>("Data urodzenia");
	    birthdateCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("birthdate")
	    );
	    TableColumn<Employee, String> phoneCol = new TableColumn<Employee, String>("Telefon");
	    phoneCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("phone")
	    );
	    TableColumn<Employee, String> officenameCol = new TableColumn<Employee, String>("Stanowisko");
	    officenameCol.setCellValueFactory(
	            new PropertyValueFactory<Employee, String>("office_name")
	    );
	    
	    employeesTable.getColumns().addAll(idCol, firstnameCol, lastnameCol, emailCol, birthdateCol, phoneCol, officenameCol);

	    employeesTable.setItems(employees);
	    
	    employeesTable.setPrefSize(580, 400);
		return employeesTable;
	}

}
