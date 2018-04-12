package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.interfaces.IInitialiser;

public class InitialisationController{
	
	protected static ArrayList<IInitialiser> initList = new ArrayList<IInitialiser>();

	public static void preInitAll(){
		initList.forEach(i -> i.preInit());
	}
	
	public static void initAll(){
		initList.forEach(i -> i.init());
	}
	
	public static void postInitAll(){
		initList.forEach(i -> i.postInit());
	}

	public static void addInitialiser(IInitialiser i) {
		initList.add(i);
	}
	
}
