package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.factories.FactoryProducer;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.Type;
import com.plox.esportsystem.model.entities.User;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


class WelcomeController implements Controller {

	private Parent parent;
    private Scene scene;
    
	private Stage stage;
    
    private String fxml;
    private String style;
    UserManager userManager = new UserManager();
    
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signinButton;
    @FXML
    private Label alertLabel;
    
    
    private final static WelcomeController INSTANCE = new WelcomeController();
     
	static WelcomeController getInstance(){
        return INSTANCE;
    }
    WelcomeController(){}

    public void signinButton()
    {
    	signinButton.setOnAction((e) -> {
    		
    		User user = userManager.verify(loginField.getText(), passwordField.getText());
    		
    		if(user != null)
    		{
    			if(loginField.getText().equals("admin"))
    			{
	    			AbstractFactory ControllerFactory = FactoryProducer.getFactory(Type.Controller);
	    			Controller controller = ControllerFactory.getController(ControllerType.AdminDashboard);
	    			((AdminDashboardController) controller).setUserEmail(user.getEmail());
	    			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    			Date date = new Date();
	    			userManager.edit(user.getId(), "lastlogin", dateFormat.format(date));
	    			controller.show(stage, "../view/AdminDashboardScreen.fxml", "com/plox/esportsystem/supply/css/application.css", user.getId());
    			}
    			else
    			{
    				alertLabel.setTextFill(javafx.scene.paint.Color.RED);
        			alertLabel.setText("To nie jest konto administratora");
    			}
    		}
    		else
    		{
    			alertLabel.setTextFill(javafx.scene.paint.Color.RED);
    			alertLabel.setText("błąd");
    		}
    		
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
       
        signinButton();
        
        scene = new Scene(parent);
        
        return scene;
    }
 
    @Override
    public void show(Stage stage,String fxml,String style, int UserID) {
        this.stage = stage;
        this.fxml = fxml;
        this.style = style;
        stage.setScene(generateScene());
        stage.setTitle("eSportSystem");
        stage.setResizable(false);
        stage.show();
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
}
