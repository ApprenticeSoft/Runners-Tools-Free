package com.Runners.Tools.Free.screens;

import com.Runners.Tools.Free.Donnees;
import com.Runners.Tools.Free.MyGdxGame;
import com.Runners.Tools.Free.Variables;
import com.Runners.Tools.Free.enums.ScreenEnum;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	private Texture textureLogo;
	private Image imageLogo;
	private Stage stage;
	
	public LoadingScreen(final MyGdxGame gam) {
		game = gam;
		Variables.ECRAN = ScreenEnum.Loading;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Donnees.Load();
		
		textureLogo = new Texture(Gdx.files.internal("Images/Logo.png"), true);
		textureLogo.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.MipMapLinearNearest);
		imageLogo = new Image(textureLogo);
		imageLogo.setWidth(9 * Gdx.graphics.getWidth()/10);
		imageLogo.setHeight(178 * imageLogo.getWidth()/757);
		imageLogo.setX(Gdx.graphics.getWidth()/2 - imageLogo.getWidth()/2);
		imageLogo.setY(Gdx.graphics.getHeight()/2 - imageLogo.getHeight()/2);
		stage = new Stage();
		
		//Chargement du TextureAtlas
		game.assets.load("Images.pack", TextureAtlas.class);
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
		game.assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		game.assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		
		FreeTypeFontLoaderParameter size1Params = new FreeTypeFontLoaderParameter();
		size1Params.fontFileName = "Fonts/calibri.ttf";			
		size1Params.fontParameters.genMipMaps = true;					
		size1Params.fontParameters.minFilter = TextureFilter.Linear;
		size1Params.fontParameters.magFilter = TextureFilter.Linear;						
		size1Params.fontParameters.size = Gdx.graphics.getWidth()/18;
		game.assets.load("font1.ttf", BitmapFont.class, size1Params);
		
		FreeTypeFontLoaderParameter size2Params = new FreeTypeFontLoaderParameter();
		size2Params.fontFileName = "Fonts/calibrib.ttf";	
		size2Params.fontParameters.genMipMaps = true;					
		size2Params.fontParameters.minFilter = TextureFilter.Linear;
		size2Params.fontParameters.magFilter = TextureFilter.Linear;								
		size2Params.fontParameters.size = Gdx.graphics.getWidth()/16;					
		game.assets.load("font2.ttf", BitmapFont.class, size2Params);
		
		FreeTypeFontLoaderParameter size3Params = new FreeTypeFontLoaderParameter();
		size3Params.fontFileName = "Fonts/calibrib.ttf";		
		size3Params.fontParameters.genMipMaps = true;					
		size3Params.fontParameters.minFilter = TextureFilter.Linear;
		size3Params.fontParameters.magFilter = TextureFilter.Linear;							
		size3Params.fontParameters.size = Gdx.graphics.getWidth()/22;					
		game.assets.load("fontOnglet.ttf", BitmapFont.class, size3Params);
		
		FreeTypeFontLoaderParameter size4Params = new FreeTypeFontLoaderParameter();
		size4Params.fontFileName = "Fonts/GOTHIC.TTF";		
		size4Params.fontParameters.genMipMaps = true;					
		size4Params.fontParameters.minFilter = TextureFilter.Linear;
		size4Params.fontParameters.magFilter = TextureFilter.Linear;	
		size4Params.fontParameters.size = 2*Gdx.graphics.getWidth()/9;					
		game.assets.load("fontCalorie.ttf", BitmapFont.class, size4Params);
		
		FreeTypeFontLoaderParameter size5Params = new FreeTypeFontLoaderParameter();
		size5Params.fontFileName = "Fonts/calibri.ttf";			
		size5Params.fontParameters.genMipMaps = true;					
		size5Params.fontParameters.minFilter = TextureFilter.Linear;
		size5Params.fontParameters.magFilter = TextureFilter.Linear;						
		size5Params.fontParameters.size = Gdx.graphics.getWidth()/16;					
		game.assets.load("fontInterval.ttf", BitmapFont.class, size5Params);
		
		FreeTypeFontLoaderParameter size6Params = new FreeTypeFontLoaderParameter();
		size6Params.fontFileName = "Fonts/calibril.ttf";	
		size6Params.fontParameters.genMipMaps = true;					
		size6Params.fontParameters.minFilter = TextureFilter.Linear;
		size6Params.fontParameters.magFilter = TextureFilter.Linear;								
		size6Params.fontParameters.size = Gdx.graphics.getWidth()/14;					
		game.assets.load("fontTemps.ttf", BitmapFont.class, size6Params);
		
		FreeTypeFontLoaderParameter size7Params = new FreeTypeFontLoaderParameter();
		size7Params.fontFileName = "Fonts/calibri.ttf";	
		size7Params.fontParameters.genMipMaps = true;					
		size7Params.fontParameters.minFilter = TextureFilter.Linear;
		size7Params.fontParameters.magFilter = TextureFilter.Linear;								
		size7Params.fontParameters.size = Gdx.graphics.getWidth()/14;					
		game.assets.load("fontDistance.ttf", BitmapFont.class, size7Params);
			
		FreeTypeFontLoaderParameter size8Params = new FreeTypeFontLoaderParameter();
		size8Params.fontFileName = "Fonts/GOTHIC.TTF";		
		size8Params.fontParameters.genMipMaps = true;					
		size8Params.fontParameters.minFilter = TextureFilter.Linear;
		size8Params.fontParameters.magFilter = TextureFilter.Linear;							
		size8Params.fontParameters.size = Gdx.graphics.getWidth()/9;					
		game.assets.load("fontMessage.ttf", BitmapFont.class, size8Params);
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
		game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
        
		if(game.assets.update())
        	((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));	
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		stage.addActor(imageLogo);
		
		imageLogo.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(0.75f),Actions.delay(1.5f)));
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
