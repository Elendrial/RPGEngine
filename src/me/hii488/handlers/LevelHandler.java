package me.hii488.handlers;

import java.util.HashMap;

import me.hii488.controllers.UpdateController;
import me.hii488.gameObjects.levels.BaseLevel;
import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;

public class LevelHandler {
	
	// TODO: Possibly create a LevelLoader which uses a LevelSchema to turn arbitrary text files/image files to levels?
	// This would make any game program files smaller, and would also mean RAM used would be less if there's a lot of large levels.
	
	private static BaseLevel currentLevel, toLoad;
	private static HashMap<String, BaseLevel> levels = new HashMap<String, BaseLevel>();
	
	public static void addLevel(BaseLevel level, String name) {
		if(!levels.containsKey(name)) levels.put(name, level);
		else throw new IllegalArgumentException("Level already exists with name \"" + name + "\"");
	}
	
	public static void overwriteLevel(BaseLevel level, String name) {
		if(!levels.containsKey(name)) Logger.getDefault().print(LogSeverity.DEBUG, "Level doesn't exist with name \"" + name + "\", adding rather than overwriting");
		levels.put(name, level);
	}
	
	public static void loadLevel(String levelName) {
		if(currentLevel != null) {
			toLoad = levels.get(levelName);
			loadingLevel();
		}
		else {
			currentLevel = levels.get(levelName);
			currentLevel.onLoad();
		}
	}
	
	public static void loadLevel(BaseLevel level) {
		toLoad = level;
		loadingLevel();
	}
	
	private static void loadingLevel() {
		UpdateController.getTickController().setLoadingNewLevel();
	}
	
	public static void endOfTick() {
		currentLevel.onUnload();
		currentLevel = toLoad;
		currentLevel.onLoad();
	}
	
	public static BaseLevel getCurrentLevel() {
		return currentLevel;
	}
	
}
