package com.Runners.Tools.Free.screens;

import com.Runners.Tools.Free.Donnees;
import com.Runners.Tools.Free.MyGdxGame;
import com.Runners.Tools.Free.Variables;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionScreen implements Screen{
	
	final MyGdxGame game;
	OrthographicCamera camera;
	private ImageButton FR, ES, UK, Back;
	private ImageButtonStyle FRstyle, ESstyle, UKstyle, BackStyle;
	private Skin menuSkin;
	private TextureAtlas menuAtlas;
	private ButtonGroup drapeauxGroupe;
	private Table optionTable;
	private Stage stage;
	private Label labelLangage;
	private LabelStyle labelStyleLangage;
	private TextButton rateBouton;
	private TextButton removeAdsBouton;
	private TextButtonStyle textButtonStyle;
	private static final String GOOGLE_PLAY_FREE_GAME_URL = "https://play.google.com/store/apps/details?id=com.Runners.Tools.Free.android";
	private static final String GOOGLE_PLAY_GAME_URL = "https://play.google.com/store/apps/details?id=com.pace.converter.android";
	private static final String AMAZON_FREE_GAME_URL = "http://www.amazon.com/gp/mas/dl/android?p=com.Runners.Tools.Free.android";
	private static final String AMAZON_GAME_URL = "http://www.amazon.com/gp/mas/dl/android?p=com.pace.converter.android";
	
	public OptionScreen(final MyGdxGame gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		
		menuSkin = new Skin();
		menuAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		menuSkin.addRegions(menuAtlas);
		
		drapeauxGroupe = new ButtonGroup();
		
		labelStyleLangage = new LabelStyle(game.assets.get("font2.ttf", BitmapFont.class), Color.WHITE);
		labelLangage = new Label("Language", labelStyleLangage);
		
		BackStyle = new ImageButtonStyle();
		BackStyle.imageUp = menuSkin.getDrawable("Back");
		BackStyle.imageDown = menuSkin.getDrawable("BackChecked");
		Back = new ImageButton(BackStyle);
		Back.setWidth(Gdx.graphics.getWidth()/8);
		Back.setHeight(77 * Back.getWidth()/147);
		Back.setX(Gdx.graphics.getHeight()/20);
		Back.setY(Gdx.graphics.getHeight()/20);
		
		FRstyle = new ImageButtonStyle();
		FRstyle.imageUp = menuSkin.getDrawable("DrapeauFR");
		FRstyle.imageDown = menuSkin.getDrawable("DrapeauFRChecked");
		FRstyle.imageChecked = menuSkin.getDrawable("DrapeauFRChecked");
		FR = new ImageButton(FRstyle);
		ESstyle = new ImageButtonStyle();
		ESstyle.imageUp = menuSkin.getDrawable("DrapeauES");
		ESstyle.imageDown = menuSkin.getDrawable("DrapeauESChecked");
		ESstyle.imageChecked = menuSkin.getDrawable("DrapeauESChecked");
		ES = new ImageButton(ESstyle);
		UKstyle = new ImageButtonStyle();
		UKstyle.imageUp = menuSkin.getDrawable("DrapeauUK");
		UKstyle.imageDown = menuSkin.getDrawable("DrapeauUKChecked");
		UKstyle.imageChecked = menuSkin.getDrawable("DrapeauUKChecked");
		UK = new ImageButton(UKstyle);
		
		drapeauxGroupe.add(UK, ES, FR);
		drapeauxGroupe.setMaxCheckCount(1);
		drapeauxGroupe.setMinCheckCount(1);
		
		optionTable = new Table();
		optionTable.setFillParent(true);
		optionTable.add(labelLangage).colspan(3).pad(25);
		optionTable.row().height(Gdx.graphics.getWidth()/8).width(Gdx.graphics.getWidth()/8).spaceRight(Gdx.graphics.getWidth()/13);
		optionTable.add(UK);
		optionTable.add(FR);
		optionTable.add(ES);
		optionTable.setY(Gdx.graphics.getHeight()/7);
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = menuSkin.getDrawable("Bouton");
		textButtonStyle.down = menuSkin.getDrawable("BoutonChecked");
		textButtonStyle.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyle.pressedOffsetY = -2;
		
		rateBouton = new TextButton("Rate", textButtonStyle);
		rateBouton.setWidth(11 * Gdx.graphics.getWidth()/30);
		rateBouton.setHeight(Gdx.graphics.getWidth()/6);
		rateBouton.setX(Gdx.graphics.getWidth()/2 - rateBouton.getWidth()/2);
		rateBouton.setY(4 * Gdx.graphics.getHeight()/10);
		
		removeAdsBouton = new TextButton("Remove Ads", textButtonStyle);
		removeAdsBouton.setWidth(rateBouton.getWidth());
		removeAdsBouton.setHeight(rateBouton.getHeight());
		removeAdsBouton.setX(Gdx.graphics.getWidth()/2 - rateBouton.getWidth()/2);
		removeAdsBouton.setY(rateBouton.getY() - rateBouton.getHeight() - Gdx.graphics.getHeight()/40);
		removeAdsBouton.getLabel().setWrap(true);;
		
		stage.addActor(optionTable);
		stage.addActor(Back);
		stage.addActor(rateBouton);
		stage.addActor(removeAdsBouton);
		
		switch(Donnees.getLangage()){
			case 1 :
				labelLangage.setText("Language");
				rateBouton.setText("Rate");
				removeAdsBouton.setText("Remove Ads");
				UK.setChecked(true);
				break;
			case 2 :
				labelLangage.setText("Langage");
				rateBouton.setText("Evaluer");
				removeAdsBouton.setText("Supprimer Les Publicités");
				FR.setChecked(true);
				break;
			case 3 :
				labelLangage.setText("Idioma");
				rateBouton.setText("Evaluar");
				removeAdsBouton.setText("Eliminar Los Anuncios");
				ES.setChecked(true);
				break;
			default :
				labelLangage.setText("Language");
				rateBouton.setText("Rate");
				removeAdsBouton.setText("Remove Ads");	
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.383f, 0.453f, 0.508f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		
		stage.act();
		stage.draw();
		
		switch(Donnees.getLangage()){
			case 1 :
				labelLangage.setText("Language");
				rateBouton.setText("Rate");
				removeAdsBouton.setText("Remove Ads");
				UK.setChecked(true);
				break;
			case 2 :
				labelLangage.setText("Langage");
				rateBouton.setText("Evaluer");
				removeAdsBouton.setText("Supprimer Les Publicités");
				FR.setChecked(true);
				break;
			case 3 :
				labelLangage.setText("Idioma");
				rateBouton.setText("Evaluar");
				removeAdsBouton.setText("Eliminar Los Anuncios");
				ES.setChecked(true);
				break;
			default :
				labelLangage.setText("Language");
				rateBouton.setText("Rate");
				removeAdsBouton.setText("Remove Ads");	
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		UK.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangage(1);
				System.out.println("Donnees.getLangage()" + Donnees.getLangage());
			}
		});
		
		FR.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangage(2);
				System.out.println("Donnees.getLangage()" + Donnees.getLangage());
			}
		});

		ES.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangage(3);
				System.out.println("Donnees.getLangage()" + Donnees.getLangage());
			}
		});

		Back.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				switch(Variables.ECRAN){
					case Calorie :
						game.setScreen(new CalorieScreen(game));
						break;
					case Converter :
						game.setScreen(new MainMenuScreen(game));
						break;
					case Interval :
						game.setScreen(new IntervalScreen(game));
						break;
					default :
						game.setScreen(new MainMenuScreen(game));	
				}
			}
		});
		
		rateBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(GOOGLE_PLAY_FREE_GAME_URL);
				//Gdx.net.openURI(AMAZON_FREE_GAME_URL);
			}
		});
		
		removeAdsBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(GOOGLE_PLAY_GAME_URL);
				//Gdx.net.openURI(AMAZON_GAME_URL);
			}
		});	
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
