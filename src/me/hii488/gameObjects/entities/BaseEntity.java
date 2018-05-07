package me.hii488.gameObjects.entities;

import me.hii488.dataTypes.VectorBox;
import me.hii488.gameObjects.levels.BaseLevel;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;
import me.hii488.interfaces.ITickable;

public abstract class BaseEntity implements ITexturedObject, IGameObject, ITickable{
	
	protected String entityName;
	protected BaseLevel parentLevel;
	
	public String getEntityName() {
		return entityName;
	}
	
	public void setParentLevel(BaseLevel l) {
		parentLevel = l;
	}
	
	public BaseLevel getParentLevel() {
		return parentLevel;
	}
	
	public abstract VectorBox getCollisionArea();
	
}
