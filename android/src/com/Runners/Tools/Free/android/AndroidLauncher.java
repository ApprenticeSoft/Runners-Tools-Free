package com.Runners.Tools.Free.android;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.Runners.Tools.Free.ActionResolver;
import com.Runners.Tools.Free.MyGdxGame;
import com.Runners.Tools.Free.android.IntentServiceTimer.MyBinder;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements ActionResolver{
	
	protected View gameView;
	int aa, bb, cc, dd;
	public final static String ECHAUFFEMENT = "Echauffement", REPOS = "repos", EFFORT = "effort", TOTAL = "total";
	Intent intentTimer;
	IntentServiceTimer intentServiceTimer;
	boolean Bound = false;
	boolean restart = false;
	
	private static final String BANNER_ID = "ca-app-pub-7775582829834874/5122815948";
	private static final String INTERSTITIAL_ID = "ca-app-pub-7775582829834874/8076282345";
	private static final String GOOGLE_PLAY_FREE_GAME_URL = "https://play.google.com/store/apps/details?id=com.Runners.Tools.Free.android";
	private static final String GOOGLE_PLAY_GAME_URL = "https://play.google.com/store/apps/details?id=com.pace.converter.android";
	private static final String GOOGLE_PLAY_STORE_URL = "https://play.google.com/store/apps/developer?id=Apprentice+Soft";
	private static final String AMAZON_GAME_URL = "http://www.amazon.com/gp/mas/dl/android?p=com.pace.converter.android";
	private static final String AMAZON_FREE_GAME_URL = "http://www.amazon.com/gp/mas/dl/android?p=com.Runners.Tools.Free.android";
	private static final String AMAZON_STORE_URL = "http://www.amazon.com/gp/mas/dl/android?p=com.premier.jeu.android&showAll=1";
	private RelativeLayout rLayout;
	private AdView adView;
	private InterstitialAd interstitialAd;
	private AdView admobView;
	private ConnectivityManager connManager;
	private NetworkInfo info;
	private NetworkInfo info1;

	
	public AndroidLauncher() {
        super();
    }
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new MyGdxGame(), config);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);					//Test en cours
	    //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);	
		
		//scrollView = new ScrollView(this);	
		/*
		ScrollView.LayoutParams fParams = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
				ScrollView.LayoutParams.MATCH_PARENT);
		scrollView.setLayoutParams(fParams);
		*/
		//scrollView.setLayoutParams(new ViewGroup.LayoutParams(
        //        ViewGroup.LayoutParams.MATCH_PARENT,
        //        ViewGroup.LayoutParams.MATCH_PARENT));
	    //scrollView.setFillViewport(true);
	    //scrollView.setScrollContainer(true);
	    //scrollView.onCheckIsTextEditor();
		
	    rLayout = new RelativeLayout(this);
	    RelativeLayout.LayoutParams rParams = 
	            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
	                    RelativeLayout.LayoutParams.WRAP_CONTENT);
	        rParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	        rParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	    rLayout.setLayoutParams(rParams);

	    
	    admobView = createAdView();
		View gameView = createGameView(config);
		
		rLayout.addView(gameView);
		rLayout.addView(admobView);
		setContentView(rLayout);
		
		intentTimer = new Intent(AndroidLauncher.this, IntentServiceTimer.class);	//Création du service
		bindService(intentTimer, mConnection, Context.BIND_AUTO_CREATE);			//Liaison du service avec l'activité
		
		connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	   	info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	   	info1 = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);   	
	   	
		startAdvertising(admobView);
		
		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(INTERSTITIAL_ID);
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
			}
			@Override
			public void onAdClosed() {
			}
		});
		showOrLoadInterstital();
		
	}
	
	 private View createGameView(AndroidApplicationConfiguration config) {
		    gameView = initializeForView(new MyGdxGame(this), config);
		    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		    params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
			params.addRule(RelativeLayout.BELOW, adView.getId());
		    gameView.setLayoutParams(params);
		    return gameView;
	  }
	 
	 private AdView createAdView() {
		 adView = new AdView(this);
		 adView.setAdSize(AdSize.SMART_BANNER);
		 adView.setAdUnitId(BANNER_ID);
		 adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
		 RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		 	params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		 	adView.setLayoutParams(params);
		 	//adView.setBackgroundColor(Color.BLACK);
		 	return adView;
		 }
	 
	 public void startAdvertising(AdView adView) {
			 AdRequest adRequest = new AdRequest.Builder().build();
			 adView.loadAd(adRequest);
			 try{
				 if (info.isConnected() | info1.isConnected())
					 adView.setVisibility(View.VISIBLE); 
			 }catch(Exception e){		
				 System.out.println("/**********************************ERREUR*******************************************/");
			 }
		 }
	 /*
		public void showAds() {
	        runOnUiThread(new Runnable() {
	                @Override
	                public void run() {
	              	  if (!info.isConnected() & !info1.isConnected()){
	              		  adView.setVisibility(View.GONE);
	              	  }
	              	  else adView.setVisibility(View.VISIBLE);
	                }
	        });
		}
		 
			public void hideAds() {
		        runOnUiThread(new Runnable() {
		                @Override
		                public void run() {
		              	  if (info.isConnected() | info1.isConnected())
		              		  adView.setVisibility(View.GONE);
		                }
		        });
			}
	*/
		public void showOrLoadInterstital() {
			try {
				runOnUiThread(new Runnable() {
					public void run() {
						if (info.isConnected() | info1.isConnected()){
							if (interstitialAd.isLoaded()) {
								interstitialAd.show();
		
							} else {
								AdRequest interstitialRequest = new AdRequest.Builder().build();
								interstitialAd.loadAd(interstitialRequest);
							}
						}
					}
				});
			} catch (Exception e) {
			}
		  }
		
	 @Override
	    protected void onStop() {
	        super.onStop();
	        // Unbind from the service
	        if (Bound) {
	            unbindService(mConnection);
	            Bound = false;
	        }
	    }
	 
	 //Permet de lier un Service à l'activité
	 private ServiceConnection mConnection = new ServiceConnection() {
	        @Override
	        public void onServiceConnected(ComponentName className,
	                IBinder service) {
	            // We've bound to LocalService, cast the IBinder and get LocalService instance
	        	MyBinder binder = (MyBinder) service;	//MyBinder est défini dans le service (IntentService dans ce cas)
	        	intentServiceTimer = binder.getService();
	            Bound = true;
	        }

	        @Override
	        public void onServiceDisconnected(ComponentName arg0) {
	            Bound = false;
	        }
	    };
	
	//Méthodes de l'interface ActionResolver permettant d'obtenir des données de l'activité et d'envoyer des données vers l'activité   
	@Override
	public void Timer(int a, int b, int c, int d) {
		aa = a;
		bb = b;
		cc = c;
		dd = d;

		intentTimer.putExtra(ECHAUFFEMENT, aa);
		intentTimer.putExtra(REPOS, bb);
		intentTimer.putExtra(EFFORT, cc);
		intentTimer.putExtra(TOTAL, dd);
		
		//if(!restart){
		//	startService(intentTimer);						//Si on utilise bindService, il ne faut pas utiliser startService()
		//	restart = true;
		//}
		//else if(restart)									//Important pour ne pas lancer deux services en même temps
			intentServiceTimer.startTimer(intentTimer);
	}

	@Override
	public void stopTimer() {
		intentServiceTimer.stopTimer();
	}
	
	@Override
	public int getTotal() {
		return intentServiceTimer.getTotal();
	}
	
	@Override
	public int getEchauffement() {
		return intentServiceTimer.getEchauffement();
	}
	
	@Override
	public int getEffort() {
		return intentServiceTimer.getEffort();
	}
	
	@Override
	  public void onResume() {
		    super.onResume();
		    if (adView != null) adView.resume();
	  }
	
  @Override
  public void onPause() {
	    if (adView != null) adView.pause();
	    super.onPause();
  }

  @Override
  public void onDestroy() {
	    if (adView != null) adView.destroy();
	    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
	    final Dialog dialog = new Dialog(this);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	
	    LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
	
	    Button quitButton = new Button(this);
	    quitButton.setText("Quit");
	    quitButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	    	  intentServiceTimer.stopTimer();
	    	  finish();
	      }
	    });
	    
	    Button rateButton = new Button(this);
	    rateButton.setText("Rate");
	    rateButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	    	//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AMAZON_FREE_GAME_URL)));  
	        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_FREE_GAME_URL)));
	        dialog.dismiss();
	      }
	    });
	    
	    Button removeAdsButton = new Button(this);
	    removeAdsButton.setText("Remove Ads");
	    removeAdsButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	    	//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AMAZON_GAME_URL)));  
	        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_GAME_URL)));
	        dialog.dismiss();
	      }
	    });
	    
	    Button moreAppButton = new Button(this);
	    moreAppButton.setText("More Apps");
	    moreAppButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	    	//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AMAZON_STORE_URL)));  
	        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_STORE_URL)));
	        dialog.dismiss();
	      }
	    });

	    linearLayout.addView(moreAppButton);
	    linearLayout.addView(rateButton);
	    linearLayout.addView(removeAdsButton);
	    linearLayout.addView(quitButton);
	    
	    dialog.setContentView(linearLayout);
	    dialog.show();
  }	 

}