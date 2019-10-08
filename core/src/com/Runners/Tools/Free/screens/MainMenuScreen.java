package com.Runners.Tools.Free.screens;

import com.Runners.Tools.Free.Donnees;
import com.Runners.Tools.Free.MyGdxGame;
import com.Runners.Tools.Free.ScrollPaneList;
import com.Runners.Tools.Free.Variables;
import com.Runners.Tools.Free.enums.ScreenEnum;
import com.Runners.Tools.Free.enums.TextFieldEnum;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenuScreen implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private LabelStyle labelStyle, labelStyleTitre, labelStyleOnglet, labelStyleTemps, labelStyleDistance;
	private Label labelSpeed, labelSpeedTitre, labelPace, labelPaceTitre,
					labelMarathon, labelMarathonTemps, labelDemiMarathon, labelDemiMarathonTemps, label10K, label10KTemps, labelCustomTemps, labelK,
					labelOngletConverter, labelOngletCalorie, labelOngletInterval,
					labelSeparateurSpeed, labelSeparateurPace;
	private Skin menuSkin;
	private TextureAtlas menuAtlas;
	TextFieldEnum textFieldEnum;
	private ScrollPaneList scrollPaneListCustom, scrollPaneListSpeedDecimal, scrollPaneListSpeed, scrollPaneListPaceS, scrollPaneListPaceM;
	private String stringMarathonTemps, stringDemiMarathonTemps, stringDixKTemps, stringCustomTemps;
	private Table tableSpeed, tablePace;
	int marathonTemps, demiMarathonTemps, dixKTemps, customTemps,
		shapeRendererX, shapeRendererTempsX, shapeRendererY1, shapeRendererY2, shapeRendererY3, shapeRendererY4,
		shapeRendererHeight, shapeRendererWidth, shapeRendererTempsWidth,
		ONGLET_HEIGHT, ONGLET_Y;
	private float tempsSpeed, tempsPace;
	private boolean Test1actif, Paceactif;
	private ImageButton optionBouton;
	private ImageButtonStyle optionBoutonStyle;
	private String stringOngletConverter1, stringOngletCalorie1, stringOngletInterval1, stringSpeed, stringPace, stringDemiMarathon;
	
	public MainMenuScreen(final MyGdxGame gam) {
		game = gam;
		Variables.ECRAN = ScreenEnum.Converter;
		
		Variables.TEMPS = TimeUtils.millis();
		System.out.println(Variables.TEMPS);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		/**************************DIFFERENTES LANGUES****************************/
		switch(Donnees.getLangage()){
			case 1 :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringSpeed = new String("Speed");
				stringPace = new String("Pace");
				stringDemiMarathon = new String("Half Marathon");
				break;
			case 2 :
				stringOngletConverter1 = new String("VITESSE/ CADENCE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVALLES");
				stringSpeed = new String("Vitesse");
				stringPace = new String("Cadence");
				stringDemiMarathon = new String("Demi Marathon");	
				break;
			case 3 :
				stringOngletConverter1 = new String("VELOCIDAD/ RITMO");
				stringOngletCalorie1 = new String("CALORIAS");
				stringOngletInterval1 = new String("INTERVALO");
				stringSpeed = new String("Velocidad");
				stringPace = new String("Ritmo");
				stringDemiMarathon = new String("Media Maratón");			
				break;
			default :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringSpeed = new String("Speed");
				stringPace = new String("Pace");
				stringDemiMarathon = new String("Half Marathon");
		}
		/*************************************************************************/
		
		shapeRendererX = (int)(0.078f*Gdx.graphics.getWidth());
		shapeRendererTempsX = (int)(0.372f*Gdx.graphics.getWidth());
		shapeRendererY1 = (int)(0.403f*Gdx.graphics.getHeight());
		shapeRendererY2 = (int)(0.313f*Gdx.graphics.getHeight());
		shapeRendererY3 = (int)(0.220f*Gdx.graphics.getHeight());
		shapeRendererY4 = (int)(0.127f*Gdx.graphics.getHeight());
		shapeRendererHeight = (int)(0.054f*Gdx.graphics.getHeight());
		shapeRendererWidth = (int)(0.226f*Gdx.graphics.getWidth());
		shapeRendererTempsWidth = (int)(0.458f*Gdx.graphics.getWidth());
		ONGLET_HEIGHT = (int)(0.07f*Gdx.graphics.getHeight());
		ONGLET_Y = (int)(Gdx.graphics.getHeight() - ONGLET_HEIGHT);
		
		//Skin ne contenant qu'un TextureAtlas
		menuSkin = new Skin();
		menuAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		menuSkin.addRegions(menuAtlas);
		
		stage = new Stage();
	
		labelStyle = new LabelStyle(game.assets.get("font1.ttf", BitmapFont.class), Color.BLACK);
		labelStyleTemps = new LabelStyle(game.assets.get("fontTemps.ttf", BitmapFont.class), Color.WHITE);
		labelStyleDistance = new LabelStyle(game.assets.get("fontDistance.ttf", BitmapFont.class), Color.WHITE);
		labelStyleTitre = new LabelStyle(game.assets.get("font2.ttf", BitmapFont.class), Color.BLACK);
		labelStyleOnglet = new LabelStyle(game.assets.get("fontOnglet.ttf", BitmapFont.class), Color.WHITE);
		
		Variables.compteurX1 = 0.065f*Gdx.graphics.getWidth();
		Variables.compteurX2 = 0.539f*Gdx.graphics.getWidth();
		Variables.compteurY = 0.662f*Gdx.graphics.getHeight();
		Variables.compteurWidth = 0.399f*Gdx.graphics.getWidth();
		Variables.compteurHeight = 0.112f*Gdx.graphics.getHeight();
		
		labelSpeedTitre = new Label(stringSpeed, labelStyleTitre);
		labelSpeedTitre.setY(Variables.compteurY + 3*Variables.compteurHeight/4 - labelSpeedTitre.getHeight()/2);
		labelSpeedTitre.setX(Variables.compteurX1 + Variables.compteurWidth/2 - labelSpeedTitre.getWidth()/2);
		labelPaceTitre = new Label(stringPace, labelStyleTitre);
		labelPaceTitre.setY(Variables.compteurY + 3*Variables.compteurHeight/4 - labelPaceTitre.getHeight()/2);
		labelPaceTitre.setX(Variables.compteurX2 + Variables.compteurWidth/2 - labelPaceTitre.getWidth()/2);
		
		/***********************************************************************************/
		/************************COMPTEURS DE VITESSE ET RYTHME*****************************/
		/***********************************************************************************/
		labelSpeed = new Label("km/h", labelStyle);
		labelSeparateurSpeed = new Label(".", labelStyle);
		scrollPaneListSpeedDecimal = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 100, Color.BLACK); 
		scrollPaneListSpeed = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 100, Color.BLACK);
		
		tableSpeed = new Table();
		tableSpeed.defaults().height(scrollPaneListSpeedDecimal.getItemHeight()).top().pad(1);
		tableSpeed.add(scrollPaneListSpeed.getScrollPane()).width(scrollPaneListSpeed.getWidth());
		tableSpeed.add(labelSeparateurSpeed).height(labelSeparateurSpeed.getHeight()).width(game.assets.get("fontInterval.ttf", BitmapFont.class).getBounds(".").width).top();
		tableSpeed.add(scrollPaneListSpeedDecimal.getScrollPane()).width(scrollPaneListSpeedDecimal.getWidth());
		tableSpeed.add(labelSpeed).width(labelSpeed.getWidth()).height(labelSeparateurSpeed.getHeight()).space(5).top();
		tableSpeed.setFillParent(true);
		tableSpeed.setX((tableSpeed.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + Variables.compteurX1 + Variables.compteurWidth/2 - tableSpeed.getPrefWidth()/2);
		tableSpeed.setY((tableSpeed.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + Variables.compteurY + Variables.compteurHeight/4 - 0.5f*tableSpeed.getPrefHeight());
		
		labelPace = new Label("min/km", labelStyle);
		labelSeparateurPace = new Label(":", labelStyle);
		scrollPaneListPaceS = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 60, Color.BLACK); 
		scrollPaneListPaceM = new ScrollPaneList(game.assets.get("font1.ttf", BitmapFont.class), 60, Color.BLACK);
		
		tablePace = new Table();
		tablePace.defaults().height(scrollPaneListPaceS.getItemHeight()).top().pad(1);
		tablePace.add(scrollPaneListPaceM.getScrollPane()).width(scrollPaneListPaceM.getWidth());
		tablePace.add(labelSeparateurPace).height(labelSeparateurPace.getHeight()).width(game.assets.get("fontInterval.ttf", BitmapFont.class).getBounds(".").width).top();
		tablePace.add(scrollPaneListPaceS.getScrollPane()).width(scrollPaneListPaceS.getWidth());
		tablePace.add(labelPace).height(labelSeparateurPace.getHeight()).width(labelPace.getWidth()).space(5).top();
		tablePace.setFillParent(true);
		tablePace.setX((tablePace.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + Variables.compteurX2 + Variables.compteurWidth/2 - tablePace.getPrefWidth()/2);
		tablePace.setY(tableSpeed.getY());
		
		Test1actif = true;
		Paceactif = false;
		
		/***********************************************************************************/
		/***********************Affichage des distances et temps****************************/
		/***********************************************************************************/	
		labelMarathon = new Label("Marathon", labelStyleDistance);
		labelMarathon.setX(47*Gdx.graphics.getWidth()/100 - labelMarathon.getWidth());
		labelMarathon.setY(shapeRendererY1 + shapeRendererHeight/2 - labelMarathon.getHeight()/2);
		marathonTemps = 0;
		stringMarathonTemps = String.format("%02d:%02d:%02d",
									marathonTemps/3600, 
									(marathonTemps%3600)/60,
									marathonTemps%60);
		labelMarathonTemps = new Label(stringMarathonTemps, labelStyleTemps);
		labelMarathonTemps.setText("-- : -- : --");
		labelMarathonTemps.setWidth(game.assets.get("fontTemps.ttf", BitmapFont.class).getBounds(labelMarathonTemps.getText()).width);
		labelMarathonTemps.setX(55*Gdx.graphics.getWidth()/100);
		labelMarathonTemps.setY(labelMarathon.getY());
		labelMarathonTemps.setAlignment(Align.center);
		
		labelDemiMarathon = new Label(stringDemiMarathon, labelStyleDistance);
		labelDemiMarathon.setX(47*Gdx.graphics.getWidth()/100 - labelDemiMarathon.getWidth());
		labelDemiMarathon.setY(shapeRendererY2 + shapeRendererHeight/2 - labelDemiMarathon.getHeight()/2);
		demiMarathonTemps = 0;
		stringDemiMarathonTemps = String.format("%02d : %02d : %02d",
									demiMarathonTemps/3600, 
									(demiMarathonTemps%3600)/60,
									demiMarathonTemps%60);
		labelDemiMarathonTemps = new Label(stringDemiMarathonTemps, labelStyleTemps);
		labelDemiMarathonTemps.setText("-- : -- : --");
		labelDemiMarathonTemps.setWidth(game.assets.get("fontTemps.ttf", BitmapFont.class).getBounds(labelMarathonTemps.getText()).width);
		labelDemiMarathonTemps.setX(labelMarathonTemps.getX());
		labelDemiMarathonTemps.setY(labelDemiMarathon.getY());
		labelDemiMarathonTemps.setAlignment(Align.center);
		
		label10K = new Label("10 Km", labelStyleDistance);
		label10K.setX(47*Gdx.graphics.getWidth()/100 - label10K.getWidth());
		label10K.setY(shapeRendererY3 + shapeRendererHeight/2 - label10K.getHeight()/2);
		dixKTemps = 0;
		stringDixKTemps = String.format("%02d : %02d : %02d",
									dixKTemps/3600, 
									(dixKTemps%3600)/60,
									dixKTemps%60);
		label10KTemps = new Label(stringDixKTemps, labelStyleTemps);
		label10KTemps.setText("-- : -- : --");
		label10KTemps.setWidth(game.assets.get("fontTemps.ttf", BitmapFont.class).getBounds(labelMarathonTemps.getText()).width);
		label10KTemps.setX(labelMarathonTemps.getX());
		label10KTemps.setY(label10K.getY());
		label10KTemps.setAlignment(Align.center);
		
		labelK = new Label(" Km", labelStyleDistance);
		labelK.setX(47*Gdx.graphics.getWidth()/100 - labelK.getWidth());
		labelK.setY(shapeRendererY4 + shapeRendererHeight/2 - labelK.getHeight()/2);
		labelK.setAlignment(Align.right);
		customTemps = 0;
		stringCustomTemps = String.format("%02d : %02d : %02d",
				customTemps/3600, 
				(customTemps%3600)/60,
				customTemps%60);
		labelCustomTemps = new Label(stringCustomTemps, labelStyleTemps);
		labelCustomTemps.setText("-- : -- : --");
		labelCustomTemps.setWidth(game.assets.get("fontTemps.ttf", BitmapFont.class).getBounds(labelMarathonTemps.getText()).width);
		labelCustomTemps.setX(labelMarathonTemps.getX());
		labelCustomTemps.setY(shapeRendererY4 + shapeRendererHeight/2 - labelCustomTemps.getHeight()/2);
		labelCustomTemps.setAlignment(Align.center);
		/***********************************************************************************/
		
		/*****************************DISTANCE PERSONNELLES*********************************/
		scrollPaneListCustom = new ScrollPaneList(game.assets.get("fontDistance.ttf", BitmapFont.class), 1000, Color.WHITE);
		scrollPaneListCustom.setX(labelK.getX() - scrollPaneListCustom.getWidth());
		scrollPaneListCustom.setY(shapeRendererY4 + shapeRendererHeight/2 - 143*scrollPaneListCustom.getList().getItemHeight()/200);
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
		
		stage.addActor(labelSpeedTitre);
		stage.addActor(labelPaceTitre);
		stage.addActor(labelMarathon);
		stage.addActor(labelMarathonTemps);
		stage.addActor(labelDemiMarathon);
		stage.addActor(labelDemiMarathonTemps);
		stage.addActor(label10K);
		stage.addActor(label10KTemps);
		stage.addActor(labelCustomTemps);
		stage.addActor(labelK);
		stage.addActor(scrollPaneListCustom.getScrollPane());
		stage.addActor(labelOngletConverter);
		stage.addActor(labelOngletCalorie);
		stage.addActor(labelOngletInterval);
		stage.addActor(tableSpeed);
		stage.addActor(tablePace);
		stage.addActor(optionBouton);
		
		System.out.println("Nombre de ms pour préparer l'écran = " + (TimeUtils.millis() - Variables.TEMPS));
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
		game.batch.draw(menuAtlas.findRegion("Barre"), Variables.ONGLET_X2, ONGLET_Y, 2 * Variables.ONGLET_WIDTH, ONGLET_HEIGHT);
		game.batch.end();
		
		scrollPaneListCustom.actif();
		
		stage.act();
		stage.draw();

		if(scrollPaneListPaceS.scrollPane.isPanning() || scrollPaneListPaceM.scrollPane.isPanning()){
			Paceactif = true;
			Test1actif = false;
		}
		else if(scrollPaneListSpeedDecimal.scrollPane.isPanning() || scrollPaneListSpeed.scrollPane.isPanning()){
			Paceactif = false;
			Test1actif = true;
		}
		
		if(Test1actif){
			scrollPaneListSpeedDecimal.actif();
			scrollPaneListSpeed.actif();
			if(Integer.parseInt(scrollPaneListSpeedDecimal.getSelected()) == 0 && Integer.parseInt(scrollPaneListSpeed.getSelected()) == 0)
				tempsSpeed = 0;
			else tempsSpeed = 60/((Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100 + Float.parseFloat(scrollPaneListSpeed.getSelected()))/60);
			
			if(tempsSpeed > 3598)
				tempsSpeed = 3598;
			
			scrollPaneListPaceS.setSelectedIndex((int)(tempsSpeed%60));
			scrollPaneListPaceS.setScrollY(scrollPaneListPaceS.getItemHeight() * (int)(tempsSpeed%60));
			scrollPaneListPaceM.setSelectedIndex((int)(tempsSpeed/60));
			scrollPaneListPaceM.setScrollY(scrollPaneListPaceM.getItemHeight() * (int)(tempsSpeed/60));
		}
		else if(Paceactif){
			scrollPaneListPaceS.actif();
			scrollPaneListPaceM.actif();
			if(Integer.parseInt(scrollPaneListPaceS.getSelected()) == 0 && Integer.parseInt(scrollPaneListPaceM.getSelected()) == 0)
				tempsPace = 0;
			else tempsPace = 1/((Float.parseFloat(scrollPaneListPaceS.getSelected())/60 + Float.parseFloat(scrollPaneListPaceM.getSelected()))/60);
			
			if (tempsPace > 99)
				tempsPace = 99;
			scrollPaneListSpeedDecimal.setSelectedIndex((int)(100*(tempsPace%1)));
			scrollPaneListSpeedDecimal.setScrollY(scrollPaneListSpeedDecimal.getItemHeight() * (int)(100*(tempsPace%1)));
			scrollPaneListSpeed.setSelectedIndex((int)(tempsPace));											//VOIR BUG ICI quand minute = 00 et secondes > 00
			scrollPaneListSpeed.setScrollY(scrollPaneListSpeed.getItemHeight() * (int)(tempsPace));
		}
		
		//Gestion des unités Système Métrique/Système Impérial
		if(Donnees.getMETRIC_SYSTEM()){
			labelSpeed.setText("km/h");
			labelPace.setText("min/km");
			label10K.setText("10 Km");
			labelK.setText(" Km");
		}
		else {
			labelSpeed.setText("mph");
			labelPace.setText("min/mi");
			label10K.setText("6.2 mi");
			labelK.setText(" mi");	
		}
		
		labelK.setX(47*Gdx.graphics.getWidth()/100 - labelK.getWidth());
		labelK.setWidth(game.assets.get("fontDistance.ttf", BitmapFont.class).getBounds(labelK.getText()).width);
		scrollPaneListCustom.setX(labelK.getX() - scrollPaneListCustom.getWidth());
		
		//Affichage des temps de course calculés	
		if(Integer.parseInt(scrollPaneListSpeed.getSelected()) == 0 && Integer.parseInt(scrollPaneListSpeedDecimal.getSelected()) == 0){
			labelMarathonTemps.setText("-- : -- : --");
			labelDemiMarathonTemps.setText("-- : -- : --");
			label10KTemps.setText("-- : -- : --");
			labelCustomTemps.setText("-- : -- : --");
		}		
		else {
			if(Donnees.getMETRIC_SYSTEM()){
				marathonTemps = (int)(3600*42.195/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				demiMarathonTemps = (int)(3600*21.0975/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				dixKTemps = (int)(3600*10/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				customTemps = (int)(3600*Integer.parseInt(scrollPaneListCustom.getSelected())/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
			}
			else {
				marathonTemps = (int)(3600*26.21875/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				demiMarathonTemps = (int)(3600*13.109375/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				dixKTemps = (int)(3600*6.2137119/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
				customTemps = (int)(3600*Integer.parseInt(scrollPaneListCustom.getSelected())/(Float.parseFloat(scrollPaneListSpeed.getSelected()) + Float.parseFloat(scrollPaneListSpeedDecimal.getSelected())/100));
			}
			
			stringMarathonTemps = String.format("%02d : %02d : %02d",
										marathonTemps/3600, 
										(marathonTemps%3600)/60,
										marathonTemps%60);
			labelMarathonTemps.setText(stringMarathonTemps);
			
			stringDemiMarathonTemps = String.format("%02d : %02d : %02d",
										demiMarathonTemps/3600, 
										(demiMarathonTemps%3600)/60,
										demiMarathonTemps%60);
			labelDemiMarathonTemps.setText(stringDemiMarathonTemps);
			
			stringDixKTemps = String.format("%02d : %02d : %02d",
										dixKTemps/3600, 
										(dixKTemps%3600)/60,
										dixKTemps%60);
			label10KTemps.setText(stringDixKTemps);
			
			stringCustomTemps = String.format("%02d : %02d : %02d",
					customTemps/3600, 
					(customTemps%3600)/60,
					customTemps%60);
			labelCustomTemps.setText(stringCustomTemps);
		}
		
		//Positionnement des temps
		labelMarathonTemps.setX(55*Gdx.graphics.getWidth()/100);
		labelMarathonTemps.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(labelMarathonTemps.getText()).width);
		labelDemiMarathonTemps.setX(55*Gdx.graphics.getWidth()/100);
		labelDemiMarathonTemps.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(labelDemiMarathonTemps.getText()).width);
		label10KTemps.setX(55*Gdx.graphics.getWidth()/100);
		label10KTemps.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(label10KTemps.getText()).width);
		labelCustomTemps.setX(55*Gdx.graphics.getWidth()/100);
		labelCustomTemps.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(labelCustomTemps.getText()).width);
		
		//Utilisation des onglets
		if(Gdx.input.justTouched() && Gdx.input.getX() > Variables.ONGLET_X2 && Gdx.input.isTouched() && Gdx.input.getX() < Variables.ONGLET_X3 && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y)){
			Variables.TEMPS = TimeUtils.millis();
			game.setScreen(new CalorieScreen(game));
		}
		
		if(Gdx.input.justTouched() && Gdx.input.getX() > Variables.ONGLET_X3 && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y)){
			Variables.TEMPS = TimeUtils.millis();
			game.setScreen(new IntervalScreen(game));
		}
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
		
		labelSpeed.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(false);
				}			
				else if(!Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(true);
				}
			}
		});
		
		labelPace.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(false);
				}			
				else if(!Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(true);
				}
			}
		});
		
		label10K.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(false);
				}			
				else if(!Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(true);
				}
			}
		});
		
		labelK.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(false);
				}			
				else if(!Donnees.getMETRIC_SYSTEM()){
					Donnees.setMETRIC_SYSTEM(true);
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
		menuAtlas.dispose();
		scrollPaneListCustom.dispose();
		scrollPaneListSpeedDecimal.dispose();
		scrollPaneListSpeed.dispose();
		scrollPaneListPaceS.dispose();
		scrollPaneListPaceM.dispose();
	}
}
