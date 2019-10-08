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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class IntervalScreen implements Screen{
	
	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private Skin menuSkin;
	private TextureAtlas menuAtlas;
	private LabelStyle labelStyleSeparateur, labelStyleOnglet, labelStyleTitre, labelStyleCalorie, labelStyleMessage;
	private Label labelOngletConverter, labelOngletCalorie, labelOngletInterval,
					labelTimer, labelEchauffement, labelSeparateurEchauffement, labelRepos, labelSeparateurRepos, labelEffort, labelSeparateurEffort, labelCycle,
					labelMessage;
	private ScrollPaneList scrollPaneListEchauffementS, scrollPaneListEchauffementM, scrollPaneListReposS, scrollPaneListReposM, scrollPaneListEffortS, scrollPaneListEffortM, scrollPaneListCycle;
	private Table tableEchauffement, tableRepos, tableEffort, tableCycle;
	
	private int shapeRendererX, shapeRendererY1, shapeRendererY2, shapeRendererY3, shapeRendererY4, shapeRendererWidth, shapeRendererHeight,
				ONGLET_HEIGHT, ONGLET_Y;
	
	public static int tempsTotal, tempsEchauffement, tempsRepos, tempsEffort, nbCycle;
	private String stringTimer;
	private ImageButton PlayButton, optionBouton;
	private ImageButtonStyle imageButtonStylePlay, optionBoutonStyle;
	private boolean initInterval;
	private String stringOngletConverter1, stringOngletCalorie1, stringOngletInterval1, stringEchauffement, stringRepos, stringEffort, stringCycle;
	float r, g, b, r2, g2, b2;
	
	
	public IntervalScreen(final MyGdxGame gam){
		game = gam;
		
		System.out.println("Nombre de ms pour changer d'écran = " + (TimeUtils.millis() - Variables.TEMPS));
		
		Variables.ECRAN = ScreenEnum.Interval;
		
		Donnees.setINTERSTITIAL_TRIGGER(Donnees.getINTERSTITIAL_TRIGGER() - 1);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		/**************************DIFFERENTES LANGUES****************************/
		switch(Donnees.getLangage()){
			case 1 :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringEchauffement = new String("Warmup");
				stringEffort = new String("Work");
				stringRepos = new String("Rest");
				stringCycle = new String("Repeat");
				break;
			case 2 :
				stringOngletConverter1 = new String("VITESSE/ CADENCE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVALLES");
				stringEchauffement = new String("Échauffement");
				stringEffort = new String("Effort");
				stringRepos = new String("Repos");
				stringCycle = new String("Répétition");	
				break;
			case 3 :
				stringOngletConverter1 = new String("VELOCIDAD/ RITMO");
				stringOngletCalorie1 = new String("CALORIAS");
				stringOngletInterval1 = new String("INTERVALO");
				stringEchauffement = new String("Calentamiento");
				stringEffort = new String("Esfuerzo");
				stringRepos = new String("Resto");
				stringCycle = new String("Repeticion");			
				break;
			default :
				stringOngletConverter1 = new String("SPEED/PACE");
				stringOngletCalorie1 = new String("CALORIES");
				stringOngletInterval1 = new String("INTERVAL");
				stringEchauffement = new String("Warmup");
				stringEffort = new String("Work");
				stringRepos = new String("Rest");
				stringCycle = new String("Repeat");
		}
		/*************************************************************************/
		
		stage = new Stage();
		initInterval = false;
		
		//Skin ne contenant qu'un TextureAtlas
		menuSkin = new Skin();
		menuAtlas = new TextureAtlas("Images.pack");
		menuSkin.addRegions(menuAtlas);
		
		imageButtonStylePlay = new ImageButtonStyle();
		imageButtonStylePlay.imageUp = menuSkin.getDrawable("Play");
		imageButtonStylePlay.imageDown = menuSkin.getDrawable("Stop");
		imageButtonStylePlay.imageChecked = menuSkin.getDrawable("Stop");
		PlayButton = new ImageButton(imageButtonStylePlay);
		PlayButton.setWidth(Gdx.graphics.getWidth()/3);
		PlayButton.setHeight(PlayButton.getWidth());
		PlayButton.setX(Gdx.graphics.getWidth()/2 - PlayButton.getWidth()/2);
		PlayButton.setY(Gdx.graphics.getHeight()/20);
		if(Variables.TIMER_ACTIF)
			PlayButton.setChecked(true);
		
		shapeRendererX = (int)(5 * Gdx.graphics.getWidth()/100); 
		shapeRendererY1 = (int)(80 * Gdx.graphics.getHeight()/100); 
		shapeRendererY2 = (int)(70 * Gdx.graphics.getHeight()/100); 
		shapeRendererY3 = (int)(60 * Gdx.graphics.getHeight()/100); 
		shapeRendererY4 = (int)(50 * Gdx.graphics.getHeight()/100); 
		shapeRendererWidth = (int)(9*Gdx.graphics.getWidth()/10); 
		shapeRendererHeight = (int)(0.068f*Gdx.graphics.getHeight()); 
		ONGLET_HEIGHT = (int)(0.07f*Gdx.graphics.getHeight());
		ONGLET_Y = (int)(Gdx.graphics.getHeight() - ONGLET_HEIGHT);
		
		labelStyleSeparateur = new LabelStyle(game.assets.get("fontInterval.ttf", BitmapFont.class), Color.BLACK);
		labelStyleTitre = new LabelStyle(game.assets.get("font2.ttf", BitmapFont.class), Color.BLACK);
		labelStyleOnglet = new LabelStyle(game.assets.get("fontOnglet.ttf", BitmapFont.class), Color.WHITE);
		labelStyleCalorie = new LabelStyle(game.assets.get("fontCalorie.ttf", BitmapFont.class), Color.WHITE);
		labelStyleMessage = new LabelStyle(game.assets.get("fontMessage.ttf", BitmapFont.class), Color.WHITE);
		
		/*****************************SELECTION DES DURÉES***********************************/
		scrollPaneListEchauffementS = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListEchauffementS.setSelectedIndex(Donnees.getWarmupS());
		scrollPaneListEchauffementS.setScrollY(scrollPaneListEchauffementS.getItemHeight() * Donnees.getWarmupS());
		
		scrollPaneListEchauffementM = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListEchauffementM.setSelectedIndex(Donnees.getWarmupM());
		scrollPaneListEchauffementM.setScrollY(scrollPaneListEchauffementM.getItemHeight() * Donnees.getWarmupM());
		
		scrollPaneListReposS = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListReposS.setSelectedIndex(Donnees.getRestS());
		scrollPaneListReposS.setScrollY(scrollPaneListReposS.getItemHeight() * Donnees.getRestS()); 
		
		scrollPaneListReposM = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListReposM.setSelectedIndex(Donnees.getRestM());
		scrollPaneListReposM.setScrollY(scrollPaneListReposM.getItemHeight() * Donnees.getRestM());
		
		scrollPaneListEffortS = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListEffortS.setSelectedIndex(Donnees.getWorkS());
		scrollPaneListEffortS.setScrollY(scrollPaneListEffortS.getItemHeight() * Donnees.getWorkS());
		
		scrollPaneListEffortM = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 60, Color.BLACK);
		scrollPaneListEffortM.setSelectedIndex(Donnees.getWorkM());
		scrollPaneListEffortM.setScrollY(scrollPaneListEffortM.getItemHeight() * Donnees.getWorkM());
		
		scrollPaneListCycle = new ScrollPaneList(game.assets.get("fontInterval.ttf", BitmapFont.class), 100, Color.BLACK);
		scrollPaneListCycle.setSelectedIndex(Donnees.getCycle());
		scrollPaneListCycle.setScrollY(scrollPaneListCycle.getItemHeight() * Donnees.getCycle());
		
		labelEchauffement = new Label(stringEchauffement, labelStyleTitre);
		labelEchauffement.setX(1.2f * shapeRendererX);
		labelEchauffement.setY(shapeRendererY1 + shapeRendererHeight/2 - labelEchauffement.getHeight()/2);
		labelRepos = new Label(stringRepos, labelStyleTitre);
		labelRepos.setX(labelEchauffement.getX());
		labelRepos.setY(shapeRendererY3 + shapeRendererHeight/2 - labelRepos.getHeight()/2);
		labelEffort = new Label(stringEffort, labelStyleTitre);
		labelEffort.setX(labelEchauffement.getX());
		labelEffort.setY(shapeRendererY2 + shapeRendererHeight/2 - labelEffort.getHeight()/2);
		labelCycle = new Label(stringCycle, labelStyleTitre);
		labelCycle.setX(labelEchauffement.getX());
		labelCycle.setY(shapeRendererY4 + shapeRendererHeight/2 - labelCycle.getHeight()/2);
		
		labelSeparateurEchauffement = new Label(":", labelStyleSeparateur);
		labelSeparateurRepos = new Label(":", labelStyleSeparateur);
		labelSeparateurEffort = new Label(":", labelStyleSeparateur);
		
		tableEchauffement = new Table();
		tableRepos = new Table();
		tableEffort = new Table();
		tableCycle = new Table();
		
		tableEchauffement.defaults().height(scrollPaneListEchauffementS.getItemHeight()).top().pad(3);
		tableEchauffement.add(scrollPaneListEchauffementM.getScrollPane()).width(scrollPaneListEchauffementM.getWidth());
		tableEchauffement.add(labelSeparateurEchauffement).height(labelSeparateurEchauffement.getHeight()).width(game.assets.get("fontInterval.ttf", BitmapFont.class).getBounds(":").width).top();
		tableEchauffement.add(scrollPaneListEchauffementS.getScrollPane()).width(scrollPaneListEchauffementS.getWidth());
		tableEchauffement.setFillParent(true);
		tableEchauffement.setX((tableEchauffement.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + 0.8f * shapeRendererX + shapeRendererWidth - tableEchauffement.getPrefWidth());
		tableEchauffement.setY((tableEchauffement.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + shapeRendererY1 + shapeRendererHeight/2 - 0.6f * scrollPaneListEchauffementS.getItemHeight());
		
		tableEffort.defaults().height(scrollPaneListEffortS.getItemHeight()).top().pad(3);
		tableEffort.add(scrollPaneListEffortM.getScrollPane()).width(scrollPaneListEffortM.getWidth());
		tableEffort.add(labelSeparateurEffort).height(labelSeparateurEffort.getHeight()).width(game.assets.get("fontInterval.ttf", BitmapFont.class).getBounds(":").width).top();
		tableEffort.add(scrollPaneListEffortS.getScrollPane()).width(scrollPaneListEffortS.getWidth());
		tableEffort.setFillParent(true);
		tableEffort.setX((tableEffort.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + 0.8f * shapeRendererX + shapeRendererWidth - tableEffort.getPrefWidth());
		tableEffort.setY((tableEffort.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + shapeRendererY2 + shapeRendererHeight/2 - 0.6f * scrollPaneListEffortS.getItemHeight());
		
		tableRepos.defaults().height(scrollPaneListReposS.getItemHeight()).top().pad(3);
		tableRepos.add(scrollPaneListReposM.getScrollPane()).width(scrollPaneListReposM.getWidth());
		tableRepos.add(labelSeparateurRepos).height(labelSeparateurRepos.getHeight()).width(game.assets.get("fontInterval.ttf", BitmapFont.class).getBounds(":").width).top();
		tableRepos.add(scrollPaneListReposS.getScrollPane()).width(scrollPaneListReposS.getWidth());
		tableRepos.setFillParent(true);
		tableRepos.setX((tableRepos.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + 0.8f * shapeRendererX + shapeRendererWidth - tableRepos.getPrefWidth());
		tableRepos.setY((tableRepos.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + shapeRendererY3 + shapeRendererHeight/2 - 0.6f * scrollPaneListReposS.getItemHeight());

		tableCycle.defaults().height(scrollPaneListReposS.getItemHeight()).top().pad(3);
		tableCycle.add(scrollPaneListCycle.getScrollPane()).width(scrollPaneListReposM.getWidth());
		tableCycle.setFillParent(true);
		tableCycle.setX((tableCycle.getPrefWidth()/2 - Gdx.graphics.getWidth()/2) + 0.8f * shapeRendererX + shapeRendererWidth - tableCycle.getPrefWidth());
		tableCycle.setY((tableCycle.getPrefHeight()/2 - Gdx.graphics.getHeight()/2) + shapeRendererY4 + shapeRendererHeight/2 - 0.6f * scrollPaneListReposS.getItemHeight());
		
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
		labelMessage = new Label("", labelStyleMessage);
		labelMessage.setHeight(game.assets.get("fontMessage.ttf", BitmapFont.class).getBounds("0").height);
		labelMessage.setX(- Gdx.graphics.getWidth());
		labelMessage.setY(23*Gdx.graphics.getHeight()/30 - labelMessage.getHeight()/2);

		labelTimer = new Label("00:00:00", labelStyleCalorie);
		labelTimer.setHeight(game.assets.get("fontCalorie.ttf", BitmapFont.class).getBounds("0").height);
		labelTimer.setX(Gdx.graphics.getWidth()/2 - labelTimer.getWidth()/2);
		labelTimer.setY(11*Gdx.graphics.getHeight()/30 - labelTimer.getHeight()/2);
		
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
		stage.addActor(labelTimer);
		stage.addActor(tableEchauffement);
		stage.addActor(tableEffort);
		stage.addActor(tableRepos);
		stage.addActor(tableCycle);
		stage.addActor(PlayButton);
		stage.addActor(labelEchauffement);
		stage.addActor(labelEffort);
		stage.addActor(labelRepos);
		stage.addActor(labelCycle);
		stage.addActor(optionBouton);
		stage.addActor(labelMessage);
		
		r = 0.383f;
		g = 0.453f;
		b = 0.508f;
		r2 = 0.309f;
		g2 = 0.367f;
		b2 = 0.410f;
		
		System.out.println("Nombre de ms pour afficher l'écran = " + (TimeUtils.millis() - Variables.TEMPS));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.setColor(1,1,1,1);
		game.batch.draw(menuAtlas.findRegion("Barre"), shapeRendererX, shapeRendererY1, shapeRendererWidth, shapeRendererHeight);
		game.batch.draw(menuAtlas.findRegion("Barre"), shapeRendererX, shapeRendererY2, shapeRendererWidth, shapeRendererHeight);
		game.batch.draw(menuAtlas.findRegion("Barre"), shapeRendererX, shapeRendererY3, shapeRendererWidth, shapeRendererHeight);
		game.batch.draw(menuAtlas.findRegion("Barre"), shapeRendererX, shapeRendererY4, shapeRendererWidth, shapeRendererHeight);
		game.batch.setColor(0.309f, 0.367f, 0.410f, 1);
		game.batch.draw(menuAtlas.findRegion("Barre"), Variables.ONGLET_X1, ONGLET_Y, 2 * Variables.ONGLET_WIDTH, ONGLET_HEIGHT);
		game.batch.end();
		
		//Permet d'initialiser le scrollPane à la valeur enregistrée
		if(!initInterval && (scrollPaneListEchauffementS.getScrollY() != (scrollPaneListEchauffementS.getItemHeight() * Donnees.getWarmupS()) ||
								scrollPaneListEchauffementM.getScrollY() != (scrollPaneListEchauffementM.getItemHeight() * Donnees.getWarmupM()) ||
								scrollPaneListEffortS.getScrollY() != (scrollPaneListEffortS.getItemHeight() * Donnees.getWorkS()) ||
								scrollPaneListEffortM.getScrollY() != (scrollPaneListEffortM.getItemHeight() * Donnees.getWorkM()) ||
								scrollPaneListReposS.getScrollY() != (scrollPaneListReposS.getItemHeight() * Donnees.getRestS()) ||
								scrollPaneListReposM.getScrollY() != (scrollPaneListReposM.getItemHeight() * Donnees.getRestM()) ||
								scrollPaneListCycle.getScrollY() != (scrollPaneListCycle.getItemHeight() * Donnees.getCycle()))){
			scrollPaneListEchauffementS.setSelectedIndex(Donnees.getWarmupS());
			scrollPaneListEchauffementS.setScrollY(scrollPaneListEchauffementS.getItemHeight() * Donnees.getWarmupS());
			scrollPaneListEchauffementM.setSelectedIndex(Donnees.getWarmupM());
			scrollPaneListEchauffementM.setScrollY(scrollPaneListEchauffementM.getItemHeight() * Donnees.getWarmupM());
			scrollPaneListEffortS.setSelectedIndex(Donnees.getWorkS());
			scrollPaneListEffortS.setScrollY(scrollPaneListEffortS.getItemHeight() * Donnees.getWorkS());
			scrollPaneListEffortM.setSelectedIndex(Donnees.getWorkM());
			scrollPaneListEffortM.setScrollY(scrollPaneListEffortM.getItemHeight() * Donnees.getWorkM());
			scrollPaneListReposS.setSelectedIndex(Donnees.getRestS());
			scrollPaneListReposS.setScrollY(scrollPaneListReposS.getItemHeight() * Donnees.getRestS());
			scrollPaneListReposM.setSelectedIndex(Donnees.getRestM());
			scrollPaneListReposM.setScrollY(scrollPaneListReposM.getItemHeight() * Donnees.getRestM());
			scrollPaneListCycle.setSelectedIndex(Donnees.getCycle());
			scrollPaneListCycle.setScrollY(scrollPaneListCycle.getItemHeight() * Donnees.getCycle());
			
			if (scrollPaneListEchauffementS.getScrollY() != (scrollPaneListEchauffementS.getItemHeight() * Donnees.getWarmupS()) ||
					scrollPaneListEchauffementM.getScrollY() != (scrollPaneListEchauffementM.getItemHeight() * Donnees.getWarmupM()) ||
					scrollPaneListEffortS.getScrollY() != (scrollPaneListEffortS.getItemHeight() * Donnees.getWorkS()) ||
					scrollPaneListEffortM.getScrollY() != (scrollPaneListEffortM.getItemHeight() * Donnees.getWorkM()) ||
					scrollPaneListReposS.getScrollY() != (scrollPaneListReposS.getItemHeight() * Donnees.getRestS()) ||
					scrollPaneListReposM.getScrollY() != (scrollPaneListReposM.getItemHeight() * Donnees.getRestM()) ||
					scrollPaneListCycle.getScrollY() != (scrollPaneListCycle.getItemHeight() * Donnees.getCycle())){
				initInterval = false;
			}
			else initInterval = true;
		}
		else {
			scrollPaneListReposS.actif();
			scrollPaneListReposM.actif();
			scrollPaneListEchauffementS.actif();
			scrollPaneListEchauffementM.actif();
			scrollPaneListEffortS.actif();
			scrollPaneListEffortM.actif();
			scrollPaneListCycle.actif();
		}
		
		stage.act();
		stage.draw();
		
		/****************************************************************************/
		/**************************AFFICHAGE DU COMPTEUR*****************************/
		/****************************************************************************/	
		if(!Variables.TIMER_ACTIF){
			r = 0.383f;
			g = 0.453f;
			b = 0.508f;
			r2 = 0.309f;
			g2 = 0.367f;
			b2 = 0.410f;

			tempsEchauffement = Integer.parseInt(scrollPaneListEchauffementS.getSelected()) + 60 * Integer.parseInt(scrollPaneListEchauffementM.getSelected());
			tempsEffort = Integer.parseInt(scrollPaneListEffortS.getSelected()) + 60 * Integer.parseInt(scrollPaneListEffortM.getSelected());
			tempsRepos = tempsEffort + Integer.parseInt(scrollPaneListReposS.getSelected()) + 60 * Integer.parseInt(scrollPaneListReposM.getSelected());
			nbCycle = Integer.parseInt(scrollPaneListCycle.getSelected());
			tempsTotal = tempsEchauffement + nbCycle * tempsRepos;
			
			stringTimer = String.format("%02d:%02d:%02d",
					tempsTotal/3600, 
					(tempsTotal%3600)/60,
					tempsTotal%60);
			
			//Gestion de l'affichage des compteurs	
			labelTimer.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - labelTimer.getWidth()/2,
					(11*Gdx.graphics.getHeight()/30 - labelTimer.getHeight()/2), 
					0.5f));		
			
			if(labelTimer.getY() < (shapeRendererY4 - labelTimer.getHeight())){
				if(shapeRendererWidth < (int)(9*Gdx.graphics.getWidth()/10))
					shapeRendererWidth += 25;
				else if(shapeRendererWidth > (int)(9*Gdx.graphics.getWidth()/10))
					shapeRendererWidth = (int)(9*Gdx.graphics.getWidth()/10);
				else if(shapeRendererWidth == (int)(9*Gdx.graphics.getWidth()/10)){
					tableEchauffement.addAction(Actions.fadeIn(0.1f));
					tableEffort.addAction(Actions.fadeIn(0.1f));
					tableRepos.addAction(Actions.fadeIn(0.1f));
					tableCycle.addAction(Actions.fadeIn(0.1f));
					labelEchauffement.addAction(Actions.fadeIn(0.1f));
					labelEffort.addAction(Actions.fadeIn(0.1f));
					labelRepos.addAction(Actions.fadeIn(0.1f));
					labelCycle.addAction(Actions.fadeIn(0.1f));
					optionBouton.addAction(Actions.fadeIn(0.1f));
				}
			}
			
			labelMessage.addAction(Actions.moveTo(- Gdx.graphics.getWidth(),
					(23*Gdx.graphics.getHeight()/30 - labelMessage.getHeight()/2), 
					0.2f));
		}	
		else {
			
			if(game.actionResolver.getEchauffement() > 1){
				r = 0.355f;
				g = 0.605f;
				b = 0.832f;
				r2 = 0.18f;
				g2 = 0.457f;
				b2 = 0.711f;
				labelMessage.setText(stringEchauffement);
				labelMessage.setWidth(game.assets.get("fontMessage.ttf", BitmapFont.class).getBounds(labelMessage.getText()).width);
			}				
			else if(game.actionResolver.getEchauffement() < 1){
				if(game.actionResolver.getEffort() > 0){
					labelMessage.setText(stringEffort);
					labelMessage.setWidth(game.assets.get("fontMessage.ttf", BitmapFont.class).getBounds(labelMessage.getText()).width);
					r = 0.832f;
					g = 0.469f;
					b = 0.449f;
					r2 = 0.730f;
					g2 = 0.414f;
					b2 = 0.395f;
				}
				else {
					labelMessage.setText(stringRepos);
					labelMessage.setWidth(game.assets.get("fontMessage.ttf", BitmapFont.class).getBounds(labelMessage.getText()).width);
					r = 0.609f;
					g = 0.633f;
					b = 0.332f;
					r2 = 0.516f;
					g2 = 0.531f;
					b2 = 0.281f;
				}
			}
			
			
			stringTimer = String.format("%02d:%02d:%02d",
					game.actionResolver.getTotal()/3600, 
					(game.actionResolver.getTotal()%3600)/60,
					game.actionResolver.getTotal()%60);
			
			
			if(game.actionResolver.getTotal() == 0){
				PlayButton.setChecked(false);
				Variables.TIMER_ACTIF = false;	
				game.actionResolver.stopTimer();
			}
			
			//Gestion de l'affichage des compteurs
			tableEchauffement.addAction(Actions.fadeOut(0.1f));
			tableEffort.addAction(Actions.fadeOut(0.1f));
			tableRepos.addAction(Actions.fadeOut(0.1f));
			tableCycle.addAction(Actions.fadeOut(0.1f));
			labelEchauffement.addAction(Actions.fadeOut(0.1f));
			labelEffort.addAction(Actions.fadeOut(0.1f));
			labelRepos.addAction(Actions.fadeOut(0.1f));
			labelCycle.addAction(Actions.fadeOut(0.1f));
			optionBouton.addAction(Actions.fadeOut(0.1f));
			if(shapeRendererWidth > 0)
				shapeRendererWidth -= 25;
			else if(shapeRendererWidth < 0)
				shapeRendererWidth = 0;
			if(shapeRendererWidth == 0){
				labelTimer.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - labelTimer.getWidth()/2,
													(Gdx.graphics.getHeight()/2 - labelTimer.getHeight()/2), 
													0.7f));	
				
				labelMessage.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - labelMessage.getWidth()/2,
						(23*Gdx.graphics.getHeight()/30 - labelMessage.getHeight()/2), 
						0.2f));
			}
		}
						
		labelTimer.setText(stringTimer);
		labelTimer.setWidth(game.assets.get("fontCalorie.ttf", BitmapFont.class).getBounds(labelTimer.getText()).width);
		/****************************************************************************/
		
		/****************************************************************************/
		/************************ENREGISTREMENT DU COMPTEUR**************************/
		/****************************************************************************/	
		Donnees.setWarmupS(Integer.parseInt(scrollPaneListEchauffementS.getSelected()));
		Donnees.setWarmupM(Integer.parseInt(scrollPaneListEchauffementM.getSelected()));
		Donnees.setWorkS(Integer.parseInt(scrollPaneListEffortS.getSelected()));
		Donnees.setWorkM(Integer.parseInt(scrollPaneListEffortM.getSelected()));
		Donnees.setRestS(Integer.parseInt(scrollPaneListReposS.getSelected()));
		Donnees.setRestM(Integer.parseInt(scrollPaneListReposM.getSelected()));
		Donnees.setCycle(Integer.parseInt(scrollPaneListCycle.getSelected()));
		/****************************************************************************/
		
		//Utilisation des onglets
		if(Gdx.input.justTouched() && Gdx.input.getX() < Variables.ONGLET_WIDTH && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y))
			game.setScreen(new MainMenuScreen(game));
		
		if(Gdx.input.justTouched() && Gdx.input.getX() > Variables.ONGLET_X2 && Gdx.input.getX() < (Variables.ONGLET_X2 + Variables.ONGLET_WIDTH) && Gdx.input.getY() < (Gdx.graphics.getHeight() - ONGLET_Y))
			game.setScreen(new CalorieScreen(game));
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
		
		PlayButton.addListener(new ClickListener(){
			 @Override
		        public void clicked(InputEvent event, float x, float y) {
				 if(!Variables.TIMER_ACTIF){
					 game.actionResolver.Timer(tempsEchauffement, tempsRepos, tempsEffort, tempsTotal);
					 Variables.TIMER_ACTIF = true;
				 }
				 
				 else if(Variables.TIMER_ACTIF){
					 game.actionResolver.stopTimer();
					 Variables.TIMER_ACTIF = false;	
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
		scrollPaneListEchauffementS.dispose();
		scrollPaneListEchauffementM.dispose();
		scrollPaneListReposS.dispose();
		scrollPaneListReposM.dispose();
		scrollPaneListEffortS.dispose();
		scrollPaneListEffortM.dispose();
		scrollPaneListCycle.dispose();
	}

}