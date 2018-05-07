package me.example.spaceinvaders.gameObjects.entities;

import java.awt.Graphics;

import me.hii488.dataTypes.VectorBox;
import me.hii488.gameObjects.entities.FreeEntity;
import me.hii488.interfaces.IInputListener;

public class SIPlayer extends FreeEntity implements IInputListener{

	@Override
	public String getTextureKey() {
		return "player";
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/entities/player.png";
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
	public void render(Graphics g) {
		
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
