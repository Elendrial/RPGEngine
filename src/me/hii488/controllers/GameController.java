package me.hii488.controllers;

import java.util.Random;

import me.hii488.graphics.Window;

public class GameController {
	
	public static boolean isRunning = true;
	public static boolean isPaused = false;
	
	public static Random rand = new Random();
	
	public static void setupEngine(){ //This may grow, it may not
		
	}
	
	public static void loadWindow(String windowTitle, int windowWidth, int windowHeight){
		loadWindow(new Window(windowTitle, windowWidth, windowHeight));
	}
	
	public static void loadWindow(Window w){ // Separate this <- current me: separate what? What do you mean past self?! What do you meeaaannnn???!?
		UpdateController.renderController.window = w;
	}
	
	public static void startGame(){
		InitialisationController.preInitAll();
		InitialisationController.initAll();
		InitialisationController.postInitAll();
		
		getWindow().createDisplay();
		UpdateController.start();
	}
	
	public static void closeGame(){
		isRunning = false;
	}
	
	public static Window getWindow(){
		return UpdateController.renderController.window;
	}
	
}
