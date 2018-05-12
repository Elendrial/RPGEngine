package me.example.spaceinvaders.gameObjects.entities;

import me.hii488.gameObjects.entities.FreeEntity;

public class SIBullet extends FreeEntity{

	private double normalVelocity = 3;
	private int bulletType, bulletState, timeUntilStateChange;
	
	@Override
	public void onLoad() {
		bulletState = 0;
		timeUntilStateChange = 5;
	}
	
	public void setBulletType(int i) {
		bulletType = i; // -1 = player shot, 1 = enemy shot, 2 = enemy fast shot 
	}
	
	@Override
	public void updateOnTick() {
		this.position.translate(0, normalVelocity * bulletType);
		
		timeUntilStateChange--;
		if(timeUntilStateChange <= 0) {
			timeUntilStateChange = 5;
			bulletState = bulletState == 1 ? 0 : 1;
		}
		
		// TODO: Collision check
	}

	@Override
	public String getTextureKey() {
		return "bullet";
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/entities/bullet.png";
	}

	@Override
	public int getTextureState() {
		return bulletType == -1 ? 0 : bulletType * 2 + bulletState;
	}

	@Override
	public int getHighestState() {
		return 4;
	}

	@Override
	public void onUnload() {}
	
	@Override
	public void updateOnSec() {}
}
