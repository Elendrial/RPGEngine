package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.handlers.InputHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.KeyBindRegistry;

public class InitialisationController{
	
	protected static ArrayList<IInitialiser> initList = new ArrayList<IInitialiser>();

	public static void preInitAll(){
		enginePreInit();
		initList.forEach(i -> i.preInit());
	}
	
	public static void initAll(){
		engineInit();
		initList.forEach(i -> i.init());
	}
	
	public static void postInitAll(){
		enginePostInit();
		initList.forEach(i -> i.postInit());
	}

	public static void addInitialiser(IInitialiser i) {
		initList.add(i);
	}
	
	private static void enginePreInit() {
		InputHandler.addInputListener(new KeyBindRegistry());
	}

	private static void engineInit() {}

	private static void enginePostInit() {}
	
}
