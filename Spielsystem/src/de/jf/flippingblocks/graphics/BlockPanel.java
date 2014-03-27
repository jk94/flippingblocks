package de.jf.flippingblocks.graphics;

import java.awt.Button;

import javax.naming.Context;

import de.jf.flippingblocks.Enum.EnumColor;

public class BlockPanel extends Button {

	private final int s_margin_right = 10;
	private final int s_margin_left = 10;
	private final int s_margin_top = 10;
	private final int s_margin_bottom = 10;
	private int active_margin_right ;
	private int active_margin_left ;
	private int active_margin_top;
	private int active_margin_bottom ;
	
	private EnumColor color;
	
	
	public BlockPanel(String name, EnumColor color, int width, int height){
		
		
	}
	
	public void changeColor(EnumColor color){
		this.color=color;
		//this.getBackground().setColorFilter(Color.parseColor(color.name()), PorterDuff.Mode.MULTIPLY);
	}

	public EnumColor getColor() {
		return color;
	}

		
	public void restoreStandardMargin(){
		this.active_margin_left = s_margin_left;
		this.active_margin_right = s_margin_right;
		this.active_margin_top = s_margin_top;
		this.active_margin_bottom = s_margin_bottom;
		
		
	}
	
	public void setNewMargin(int top_margin, int bottom_margin, int left_margin, int right_margin) {
		this.active_margin_top = top_margin;
		this.active_margin_bottom = bottom_margin;
		this.active_margin_left = left_margin;
		this.active_margin_right = right_margin;
		
		
		
	}
	
	
	
	public void setSize(int width , int height){
		
	}
	
	public static BlockPanel generateNewBlockPanel(Context context, String name, int color, int width, int height){
		return new BlockPanel(name, EnumColor.WHITE, width, height);
	}
	
	
	
	
	
}
