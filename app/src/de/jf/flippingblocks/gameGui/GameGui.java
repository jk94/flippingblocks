package de.jf.flippingblocks.gameGui;

import java.io.Serializable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import de.jf.flippingblocks.Control;
import de.jf.flippingblocks.R;

import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.gestures.MoveMenu;
import de.jf.flippingblocks.gestures.SwipeGesture;
import de.jf.flippingblocks.graphics.BlockPanel;
import de.jf.flippingblocks.graphics.CentralStyleGenerator;

public class GameGui extends Activity  {

	GridLayout mainLayout;
	LinearLayout swipeInMenu;
	LinearLayout center;
	GridLayout field;
	private int grid_col;
	private int grid_row;

	Control control;
	AdView adView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_gamegui);
	
		
		
		initialisiere();
	}

	public void initialisiere() {
		
		
		
		
		
		
		
		grid_col = this.getIntent().getExtras().getInt("col");
		grid_row = this.getIntent().getExtras().getInt("row");
		control = new Control(this,grid_col,grid_row);
		
		
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
		
		AdView adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-9906233160008931/6838748603");
		
		 center.addView(adView);
		 AdRequest adRequest = new AdRequest.Builder().build();
		 adView.loadAd(adRequest);
	
		
		for(int i = 0; i < grid_col * grid_row ;i++){
			String name = "" + i;
			BlockPanel temp = addBlockPanel(EnumColor.CYAN);
			temp.setText(name);
			temp.setOnTouchListener(gesture);
			field.addView(temp);
			
		}

	}
	
	public BlockPanel addBlockPanel(EnumColor color){
		
		BlockPanel panel = CentralStyleGenerator.generateBlockPanel(this, color, grid_col);
		panel.setOnClickListener(generateOnClickListener(panel));
//		field.addView(panel);
		return panel;
	}
	
	
	public View.OnClickListener generateOnClickListener(BlockPanel panel){
		final BlockPanel panel_pan = panel;
		final Context cont = this;
		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BlockPanel pan = panel_pan;
				Context con = cont;
				control.action(pan, con);
				
			}
		};
		
		return listener;
	}

	
	@Override
	  public void onResume() {
	    super.onResume();
	    if (adView != null) {
	      adView.resume();
	    }
	  }

	  @Override
	  public void onPause() {
	    if (adView != null) {
	      adView.pause();
	    }
	    super.onPause();
	  }

	  /** Called before the activity is destroyed. */
	  @Override
	  public void onDestroy() {
	    // Destroy the AdView.
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
	  }



}
