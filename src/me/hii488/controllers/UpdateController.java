package me.hii488.controllers;

import java.util.concurrent.locks.ReentrantLock;

import me.hii488.EngineSettings;

public class UpdateController implements Runnable{
	// TODO: Think about completely rewriting this, it works, but does it work as well as it could? Not sure
	// Will probably need a complete rewrite from scratch if I do change it.
	
	protected static TickController tickController = new TickController();
	protected static RenderController renderController = new RenderController();
	public static UpdateController instance = new UpdateController();
	
	private boolean tickNotify = false, renderNotify = false;

	protected ReentrantLock instancelock = new ReentrantLock();
	
	public static void start(){
		renderController.start();
		tickController.start();
		new Thread(instance).start();
	}
	
	public synchronized void tickDone(){
		tickNotify = true;
		this.notifyAll();
	}
	
	public synchronized void renderDone(){
		renderNotify = true;
		this.notifyAll();
	}
	
	public synchronized void waitForEnd(){
		while(!tickNotify && !renderNotify){
			try {
				this.wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		tickNotify = false;
		renderNotify = false;
	}
	
	public void update(){
		tickController.update();
		renderController.update();
		waitForEnd();
	}
	
	public void updateSecond(){
		tickController.markForSecTick();
	}
	
	public void setTickRate(int tps) {
		EngineSettings.World.TargetTPS = tps;
		updateTickRate();
	}
	
	public void updateTickRate() {
		if(targetTPS != (int) (EngineSettings.World.TargetTPS * EngineSettings.World.currentSpeed)){
			targetTPS = (int) (EngineSettings.World.TargetTPS * EngineSettings.World.currentSpeed);
			nsPerTick = (1D / targetTPS) * 1000000000D;
			actualTPS = targetTPS;
			System.err.println("Target TPS changed to: " + targetTPS);
		}
	}
	
	public int TPS = 0;
	public int actualTPS;
	
	private double nsPerTick;
	private int targetTPS;
	
	@Override
	public void run() {
		int tick = 0;
		
		targetTPS = (int) (EngineSettings.World.TargetTPS * EngineSettings.World.currentSpeed);
		actualTPS = targetTPS;
		double fpsTimer = System.currentTimeMillis();
		nsPerTick = (1D / targetTPS) * 1000000000D;
		double then = System.nanoTime();
		double now;
		double unprocessed = 0;
		
		while (GameController.isRunning) {
			now = System.nanoTime();
			unprocessed += (now - then) / nsPerTick;
			then = now;
			while (unprocessed >= 1 && unprocessed < actualTPS) {
				if(!GameController.isPaused) update();
				
				tick++;
				unprocessed--;
			}
			
			if(unprocessed >= actualTPS) unprocessed = 0;
			
			// If the current time is 1 second greater than the last time we printed
			if (System.currentTimeMillis() - fpsTimer >= 1000) {
				TPS = tick;
				tick = 0;
				fpsTimer += 1000;
				
				if(!GameController.isPaused) updateSecond();
				
				if(EngineSettings.Logging.tpsPrinter) System.out.println("TPS: " + TPS);
				
				if(TPS > actualTPS * 2.5){
					then = System.nanoTime();
					actualTPS -= actualTPS * 0.05D;
					nsPerTick = (1D / actualTPS) * 1000000000D;
					System.err.println("Target TPS lowered to: " + actualTPS + " in order to avoid runaway.");
					System.err.println("Skipped " + (int)unprocessed + " ticks in the process.");
					unprocessed = 0;
					fpsTimer = System.currentTimeMillis() + 1000;
				}
			}
			
			// This is NOT to sleep, but to limit the game loop
			try { if(actualTPS > 500) Thread.sleep(1);
				  else Thread.sleep(5); 
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
	
	
	public static TickController getTickController() {
		return tickController;
	}
	public static RenderController getRenderController() {
		return renderController;
	}
	
}
