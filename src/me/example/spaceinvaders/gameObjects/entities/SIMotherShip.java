package me.example.spaceinvaders.gameObjects.entities;

import me.hii488.dataTypes.VectorBox;
import me.hii488.gameObjects.entities.FreeEntity;

public class SIMotherShip extends FreeEntity {
	
	@Override
	public String getTextureKey() {
		return null;
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
	public void onLoad() {
		
	}

	@Override
	public void onUnload() {
		
	}

	@Override
	public void updateOnTick() {
		
	}

	@Override
	public void updateOnSec() {
		
	}

	@Override
	public VectorBox getCollisionArea() {
		return null;
	}
	
}
