package com.badwater.Riverboat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badwater.Riverboat.Riverboat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "camera";
		
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new Riverboat(), config);
	}
}
