package de.jf.flippingblocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Launcher extends Activity {

	// defining Content Container
	GridLayout mainLayout ;
	LinearLayout mainContent  ;
	LinearLayout sideMenuContent  ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initialise();
    }

    
    public void initialise(){
    	
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
    	
    	//defining ActionBar
    	this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setHomeButtonEnabled(true);
    	this.getActionBar().setTitle("Flipping Blocks");
    	
    
    	
    	
    	// defining the Layout
    	 mainLayout = new GridLayout(this);
    	 mainContent = new LinearLayout(this);
    	 sideMenuContent = new LinearLayout(this);
    	
    	// define mainLayout
    	mainLayout.setBackgroundColor(Color.BLACK);
    	mainLayout.setOrientation(0);
    	
    	
    	
    	sideMenuContent.setBackgroundColor(Color.GREEN);
    	
    	mainContent.setBackgroundColor(Color.WHITE);
    	
    	
    	
    	
    	
    	
    	// adding Components to different Components
    	this.setContentView(mainLayout);
    	
    	mainLayout.addView(sideMenuContent);
    	mainLayout.addView(mainContent);
    	
    	Button temp = generateButton("Start the game", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
			}
		});
    	mainContent.addView(temp);
    	
    	temp = generateButton("Options", null);
    	sideMenuContent.addView(temp);
    	
    	
    }
    
    
    
    public Button generateButton(String name, OnClickListener listener){
    	Button button = new Button(this);
    	button.setText(name);
    	button.setOnClickListener(listener);
    	return button;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.launcher, menu);
        System.out.println("Test");
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	LayoutParams temp = sideMenuContent.getLayoutParams();
		if(temp.width != 0)
		temp.width = 0;
		else
			temp.width = 200;
		sideMenuContent.setLayoutParams(temp);
    	return true;
    }
    
    
}
