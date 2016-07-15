package com.plox.esportsystem.controller;

import com.plox.esportsystem.factories.AbstractFactory;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.EntityType;
import com.plox.esportsystem.model.entities.Entity;

public class ControllerFactory extends AbstractFactory {

	@Override
	public Controller getController(ControllerType type) {
		@SuppressWarnings("unused")
		Controller controller;
		switch (type)
		{
			case Welcome:
				return controller = WelcomeController.getInstance();
			default:
				return null;
		}
	}

	@Override
	public Entity getEntity(EntityType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
