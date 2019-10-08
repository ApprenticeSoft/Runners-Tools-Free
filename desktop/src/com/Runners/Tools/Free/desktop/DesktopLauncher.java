package com.Runners.Tools.Free.desktop;

import com.Runners.Tools.Free.MyGdxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Premier Jeu";
	    config.width = 480;
	    config.height = 800;
		new LwjglApplication(new MyGdxGame(new ActionResolverDesktop()), config);
	}
}
