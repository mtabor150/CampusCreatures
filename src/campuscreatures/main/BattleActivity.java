package campuscreatures.main;

import java.util.ArrayList;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;

import com.example.campuscreatures.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("NewApi")
public class BattleActivity extends Activity {

	//private boolean isSinglePlayer;
	
	//one battle, two creatures, one boolean to track the player's turn
	private Battle currentBattle;
	private BattleCreature player;
	private BattleCreature opponent;
	//private boolean isPlayerTurn;

	//three modifiable TextViews for player and opponent
	//those for player:
	private TextView playerTitle;
	private TextView playerHealth;
	private TextView playerLevel;
	private TextView playerXP;
	private TextView playerTurn;
	//those for opponent:
	private TextView oppTitle;
	private TextView oppHealth;
	private TextView oppLevel;
	private TextView oppXP;
	private TextView oppTurn;

	private TextView messageTextView;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		
		//create sample battleActions, creatures, and Battle
		//create battleActions
		BattleAction kick = new BattleAction("kick",1,0,10);
		BattleAction heal = new BattleAction("heal",0,2,10);
		BattleAction burn = new BattleAction("burn",2,0,5);
		BattleAction push = new BattleAction("push",2,0,5);
		BattleAction intimidate = new BattleAction("intimidate",1,0,10);
		
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
		
		//create sample creatures
		player = new BattleCreature("Phil",1,10,10,0,simpleActions1);
		simpleActions2.remove(3);
		simpleActions2.add(intimidate);
		opponent = new BattleCreature("Mark",1,10,10,0,simpleActions2);

		//create the battle for this activity
		boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, isSinglePlayer);


		//the following are the TableRows from the layout to be referenced
		TableRow playerTitleTR= (TableRow) this.findViewById(R.id.playerTitle);
		TableRow playerHealthTR = (TableRow) this.findViewById(R.id.playerHealth);
		TableRow playerLevelTR = (TableRow) this.findViewById(R.id.playerLevel);
		TableRow playerXPTR = (TableRow) this.findViewById(R.id.playerExperience);
		TableRow playerTurnTR = (TableRow) this.findViewById(R.id.playerTurn);

		TableRow oppTitleTR = (TableRow) this.findViewById(R.id.oppTitle);
		TableRow oppHealthTR = (TableRow) this.findViewById(R.id.oppHealth);
		TableRow oppLevelTR = (TableRow) this.findViewById(R.id.oppLevel);
		TableRow oppXPTR = (TableRow) this.findViewById(R.id.oppExperience);
		TableRow oppTurnTR = (TableRow) this.findViewById(R.id.oppTurn);


		//create player textViews to be added to tableRows
		playerTitle = new TextView(this);
		playerHealth = new TextView(this);
		playerLevel = new TextView(this);
		playerXP = new TextView(this);
		playerTurn = new TextView(this);
		playerTitle.setText(player.getTitle());
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerLevel.setText("Level: " + player.getLevel());
		playerXP.setText("Experience: " + player.getCreatureExperience());
		playerTurn.setText("^");
		playerTitleTR.addView(playerTitle);
		playerHealthTR.addView(playerHealth);
		playerLevelTR.addView(playerLevel);
		playerXPTR.addView(playerXP);
		playerTurnTR.addView(playerTurn);

		//create opponent TextViews to be added to TableRows
		oppTitle = new TextView(this);
		oppHealth = new TextView(this);
		oppLevel = new TextView(this);
		oppXP = new TextView(this);
		oppTurn = new TextView(this);
		oppTitle.setText(opponent.getTitle());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
		oppLevel.setText("Level: " + opponent.getLevel());
		oppXP.setText("Experience: " + opponent.getCreatureExperience());
		oppTurn.setText("");
		oppTitleTR.addView(oppTitle);
		oppHealthTR.addView(oppHealth);
		oppLevelTR.addView(oppLevel);
		oppXPTR.addView(oppXP);
		oppTurnTR.addView(oppTurn);

		//create message TextView
		messageTextView = (TextView) this.findViewById(R.id.battleMessage);
		messageTextView.setText("Fight!");
		
		/*
		 * print out the value that was passed through the intent
		 */
		
		if (currentBattle.isSinglePlayer()) {
			setMessage("it is a single player round");
		}
		else {
			setMessage("it is a two player round");
		}
		
		
		//change names of battleAction buttons
		adjustBattleActionButtons();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}
	
	//change buttons to reflect names of attacks the current creature
	private void adjustBattleActionButtons() {
		//figure out which creature has it's turn and modify the buttons to reflect its moves
		BattleCreature tempCreature;
		if(currentBattle.isPlayerTurn()) {
			tempCreature = player;
		}
		else {
			tempCreature = opponent;
		}
		for (int i = 0; i< 4; i++) {
			Button tempButton = (Button) this.findViewById(R.id.button1);
			
			switch(i) {
			case 0: tempButton = (Button) this.findViewById(R.id.attack1);
			break;
			case 1: tempButton = (Button) this.findViewById(R.id.attack2);
			break;
			case 2: tempButton = (Button) this.findViewById(R.id.attack3);
			break;
			case 3: tempButton = (Button) this.findViewById(R.id.attack4);
			break;
			default: break;
			}
			//grab the layout, then set the text of the Button called R.id.Counter:
			//RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_battle);
			//remoteViews.setTextViewText(R.id.button1, "hole");
			tempButton.setText(tempCreature.getBattleActions().get(i).getTitle());
		}
		
	}

	//one function to implement each of the buttons
	private void battleAction(int i) {
		/*
		 * if it is player's turn, allow the action
		 * otherwise, have the opponent do its action for that button.
		 * Until a simple AI is developed we will continue this implementation
		 */
		if (currentBattle.isPlayerTurn()) {
			currentBattle.playerAction(i);
		}
		else {
			if(currentBattle.isSinglePlayer()) {
				return;
			}
			else {
				currentBattle.oppAction(i);
			}
		}
		setHealth();
		updateTurnPointer();
		

		//if round == 0, battle is over, set the battle message
		if(currentBattle.getRound() == 0) {
			if(player.getCurrentHealth() > 0) {
				setMessage(player.getTitle() + " won!");
			}
			else {
				setMessage(opponent.getTitle() + " won!");
			}
		}
		else{
			if(!currentBattle.isSinglePlayer()){
				adjustBattleActionButtons();
			}
			//updateTurnPointer();
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

	//for the message textView at the bottom of the page, this function sets the value
	private void setMessage(String message) {
		messageTextView.setText(message);
	}
	
	//change the "^" to the proper creature. "^" points to the creature whose turn it is
	private void updateTurnPointer() {
		if(currentBattle.isPlayerTurn()) {
			playerTurn.setText("^");
			oppTurn.setText("");
		}
		else {
			playerTurn.setText("");
			oppTurn.setText("^");
		}
	}
	
	private void setHealth() {
		playerHealth.setText("Health: " + player.getCurrentHealth());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
	}

}