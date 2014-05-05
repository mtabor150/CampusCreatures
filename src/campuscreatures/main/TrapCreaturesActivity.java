package campuscreatures.main;

import java.util.ArrayList;
import java.util.List;

import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import campuscreatures.database.Player;
import campuscreatures.location.Location;
import campuscreatures.location.LocationService;
import campuscreatures.location.Zone;
import campuscreatures.profile.UserProfile;
import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import campuscreatures.database.*;
import campuscreatures.location.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;


public class TrapCreaturesActivity extends Activity {
	
	private boolean isSinglePlayer=true;//used only to TEST battle mechanics
	private Battle currentBattle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trap_creatures);
		
		
		
		currentBattle = null; //this is used to pass an Intent to a BattleActivity
		
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
	
	
	/*
	 * The below function shows how to pass a Battle object to a BattleActivity.
	 * As far as I can tell, this function must be placed in every activity which
	 * might want to start a battle.
	 */
	public void goToBattle(View view) {
		
		
		setupSampleBattle(); //only used to setup currentBattle but any battle can be used.
		Intent i = new Intent(this, BattleActivity.class);
		i.putExtra("Battle", currentBattle);
		System.out.println("goToBattle...");
		startActivity(i);
		
	}

	public void goToDBTesting(View view){
		
	}
	
	public void getZone(View view){
		
		TextView locationText = (TextView) findViewById(R.id.locationText);
		TextView zoneText = (TextView) findViewById(R.id.zoneText);
		MapZones zones = new MapZones();
		
		
		
		//Location currentLocation1 = MainActivity.location.getLocation();
		Location currentLocation = new Location(MainActivity.location.getLatitude(), MainActivity.location.getLongitude());
		//declares a temporary zone
		Zone currentZone = new Zone("temp", currentLocation, 0);
		
		for (Zone z : zones){
			System.out.println(z.getZoneName() + "getZoneName");
		
		}
		
		double currentError = 10000000.0;
		for (Zone z : zones){
			System.out.println(z.getZoneName());
			if (z.inZone(currentLocation) < currentError){
				currentZone = z;
				currentError = z.inZone(currentLocation);
			}
		}
		/*
		zoneText.setText(currentZone.getZoneName());
		locationText.setText(String.valueOf(MainActivity.location.getLatitude()) + " "
				+ String.valueOf(MainActivity.location.getLongitude()));
		*/
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		UserProfile tempProfile = new UserProfile(view.getContext());
		System.out.println("Creature count " + dbHelper.getCreature(2));
		//get first creature in player party
		BattleCreature tempCreature;
		tempCreature = tempProfile.getParty().getPartyMember(0);
		System.out.println("tempCreatureLevel = " + tempCreature.getLevel());
		//System.out.println(tempCreature.getTitle());
		
		//get current location
		List<Creatures> locals = new ArrayList<Creatures>();
		locals = dbHelper.getAllCreaturesByRegion(currentZone.getZoneName());
		int rando = locals.size();
		
		//make a random number generator to make encountered creature random
		Random encounter = new Random();
		int encounterID = encounter.nextInt(rando);
		
		//create a random creature based on location
		Creatures tempOpponent;
		int modifier = tempCreature.getLevel() * 5-4;
		int attMOD = 0;
		int defMOD = 0;
		int speedMOD = 0;
		int healthMOD = 0;
		for (int i = 0; i<modifier; i++){
			Random modChoose = new Random();
			int modChoice = modChoose.nextInt(4);
			if (modChoice == 0){
				attMOD++;
			}
			if (modChoice == 1){
				defMOD++;
			}
			if (modChoice == 2){
				speedMOD++;
			}
			if (modChoice == 3){
				healthMOD++;
			}
		}
		tempOpponent = locals.get(encounterID);
		ArrayList<BattleAction> moveset = tempOpponent.getMoveSet(tempOpponent);
		System.out.println(moveset);
		BattleCreature localBattleOpponent = new BattleCreature(tempOpponent.getId(), tempOpponent.getName(), tempOpponent.getRegion(),
				tempOpponent.getDistrict(), tempOpponent.getType(), 1 + attMOD, 1 + defMOD, tempCreature.getLevel(), 
				1 + speedMOD, 5+ (5 * healthMOD), 5 + (5 * healthMOD), tempOpponent.getExperience(), moveset);		
		
		//start the battle
		currentBattle = new Battle(tempCreature,localBattleOpponent, true);
		System.out.println("...setupTrueBattle");
		
		Intent i = new Intent(this, BattleActivity.class);
		i.putExtra("Battle", currentBattle);
		System.out.println("goToBattle...");
		startActivity(i);
		dbHelper.close();
	}
	
	/*
	 * this is only used to show that we can pass a Battle object from this activity to another
	 * activity.
	 */
	private void setupSampleBattle() {
		System.out.println("setupSampelActivity...");
		BattleAction kick = new BattleAction("kick",1,0,10);
		BattleAction heal = new BattleAction("heal",0,2,10);
		BattleAction burn = new BattleAction("burn",2,0,5);
		BattleAction push = new BattleAction("push",2,0,5);
		BattleAction intimidate = new BattleAction("intimidate",1,0,10);
		
		System.out.println("got here D");
		ArrayList<BattleAction> simpleActions1 = new ArrayList();
		simpleActions1.add(kick);
		simpleActions1.add(heal);
		simpleActions1.add(burn);
		simpleActions1.add(push);
		
		ArrayList<BattleAction> simpleActions2 = new ArrayList();
		simpleActions2.add(kick);
		simpleActions2.add(heal);
		simpleActions2.add(burn);
		simpleActions2.add(intimidate);
		
		System.out.println("got here E");
		//create sample creatures
		BattleCreature player = new BattleCreature(0,"Tester", "Ritter Hall", "Saint Louis University", "earth",10, 10 ,1,3,10,10,0,simpleActions1);
		BattleCreature opponent = new BattleCreature(1,"Marcus Taborius", "Ritter Hall", "Saint Louis University", "earth",10, 10 ,1,3,10,10,0,simpleActions2);
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
		System.out.println("...setupSampleBattle");
	}

}
