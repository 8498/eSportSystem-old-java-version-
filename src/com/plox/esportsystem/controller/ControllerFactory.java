package com.plox.esportsystem.controller;

import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.PaneType;


public class ControllerFactory extends AbstractFactory {

	@Override
	public Controller getController(ControllerType type) {
		@SuppressWarnings("unused")
		Controller controller;
		switch (type)
		{
			case Welcome:
				return controller = WelcomeController.getInstance();
			case AdminDashboard:
				return controller = AdminDashboardController.getInstance();
			default:
				return null;
		}
	}

	@Override
	public ControllerPane getController(PaneType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
