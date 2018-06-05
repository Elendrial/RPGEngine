package me.example.spaceinvaders.gameObjects;

import me.example.spaceinvaders.gameObjects.entities.SIEnemy;
import me.example.spaceinvaders.gameObjects.entities.SIPlayer;
import me.example.spaceinvaders.gameObjects.tiles.SIBackGroundTile;
import me.example.spaceinvaders.gameObjects.tiles.SIWallTile;
import me.hii488.EngineSettings;
import me.hii488.gameObjects.levels.BaseLevel;

public class SILevel extends BaseLevel{
	
	public SILevel() {
		super();
		// Setup the grids.
		this.getTileGrid().autoSetup(EngineSettings.Texture.tileSize);
		this.getTileGrid().fillDimensionsWith(0, 0, getTileGrid().getWidth(), getTileGrid().getHeight(), SIBackGroundTile.class);
		
		this.getEntityGrid().autoSetup(30);
	}
	
	public void onLoad() {
		// Place the tiles
		int amountOfBarricades = getTileGrid().getDimensions().getIX()/6;
		int startOffset = (getTileGrid().getDimensions().getIX() % 6) / 2;
		int yOffset = getTileGrid().getDimensions().getIY() - 5;
		
		for(int i = 0; i < amountOfBarricades; i++) {
			getTileGrid().setObjectAt((i * 6) + startOffset, yOffset, new SIWallTile());
			for(int j = 0; j < 2; j++) getTileGrid().setObjectAt((i * 6) + startOffset + 1 + j, yOffset - 1, new SIWallTile());
			getTileGrid().setObjectAt((i * 6) + startOffset + 3, yOffset, new SIWallTile());
		}
		
		// Place down the entities.
		int halfWidth = getEntityGrid().getDimensions().getIX()/2;
		for(int i = halfWidth - 6; i < halfWidth + 6; i++) {
			for(int j = 1; j < 6; j++) {
				addEntity(new SIEnemy(i,j).setVariant((int) ((7 - j)/2D)));
			}
		}
		
		addEntity(new SIPlayer());
		
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
