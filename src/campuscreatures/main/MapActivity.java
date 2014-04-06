package campuscreatures.main;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/*
 * MapActivity class: for testing display user's location using latitude and longitude 
 */

public class MapActivity extends Activity {
	
	ListView locationList;
	ArrayAdapter<String> locationArray; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		locationList = (ListView) findViewById(R.id.locationList);
		locationArray = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, 0);
		locationList.setAdapter(locationArray);

	}
	
	@Override
	public void onStart(){
		super.onStart();
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	public void displayGPS(View view){
		locationArray.clear();
		locationArray.add("latitude " + MainActivity.location.getLatitude());
		locationArray.add("longitude " + MainActivity.location.getLongitude());
	
	}
} 
