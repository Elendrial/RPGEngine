package me.hii488.gameObjects.entities;

import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;

public abstract class BaseEntity implements ITexturedObject, IGameObject{
	
	protected String entityName;
	
	public String getEntityName() {
		return entityName;
	}
	
}
