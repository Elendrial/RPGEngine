package me.example.spaceinvaders;

import me.example.spaceinvaders.gameObjects.SILevel;
import me.example.spaceinvaders.gameObjects.entities.SIBullet;
import me.example.spaceinvaders.gameObjects.entities.SIEnemy;
import me.example.spaceinvaders.gameObjects.entities.SIMotherShip;
import me.example.spaceinvaders.gameObjects.entities.SIPlayer;
import me.example.spaceinvaders.gameObjects.tiles.SIBackGroundTile;
import me.example.spaceinvaders.gameObjects.tiles.SIWallTile;
import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;
import me.hii488.controllers.InitialisationController;
import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.EntityRegistry;
import me.hii488.registries.TileRegistry;

public class SpaceInvaders implements IInitialiser{
	
	public static void main(String[] args) {
		InitialisationController.addInitialiser(new SpaceInvaders());
		
		EngineSettings.Texture.tileSize = 16;
		
		GameController.loadAndStartGame("SpaceInvaders", 1000, 800);
	}
	
	@Override
	public void preInit() {
		EntityRegistry.registerEntity("bullet", SIBullet.class);
		EntityRegistry.registerEntity("player", SIPlayer.class);
		EntityRegistry.registerEntity("enemy", SIEnemy.class);
		EntityRegistry.registerEntity("mothership", SIMotherShip.class);
		
		TileRegistry.registerTile("background", SIBackGroundTile.class);
		TileRegistry.registerTile("wall", SIWallTile.class);
	}
	
	@Override
	public void init() {
		LevelHandler.addLevel(new SILevel(), "SpaceInvaderLevel");		
	}
	
	@Override
	public void postInit() {
		LevelHandler.loadLevel("SpaceInvaderLevel");
	}
	
}
