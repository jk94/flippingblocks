package de.jf.flippingblocks.gameGui;

import de.jf.flippingblocks.R;
import de.jf.flippingblocks.gestures.MoveMenu;
import de.jf.flippingblocks.gestures.SwipeGesture;
import de.jf.flippingblocks.graphics.BlockPanel;
import de.jf.flippingblocks.graphics.CentralStyleGenerator;
import de.jf.flippingblocks.graphics.ResizeAnimation;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GameGui extends Activity {

	GridLayout mainLayout;
	LinearLayout swipeInMenu;
	LinearLayout center;
	GridLayout field;
	private int grid_col;
	private int grid_row;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_gamegui);
	
		initialisiere();
	}

	public void initialisiere() {
		
		grid_col = this.getIntent().getExtras().getInt("cols");
		grid_row = this.getIntent().getExtras().getInt("rows");
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// defining ActionBar
		this.getActionBar().hide();

		mainLayout = CentralStyleGenerator.generateMainLayout(this);
		swipeInMenu = CentralStyleGenerator.generateSideMenu(this);
		center = CentralStyleGenerator.generateLauncherField(this);
		// muss an dem modus angepasst werden
		field = CentralStyleGenerator.generateGameField(this,grid_col,grid_row);
	
		// add gestures
		Context ctx = this;
		SwipeGesture gesture = new SwipeGesture(this) {

			public void onSwipeRight() {
				MoveMenu.enlargeMenu(swipeInMenu,ctx);
			}

			public void onSwipeLeft() {
				MoveMenu.minimizeMenu(swipeInMenu,ctx);
			}

			public void onSwipeTop() {
			}

			public void onSwipeBottom() {
			}

		};

		mainLayout.setOnTouchListener(gesture);
		swipeInMenu.setOnTouchListener(gesture);
		field.setOnTouchListener(gesture);

		// add components

		this.setContentView(mainLayout);
		mainLayout.addView(swipeInMenu);
		mainLayout.addView(center);
		center.addView(field);
		
		
		for(int i = 0; i < grid_col * grid_row ;i++){
			String name = "" + i;
			field.addView(CentralStyleGenerator.generateBlockPanel(this, Color.CYAN, grid_col));
			
		}

	}
	
	public BlockPanel addBlockPanel(int color){
		
		BlockPanel panel = CentralStyleGenerator.generateBlockPanel(this, color, grid_col);
		field.addView(panel);
		return panel;
	}

	
	



}
