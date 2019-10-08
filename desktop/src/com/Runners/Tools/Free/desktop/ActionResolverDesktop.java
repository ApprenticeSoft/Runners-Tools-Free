package com.Runners.Tools.Free.desktop;

import com.Runners.Tools.Free.ActionResolver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ActionResolverDesktop implements ActionResolver{

	int aa, bb, cc, dd;

	@Override
	public void Timer(int a, int b, int c, int d) {
		Sound soundTest = Gdx.audio.newSound(Gdx.files.internal("Sons/Collision.mp3"));
		
		aa = a;
		bb = b;
		cc = c;
		dd = d;

		System.out.println(" Le temps d'échauffement : " + aa);
		System.out.println(" Le temps de repos est : " + bb);
		System.out.println(" Le temps d'effort est : " + cc);
		System.out.println(" Le temps total est : " + dd);
		
		
		Timer timer = new Timer();
		timer.scheduleTask(new Task(){
		    public void run() {
		        aa--;
		        bb--;
		        cc--;
		        dd--;
		        System.out.println(" Le temps d'échauffement : " + aa);
				System.out.println(" Le temps de repos est : " + bb);
				System.out.println(" Le temps d'effort est : " + cc);
				System.out.println(" Le temps total est : " + dd);
		    }
		}, 1,1);
		
		if(aa == 0) soundTest.play();		
	}	

	@Override
	public void stopTimer() {
		System.out.println("stopTimer");
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getEchauffement() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getEffort() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	@Override
	public void showAds(){
		System.out.println("showAds");
	}
	
	@Override
	public void hideAds(){
		System.out.println("hideAds");
	}
	*/
	@Override
	public void showOrLoadInterstital(){
		System.out.println("showOrLoadInterstital");
	}
	
	/*
	@Override
	public void showOrLoadAmazonInterstital(){
		System.out.println("showOrLoadAmazonInterstital");
	}
	*/
}