package me.example.spaceinvaders.gameObjects;

import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;
import me.hii488.gameObjects.levels.BaseLevel;

public class SILevel extends BaseLevel{
	
	public SILevel() {
		super();
		int tileSize = EngineSettings.Texture.tileSize;
		this.getTileGrid().setDimensions(GameController.getWindow().width/tileSize, GameController.getWindow().height/tileSize);
	}
	
}
