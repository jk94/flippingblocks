package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

public class Spielfeld {
	
	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<Block>> blocklist;
	private int borderX, borderY;
	
	public Spielfeld(int sizex, int sizey){
		borderX = sizex;
		borderY = sizey;
		blocklist = new ArrayList<ArrayList<Block>>(borderX);
		for(ArrayList<Block> a:blocklist){
			a = new ArrayList<Block>(borderY);
		}
	}
	
	

}
