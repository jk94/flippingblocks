package de.jf.flippingblocks;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.jf.flippingblocks.spielelemente.Spielfeld;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;

import android.os.Bundle;
import android.view.GestureDetector;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
		
//		//sideMenu ausblenden
//		LayoutParams params = sideMenuContent.getLayoutParams();
//		params.width = 0;
//		params.height = LayoutParams.MATCH_PARENT;
////		sideMenuContent.setLayoutParams(params);
//		
//		//Content 
//		params = mainContent.getLayoutParams();
////		params.height = LayoutParams.MATCH_PARENT;
//		mainContent.setLayoutParams(params);
		
		

		// initialize GestureListener
		SwipeGesture gesture = new SwipeGesture(this){
		

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
	

	//add gesture listener
	
	mainLayout.setOnTouchListener(gesture);
	mainContent.setOnTouchListener(gesture);
	sideMenuContent.setOnTouchListener(gesture);
		// define mainLayout
		mainLayout.setBackgroundColor(Color.BLACK);
		mainLayout.setOrientation(0);

		// define sideMenuContent
		sideMenuContent.setBackgroundColor(Color.GREEN);

		mainContent.setBackgroundColor(Color.WHITE);

		// adding Components to different Components
		this.setContentView(mainLayout);

		mainLayout.addView(sideMenuContent);
		mainLayout.addView(mainContent);

		sideMenuContent.addView(generateButton("Option", null));
		mainContent.addView(generateButton("Start game", null));
		
	}


	public Button generateButton(String name, OnClickListener listener) {
		Button button = new Button(this);
		button.setText(name);
		button.setOnClickListener(listener);
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

		if (temp.width != 0) {
			ResizeAnimation animation = new ResizeAnimation(sideMenuContent,
					(float) (temp.width), (float) (temp.height),
					(float) (temp.width) - (float) (temp.width),
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
