package de.jf.flippingblocks.spielelemente;

import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.graphics.BlockPanel;


public class Block {

	private EnumColor color;
       ;


	private BlockPanel btn;

	public Block(EnumColor color, BlockPanel b) {
		this.color = color;
		this.btn = b;
	}
        
     

	public EnumColor getColor() {
		return color;
	}

	public void setColor(EnumColor color) {
		this.color = color;
	}
	
	public Block(int color){
		this.btn = null;
		this.color = EnumColor.RED;
	}

	public BlockPanel getBtn() {
		return btn;
	}

	public void setBtn(BlockPanel btn){
		this.btn = btn;
	}
	
	
}
