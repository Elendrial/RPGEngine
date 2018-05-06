package me.hii488;

import java.awt.Color;

import me.hii488.dataTypes.Vector;

public class EngineSettings {

	public static class World {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;	
	}
	
	public static class Camera {

		public static boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
		public static Vector currentPosition = new Vector(0,0);

	}

	public static class Logging {
		// TODO: This.
		public static boolean debug = false;
		public static boolean tpsPrinter = true;
	}

	public static class Texture {
		public static final String errorImageLocation = "textures/errorTextures/error.png";
		public static int tileSize = 16;
		public static Color background = Color.BLACK;
	}
}
