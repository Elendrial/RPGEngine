package me.example.spaceinvaders.gameObjects.entities;

import java.awt.event.KeyEvent;

import me.example.spaceinvaders.gameObjects.SILevel;
import me.hii488.controllers.GameController;
import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.FreeEntity;
import me.hii488.interfaces.IInputListener;
import me.hii488.registries.KeyBindRegistry;

public class SIPlayer extends FreeEntity implements IInputListener{

	private boolean leftPressed = false, rightPressed = false, shootPressed = false;
	private double moveSpeed = 0.4;
	private int shootCooldown, cooldownTime;
	private int health;
	
	@Override
	public void onLoad() {
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
				bullet.setPosition(getPosition().translate(getCollisionArea().getWidth()/2 - bullet.getCollisionArea().getWidth()/2, -bullet.getCollisionArea().getHeight() - 1));
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
	public void onUnload() {
		
	}

	@Override
	public void updateOnSec() {
		
	}
	
	public void keyPressed(KeyEvent arg0) {
		// Ew, gotta change how this is done
		if(KeyBindRegistry.getKeyBindValue(KeyBind.MOVE_LEFT).contains(arg0.getKeyCode())) {
			leftPressed = true;
		}
		
		else if(KeyBindRegistry.getKeyBindValue(KeyBind.MOVE_RIGHT).contains(arg0.getKeyCode())) {
			rightPressed = true;
		}
		
		else if(KeyBindRegistry.getKeyBindValue(KeyBind.INTERACT).contains(arg0.getKeyCode())) {
			shootPressed = true;
		}
	}
	
	public void keyReleased(KeyEvent arg0) {
		if(KeyBindRegistry.getKeyBindValue(KeyBind.MOVE_LEFT).contains(arg0.getKeyCode())) {
			leftPressed = false;
		}
		
		else if(KeyBindRegistry.getKeyBindValue(KeyBind.MOVE_RIGHT).contains(arg0.getKeyCode())) {
			rightPressed = false;
		}
		
		else if(KeyBindRegistry.getKeyBindValue(KeyBind.INTERACT).contains(arg0.getKeyCode())) {
			shootPressed = false;
		}
	}

}
