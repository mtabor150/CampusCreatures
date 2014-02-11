package com.example.campuscreatures;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

/*
 * MapActivity class: finds a user's location using latitude and longitude points. 
 * 
 * 
 */

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	public class MyLocationListener implements LocationListener {

    	@Override
		public void onLocationChanged(Location loc) {
	
    		// TODO Auto-generated method stub
    		loc.getLatitude();
    		loc.getLongitude();
		
    		String text = "My current location is: " + "Latitude = " + loc.getLatitude() + "Longitude = " + loc.getLongitude(); 
    		//Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    	}

    	@Override
    	public void onProviderDisabled(String provider) {
    		// TODO Auto-generated method stub
    		Toast.makeText(getApplicationContext(),"GPS Disabled", Toast.LENGTH_SHORT).show();
    	}

    	@Override
    	public void onProviderEnabled(String provider) {
    		// TODO Auto-generated method stub
    		Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();
    	}

    	@Override
    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		// TODO Auto-generated method stub
    		
    	}

    } /* End of Class MyLocationListener */
} 	  /* End of MapActivity */
