package com.plox.esportsystem.factories;

import com.plox.esportsystem.controller.Controller;
import com.plox.esportsystem.main.ControllerType;
import com.plox.esportsystem.main.EntityType;
import com.plox.esportsystem.model.entities.Entity;

public abstract class AbstractFactory {
	public abstract Controller getController(ControllerType type);
	public abstract Entity getEntity(EntityType type);
}
