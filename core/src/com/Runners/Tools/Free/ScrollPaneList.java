package com.Runners.Tools.Free;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ScrollPaneList {
	
	BitmapFont font;
	ListStyle listStyle;
	public List list;
	public ScrollPane scrollPane;
	Skin skin;
	TextureAtlas atlas;
	int limite;
	
	public ScrollPaneList(BitmapFont mfont, int limit, Color color){
		font = mfont;
		limite = limit;
		
		skin = new Skin();
		atlas = new TextureAtlas("Images.pack");
		skin.addRegions(atlas);
		
		listStyle = new ListStyle(font, color, color, skin.getDrawable("CelluleVide"));
		list = new List(listStyle);
		String string[] = new String[limite];
		for(int i = 0; i < string.length; i++){
			if(limite < 11)
				string[i] = Integer.toString(i);
			else if(limite < 101)
				string[i] = String.format("%02d", i);
			else if(limite < 1001)
				string[i] = String.format("%03d", i);
			else if(limite < 10001)
				string[i] = String.format("%04d", i);
		}
		
		list.setItems(string);
		
		scrollPane = new ScrollPane(list);
		scrollPane.setHeight(1.2f*list.getItemHeight());
		
		if(limite < 11){
			list.setWidth(font.getBounds("0").width);
			scrollPane.setWidth(font.getBounds("0").width);
		}
		else if(limite < 101){
			list.setWidth(font.getBounds("00").width);
			scrollPane.setWidth(font.getBounds("00").width);
		}
		else if(limite < 1001){
			list.setWidth(font.getBounds("000").width);
			scrollPane.setWidth(font.getBounds("000").width);
		}
		else if(limite < 10001){
			list.setWidth(font.getBounds("0000").width);
			scrollPane.setWidth(font.getBounds("0000").width);
		}
	}
	
	public void actif(){
		if(!scrollPane.isPanning() && !scrollPane.isFlinging()){
			if((scrollPane.getScrollY()/list.getItemHeight())%1 < 0.5f)
				scrollPane.setScrollY(scrollPane.getScrollY() - scrollPane.getScrollY()%list.getItemHeight());	
			else scrollPane.setScrollY(list.getItemHeight() + scrollPane.getScrollY() - scrollPane.getScrollY()%list.getItemHeight());
		}
		
		if(scrollPane.getScrollY()%list.getItemHeight() == 0)
			list.setSelectedIndex((int )(scrollPane.getScrollY()/list.getItemHeight()));
	}
	
	public void setX(float a){
		scrollPane.setX(a);
	}
	
	public void setY(float a){
		scrollPane.setY(a);
	}
	
	public float getX(){
		return scrollPane.getX();
	}
	
	public float getY(){
		return scrollPane.getY();
	}
	
	public float getWidth(){
		return scrollPane.getWidth();
	}
	
	public float getHeight(){
		return scrollPane.getHeight();
	}
	
	public void setScrollY(float y){
		scrollPane.setScrollY(y);
	}
	
	public float getScrollY(){
		return scrollPane.getScrollY();
	}
	
	public String getSelected(){
		return (String) list.getSelected();
	}
	
	public void setSelectedIndex(int index){
		list.setSelectedIndex(index);
	}
	
	public List getList(){
		return list;
	}
	
	public float getItemHeight(){
		return list.getItemHeight();
	}
	
	public ScrollPane getScrollPane(){
		return scrollPane;
	}
	
	public void setDebug(boolean debug){
		scrollPane.setDebug(debug);
	}
	
	public void setHeight(float val){
		scrollPane.setHeight(val);
	}
	
	public void dispose() {
		skin.dispose();
		atlas.dispose();
	}
}
