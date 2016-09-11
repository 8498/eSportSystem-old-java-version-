package com.plox.esportsystem.controller.tables;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.controller.ControllerPane;
import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.factories.FactoryProducer;
import com.plox.esportsystem.main.PaneType;
import com.plox.esportsystem.main.Type;
import com.plox.esportsystem.model.entities.Employee;
import com.plox.esportsystem.model.entities.EmployeeManager;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private int selectedItemId;
	
	@Override
	public void generatePane(Pane pane, String fxml, String style, int userID) {
		
		TableView<?> tableview = getTableView();
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxml));
        fxmlloader.setController(this);
        
        try {
			newpane = (Pane) fxmlloader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        newpane.getStylesheets().add(style);
        
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	try {
            		Field field = newSelection.getClass().getDeclaredField("id");
            		field.setAccessible(true);
            		Object value = field.get(newSelection);
            		
            		selectedItemId = (int) value;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        newpane.getChildren().add(tableview);
        newpane.getChildren().add(getAddButton(pane));
        newpane.getChildren().add(getDeleteButton());
        
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
	    
	    employeesTable.setPrefSize(580, 380);
		return employeesTable;
	}

	private Button getAddButton(Pane CenterPane)
	{
		Button addFormButton = new Button("Dodaj pracownika");
		
		addFormButton.autosize();
		addFormButton.setTranslateY(400);
        addFormButton.setOnMouseClicked((e) -> {
        	CenterPane.getChildren().clear();
			AbstractFactory ControllerPaneFactory = FactoryProducer.getFactory(Type.Pane);
			ControllerPane controller = ControllerPaneFactory.getController(PaneType.employeesAddFormPane);
			controller.generatePane(CenterPane, "../../view/addFormPane.fxml", "com/plox/esportsystem/supply/css/application.css", 0);
        });
        
		return addFormButton;
	}
	
	private Button getDeleteButton()
	{
		Button deleteFormButton = new Button("Usuń");
		
		EmployeeManager employeeManager = new EmployeeManager();
		
		deleteFormButton.autosize();
		deleteFormButton.setTranslateY(400);
		deleteFormButton.setTranslateX(200);
		deleteFormButton.setOnMouseClicked((e) -> {
			employeeManager.delete(selectedItemId);
		});
		
		return deleteFormButton;
	}
}
