package me.hii488.graphics;

import me.hii488.EngineSettings;
import me.hii488.dataTypes.Vector;

public class Camera {
	
	// TODO: Maybe make separate cameras with each level? Then you can set the camera per level
	
	private static Vector cameraPosition = new Vector(0,0);
	private static Vector oldPosition = new Vector(0,0);
	public static float scale = 1;
	
	public static void update() {
		oldPosition.setLocation(cameraPosition);
	}
	
	public static void moveTo(Vector v){
		if(EngineSettings.Camera.movable) cameraPosition.setLocation(v);
	}
	
	public static void smoothMoveTo(Vector v, int milliSeconds){
		if(EngineSettings.Camera.movable) {
			//TODO: Smooth move			
		}
	}
	
	public static Vector getPosition() {
		return oldPosition.getCopy();
	}
	
	public static Vector getWorldVectorFromGraphicVector(Vector v) {
		return v.getCopy().translate(cameraPosition);
	}
	
	public static Vector getWorldVectorFromGraphicVector(int x, int y) {
		return cameraPosition.getCopy().translate(x, y);
	}
	
	public static Vector getGraphicVectorFromWorldVector(Vector v) {
		return v.getCopy().translate(cameraPosition.negated());
	}
	
	public static Vector getGraphicVectorFromWorldVector(int x, int y) {
		return cameraPosition.getCopy().negated().translate(x, y);
	}
	
}
