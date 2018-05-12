package me.example.spaceinvaders.gameObjects.entities;

import me.hii488.controllers.GameController;
import me.hii488.gameObjects.entities.GridEntity;

public class SIEnemy extends GridEntity{

	private boolean armsUp = false, atBottom = false;
	private int variant;
	private double shotChance = 0.2;
	
	public void setVariant(int i) {
		variant = i % 3;
	}
	
	public void setAtBottom(boolean b) {
		atBottom = b;
	}
	
	@Override
	public void updateOnSec() {
		armsUp = !armsUp;
	}
	
	@Override
	public void updateOnTick() {
		if(atBottom && GameController.rand.nextDouble() < shotChance) {
			SIBullet bullet = new SIBullet();
			bullet.setBulletType(variant == 2 ? 2 : 1);
			bullet.setPosition(getAbsPosition().translate(getCollisionArea().getWidth()/2 - bullet.getCollisionArea().getWidth()/2, getCollisionArea().getHeight() + 1));
			parentLevel.addEntity(bullet);
		}		
	}
	
	@Override
	public String getTextureKey() {
		return "standardEnemy";
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/entities/bullet.png";
	}

	@Override
	public int getTextureState() {
		return (armsUp ? 1 : 0) + 2 * variant;
	}

	@Override
	public int getHighestState() {
		return 5; // arms up/down * 3 variants
	}

	@Override
	public void onLoad() {}

	@Override
	public void onUnload() {}

}
