package com.plox.esportsystem.factories;

import com.plox.esportsystem.controller.ControllerFactory;
import com.plox.esportsystem.controller.ControllerPaneFactory;
import com.plox.esportsystem.main.Type;

public class FactoryProducer {
	public static AbstractFactory getFactory(Type type) {
		switch (type) {
		  case Controller:
			  return new ControllerFactory();
		  case Pane:
			  return new ControllerPaneFactory();
		  default:
		    return null;
		}
	}
}
