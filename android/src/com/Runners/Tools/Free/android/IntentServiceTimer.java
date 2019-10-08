package com.Runners.Tools.Free.android;
import java.util.Timer;
import java.util.TimerTask;

import com.Runners.Tools.Free.android.R;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class IntentServiceTimer extends IntentService{
	private final static String TAG = "IntentServiceExample";
	static final String TIME = "time";
	int echauffement, repos, reposInit, effort, effortInit, total;
	Timer timer;
	MediaPlayer debutPlayer, effortPlayer, reposPlayer, finPlayer;
	
	private final IBinder mBinder = new MyBinder();
	
	public IntentServiceTimer(){
		super(TAG);
	}
	
	public void onCreate(){
		super.onCreate();
		Log.d(TAG, "************************************************************Service Started!");
		timer = new Timer();
		debutPlayer = MediaPlayer.create(this, R.raw.depart);
		effortPlayer = MediaPlayer.create(this, R.raw.effort);
		reposPlayer = MediaPlayer.create(this, R.raw.repos);
		finPlayer = MediaPlayer.create(this, R.raw.fin);
		}
	
	@Override
	protected void onHandleIntent(final Intent intent) {
		startTimer(intent);
	}
	
	//La classe et la méthode suivante permettent de lier le Service à l'activité
	public class MyBinder extends Binder{
		IntentServiceTimer getService(){
			return IntentServiceTimer.this;
		}
	}
	
	 @Override
	  public IBinder onBind(Intent arg0) {
	    return mBinder;
	  }
	 
	 //Méthode personnelle pour retourner une donnée du Service vers l'activité
	 public int getTotal(){
		 return total;
	 }

	public int getEchauffement() {
		return echauffement;
	}

	public int getEffort() {
		return effort;
	}	
	
	 public void stopTimer(){
		 timer.cancel();
	 }
	 

	 public void startTimer(final Intent intent){
		debutPlayer.start();
		 
		echauffement = intent.getIntExtra(AndroidLauncher.ECHAUFFEMENT, -1);
	 	repos = intent.getIntExtra(AndroidLauncher.REPOS, -1);
	 	effort = intent.getIntExtra(AndroidLauncher.EFFORT, -1);
		total = intent.getIntExtra(AndroidLauncher.TOTAL, -1);
		reposInit = intent.getIntExtra(AndroidLauncher.REPOS, -1);
		effortInit = intent.getIntExtra(AndroidLauncher.EFFORT, -1);
		 
		 timer = new Timer();
		 timer.schedule(new TimerTask(){
			    public void run() {
					
			    	if(total > 0){
			    		if (echauffement > 0){
			    			echauffement--;
			    			total--;
			    		}
			    		else{
			    			echauffement--;
			    			repos--;
					    	effort--;
					    	total--;
										    	
					    	if(effort == 0 && total > 0)
						    	reposPlayer.start();
					    	
					    	if(repos == 0 && total > 0){
						    	effortPlayer.start();
						    	repos = reposInit;
						    	effort = effortInit;
					    	}
			    		}
			    		
			    		if(total == 0)
				    		finPlayer.start();
			    		
			    		if(echauffement == 0)
					    	effortPlayer.start();
			    	}
			    	
			    	//Log.d(TAG, "Echauffement : " + echauffement);
			    	//Log.d(TAG, "Repos : " + repos);
			    	//Log.d(TAG, "Effort : " + effort);
			    	intent.putExtra(TIME, total);
			    }
			}, 1000,1000);
	 }
}