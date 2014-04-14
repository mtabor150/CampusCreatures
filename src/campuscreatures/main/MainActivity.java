package campuscreatures.main;

import campuscreatures.database.DatabaseHelper;
import campuscreatures.location.LocationService;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private MainPagerAdapter mAdapter;

	private Intent locationIntent = null;
	public static LocationService location = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setup
		mAdapter = new MainPagerAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(mAdapter);
		viewPager.setCurrentItem(1);
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});

		//initialize location service
		locationIntent = new Intent(this, LocationService.class);
		startService(locationIntent);
		location = new LocationService(this);
	}

	protected void onDestroy(){
		stopService(locationIntent);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void goToTrap(View view) {
		Intent i = new Intent(this, TrapCreaturesActivity.class);
		startActivity(i);
	}

	public void goToSettings(View view) {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

	public void goToMap(View view) {
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}

	public void goToCreatureStats(View view) {
		Intent i = new Intent(this, CreatureStatsActivity.class);
		startActivity(i);
	}

}