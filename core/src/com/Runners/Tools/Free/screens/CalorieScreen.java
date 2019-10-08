package com.Runners.Tools.Free.screens;

import com.Runners.Tools.Free.Donnees;
import com.Runners.Tools.Free.MyGdxGame;
import com.Runners.Tools.Free.ScrollPaneList;
import com.Runners.Tools.Free.Variables;
import com.Runners.Tools.Free.enums.ScreenEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class CalorieScreen implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private LabelStyle labelStyle, labelStyleOnglet, labelStyleTitre, labelStyleCalorie, labelStyleUnit�;
	private Label labelOngletConverter, labelOngletCalorie, labelOngletInterval,
					labelPoids, labelPoidsUnit�, labelDistance, labelDistanceUnit�, labelCalorie, labelCalorieCalcul�e;
	private Skin skin, menuSkin;
	private TextureAtlas menuAtlas;
	private Table tablePoids, tableDistance;
	private ScrollPaneList scrollPaneListPoids, scrollPaneListDistance;
	boolean initPoids;
	private ImageButton optionBouton;
	private ImageButtonStyle optionBoutonStyle;
	private String stringOngletConverter1, stringOngletCalorie1, stringOngletInterval1, stringPoids, stringDistance;
	
	float chiffreDistance, chiffrePoids;
	int ONGLET_HEIGHT, ONGLET_Y;
	
	public CalorieScreen(final MyGdxGame gam) {
		game = gam;
		
		System.out.println("Nombre de ms pour changer d'�cran = " + (TimeUtils.millis() - Variables.TEMPS));
		
		Variables.ECRAN = ScreenEnum.Calorie;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		/**************************DIFFERENTES LANGUES****************************/
		switch(Donnees.getLangage()){
			case 1 :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringPoids = new String("Weight");
				stringDistance = new String("Distance");
				break;
			case 2 :
				stringOngletConverter1 = new String("VITESSE/ CADENCE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVALLES");
				stringPoids = new String("Poids");
				stringDistance = new String("Distance");	
				break;
			case 3 :
				stringOngletConverter1 = new String("VELOCIDAD/ RITMO");
				stringOngletCalorie1 = new String("CALORIAS");
				stringOngletInterval1 = new String("INTERVALO");
				stringPoids = new String("Peso");
				stringDistance = new String("Distancia");			
				break;
			default :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringPoids = new String("Weight");
				stringDistance = new String("Distance");
		}	
		/*************************************************************************/
		
		menuSkin = new Skin();
		menuAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		menuSkin.addRegions(menuAtlas);
		
		stage = new Stage();
		
		initPoids = false;
		
		ONGLET_HEIGHT = (int)(0.07f*Gdx.graphics.getHeight());
		ONGLET_Y = (int)(Gdx.graphics.getHeight() - ONGLET_HEIGHT);
		
		Variables.compteurX1 = 0.065f*Gdx.graphics.getWidth();
		Variables.compteurX2 = 0.539f*Gdx.graphics.getWidth();
		Variables.compteurY = 0.662f*Gdx.graphics.getHeight();
		Variables.compteurWidth = 0.399f*Gdx.graphics.getWidth();
		Variables.compteurHeight = 0.112f*Gdx.graphics.getHeight();
	
		labelStyle = new LabelStyle(game.assets.get("font1.ttf", BitmapFont.class), Color.WHITE);
		labelStyleUnit� = new LabelStyle(game.assets.get("font1.ttf", BitmapFont.class), Color.BLACK);
		labelStyleTitre = new LabelStyle(game.assets.get("font2.ttf", BitmapFont.class), Color.BLACK);
		labelStyleOnglet = new LabelStyle(game.assets.get("fontOnglet.ttf", BitmapFont.class), Color.WHITE);
		labelStyleCalorie = new LabelStyle(game.assets.get("fontCalorie.ttf", BitmapFont.class), Color.WHITE);
		
		labelPoids = new Label(stringPoids, labelStyleTitre);
		labelPoids.setX(Variables.compteurX1 + Variables.compteurWidth/2 - labelPoids.getWidth()/2);
		labelPoids.setY(Variables.compteurY + 3*Variables.compteurHeight/4 - labelPoids.getHeight()/2);
		labelDistance = new Label(stringDistance, labelStyleTitre);
		labelDistance.setX(Variables.compteurX2 + Variables.compteurWidth/2 - labelDistance.getWidth()/2);
		labelDistance.setY(Variables.compteurY + 3*Variables.compteurHeight/4 - labelDistance.getHeight()/2);
		labelCalorieCalcul�e = new Label("0", labelStyleCalorie);
		labelCalorieCalcul�e.setX(Gdx.graphics.getWidth()/2 - labelCalorieCalcul�e.getWidth()/2);
		labelCalorieCalcul�e.setY(3*Variables.compteurY/5 - labelCalorieCalcul�e.getHeight()/2);
		labelCalorieCalcul�e.setWidth(game.assets.get("fontCalorie.ttf", BitmapFont.class).getBounds(labelCalorieCalcul�e.getText()).width);
		labelCalorie = new Label("kcal", labelStyle);
		labelCalorie.setX(Gdx.graphics.getWidth()/2 - labelCalorie.getWidth()/2);
		labelCalorie.setY(labelCalorieCalcul�e.getY() - Gdx.graphics.getHeight()/40);
		
		/*****************************S�LECTION DES DONN�ES*********************************/	
		scrollPaneListPoids = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 500, Color.BLACK);
		scrollPaneListPoids.setSelectedIndex(Donnees.getPoids());
		scrollPaneListPoids.setScrollY(scrollPaneListPoids.getItemHeight() * Donnees.getPoids());
		labelPoidsUnit� = new Label(Donnees.getPoidsUnite(), labelStyleUnit�);
		
		tablePoids = new Table();
		tablePoids.defaults().height(scrollPaneListPoids.getItemHeight()).top().pad(3);
		tablePoids.add(scrollPaneListPoids.getScrollPane()).width(scrollPaneListPoids.getWidth());
		tablePoids.add(labelPoidsUnit�).height(scrollPaneListPoids.getItemHeight()).width(game.assets.get("font1.ttf", BitmapFont.class).getBounds(labelPoidsUnit�.getText()).width).top();
		tablePoids.setFillParent(true);
		tablePoids.setX((tablePoids.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + Variables.compteurX1 + Variables.compteurWidth/2 - tablePoids.getPrefWidth()/2);
		tablePoids.setY((tablePoids.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + Variables.compteurY + Variables.compteurHeight/4 - 0.5f*tablePoids.getPrefHeight());
		
		scrollPaneListDistance = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 10000, Color.BLACK);;
		labelDistanceUnit� = new Label(Donnees.getDistanceUnite(), labelStyleUnit�);
		tableDistance = new Table();
		tableDistance.defaults().height(scrollPaneListDistance.getItemHeight()).top().pad(3);
		tableDistance.add(scrollPaneListDistance.getScrollPane()).width(scrollPaneListDistance.getWidth());
		tableDistance.add(labelDistanceUnit�).height(scrollPaneListDistance.getItemHeight()).width(game.assets.get("font1.ttf", BitmapFont.class).getBounds(labelDistanceUnit�.getText()).width).top();
		tableDistance.setFillParent(true);
		tableDistance.setX((tableDistance.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + Variables.compteurX2 + Variables.compteurWidth/2 - tableDistance.getPrefWidth()/2);
		tableDistance.setY((tableDistance.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + Variables.compteurY + Variables.compteurHeight/4 - 0.5f*tableDistance.getPrefHeight());
		/***********************************************************************************/
		
		/************************************ONGLETS****************************************/
		labelOngletConverter = new Label(stringOngletConverter1, labelStyleOnglet);
		labelOngletConverter.setWidth(Variables.ONGLET_WIDTH);
		labelOngletConverter.setX(Variables.ONGLET_X2/2 - labelOngletConverter.getWidth()/2);
		labelOngletConverter.setY(ONGLET_Y + 2*ONGLET_HEIGHT/4 - labelOngletConverter.getHeight()/2);
		labelOngletConverter.setAlignment(Align.center);
		labelOngletConverter.setWrap(true);
		
		labelOngletCalorie = new Label(stringOngletCalorie1, labelStyleOnglet);
		labelOngletCalorie.setX(Variables.ONGLET_X2 + Variables.ONGLET_WIDTH/2 - labelOngletCalorie.getWidth()/2);
		labelOngletCalorie.setY(ONGLET_Y + 2*ONGLET_HEIGHT/4 - labelOngletCalorie.getHeight()/2);

		labelOngletInterval = new Label(stringOngletInterval1, labelStyleOnglet);
		labelOngletInterval.setX(Variables.ONGLET_X3 + Variables.ONGLET_WIDTH/2 - labelOngletInterval.getWidth()/2);
		labelOngletInterval.setY(ONGLET_Y + 2*ONGLET_HEIGHT/4 - labelOngletInterval.getHeight()/2);
		/***********************************************************************************/

		/**********************************BOUTON OPTION************************************/
		optionBoutonStyle = new ImageButtonStyle();
		optionBoutonStyle.imageUp = menuSkin.getDrawable("Option");
		optionBoutonStyle.imageDown = menuSkin.getDrawable("OptionChecked");
		optionBouton = new ImageButton(optionBoutonStyle);
		optionBouton.setWidth(Gdx.graphics.getWidth()/10);
		optionBouton.setHeight(optionBouton.getWidth());
		optionBouton.setX(9*Gdx.graphics.getWidth()/10 - optionBouton.getWidth()/2);
		optionBouton.setY(Gdx.graphics.getWidth()/10 - optionBouton.getWidth()/2);
		/***********************************************************************************/
		
		stage.addActor(labelOngletConverter);
		stage.addActor(labelOngletCalorie);
		stage.addActor(labelOngletInterval);
		stage.addActor(labelPoids);
		stage.addActor(labelDistance);
		stage.addActor(labelCalorie);
		stage.addActor(labelCalorieCalcul�e);
		stage.addActor(tablePoids);
		stage.addActor(tableDistance);
		stage.addActor(optionBouton);
		
		System.out.println("Nombre de ms pour afficher l'�cran = " + (TimeUtils.millis() - Variables.TEMPS));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.383f, 0.453f, 0.508f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.setColor(1,1,1,1);
		game.batch.draw(menuAtlas.findRegion("CelluleVitesse"), Variables.compteurX1, Variables.compteurY, Variables.compteurWidth, Variables.compteurHeight);
		game.batch.draw(menuAtlas.findRegion("CelluleVitesse"), Variables.compteurX2, Variables.compteurY, Variables.compteurWidth, Variables.compteurHeight);
		game.batch.setColor(0.309f, 0.367f, 0.410f, 1);
		game.batch.draw(menuAtlas.findRegion("Barre"), Variables.ONGLET_X1, ONGLET_Y, Variables.ONGLET_WIDTH, ONGLET_HEIGHT);
		game.batch.draw(menuAtlas.findRegion("Barre"), Variables.ONGLET_X3, ONGLET_Y, Variables.ONGLET_WIDTH, ONGLET_HEIGHT);
		game.batch.end();
		
		//Permet d'initialiser le scrollPane � la valeur enregistr�e
		if(!initPoids && scrollPaneListPoids.getScrollY() != (scrollPaneListPoids.getItemHeight() * Donnees.getPoids()) && Donnees.getPoids() != 199){
			scrollPaneListPoids.setSelectedIndex(Donnees.getPoids());
			scrollPaneListPoids.setScrollY(scrollPaneListPoids.getItemHeight() * Donnees.getPoids());
			if (scrollPaneListPoids.getScrollY() != (scrollPaneListPoids.getItemHeight() * Donnees.getPoids())) initPoids = false;
			else initPoids = true;
		}
		else scrollPaneListPoids.actif();
		
		scrollPaneListDistance.actif();
		
		stage.act();
		stage.draw();
		
		/***********************************************************************************/
		/*******************************CALCUL DES CALORIES*********************************/
		if(labelPoidsUnit�.textEquals("kg"))
			chiffrePoids = Float.parseFloat(scrollPaneListPoids.getSelected());
		else if(labelPoidsUnit�.textEquals("lb"))
			chiffrePoids = 0.45359237f * Float.parseFloat(scrollPaneListPoids.getSelected());
		
		if(labelDistanceUnit�.textEquals("km"))
			chiffreDistance = Float.parseFloat(scrollPaneListDistance.getSelected());
		else if(labelDistanceUnit�.textEquals("mi"))
			chiffreDistance = 1.609344f * Float.parseFloat(scrollPaneListDistance.getSelected());
		
		labelCalorieCalcul�e.setText(Integer.toString((int)(chiffrePoids * chiffreDistance)));
		labelCalorieCalcul�e.setWidth(game.assets.get("fontCalorie.ttf", BitmapFont.class).getBounds(labelCalorieCalcul�e.getText()).width);
		labelCalorieCalcul�e.setX(Gdx.graphics.getWidth()/2 - labelCalorieCalcul�e.getWidth()/2);
		/***********************************************************************************/
		
		Donnees.setPoids(Integer.parseInt(scrollPaneListPoids.getSelected()));
		
		//Utilisation des onglets
		if(Gdx.input.justTouched() && Gdx.input.getX() < Variables.ONGLET_WIDTH && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y))
			game.setScreen(new MainMenuScreen(game));
		
		if(Gdx.input.justTouched() && Gdx.input.getX() > Variables.ONGLET_X3 && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y))
			game.setScreen(new IntervalScreen(game));
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		optionBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new OptionScreen(game));
			}
		});
		
		labelPoidsUnit�.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(labelPoidsUnit�.textEquals("kg")){
					labelPoidsUnit�.setText("lb");
					Donnees.setPoidsUnite("lb");
				}			
				else if(labelPoidsUnit�.textEquals("lb")){
					labelPoidsUnit�.setText("kg");
					Donnees.setPoidsUnite("kg");
				}
			}
		});
		
		labelDistanceUnit�.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(labelDistanceUnit�.textEquals("km")){
					labelDistanceUnit�.setText("mi");
					Donnees.setDistanceUnite("mi");
				}
				else if(labelDistanceUnit�.textEquals("mi")){
					labelDistanceUnit�.setText("km");
					Donnees.setDistanceUnite("km");	
				}
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
		stage.dispose();
		menuSkin.dispose();
		skin.dispose();
		menuAtlas.dispose();
		scrollPaneListPoids.dispose();
		scrollPaneListDistance.dispose();
	}

}
