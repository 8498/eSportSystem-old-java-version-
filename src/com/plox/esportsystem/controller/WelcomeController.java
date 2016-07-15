package com.plox.esportsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.plox.esportsystem.model.entities.User;
import com.plox.esportsystem.model.entities.UserManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


class WelcomeController implements Initializable, Controller {

	private Parent parent;
    private Scene scene;
    
    @SuppressWarnings("unused")
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
    
    
    private final static WelcomeController INSTANCE = new WelcomeController();
     
	static WelcomeController getInstance(){
        return INSTANCE;
    }
    WelcomeController(){}

    public void signinButton()
    {
    	signinButton.setOnAction((e) -> {
    		/*Iterator<User> iterator = userManager.getAll().iterator();
    		while(iterator.hasNext())
    		{
    			User user = iterator.next();
    			System.out.println(user.getId()+user.getLogin()+user.getPassword()+user.getEmail()+user.getLast_login_date());
    		}*/
    		//System.out.println(userManager.get(2).getId());
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
    public void show(Stage stage,String fxml,String style) {
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
