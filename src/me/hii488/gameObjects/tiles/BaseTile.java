package me.hii488.gameObjects.tiles;

import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;

public abstract class BaseTile implements ITexturedObject, IGameObject{
	
	protected String tileName;
	
	public String getTileName() {
		return tileName;
	}
}
