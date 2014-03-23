package de.jf.flippingblocks.graphics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.view.Display;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class CentralStyleGenerator {
	// This class offers the chance to generate gui_elements, so that every
	// gui_elemnt's look and feel is the same

	// angabe in %
	private final static float sideMenuWidthHidden = 0.01f;
	
	private final static float sideMenuWidthExpanded = 0.6f;
	
	
	public static final int menu_margin = 5;
	public static final int content_margin = 50;

	public static Button generateButton(Context context, String name,
			OnClickListener listener, boolean isMenu) {

		Button button = new Button(context);
		button.setText(name);
		button.setOnClickListener(listener);
		button.setTextColor(Color.CYAN);

		button.setSingleLine();

		if (isMenu) {
			button.getBackground().setColorFilter(Color.BLACK,
					PorterDuff.Mode.MULTIPLY);
		} else {
			button.getBackground().setColorFilter(Color.GRAY,
					PorterDuff.Mode.MULTIPLY);
		}

		LinearLayout.LayoutParams params;

		if (isMenu) {
			params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			params.bottomMargin = menu_margin;

		} else {

			params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.bottomMargin = content_margin;

		}

		button.setLayoutParams(params);

		return button;
	}

	public static LinearLayout generateSideMenu(Context context) {

		LinearLayout sideMenu = new LinearLayout(context);
		// define sideMenuContent
		sideMenu.setBackgroundColor(Color.DKGRAY);
		sideMenu.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
		sideMenu.setGravity(Gravity.LEFT);
		sideMenu.setOrientation(LinearLayout.VERTICAL);
		// sideMenuContent.getBackground().setColorFilter(Color.DKGRAY,
		// PorterDuff.Mode.LIGHTEN);
		// //sideMenu ausblenden
		LayoutParams params = new LayoutParams((int)getMenuWidthHidden(context),
				LayoutParams.MATCH_PARENT);

		sideMenu.setLayoutParams(params);
		//
		return sideMenu;
	}

	public static GridLayout generateMainLayout(Context context) {
		GridLayout mainLayout = new GridLayout(context);
		mainLayout.setBackgroundColor(Color.BLACK);
		mainLayout.setOrientation(0);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(params);
		return mainLayout;
	}

	public static LinearLayout generateLauncherField(Context context) {
		LinearLayout mainContent = new LinearLayout(context);
		mainContent.setGravity(Gravity.CENTER_HORIZONTAL);
		mainContent.setBackgroundColor(Color.BLACK);
		mainContent.setOrientation(LinearLayout.VERTICAL);
		mainContent.setVerticalGravity(Gravity.CENTER_VERTICAL);
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		mainContent.setLayoutParams(params);

		return mainContent;

	}
	
	public static BlockPanel generateBlockPanel(Context context , int color, int grid_col){
		Point size = getScreenResolution(context);
		int block_edge = size.x / grid_col;
		// das verhalten des BlockPanels wird in der Klasse selber verändert, da die farbe des objekts 
		// zur laufzeit geändert werden muss
		
		BlockPanel panel = new BlockPanel(context, "", color, block_edge, block_edge);
		
		
		return panel;
	}

	public static GridLayout generateGameField(Context context, int cols,
			int rows) {

		GridLayout gameField = new GridLayout(context);
		
		GridLayout.LayoutParams gparams = new GridLayout.LayoutParams( GridLayout.spec(0), GridLayout.spec(0));
//		
//		gparams.width = android.widget.GridLayout.LayoutParams.MATCH_PARENT;
//		gparams.height = android.widget.GridLayout.LayoutParams.MATCH_PARENT;
//		
//		
//		gparams.leftMargin = 20;
//		gparams.rightMargin = 20;
//		gparams.topMargin = 20;
//		gparams.bottomMargin = 20;
		
//		

		gameField.setRowCount(rows);
		gameField.setColumnCount(cols);
		
		gameField.setLayoutParams(gparams);
		gameField.setBackgroundColor(Color.BLACK);

		return gameField;

	}
	
	public static float getMenuWidthHidden(Context context){
		Point size = getScreenResolution(context);
		float width = size.x * sideMenuWidthHidden;
		
		return width;
	}
	
	public static float getMenuWidthExpanded(Context context){
		
		
		Point size = getScreenResolution(context);
		
		float width = size.x * sideMenuWidthExpanded;
		
		return width;
		
	}
	
	public static Point getScreenResolution(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		Point size = new Point();
		display.getSize(size);
		
		return size;
	}
}
