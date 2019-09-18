package me.hii488.graphics;

import me.hii488.EngineSettings;
import me.hii488.dataTypes.Vector;
import me.hii488.interfaces.IGUIAnchor;

public class Camera implements IGUIAnchor{
	
	private Vector finalPosition = new Vector(0,0);
	private int ticksLeft = -1;
	private Vector cameraPosition = new Vector(0,0);
	public float scale = 1;
	
	private static Camera cam;
	
	public static Camera get() {
		return cam == null ? (cam = new Camera()) : cam;
	}
	
	public void update() {
		if(ticksLeft > 0) {
			cameraPosition.translate(finalPosition.getCopy().translate(cameraPosition.negated()).scale(1d/(double)ticksLeft));
			ticksLeft--;
		}
	}
	
	@Override
	public Vector getPosition() {
		return cameraPosition.getCopy();
	}
	
	
	/// Static methods to make everything happy
	public static void setPosition(Vector v){
		setPosition(v.getX(), v.getY());
	}
	
	public static void setPosition(double x, double y) {
		if(EngineSettings.Camera.movable) get().cameraPosition.setLocation(x, y);
	}
	
	public void consistentMoveTo(Vector v, int ticks){
		if(EngineSettings.Camera.movable) {
			ticksLeft = ticks;
			finalPosition.setLocation(v);
		}
	}
	
	public static Vector getCamPosition() {
		return get().cameraPosition.getCopy();
	}
	
	public static Vector getRealVectorFromScreenVector(Vector v) {
		return get().getRealPositionFromAnchoredPosition(v);
	}
	
	public static Vector getRealVectorFromScreenVector(int x, int y) {
		return get().getRealPositionFromAnchoredPosition(x,y);
	}
	
	public static Vector getScreenVectorFromRealVector(Vector v) {
		return get().getAnchoredPositionFromRealPosition(v);
	}
	
	public static Vector getScreenVectorFromRealVector(int x, int y) {
		return  get().getAnchoredPositionFromRealPosition(x, y);
	}
	
}
