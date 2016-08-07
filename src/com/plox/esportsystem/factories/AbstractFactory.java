package com.plox.esportsystem.factories;

import com.plox.esportsystem.controller.Controller;
import com.plox.esportsystem.controller.ControllerPane;
import com.plox.esportsystem.main.PaneType;
import com.plox.esportsystem.main.ControllerType;

public abstract class AbstractFactory {
	public abstract Controller getController(ControllerType type);
	public abstract ControllerPane getController(PaneType type);
}
