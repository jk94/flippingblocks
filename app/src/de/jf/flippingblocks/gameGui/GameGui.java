package de.jf.flippingblocks.gameGui;

import de.jf.flippingblocks.R;
import de.jf.flippingblocks.gestures.MoveMenu;
import de.jf.flippingblocks.gestures.SwipeGesture;
import de.jf.flippingblocks.graphics.BlockPanel;
import de.jf.flippingblocks.graphics.GUI_Element_Creator;
import de.jf.flippingblocks.graphics.ResizeAnimation;
import android.app.Activity;
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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_gamegui);
		initialisiere();
	}

	public void initialisiere() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// defining ActionBar
		this.getActionBar().hide();

		mainLayout = GUI_Element_Creator.generateMainLayout(this);
		swipeInMenu = GUI_Element_Creator.generateSideMenu(this);
		center = GUI_Element_Creator.generateLauncherField(this);
		// muss an dem modus angepasst werden
		field = GUI_Element_Creator.generateGameField(this,9,18);
	
		// add gestures
		SwipeGesture gesture = new SwipeGesture(this) {

			public void onSwipeRight() {
				MoveMenu.enlargeMenu(swipeInMenu);
			}

			public void onSwipeLeft() {
				MoveMenu.minimizeMenu(swipeInMenu);
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
		
		
		for(int i = 0; i < 9*18 ;i++){
			String name = "" + i;
			field.addView(new BlockPanel(this, name, Color.CYAN, 70, 70));
			
		}

	}

	
	



}
