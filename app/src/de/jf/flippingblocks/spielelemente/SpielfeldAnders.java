package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

import de.jf.flippingblocks.Control;
import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.graphics.BlockPanel;
import android.content.Context;

public class SpielfeldAnders {
	Control control;
	int cols ;
	int rows ;
	Block[][] feld ;
	
	public SpielfeldAnders (Control cont, int cols , int rows){
		this.control = cont;
		this.cols = cols;
		this.rows = rows;
		feld = new Block[rows][cols];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				
				int color = (int) ((Math.random() * EnumColor.values().length - 1));
				feld[i][j] = new Block(EnumColor.values()[color], cont.getGameGui().addBlockPanel(EnumColor.values()[color]) );
				
			}
			
		}
	
		
	}
	
	public Block findBlockByRef(BlockPanel panel){
		
		Block block = null;
		for (int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				BlockPanel temp = feld[i][j].getBtn();
				if(temp.equals(panel)){
					block = feld[i][j];
					return block;
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<Block> getButtonsArround(BlockPanel panel){
		Block temp = findBlockByRef(panel);
		int x ;
		int y ;
		if (temp!=null){
			// position feststellen
			for(int i = 0 ; i < rows; i++){
				for(int j =0 ; j < cols; j++){
					if (feld[i][j] == temp){
						x = i;
						y = j;
						return getButtonsArround(temp, x, y);
					}
					
				}
				
			}
			
			
		}
		
		return null;
		
		
		
	}
	
	public void click(BlockPanel panel){
		ArrayList<Block> list = getButtonsArround(panel);
		
		System.out.println(list.size());
		if(list.size() >= 3){
		changeColorsOnBlocks(list);
		}
	}
	
	private void changeColorsOnBlocks(ArrayList<Block> list){
		for(Block x : list){
			int color = (int) ((Math.random() * EnumColor.values().length - 1));
			x.setColor(EnumColor.values()[color]);
			x.getBtn().changeColor(EnumColor.values()[color]);
			System.out.println(color);
			
		}
		
	}
	
	private ArrayList<Block> getButtonsArround(Block block, int x , int y){
		ArrayList<Block>  list = new ArrayList<Block>();
		
		generateList(list, block, x, y);
		
		return list;
		
		
		
		
		
		
	}
	
	private boolean generateList(ArrayList<Block> list, Block block, int x , int y){
		
		
		if (list.contains(feld[x][y])){
			return false;
		}
		
		if(block.getColor() == feld[x][y].getColor()){
			list.add(feld[x][y]);
			
			if (x!= 0){
				generateList(list, feld[x][y], x-1, y);
			}
			
			if(x< rows-1){
				generateList(list, feld[x][y], x+1, y);
			}
			
			if(y != 0){
				generateList(list, feld[x][y], x, y-1);
			}
			
			if(y< cols-1){
				generateList(list, feld[x][y], x, y+1);
			}
			
			return true;
		} else{
		
		return false;
		
		
		
		
		}
		
		
	}
	
	
}
