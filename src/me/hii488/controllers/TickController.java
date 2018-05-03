package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.ITickable;

public class TickController implements Runnable {
	public ArrayList<ITickable> additionalEarlyTicking = new ArrayList<ITickable>();
	public ArrayList<ITickable> additionalLateTicking = new ArrayList<ITickable>();
	
	private boolean loadingNewLevel = false;
	
	public static void addEarlyTicker(ITickable e){
		UpdateController.tickController.additionalEarlyTicking.add(e);
	}
	
	public static void addLateTicker(ITickable e){
		UpdateController.tickController.additionalLateTicking.add(e);
	}
	
	public void updateTickableOnTick(){
		// Early tickers tick first
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnTick();
		
		// Tick the loaded container now.
		LevelHandler.getCurrentLevel().updateOnTick();
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnTick();
	}
	
	public void updateTickableOnSec(){
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnSec();
		
		// Tick the loaded container now.
		LevelHandler.getCurrentLevel().updateOnSec();
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnSec();
	}
	
	
	public void endOfTick(){
		LevelHandler.getCurrentLevel().endOfTick();
		
		if(loadingNewLevel) {
			LevelHandler.endOfTick();
			loadingNewLevel = false;
		}
	}
	
	public void setLoadingNewLevel() {
		loadingNewLevel = true;
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	
	public int TPS = 0;
	public int actualTPS;
	
	public boolean isRunning = true;
	
	public void tick(){
		try{updateTickableOnTick();}catch(Exception e){e.printStackTrace();}
		try{endOfTick();}catch(Exception e){e.printStackTrace();}
	}
	
	public void sec(){ // This is not called currently to simplify the ticking process while I redo it.
		try{updateTickableOnSec();}catch(Exception e){e.printStackTrace();}
		try{endOfTick();} catch(Exception e){e.printStackTrace();}
	}

	public synchronized void update(){
		update = true;
		notifyAll();
	}
	
	private boolean update = false;
	private synchronized void waitForUpdate(){
		while(!update){
			try {
				this.wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		update = false;
	}
	
	private boolean updateSecond = false;
	public void markForSecTick(){
		updateSecond = true;
	}
	
	@Override
	public void run() {
		while (GameController.isRunning) {
			waitForUpdate();
			if(isRunning) {
				tick();
				if(updateSecond){
					sec();
					updateSecond = false;
				}
			}
			UpdateController.instance.renderDone();
		}
	}

	
}
