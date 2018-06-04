package me.example.spaceinvaders.gameObjects.entities;

import me.hii488.gameObjects.entities.FreeEntity;

public class SIMotherShip extends FreeEntity {
	
	private int direction = 0;
	private double speed = 0.7;
	
	{
		entityName = "mothership";
	}
	
	public void setDirection(int i) {
		direction = i;
	}
	
	@Override
	public void updateOnTick() {
		position.translate(direction * speed, 0);
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/entities/mothership.png";
	}

	@Override
	public int getTextureState() {
		return 0;
	}

	@Override
	public int getHighestState() {
		return 0;
	}

	@Override
	public void onLoad() {}

	@Override
	public void onUnload() {}

	@Override
	public void updateOnSec() {}
	
}
