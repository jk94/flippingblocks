package de.jf.flippingblocks;


import android.app.Activity;
import android.content.Context;
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
import de.jf.flippingblocks.graphics.CentralStyleGenerator;
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
		mainLayout = CentralStyleGenerator.generateMainLayout(this);
		mainContent =CentralStyleGenerator.generateLauncherField(this);
		sideMenuContent = CentralStyleGenerator.generateSideMenu(this);
		
		Context ctx = this;
		SwipeGesture gesture = new SwipeGesture(this) {

			@Override
			public void onSwipeRight() {
				MoveMenu.enlargeMenu(sideMenuContent, ctx);
			}

			@Override
			public void onSwipeLeft() {
				MoveMenu.minimizeMenu(sideMenuContent,ctx);
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

		
		sideMenuContent.addView(CentralStyleGenerator.generateButton(this, "Option", null, true));
		sideMenuContent.addView(CentralStyleGenerator.generateButton(this, "Save", null, true));
		sideMenuContent.addView(CentralStyleGenerator.generateButton(this, "Share", null, true));
		sideMenuContent.addView(CentralStyleGenerator.generateButton(this, "Highscores", null, true));
		
		
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize  3 x 3", generateModusListener(3,3), false));
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize  5 x 5", generateModusListener(5,5), false));
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize  7 x 7", generateModusListener(7,7), false));
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize  9 x 9", generateModusListener(9,9), false));
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize 11 x 11", generateModusListener(11,11), false));
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Fieldsize 20 x 20", generateModusListener(20,25), false));

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
		

		if (temp.width != CentralStyleGenerator.getMenuWidthHidden(this)) {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthHidden(this),
					(temp.height));
			sideMenuContent.startAnimation(animation);

		} else {

			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthExpanded(this),
					(temp.height));
			sideMenuContent.startAnimation(animation);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	public OnClickListener generateModusListener(int cols, int rows){
		final int col = cols;
		final int row = rows;
		return new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				startNewGame(col , row);
				
			}
		};
	}
	
	public void startNewGame(int cols, int rows){
		Intent intent = new Intent(this,GameGui.class);
		intent.putExtra("cols", cols);
		intent.putExtra("rows", rows);
		startActivity(intent);
	}

}
