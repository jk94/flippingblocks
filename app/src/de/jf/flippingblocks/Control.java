package de.jf.flippingblocks;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import de.jf.flippingblocks.gameGui.GameGui;
import de.jf.flippingblocks.spielelemente.Spielfeld;

@SuppressWarnings("serial")
public class Control extends Thread implements Serializable{
	/**
	 * 
	 */
	
	transient Context context;
	transient Spielfeld feld ;
	transient int col;
	transient int row;
	public static int id = 0;
	public final int id2 ;
	 GameGui gui;
	public Control ( Context context, int col , int row){
		this.id2= id ;
		id++;
		this.context = context;
		this.col = col;
		this.row = row;
		feld = new Spielfeld(col, row);
		
		Intent intent = new Intent(context,GameGui.class);
		intent.putExtra("cols", col);
		intent.putExtra("rows", row);
		intent.putExtra("control", this);
		context.startActivity(intent);
		debug();
		
	}
	public void run(){
		debug();
	}
	
	public void debug(){
		System.out.println(gui);
		System.out.println(col);
		System.out.println(row);
		System.out.println(context);
		System.out.println(feld);
	}
	public void setGameGui(GameGui gui){
		this.gui = gui;
	}
	
	

}
