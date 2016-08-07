package com.plox.esportsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public interface ControllerPane extends Initializable {
	public void generatePane(Pane pane, String fxml, String style, int userID);

	public void initialize(URL arg0, ResourceBundle arg1);
}
