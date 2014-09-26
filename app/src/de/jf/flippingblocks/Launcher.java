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
import android.widget.EditText;
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
	EditText edit_col ;
	EditText edit_row ;
	boolean menu_expanded = false;
	
	
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
		this.getActionBar().setIcon(R.drawable.ic_menu_moreoverflow);
		

		// initialize the Layout
		mainLayout = CentralStyleGenerator.generateMainLayout(this);
		mainContent =CentralStyleGenerator.generateLauncherField(this);
		sideMenuContent = CentralStyleGenerator.generateSideMenu(this);
		
		//Context ctx = this;
		SwipeGesture gesture = new SwipeGesture(this) {

			@Override
			public void onSwipeRight() {
				System.out.println("right");
				MoveMenu.enlargeMenu(sideMenuContent, ctx);
				menu_expanded = true;
			}

			@Override
			public void onSwipeLeft() {
				System.out.println("left");
				MoveMenu.minimizeMenu(sideMenuContent,ctx);
				menu_expanded = false;
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
		
	
		
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Start", generateModusListener(7, 9),false));
		
		
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
		

		if (menu_expanded) {
			
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthHidden(this),
					(temp.height));
			sideMenuContent.startAnimation(animation);
			menu_expanded = false;

		} else {
			
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthExpanded(this),
					(temp.height));
			sideMenuContent.startAnimation(animation);
			menu_expanded = true;

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	public OnClickListener generateModusListener(int cols, int rows){
		final int col = cols;
		final int row = rows;
		
		// muss wieder ge
		return new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO muss wieder geändert werden
				
				startNewGame(col , row);
				
			}
		};
	}
	
	public void startNewGame(int cols, int rows){
		Intent intent = new Intent(this, GameGui.class);
		intent.putExtra("col", cols);
		intent.putExtra("row", rows);
		startActivity(intent);
		
		
	}

}
