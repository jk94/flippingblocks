package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

import de.jf.flippingblocks.graphics.BlockPanel;

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
	
	public Block getBlockByBtnRef(BlockPanel b){
		Block erg = null;
		for(ArrayList<Block> y:blocklist){
			for(Block bl:y){
				if(bl.getBtn().equals(b)){
					erg = bl;
					break;
				}
			}
			if(!erg.equals(null)){
				break;
			}
		}
		return erg;
	}
	
	public ArrayList<Block> checkMates(Block b){
		ArrayList<Block> erg = new ArrayList<Block>();
		int x, y;
		boolean posfound=false;
		for(int i=0;i<blocklist.size();i++){
			for(int i2=0;i2<blocklist.get(i).size();i2++){
				if(blocklist.get(i).get(i2).equals(b)){
					x = i;
					y = i2;
					posfound = true;
					break;
				}
			}
			if(posfound){
				break;
			}
		}
		return erg;
	}

	

}
