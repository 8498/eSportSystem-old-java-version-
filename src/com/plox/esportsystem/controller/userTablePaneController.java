package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.model.entities.User;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class userTablePaneController implements ControllerPane, Initializable {

	private final static userTablePaneController INSTANCE = new userTablePaneController();
	
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
		UserManager userManager = new UserManager();
		
		TableView<User> usersTable = new TableView<User>();
	    ObservableList<User> user = FXCollections.observableArrayList(userManager.getAll());
	    TableColumn<User, String> idCol = new TableColumn<User, String>("Id");
	    idCol.setCellValueFactory(
	            new PropertyValueFactory<User, String>("id")
	    );
	    TableColumn<User, String> loginCol = new TableColumn<User, String>("Login");
	    loginCol.setCellValueFactory(
	            new PropertyValueFactory<User, String>("login")
	    );
	    TableColumn<User, String> passwordCol = new TableColumn<User, String>("Password");
	    passwordCol.setCellValueFactory(
	            new PropertyValueFactory<User, String>("password")
	    );
	    TableColumn<User, String> lastloginCol = new TableColumn<User, String>("Lastlogin");
	    lastloginCol.setCellValueFactory(
	            new PropertyValueFactory<User, String>("last_login_date")
	    );
	    
        
	    usersTable.getColumns().addAll(idCol, loginCol, passwordCol, lastloginCol);

	    usersTable.setItems(user);
	    
	    usersTable.setPrefSize(580, 400);
		return usersTable;
	}
}
