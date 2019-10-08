package com.Runners.Tools.Free;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Donnees {
	
public static Preferences prefs;
	
	public static void Load(){
		prefs = Gdx.app.getPreferences("Donnees");
		
		if (!prefs.contains("Poids")) {
		    prefs.putInteger("Poids", 0);
		}
		
		if (!prefs.contains("Langage")) {
		    prefs.putInteger("Langage", 1);
		}
		
		if (!prefs.contains("PoidsUnite")) {
		    prefs.putString("PoidsUnite", "kg");
		}
		
		if (!prefs.contains("DistanceUnite")) {
		    prefs.putString("DistanceUnite", "km");
		}
		
		if (!prefs.contains("VitesseUnite")) {
		    prefs.putString("VitesseUnite", "km/h");
		}
		
		if (!prefs.contains("PaceUnite")) {
		    prefs.putString("PaceUnite", "min/km");
		}
		
		if (!prefs.contains("INTERSTITIAL_TRIGGER")) {
		    prefs.putInteger("INTERSTITIAL_TRIGGER", 4);
		}
		
		if (!prefs.contains("METRIC_SYSTEM")) {
		    prefs.putBoolean("METRIC_SYSTEM", true);
		}
		
		if (!prefs.contains("WarmupS")) {
		    prefs.putInteger("WarmupS", 0);
		}
		
		if (!prefs.contains("WarmupM")) {
		    prefs.putInteger("WarmupM", 0);
		}
		
		if (!prefs.contains("WorkS")) {
		    prefs.putInteger("WorkS", 0);
		}
		
		if (!prefs.contains("WorkM")) {
		    prefs.putInteger("WorkM", 0);
		}
		
		if (!prefs.contains("RestS")) {
		    prefs.putInteger("RestS", 0);
		}
		
		if (!prefs.contains("RestM")) {
		    prefs.putInteger("RestM", 0);
		}
		
		if (!prefs.contains("Cycle")) {
		    prefs.putInteger("Cycle", 0);
		}
	}
	
	public static void setPoids(int val) {
	    prefs.putInteger("Poids", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getPoids() {
	    return prefs.getInteger("Poids");
	}
	
	public static void setLangage(int val) {
	    prefs.putInteger("Langage", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getLangage() {
	    return prefs.getInteger("Langage");
	}
	
	public static void setPoidsUnite(String val) {
	    prefs.putString("PoidsUnite", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static String getPoidsUnite() {
	    return prefs.getString("PoidsUnite");
	}
	
	public static void setDistanceUnite(String val) {
	    prefs.putString("DistanceUnite", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static String getDistanceUnite() {
	    return prefs.getString("DistanceUnite");
	}
	
	public static void setVitesseUnite(String val) {
	    prefs.putString("VitesseUnite", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static String getVitesseUnite() {
	    return prefs.getString("VitesseUnite");
	}
	
	public static void setPaceUnite(String val) {
	    prefs.putString("PaceUnite", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static String getPaceUnite() {
	    return prefs.getString("PaceUnite");
	}
	
	public static void setINTERSTITIAL_TRIGGER(int val) {
	    prefs.putInteger("INTERSTITIAL_TRIGGER", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getINTERSTITIAL_TRIGGER() {
	    return prefs.getInteger("INTERSTITIAL_TRIGGER");
	}
	
	public static void setMETRIC_SYSTEM(boolean val) {
	    prefs.putBoolean("METRIC_SYSTEM", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getMETRIC_SYSTEM() {
	    return prefs.getBoolean("METRIC_SYSTEM");
	}
	
	public static void setWarmupS(int val) {
	    prefs.putInteger("WarmupS", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getWarmupS() {
	    return prefs.getInteger("WarmupS");
	}
	
	public static void setWarmupM(int val) {
	    prefs.putInteger("WarmupM", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getWarmupM() {
	    return prefs.getInteger("WarmupM");
	}
	
	public static void setWorkS(int val) {
	    prefs.putInteger("WorkS", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getWorkS() {
	    return prefs.getInteger("WorkS");
	}
	
	public static void setWorkM(int val) {
	    prefs.putInteger("WorkM", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getWorkM() {
	    return prefs.getInteger("WorkM");
	}
	
	public static void setRestS(int val) {
	    prefs.putInteger("RestS", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getRestS() {
	    return prefs.getInteger("RestS");
	}
	
	public static void setRestM(int val) {
	    prefs.putInteger("RestM", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getRestM() {
	    return prefs.getInteger("RestM");
	}
	
	public static void setCycle(int val) {
	    prefs.putInteger("Cycle", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getCycle() {
	    return prefs.getInteger("Cycle");
	}
}
