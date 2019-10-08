package com.Runners.Tools.Free;

import com.Runners.Tools.Free.screens.LoadingScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class MyGdxGame extends Game implements ApplicationListener{
	public SpriteBatch batch;
	public AssetManager assets;
	public ActionResolver actionResolver;
	
	public MyGdxGame(ActionResolver actionResolver){
		this.actionResolver = actionResolver;
	}

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new AssetManager();
		
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
		
		//if(Variables.ECRAN == ScreenEnum.Activity)
		//	actionResolver.showAds();
		//else actionResolver.hideAds();
		
		if(Donnees.getINTERSTITIAL_TRIGGER() < 1){
			Donnees.setINTERSTITIAL_TRIGGER(MathUtils.random(2,4));
			actionResolver.showOrLoadInterstital();
			//Donnees.setINTERSTITIAL_TRIGGER(MathUtils.random(2,4));
			//actionResolver.showOrLoadAmazonInterstital();
		}
		
	}
	
	public void dispose () {
		batch.dispose();
	}
	
	@Override
	public void pause() {
		super.pause();
	}
}
