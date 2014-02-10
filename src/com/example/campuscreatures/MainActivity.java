package com.example.campuscreatures;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void goToGame(View view) {
		Intent i = new Intent(this, GameActivity.class);
		startActivity(i);
	}
	
	public void goToSettings(View view) {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}
	
	public void goToProfile(View view){
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}

}
