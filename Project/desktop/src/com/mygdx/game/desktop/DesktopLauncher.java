package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dark Pyramid";
		config.width = 800;
		config.height = 650;
		config.resizable = true;
		config.addIcon("UIelements/icon.png", FileType.Internal);
		new LwjglApplication(new MyGdxGame(), config);
	}
	
}
