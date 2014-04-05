package campuscreatures.main;

import campuscreatures.location.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/*
 * MapActivity class: finds a user's location using latitude and longitude points. 
 * 
 * 
 */

public class MapActivity extends Activity {
	
	Intent locationIntent = null;
	LocationService location = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		locationIntent = new Intent(this, LocationService.class);
		startService(locationIntent);
		location = new LocationService(this);
	}
	
	@Override
	public void onStart(){
		super.onStart();
		startService(locationIntent);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		stopService(locationIntent);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		stopService(locationIntent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	public void displayGPS(View view){
		//mlocListener.getLocation();
		
		TextView textView1 = (TextView) findViewById(R.id.textView1);
	    textView1.setText("latitude " + location.getLatitude());
	    
	    TextView textView2 = (TextView) findViewById(R.id.textView2);
	    textView2.setText("longitude " + location.getLongitude());
	}
} 
