package de.jf.flippingblocks;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import de.jf.flippingblocks.gameGui.GameGui;
import de.jf.flippingblocks.gestures.MoveMenu;
import de.jf.flippingblocks.gestures.SwipeGesture;
import de.jf.flippingblocks.graphics.GUI_Element_Creator;
import de.jf.flippingblocks.graphics.ResizeAnimation;


public class Launcher extends Activity implements OnClickListener {

	// defining Content Container
	GridLayout mainLayout;
	LinearLayout mainContent;
	LinearLayout sideMenuContent;
	SwipeGesture gestureListener;
	
	
	// muss dringend dynamisch gemacht werden
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		initialise();
	}

	public void initialise() {

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// defining ActionBar
		this.getActionBar().setDisplayHomeAsUpEnabled(true);
		this.getActionBar().setHomeButtonEnabled(true);
		this.getActionBar().setTitle("Flipping Blocks");
		

		// initialize the Layout
		mainLayout = GUI_Element_Creator.generateMainLayout(this);
		mainContent =GUI_Element_Creator.generateLauncherField(this);
		sideMenuContent = GUI_Element_Creator.generateSideMenu(this);
		

		SwipeGesture gesture = new SwipeGesture(this) {

			@Override
			public void onSwipeRight() {
				MoveMenu.enlargeMenu(sideMenuContent);
			}

			@Override
			public void onSwipeLeft() {
				MoveMenu.minimizeMenu(sideMenuContent);
			}

			@Override
			public void onSwipeTop() {
			}

			@Override
			public void onSwipeBottom() {
			}
		};

		// add gesture listener

		mainLayout.setOnTouchListener(gesture);
		mainContent.setOnTouchListener(gesture);
		sideMenuContent.setOnTouchListener(gesture);
		
		//filling with content
		
		
		this.setContentView(mainLayout);
		mainLayout.addView(sideMenuContent);
		mainLayout.addView(mainContent);

		
		sideMenuContent.addView(GUI_Element_Creator.generateButton(this, "Option", null, true));
		sideMenuContent.addView(GUI_Element_Creator.generateButton(this, "Save", null, true));
		sideMenuContent.addView(GUI_Element_Creator.generateButton(this, "Share", null, true));
		sideMenuContent.addView(GUI_Element_Creator.generateButton(this, "Highscores", null, true));
		
		
		mainContent.addView(GUI_Element_Creator.generateButton(this, "Fieldsize  3 x 3", generateModusListener("3x3"), false));
		mainContent.addView(GUI_Element_Creator.generateButton(this, "Fieldsize  5 x 5", generateModusListener("5x5"), false));
		mainContent.addView(GUI_Element_Creator.generateButton(this, "Fieldsize  7 x 7", generateModusListener("7x7"), false));
		mainContent.addView(GUI_Element_Creator.generateButton(this, "Fieldsize  9 x 9", generateModusListener("9x9"), false));
		mainContent.addView(GUI_Element_Creator.generateButton(this, "Fieldsize 11 x 11", generateModusListener("11x11"), false));
		

	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		moveMenuInOut();

		return true;
	}

	public void moveMenuInOut() {
		//just for the toolbar at the top
		LayoutParams temp = sideMenuContent.getLayoutParams();
		

		if (temp.width != GUI_Element_Creator.sideMenuWidthHidden) {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), GUI_Element_Creator.sideMenuWidthHidden,
					(temp.height));
			sideMenuContent.startAnimation(animation);

		} else {

			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), GUI_Element_Creator.sideMenuWidthExpanded,
					(temp.height));
			sideMenuContent.startAnimation(animation);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	public OnClickListener generateModusListener(String modus){
		final String mod = modus;
		return new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				startNewGame(mod);
				
			}
		};
	}
	
	public void startNewGame(String modus){
		Intent intent = new Intent(this,GameGui.class);
		startActivity(intent);
	}

}
