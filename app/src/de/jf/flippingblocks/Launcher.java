package de.jf.flippingblocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.jf.flippingblocks.spielelemente.Spielfeld;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;

import android.os.Bundle;
import android.view.GestureDetector;

import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.view.Display;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import de.jf.flippingblocks.graphics.ResizeAnimation;
import de.jf.flippingblocks.gestures.*;

public class Launcher extends Activity implements OnClickListener {

	// defining Content Container
	GridLayout mainLayout;
	LinearLayout mainContent;
	LinearLayout sideMenuContent;
	SwipeGesture gestureListener;

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

			public void onSwipeRight() {
				moveMenuInOut();
			}

			public void onSwipeLeft() {
				moveMenuInOut();
			}

			public void onSwipeTop() {
			}

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
		
		//define mainContent
//		mainContent.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
		mainContent.setGravity(Gravity.CENTER_HORIZONTAL);
		mainContent.setBackgroundColor(Color.BLACK);
		mainContent.setOrientation(LinearLayout.VERTICAL);
		mainContent.setVerticalGravity(Gravity.CENTER_VERTICAL);
		mainContent.setDividerPadding(200);
		

		// adding Components to different Components
		this.setContentView(mainLayout);

		mainLayout.addView(sideMenuContent);
		mainLayout.addView(mainContent);

		
		int menu_margin=25;
		int content_margin = 50;
		
		
		sideMenuContent.addView(generateButton("Options", null,menu_margin));
		sideMenuContent.addView(generateButton("Test2", null,menu_margin));
		
		
		
		mainContent.addView(generateButton("3 x 3", null,content_margin));
		mainContent.addView(generateButton("5 x 5", null,content_margin));
		mainContent.addView(generateButton("7 x 7", null,content_margin));
		
		

		
		// //sideMenu ausblenden
				LayoutParams params = sideMenuContent.getLayoutParams();
				params.width = 50;
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
	
	


	public Button generateButton(String name, OnClickListener listener, int margin) {
		
		Button button = new Button(this);
		button.setText(name);
		button.setOnClickListener(listener);
		button.setTextColor(Color.CYAN);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
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

	public boolean onOptionsItemSelected(MenuItem item) {

		moveMenuInOut();

		return true;
	}

	public void moveMenuInOut() {
		LayoutParams temp = sideMenuContent.getLayoutParams();
		float layoutWidth = 200;
		float widthHidden = 50;
		
		

		if (temp.width != widthHidden) {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(float) (temp.width), (float) (temp.height),
					widthHidden,
					(float) (temp.height));
			sideMenuContent.startAnimation(animation);
				
			
			
		} else {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(float) (temp.width), (float) (temp.height), layoutWidth,
					(float) (temp.height));
			sideMenuContent.startAnimation(animation);
			
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
