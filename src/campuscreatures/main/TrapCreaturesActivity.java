package campuscreatures.main;

import java.util.ArrayList;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
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
		
		currentBattle = null;
		
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
	
	public void goToBattle(View view) {
		setupSampleBattle2();
		Intent i = new Intent(this, BattleActivity.class);
		i.putExtra("Battle", currentBattle);
		System.out.println("goToBattle...");
		startActivity(i);
	}

	public void goToDBTesting(View view){
		Intent i = new Intent(this, DatabaseActivity.class);
		startActivity(i);
	}
	
	private void setupSampleBattle2() {
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
		BattleCreature player = new BattleCreature("philanderphil",1,4,10,10,0,simpleActions1);
		BattleCreature opponent = new BattleCreature("Markus Taborius",1,3,10,10,0,simpleActions2);
		System.out.println("got here F");
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
		System.out.println("...setupSampleBattle");
	}

}
