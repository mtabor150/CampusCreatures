package campuscreatures.main;

import java.util.ArrayList;
import java.util.List;

import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import campuscreatures.location.LocationService;
import campuscreatures.profile.UserProfile;
import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import campuscreatures.database.DatabaseService;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.RadioButton;

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
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		UserProfile tempProfile = new UserProfile(view.getContext());
		System.out.println("Creature count " + dbHelper.getCreature(2));
		//get first creature in player party
		BattleCreature tempCreature;
		tempCreature = tempProfile.getCreaturesList().get(0);
		System.out.println(tempCreature.getTitle());
		//developing moves for creatures
		BattleAction kick = new BattleAction("kick",1,0,10);
		BattleAction heal = new BattleAction("heal",0,2,10);
		BattleAction burn = new BattleAction("burn",2,0,5);
		BattleAction intimidate = new BattleAction("intimidate",1,0,10);
		//populate moveset for enemy creature
		ArrayList<BattleAction> simpleActions2 = new ArrayList();
		simpleActions2.add(kick);
		simpleActions2.add(heal);
		simpleActions2.add(burn);
		simpleActions2.add(intimidate);
		//populate player creature moveset
		tempCreature.addBattleAction(kick);
		tempCreature.addBattleAction(heal);
		tempCreature.addBattleAction(burn);
		tempCreature.addBattleAction(intimidate);
		//create enemy creature
		BattleCreature opponent = new BattleCreature(1,"Markus Taborius", "Ritter Hall", "Saint Louis University", "earth",10, 10 ,1,3,10,10,0,simpleActions2);
		System.out.println("got here F");
		currentBattle = new Battle(tempCreature,opponent, true);
		System.out.println("...setupTrueBattle");
		
		//Creatures local = dbHelper.getLocalCreatures("Ritter Hall");  //This line crashes program.
		//Creatures tempOpponent;
		//tempOpponent = locals.get(0);
		//BattleCreature tempBattleOpponent;
		//String fd = tempOpponent.getName();
		//System.out.println("THIS IS THE STRING OF DOOM: " + fd);
		//tempBattleOpponent = (fd, 1, 10, 10, 10, 10, simpleActions2);
		//tempBattleOpponent = (tempOpponent.getName(), 1, tempOpponent.getSpeed(), tempOpponent.getHealth(), tempOpponent.getHealth(), tempOpponent.getExperience(), simpleActions2);
		
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
		BattleCreature opponent = new BattleCreature(1,"Markus Taborius", "Ritter Hall", "Saint Louis University", "earth",10, 10 ,1,3,10,10,0,simpleActions2);
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
		System.out.println("...setupSampleBattle");
	}

}
