package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.factories.FactoryProducer;
import com.plox.esportsystem.main.PaneType;
import com.plox.esportsystem.main.Type;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboardController implements Controller {

	private final static AdminDashboardController INSTANCE = new AdminDashboardController();
	
	public static Controller getInstance() {
		return INSTANCE;
	}

	private String fxml;
	private Parent parent;
	private String style;
	private Scene scene;
	@SuppressWarnings("unused")
	private Stage stage;
	private String userEmail;
	private int userID;
	
	@FXML
	private VBox TablesVbox;
	@FXML
	private Pane CenterPane;
	@FXML
	public Label userEmailLabel;
	@FXML
	private Label changeEmailLabel;
	@FXML
	private Label changePasswordLabel;
	@FXML
	private Label usersTableLabel;
	@FXML
	private Label employeesTableLabel;
	
	public AdminDashboardController(){}
	
	public void changeEmail()
	{
		changeEmailLabel.setOnMouseClicked((e) -> {
			CenterPane.getChildren().clear();
			AbstractFactory ControllerPaneFactory = FactoryProducer.getFactory(Type.Pane);
			ControllerPane controller = ControllerPaneFactory.getController(PaneType.ChangeEmail);
			controller.generatePane(CenterPane, "../view/changeEmailPane.fxml", "com/plox/esportsystem/supply/css/application.css", userID);
    	});	
	}
	public void changePassword()
	{
		changePasswordLabel.setOnMouseClicked((e) -> {
			CenterPane.getChildren().clear();
			AbstractFactory ControllerPaneFactory = FactoryProducer.getFactory(Type.Pane);
			ControllerPane controller = ControllerPaneFactory.getController(PaneType.ChangePassword);
			controller.generatePane(CenterPane, "../view/changePasswordPane.fxml", "com/plox/esportsystem/supply/css/application.css", userID);
		});
	}
	public void usersTable()
	{
		usersTableLabel.setOnMouseClicked((e) -> {
			System.out.println("asd");
			CenterPane.getChildren().clear();
			AbstractFactory ControllerPaneFactory = FactoryProducer.getFactory(Type.Pane);
			ControllerPane controller = ControllerPaneFactory.getController(PaneType.usersTablePane);
			controller.generatePane(CenterPane, "../view/usersTablePane.fxml", "com/plox/esportsystem/supply/css/application.css", userID);
		});
	}
	public void employeesTable()
	{
		employeesTableLabel.setOnMouseClicked((e) -> {
			System.out.println("asd");
			CenterPane.getChildren().clear();
			AbstractFactory ControllerPaneFactory = FactoryProducer.getFactory(Type.Pane);
			ControllerPane controller = ControllerPaneFactory.getController(PaneType.employeesTablePane);
			controller.generatePane(CenterPane, "../view/employeesTablePane.fxml", "com/plox/esportsystem/supply/css/application.css", userID);
		});
	}
	@Override
	public Scene generateScene() {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxml));
        fxmlloader.setController(this);
        try {
            parent = (Parent) fxmlloader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        parent.getStylesheets().add(style);
        
        changeEmail();
        
        changePassword();
        
        usersTable();
        
        employeesTable();
        
        scene = new Scene(parent);
        
        return scene;
	}

	@Override
	public void show(Stage stage, String fxml, String style, int userID) {
		this.stage = stage;
        this.fxml = fxml;
        this.style = style;
        this.userID = userID;
        stage.setScene(generateScene());
        stage.setTitle("eSportSystem");
        userEmailLabel.setText(getUserEmail());
        stage.setResizable(false);
        stage.show();
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
	
    }

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
