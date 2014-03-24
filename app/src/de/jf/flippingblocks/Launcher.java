package de.jf.flippingblocks;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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
		
		edit_col = new EditText(this);
		edit_row = new EditText(this);
		
		edit_col.setTextColor(Color.CYAN);
		edit_row.setTextColor(Color.CYAN);
		
		mainContent.addView(edit_col);
		mainContent.addView(edit_row);
		
		mainContent.addView(CentralStyleGenerator.generateButton(this, "Start", generateModusListener(0, 0),false));
		
		
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
		
		// muss wieder ge
		return new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO muss wieder geändert werden
				int col_n= Integer.valueOf(edit_col.getText().toString());
				int row_n= Integer.valueOf(edit_row.getText().toString()); 
				startNewGame(col_n , row_n);
				
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
