package de.jf.flippingblocks;

import android.graphics.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import de.jf.flippingblocks.graphics.ResizeAnimation;
import de.jf.flippingblocks.gestures.*;

public class Launcher extends Activity implements OnClickListener {

	// defining Content Container
	GridLayout mainLayout;
	LinearLayout mainContent;
	LinearLayout sideMenuContent;
	SwipeGesture gestureListener;

	// muss dringend dynamisch gemacht werden
	final float layoutWidth = 400;
	final float widthHidden = 50;
	final int menu_margin = 5;
	final int content_margin = 50;

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
		mainLayout = new GridLayout(this);
		mainContent = new LinearLayout(this);
		sideMenuContent = new LinearLayout(this);
		

		SwipeGesture gesture = new SwipeGesture(this) {

			@Override
			public void onSwipeRight() {
				moveMenuInOut();
			}

			@Override
			public void onSwipeLeft() {
				moveMenuInOut();
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
		// define mainLayout
		mainLayout.setBackgroundColor(Color.BLACK);
		mainLayout.setOrientation(0);

		// define sideMenuContent
		sideMenuContent.setBackgroundColor(Color.DKGRAY);
		sideMenuContent.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
		sideMenuContent.setGravity(Gravity.LEFT);
		sideMenuContent.setOrientation(LinearLayout.VERTICAL);
		// sideMenuContent.getBackground().setColorFilter(Color.DKGRAY,
		// PorterDuff.Mode.LIGHTEN);

		// define mainContent
		// mainContent.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
		mainContent.setGravity(Gravity.CENTER_HORIZONTAL);
		mainContent.setBackgroundColor(Color.BLACK);
		mainContent.setOrientation(LinearLayout.VERTICAL);
		mainContent.setVerticalGravity(Gravity.CENTER_VERTICAL);
		mainContent.setDividerPadding(200);

		// adding Components to different Components
		this.setContentView(mainLayout);

		mainLayout.addView(sideMenuContent);
		mainLayout.addView(mainContent);

		sideMenuContent.addView(generateButton("Options", null, menu_margin,
				true));
		sideMenuContent
				.addView(generateButton("Test2", null, menu_margin, true));

		mainContent.addView(generateButton("Fieldsize: 3 x 3", null,
				content_margin, false));
		mainContent.addView(generateButton("Fieldsize: 5 x 5", null,
				content_margin, false));
		mainContent.addView(generateButton("Fieldsize: 7 x 7", null,
				content_margin, false));

		// //sideMenu ausblenden
		LayoutParams params = sideMenuContent.getLayoutParams();
		params.width = (int) (widthHidden);
		params.height = LayoutParams.MATCH_PARENT;
		sideMenuContent.setLayoutParams(params);
		//
		// //Content
		params = mainContent.getLayoutParams();
		params.height = LayoutParams.MATCH_PARENT;
		params.width = LayoutParams.MATCH_PARENT;
		mainContent.setLayoutParams(params);

		// initialize GestureListener

	}

	public Button generateButton(String name, OnClickListener listener,
			int margin, boolean widthMatchParent) {

		Button button = new Button(this);
		button.setText(name);
		button.setOnClickListener(listener);
		button.setTextColor(Color.CYAN);

		button.setSingleLine();

		
		if(widthMatchParent){
		button.getBackground().setColorFilter(Color.BLACK,
				PorterDuff.Mode.MULTIPLY);
		}else{
			button.getBackground().setColorFilter(Color.GRAY,
					PorterDuff.Mode.MULTIPLY);
		}

		LinearLayout.LayoutParams params;

		if (widthMatchParent) {
			params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);

		} else {

			params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);

		}

		params.bottomMargin = margin;
		button.setLayoutParams(params);

		return button;
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
		LayoutParams temp = sideMenuContent.getLayoutParams();
		System.out.println(temp.width);

		if (temp.width != widthHidden) {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), widthHidden,
					(temp.height));
			sideMenuContent.startAnimation(animation);

		} else {

			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(temp.width), (temp.height), layoutWidth,
					(temp.height));
			sideMenuContent.startAnimation(animation);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
