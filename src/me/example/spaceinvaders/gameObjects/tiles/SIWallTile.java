package me.example.spaceinvaders.gameObjects.tiles;

import me.hii488.gameObjects.tiles.BaseTile;

public class SIWallTile extends BaseTile{

	public int damage;
	
	@Override
	public void onLoad() {
		damage = 0;
	}
	
	public void onHit() {
		damage++;
		if(damage > 2) {
			SIBackGroundTile b = new SIBackGroundTile();
			this.parentGrid.setObjectAt(parentGrid.getPositionOf(this), b);
		}
	}
	
	@Override
	public String getTextureKey() {
		return "wall";
	}

	@Override
	public String getTextureLocation() {
		return "siTextures/tiles/block.png";
	}

	@Override
	public int getTextureState() {
		return 0;
	}

	@Override
	public int getHighestState() {
		return 2; // 0 = undamaged, 1 = damaged, 2 = heavily damaged
	}

	@Override
	public void onUnload() {
		
	}

}
