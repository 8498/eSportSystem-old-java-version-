package com.plox.esportsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Controller extends Initializable{
	
	Scene generateScene();
    void show(Stage stage,String fxml, String style, int userID);
    public default void initialize(URL location, ResourceBundle resources){}
}
