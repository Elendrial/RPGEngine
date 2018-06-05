package me.example.spaceinvaders.gameObjects.entities;

import java.util.ArrayList;

import me.example.spaceinvaders.gameObjects.tiles.SIWallTile;
import me.hii488.gameObjects.entities.BaseEntity;
import me.hii488.gameObjects.entities.FreeEntity;
import me.hii488.gameObjects.tiles.BaseTile;

public class SIBullet extends FreeEntity{

	private double normalVelocity = 8;
	private int bulletType, bulletState, timeUntilStateChange;
	
	{
		entityName = "bullet";
	}
	
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
		
		ArrayList<BaseEntity> intersectingEntities = this.getParentLevel().getIntersectingEntities(this);
		if(!intersectingEntities.isEmpty()) {
			System.out.println("Intersecting with other entity");
			for(BaseEntity b : intersectingEntities) {
				if(b instanceof SIEnemy || b instanceof SIMotherShip) b.destroy();
				else if(b instanceof SIPlayer) ((SIPlayer) b).onHit();
			}
			this.destroy();
		}
		
		ArrayList<BaseTile> intersectingTiles = this.getParentLevel().getIntersectingTiles(this);
		if(!intersectingTiles.isEmpty()) {
			for(BaseTile t : intersectingTiles) {
				if(t instanceof SIWallTile) {
					System.out.println("Intersecting with tile");
					((SIWallTile) t).onHit();
				}
			}
		}
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
