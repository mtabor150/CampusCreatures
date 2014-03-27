package campuscreatures.main;

import java.util.List;

import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	
	private ViewPager viewPager;
    private MainPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Home", "Map", "Creature Atlas" };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//actionbar setup
		viewPager = (ViewPager)findViewById(R.id.pager);
		mAdapter = new MainPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//add tabs
		for (String tab_name : tabs){
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(postion);
				
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
		
		
		
		
		
		DatabaseHelper database = new DatabaseHelper(this);
		/*
		 * test creatures database, and tests for all functions (add, delete, getAll)
		 */
		
		// ID,Name, Region, District, Type, Health, Magic, Attack, Defense, Speed, Moves Per Turn, Experience, Level

		database.addCreature(new Creatures("Sabortooth Tabor", "Ritter Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10));
		database.addCreature(new Creatures("Desi Djinn ", "Simon Rec", "Saint Louis University", "earth",  10, 10, 10, 10, 10, 10, 10, 10));
		database.addCreature(new Creatures("Philanderer", "Ritter Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10));
		database.addCreature(new Creatures("Weazel Man", "Pius Library", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10));
		database.addCreature(new Creatures("Adam the Intern", "Pius Library", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10));
		
		//get all Creatures
		//List<Creatures> list = database.getAllCreatures();
		
		//delete one creature
		//cause a crash, NEED TO FIX INSERT (creature) METHOD
		//database.deleteCreature(list.get(4)); //should delete Adam the Intern
		
		//get all creatures
		//database.getAllCreatures();


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
	
	public void goToMap(View view){
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}
	
	public void goToCreatureStats(View view){
		Intent i = new Intent(this, CreatureStatsActivity.class);
		startActivity(i);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	

}
