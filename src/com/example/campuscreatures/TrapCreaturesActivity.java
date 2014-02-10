package com.example.campuscreatures;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;

public class TrapCreaturesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trap_creatures);
		
		TabHost tabs = (TabHost)findViewById(android.R.id.tabhost);
	    tabs.setup();

	    // Calculator
	    TabHost.TabSpec reconTab = tabs.newTabSpec("Recon");
	    reconTab.setContent(R.id.Recon);
	    reconTab.setIndicator("Recon");
	    tabs.addTab(reconTab);

	    // Home
	    TabHost.TabSpec huntTab = tabs.newTabSpec("hunt");
	    huntTab.setContent(R.id.Hunt);
	    huntTab.setIndicator("Hunt");
	    tabs.addTab(huntTab);

	    // Home
	    TabHost.TabSpec exploreTab = tabs.newTabSpec("explore");
	    exploreTab.setIndicator("Explore");
	    exploreTab.setContent(R.id.Explore);
	    tabs.addTab(exploreTab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trap_creatures, menu);
		return true;
	}

}
