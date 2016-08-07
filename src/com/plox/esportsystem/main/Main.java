package com.plox.esportsystem.main;

import com.plox.esportsystem.controller.Controller;
import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.factories.FactoryProducer;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage){
		AbstractFactory ControllerFactory = FactoryProducer.getFactory(Type.Controller);
		Controller controller = ControllerFactory.getController(ControllerType.Welcome);
		controller.show(stage, "../view/WelcomeScreen.fxml", "com/plox/esportsystem/supply/application.css", 0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
