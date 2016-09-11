package com.plox.esportsystem.controller;


import com.plox.esportsystem.controller.forms.*;
import com.plox.esportsystem.controller.tables.*;
import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.PaneType;


public class ControllerPaneFactory extends AbstractFactory {

	@Override
	public Controller getController(ControllerType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControllerPane getController(PaneType type) {
		ControllerPane controllerPane = null;
		try{
		switch (type)
		{
			case ChangeEmail:
				return controllerPane = ChangeEmailPaneController.getInstance();
			case ChangePassword:
				return controllerPane = ChangePasswordPaneController.getInstance();
			case usersTablePane:
				return controllerPane = userTablePaneController.getInstance();
			case employeesTablePane:
				return controllerPane = employeesTablePaneController.getInstance();
			case officesTablePane:
				return controllerPane = officesTablePaneController.getInstance();
			case officesAddFormPane:
				return controllerPane = officesAddFormPaneController.getInstance();
			case employeesAddFormPane:
				return controllerPane = employeesAddFormPaneController.getInstance();
			case usersAddFormPane:
				return controllerPane = usersAddFormPaneController.getInstance();
			default:
				
		}
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return controllerPane;
	}

}
