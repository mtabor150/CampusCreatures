package campuscreatures.main;

import com.example.campuscreatures.R;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * MapActivity class: finds a user's location using latitude and longitude points. 
 * 
 * 
 */

public class MapActivity extends Activity {
	
	MyLocationListener mlocListener = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
	}
	
	@Override
	public void onPause(){
		super.onPause();
 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	public void setGPS(View view){
		mlocListener.getLocation();
	}

	public class MyLocationListener implements LocationListener {
		
		private String latitude;
		private String longitude;
    	@Override
		public void onLocationChanged(Location loc) {
    		// TODO Auto-generated method stub
		
    		latitude = "Latitude = " + loc.getLatitude();
    		longitude = "Longitude = " + loc.getLongitude();
    		
    		/*
    		
    		TextView textView1 = (TextView) findViewById(R.id.textView1);
    	    textView1.setText(latitude);
    	    
    	    TextView textView2 = (TextView) findViewById(R.id.textView2);
    	    textView2.setText(longitude);*/
    		

    	}

    	@Override
    	public void onProviderDisabled(String provider) {
    		// TODO Auto-generated method stub
    		TextView textView1 = (TextView) findViewById(R.id.textView1);
    		textView1.setText("GPS Disabled");
    	}

    	@Override
    	public void onProviderEnabled(String provider) {
    		// TODO Auto-generated method stub
    		TextView textView1 = (TextView) findViewById(R.id.textView2);
    		textView1.setText("GPS Enabled");
    	}

    	@Override
    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		// TODO Auto-generated method stub
    		
    	}
    	
    	public void getLocation(){
    		
    		
    		TextView textView1 = (TextView) findViewById(R.id.textView1);
    		TextView textView2 = (TextView) findViewById(R.id.textView2);
    		if(longitude!=null && latitude!=null) {
    			textView1.setText(latitude);
    			textView2.setText(longitude);
    		}
    		else {
    			textView1.setText("buffering...");
    			textView2.setText("Click again in a moment");
    		}
    	}

    } /* End of Class MyLocationListener */
} 	  /* End of MapActivity */
