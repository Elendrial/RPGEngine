package me.example.spaceinvaders.gameObjects.entities;

import me.hii488.dataTypes.VectorBox;
import me.hii488.gameObjects.entities.GridEntity;

public class SIEnemy extends GridEntity{

	private boolean armsUp = false;
	
	@Override
	public void updateOnSec() {
		armsUp = !armsUp;
	}
	
	@Override
	public void updateOnTick() {
		
	}
	
	@Override
	public String getTextureKey() {
		return null;
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/entities/bullet.png";
	}

	@Override
	public int getTextureState() {
		return armsUp ? 1 : 0;
	}

	@Override
	public int getHighestState() {
		return 1; // Their arms go up/down
	}

	@Override
	public void onLoad() {
		
	}

	@Override
	public void onUnload() {
		
	}

	@Override
	public VectorBox getCollisionArea() {
		return null;
	}

}
