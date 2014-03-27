package de.jf.flippingblocks;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import de.jf.flippingblocks.gameGui.GameGui;
import de.jf.flippingblocks.graphics.BlockPanel;
import de.jf.flippingblocks.spielelemente.Block;
import de.jf.flippingblocks.spielelemente.Spielfeld;


public class Control{
	/**
	 * 
	 */
	
	transient Spielfeld feld ;
	transient int col;
	transient int row;
	public static int id = 0;
	public final int id2 ;
	 GameGui gui;
	public Control (GameGui gui, int col , int row){
		this.id2= id ;
		id++;
		
		this.col = col;
		this.row = row;
		this.gui = gui;
		feld = new Spielfeld(col, row);
		
		
		
	}
	
	public void action(BlockPanel panel , Context context ){
		ArrayList<Block> liste = new ArrayList<Block>();
		feld.CheckMate(liste, feld.getBlockByBtnRef(panel), feld.getBlockByBtnRef(panel).getColor());
		feld.destroyBlocks(liste);
		
	}
	
	
	
	

}
