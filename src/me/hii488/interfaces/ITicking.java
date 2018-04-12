package me.hii488.interfaces;

public interface ITicking {
	
	public void updateOnTick();
	public void updateOnSec();
	public default void endOfTick(){}
	
}
