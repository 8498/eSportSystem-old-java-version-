package com.plox.esportsystem.controller;

import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.PaneType;


public class ControllerFactory extends AbstractFactory {

	@Override
	public Controller getController(ControllerType type) {
		Controller controller = null;
		try{
			switch (type)
			{
				case Welcome:
					return controller = WelcomeController.getInstance();
				case AdminDashboard:
					return controller = AdminDashboardController.getInstance();
				default:
					
			}
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return controller;
	}

	@Override
	public ControllerPane getController(PaneType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
