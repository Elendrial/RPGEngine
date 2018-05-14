package me.example.spaceinvaders.gameObjects;

import me.example.spaceinvaders.gameObjects.entities.SIEnemy;
import me.example.spaceinvaders.gameObjects.tiles.SIBackGroundTile;
import me.example.spaceinvaders.gameObjects.tiles.SIWallTile;
import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;
import me.hii488.gameObjects.levels.BaseLevel;

public class SILevel extends BaseLevel{
	
	public SILevel() {
		super();
		int tileSize = EngineSettings.Texture.tileSize;
		int width = GameController.getWindow().width/tileSize;
		int height = GameController.getWindow().height/tileSize;
		
		// Setup tiles.
		this.getTileGrid().setDimensions(width, height);
		this.getTileGrid().fillDimensionsWith(0, 0, width, height, SIBackGroundTile.class);
		
		this.getEntityGrid().setDimensions(width, height);
	}
	
	public void onLoad() {
		int amountOfBarricades = getTileGrid().getDimensions().getIX()/6;
		int startOffset = getTileGrid().getDimensions().getIX() % 6 / 2;
		
		for(int i = 0; i < amountOfBarricades; i++) {
			getTileGrid().setObjectAt(i * 6 + startOffset, 3, new SIWallTile());
			for(int j = 0; j < 2; j++) getTileGrid().setObjectAt(i * 6 + startOffset + 1 + j, 4, new SIWallTile());
			getTileGrid().setObjectAt(i * 6 + startOffset + 3, 3, new SIWallTile());
		}
		
		int halfWidth = getEntityGrid().getDimensions().getIX()/2;
		for(int i = halfWidth - 6; i < halfWidth + 6; i++) {
			for(int j = 1; j < 6; j++) {
				addEntity(new SIEnemy().setVariant((int) ((7 - j)/2D)));
			}
		}
		
		super.onLoad();
	}
	
	public void onUnload() {
		endOfTick();
		this.getEntityGrid().clear();
		this.getFreeEntities().clear();
		super.onUnload();
	}
	
	public void updateOnSec() {
		super.updateOnSec();
		
		// Move all the enemies and check which are at the bottom.
	}
	
	public void gameOver() {
		
	}
	
}
