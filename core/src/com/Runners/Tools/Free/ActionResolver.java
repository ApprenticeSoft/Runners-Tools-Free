package com.Runners.Tools.Free;

public interface ActionResolver {
	public void Timer(int a, int b, int c, int d);
	public void stopTimer();
	public int getTotal();
	//public void showAds();
	//public void hideAds();
	public void showOrLoadInterstital();
	//public void showOrLoadAmazonInterstital();
	int getEchauffement();
	int getEffort();
}