package me.hii488;

import java.awt.Color;

import me.hii488.dataTypes.Vector;

public class EngineSettings {

	public static class World {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;	
	}
	
	public static class Camera {
		// TODO: Reconcile this with the Camera
		public static boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
	}

	public static class Logging {
		// TODO: Reconcile this with the Logger
		public static boolean debug = false;
		public static boolean tpsPrinter = true;
	}

	public static class Texture {
		// Reconcile this with the new Grid
		public static final String errorImageLocation = "textures/errorTextures/error.png";
		public static int tileSize = 16;
		public static Color background = Color.BLACK;
	}
}
