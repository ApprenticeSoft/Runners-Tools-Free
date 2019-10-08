package com.Runners.Tools.Free;

import com.Runners.Tools.Free.enums.ScreenEnum;
import com.badlogic.gdx.Gdx;

public class Variables {

	public static boolean TIMER_ACTIF = false;
	public static ScreenEnum ECRAN; 
	public static int ONGLET_X1 = 0;
	public static int ONGLET_X2 = (int)(Gdx.graphics.getWidth()/3);
	public static int ONGLET_X3 = (int)(2*Gdx.graphics.getWidth()/3);
	public static int ONGLET_HEIGHT = (int)(0.058f*Gdx.graphics.getHeight());
	public static int ONGLET_Y = (int)(Gdx.graphics.getHeight() - ONGLET_HEIGHT);
	public static int ONGLET_WIDTH = (int)(Gdx.graphics.getWidth()/3); 
	public static long TEMPS;
	public static float compteurX1 = 0.065f*Gdx.graphics.getWidth();
	public static float compteurX2 = 0.539f*Gdx.graphics.getWidth();
	public static float compteurY = 0.662f*Gdx.graphics.getHeight();
	public static float compteurWidth = 0.399f*Gdx.graphics.getWidth();
	public static float compteurHeight = 0.112f*Gdx.graphics.getHeight();
}
