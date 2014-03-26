package de.jf.flippingblocks.spielelemente;

import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.graphics.BlockPanel;
import java.awt.Color;

public class Block {

	private EnumColor color;
        private Color color2;

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }
	private BlockPanel btn;

	public Block(EnumColor color, BlockPanel b) {
		this.color = color;
		this.btn = b;
	}
        
        public Block(Color color, BlockPanel b) {
		this.color2 = color;
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
