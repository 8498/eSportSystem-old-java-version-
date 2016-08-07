package com.plox.esportsystem.model.entities;

import java.util.ArrayList;

public interface EntityManager{
	
	public ArrayList<?> getAll();
	
	public Object get(int id);
	
	public void create(ArrayList<String> data);
	
	public void edit(int id, String columnName, Object value);
	
	public void delete(int id);

}
