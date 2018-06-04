package me.example.spaceinvaders.gameObjects.entities;

import me.example.spaceinvaders.gameObjects.SILevel;
import me.hii488.controllers.GameController;
import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.FreeEntity;
import me.hii488.handlers.InputHandler;
import me.hii488.interfaces.IInputListener;

public class SIPlayer extends FreeEntity implements IInputListener{

	private boolean leftPressed = false, rightPressed = false, shootPressed = false;
	private double moveSpeed = 2;
	private int shootCooldown, cooldownTime;
	private int health;
	
	{
		entityName = "player";
	}
	
	@Override
	public void onLoad() {
		InputHandler.addInputListener(this);
		setPosition(new Vector(GameController.getWindow().width/2 - getTextureWidth()/2, GameController.getWindow().height - 20));
		cooldownTime = 15;
		shootCooldown = 0;
		health = 3;
	}
	
	@Override
	public void updateOnTick() {
		if(leftPressed && !rightPressed) position.translate(-moveSpeed, 0);
		else if(rightPressed && !leftPressed) position.translate(moveSpeed, 0);
		
		if(shootCooldown <= 0) {
			if(shootPressed) {
				shootCooldown = cooldownTime;
				
				SIBullet bullet = new SIBullet();
				bullet.setBulletType(-1);
				bullet.setPosition(getPosition().translate(getCollisionArea().getWidth()/2 - bullet.getTextureWidth()/2, -bullet.getTextureHeight() - 1));
				parentLevel.addEntity(bullet);
			}
		}
		else shootCooldown--;
	}
	
	public void onHit() {
		health--;
		if(health <= 0) ((SILevel) parentLevel).gameOver();
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
	public void onUnload() {
		InputHandler.removeInputListener(this);
	}

	@Override
	public void updateOnSec() {
		
	}
	
	public void keyPressed(KeyBind k) {
		if(k == KeyBind.MOVE_LEFT) {
			leftPressed = true;
		}
		
		else if(k == KeyBind.MOVE_RIGHT) {
			rightPressed = true;
		}
		
		else if(k == KeyBind.INTERACT) {
			shootPressed = true;
		}
	}
	
	public void keyReleased(KeyBind k) {
		if(k == KeyBind.MOVE_LEFT) {
			leftPressed = false;
		}
		
		else if(k == KeyBind.MOVE_RIGHT) {
			rightPressed = false;
		}
		
		else if(k == KeyBind.INTERACT){
			shootPressed = false;
		}
	}

}
