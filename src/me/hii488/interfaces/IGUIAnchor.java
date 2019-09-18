package me.hii488.interfaces;

import me.hii488.dataTypes.Vector;
import me.hii488.graphics.Camera;

public interface IGUIAnchor {

	public Vector getPosition();
	
	default public Vector getScreenPositionFromAnchoredPosition(Vector v) {
		return getRealPositionFromAnchoredPosition(v).translate(Camera.getCamPosition().negated());
	}
	
	default public Vector getScreenPositionFromAnchoredPosition(int x, int y) {
		return Camera.getCamPosition().negated().translate(getRealPositionFromAnchoredPosition(x, y));
	}
	
	default public Vector getAnchoredPositionFromScreenPosition(Vector v) {
		return getAnchoredPositionFromRealPosition(v).translate(Camera.getCamPosition());
	}
	
	default public Vector getAnchoredPositionFromScreenPosition(int x, int y) {
		return Camera.getCamPosition().translate(getAnchoredPositionFromRealPosition(x, y));
	}
	
	default public Vector getRealPositionFromAnchoredPosition(Vector v) {
		return v.getCopy().translate(getPosition());
	}
	
	default public Vector getRealPositionFromAnchoredPosition(int x, int y) {
		return getPosition().getCopy().translate(x,y);
	}
	
	default public Vector getAnchoredPositionFromRealPosition(Vector v) {
		return v.getCopy().translate(getPosition().negated());
	}
	
	default public Vector getAnchoredPositionFromRealPosition(int x, int y) {
		return getPosition().negated().translate(x,y);
	}
	
}
