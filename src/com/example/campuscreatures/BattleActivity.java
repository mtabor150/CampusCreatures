package com.example.campuscreatures;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import battleMechanics.Battle;
import battleMechanics.BattleAction;
import battleMechanics.Creature;

public class BattleActivity extends Activity {
	
	//one battle, two creatures, one boolean to track the player's turn
	private Battle currentBattle;
	private Creature player;
	private Creature opponent;
	private boolean isPlayerTurn;
	
	//three modifiable TextViews for player and opponent
	//those for player:
	private TextView playerHealth;
	private TextView playerLevel;
	private TextView playerXP;
	//those for opponent:
	private TextView oppHealth;
	private TextView oppLevel;
	private TextView oppXP;
	
	private TextView messageTextView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		
		isPlayerTurn = true;
		//create sample battleActions, creatures, and Battle
		//create battleActions
		BattleAction kick = new BattleAction("kick",1,0,10);
		BattleAction heal = new BattleAction("heal",0,2,10);
		BattleAction burn = new BattleAction("burn",5,0,2);
		BattleAction push = new BattleAction("push",2,0,5);
		ArrayList<BattleAction> simpleActions = new ArrayList();
		simpleActions.add(kick);
		simpleActions.add(heal);
		simpleActions.add(burn);
		simpleActions.add(push);
		
		//create sample creatures
		player = new Creature("Phil",1,10,10,0,simpleActions);
		opponent = new Creature("Mark",1,10,10,0,simpleActions);
		
		//create the battle for this activity
		currentBattle = new Battle(player,opponent);

		
		//the following are the TableRows from the layout to be referenced
		TableRow playerTitleTR= (TableRow) this.findViewById(R.id.playerTitle);
		TableRow playerHealthTR = (TableRow) this.findViewById(R.id.playerHealth);
		TableRow playerLevelTR = (TableRow) this.findViewById(R.id.playerLevel);
		TableRow playerXPTR = (TableRow) this.findViewById(R.id.playerExperience);
		
		TableRow oppTitleTR = (TableRow) this.findViewById(R.id.oppTitle);
		TableRow oppHealthTR = (TableRow) this.findViewById(R.id.oppHealth);
		TableRow oppLevelTR = (TableRow) this.findViewById(R.id.oppLevel);
		TableRow oppXPTR = (TableRow) this.findViewById(R.id.oppExperience);
		
		
		//create player textViews to be added to tableRows
		TextView playerTitle = new TextView(this);
		playerHealth = new TextView(this);
		playerLevel = new TextView(this);
		playerXP = new TextView(this);
		playerTitle.setText(player.getTitle());
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerLevel.setText("Level: " + player.getLevel());
		playerXP.setText("Experience: " + player.getCreatureExperience());
		playerTitleTR.addView(playerTitle);
		playerHealthTR.addView(playerHealth);
		playerLevelTR.addView(playerLevel);
		playerXPTR.addView(playerXP);
		
		//create opponent TextViews to be added to TableRows
		TextView oppTitle = new TextView(this);
		oppHealth = new TextView(this);
		oppLevel = new TextView(this);
		oppXP = new TextView(this);
		oppTitle.setText(opponent.getTitle());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
		oppLevel.setText("Level: " + opponent.getLevel());
		oppXP.setText("Experience: " + opponent.getCreatureExperience());
		oppTitleTR.addView(oppTitle);
		oppHealthTR.addView(oppHealth);
		oppLevelTR.addView(oppLevel);
		oppXPTR.addView(oppXP);
		
		//create message TextView
		messageTextView = (TextView) this.findViewById(R.id.battleMessage);
		messageTextView.setText("Fight!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}
	
	//one function to implement each of the 
	private void battleAction(int i) {
		System.out.println("got here " + i);
		
		//if it is player's turn, allow the action
		if (isPlayerTurn) {
			isPlayerTurn = false;
			currentBattle.playerAction(i);
		}
		//Otherwise, have the opponent do its action
		//Until a simple AI is developed we will continue this implementation
		else {
			isPlayerTurn = true;
			currentBattle.oppAction(i);
		}
		System.out.println(player.getCurrentHealth());
		playerHealth.setText("Health: " + player.getCurrentHealth());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
		
		//if round == 0, battle is over, set the battle message
		if(currentBattle.getRound() == 0) {
			setMessage("Game Over! \nDoesn't matter who won, phil >> mark.");
		}
	}
	
	public void battleAction1(View view) {
		this.battleAction(0);
	}
	
	public void battleAction2(View view) {
		battleAction(1);
	}
	
	public void battleAction3(View view) {
		battleAction(2);
	}
	
	public void battleAction4(View view) {
		battleAction(3);
	}
	
	private void setMessage(String message) {
		messageTextView.setText(message);
	}

}
