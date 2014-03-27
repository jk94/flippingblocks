package de.jf.flippingblocks.graphics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Button;
import android.widget.LinearLayout;
import de.jf.flippingblocks.Enum.EnumColor;

public class BlockPanel extends Button {

	LinearLayout.LayoutParams params;

	private final int s_margin_right = 10;
	private final int s_margin_left = 10;
	private final int s_margin_top = 10;
	private final int s_margin_bottom = 10;
	private int active_margin_right;
	private int active_margin_left;
	private int active_margin_top;
	private int active_margin_bottom;

	private EnumColor color;

	public BlockPanel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public BlockPanel(Context context, String name, EnumColor color, int width,
			int height) {
		super(context);
		this.setText(name);
		// es können auch attribute wie layoutparams.match_parent oder
		// wrap_content sein

		this.params = new LinearLayout.LayoutParams(width, height);

		// set standard margin
		this.params.leftMargin = s_margin_left;
		this.params.rightMargin = s_margin_right;
		this.params.topMargin = s_margin_top;
		this.params.bottomMargin = s_margin_bottom;

		//
		this.active_margin_left = s_margin_left;
		this.active_margin_right = s_margin_right;
		this.active_margin_top = s_margin_top;
		this.active_margin_bottom = s_margin_bottom;

		this.getBackground().setColorFilter(Color.parseColor(color.name()),
				PorterDuff.Mode.MULTIPLY);

		this.setLayoutParams(params);
	}

	public void changeColor(EnumColor color) {
		this.color = color;
		this.getBackground().setColorFilter(Color.parseColor(color.name()),
				PorterDuff.Mode.MULTIPLY);
	}

	public EnumColor getColor() {
		return color;
	}

	public int getSColor(EnumColor c) {
		int erg = -1;
		switch (color) {
		case BLACK:
			erg = Color.BLACK;
			break;
		case CYAN:
			erg = Color.CYAN;
			break;
		case GRAY:
			erg = Color.GRAY;
			break;
		case BLUE:
			erg = Color.BLUE;
			break;
		case GREEN:
			erg = Color.GREEN;
			break;
		case RED:
			erg = Color.RED;
			break;
		case WHITE:
			erg = Color.WHITE;
			break;
		case YELLOW:
			erg = Color.YELLOW;
			break;
		default:
			erg = Color.BLACK;
			break;
		}
		return erg;
	}

	public void restoreStandardMargin() {
		this.active_margin_left = s_margin_left;
		this.active_margin_right = s_margin_right;
		this.active_margin_top = s_margin_top;
		this.active_margin_bottom = s_margin_bottom;

		this.params.leftMargin = s_margin_left;
		this.params.rightMargin = s_margin_right;
		this.params.topMargin = s_margin_top;
		this.params.bottomMargin = s_margin_bottom;

		this.setLayoutParams(params);
	}

	public void setNewMargin(int top_margin, int bottom_margin,
			int left_margin, int right_margin) {
		this.active_margin_top = top_margin;
		this.active_margin_bottom = bottom_margin;
		this.active_margin_left = left_margin;
		this.active_margin_right = right_margin;

		this.params.bottomMargin = bottom_margin;
		this.params.topMargin = top_margin;
		this.params.leftMargin = left_margin;
		this.params.rightMargin = right_margin;

		this.setLayoutParams(params);

	}

	public void setSize(int width, int height) {
		this.params.height = height;
		this.params.width = width;

		this.setLayoutParams(params);
	}

	public static BlockPanel generateNewBlockPanel(Context context,
			String name, int color, int width, int height) {
		return new BlockPanel(context, name, EnumColor.WHITE, width, height);
	}

}
