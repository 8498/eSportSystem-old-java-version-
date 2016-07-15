package com.plox.esportsystem.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Controller {
	
	Scene generateScene();
    void show(Stage stage,String fxml, String style);
}
