package campuscreatures.main;

import java.util.ArrayList;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/*
 * Note for major editing:
 * For restructuring the user interface:
 * *Add a timer or a button to implement the round after actions have been chosen.
 * *change the "messageTextView" at the bottom to aggregate messages.
 */
@SuppressLint("NewApi")
public class BattleActivity extends Activity {

	//private boolean isSinglePlayer;
	
	//one battle, two creatures, one boolean to track the player's turn
	private Battle currentBattle;
	private BattleCreature player;
	private BattleCreature opponent;

	//three modifiable TextViews for player and opponent
	//those for player:
	private TextView playerTitle;
	private TextView playerHealth;
	private TextView playerLevel;
	private TextView playerXP;
	//those for opponent:
	private TextView oppTitle;
	private TextView oppHealth;
	private TextView oppLevel;
	private TextView oppXP;

	private TextView messageTextView;
	
	//ScrollViews for battleActions
	private ListView playerBattleActionsListView;
	private ListView opponentBattleActionsListView;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		/*
		 * set up sample battle and set up the battle stats
		 */
		setupSampleBattle();
		setupBattleStats();
		
		/*
		 * set up scrollViews
		 */
		addBattleActionScrollViews();
		
		if (currentBattle.isSinglePlayer()) {
			setMessage("it is a single player round");
		}
		else {
			setMessage("it is a two player round");
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}
	
	//The only button should now be connected to this function
	public void implementRound(View view) {
		currentBattle.implementRound();
		setHealth();
	}

	//for the message textView at the bottom of the page, this function sets the value
	private void setMessage(String message) {
		messageTextView.setText(message);
	}
	
	private void setHealth() {
		playerHealth.setText("Health: " + player.getCurrentHealth());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
	}
	
	/*
	 * This function creates several battle actions for two battle creatures, who are
	 * then used to make the battle for this activity. That is, if a sample is needed
	 */
	private void setupSampleBattle() {
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
		player = new BattleCreature("Phil",1,4,10,10,0,simpleActions1);
		opponent = new BattleCreature("Mark",1,3,10,10,0,simpleActions2);
		
		//create the battle for this activity
		boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, isSinglePlayer);
	}
	
	
	/*
	 * This sets up the forms and their stats properly for the battle sample
	 */
	private void setupBattleStats() {
		if(opponent == null | player == null) { //otherwise this function will crash
			return;
		}
		
		//create TableRows and set them equal to those in the activity_battle.xml file
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
		playerTitle.setText(player.getTitle());
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerLevel.setText("Level: " + player.getLevel());
		playerXP.setText("Experience: " + player.getCreatureExperience());
		playerTitleTR.addView(playerTitle);
		playerHealthTR.addView(playerHealth);
		playerLevelTR.addView(playerLevel);
		playerXPTR.addView(playerXP);

		//create opponent TextViews to be added to TableRows
		oppTitle = new TextView(this);
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
	
	private void addBattleActionScrollViews() {
		//instantiate battle action scrollviews
		playerBattleActionsListView = (ListView) this.findViewById(R.id.listview1);
		opponentBattleActionsListView = (ListView) this.findViewById(R.id.listview2);
		
		//make a scroll view example...
		String[] playerBattleActions = new String[player.getBattleActions().size()];
		for(int i=0; i<player.getBattleActions().size(); i++) {
			playerBattleActions[i] = player.getBattleActions().get(i).getTitle();
		}
		String[] oppBattleActions = new String[opponent.getBattleActions().size()];
		for(int i=0; i<player.getBattleActions().size(); i++) {
			oppBattleActions[i] = opponent.getBattleActions().get(i).getTitle();
		}
		
		//new ArrayAdapter<String>();
		playerBattleActionsListView.setAdapter(new ArrayAdapter<String>( this, R.layout.battle_actions_list , playerBattleActions));
		opponentBattleActionsListView.setAdapter(new ArrayAdapter<String>(this, R.layout.battle_actions_list, oppBattleActions));
		//ListView listView = getListView();
		//playerBattleActionsListView.setTextFilterEnabled(true);
		
		playerBattleActionsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    player.chooseBattleAction((int)id);
			    setMessage("player chose " + player.getCurrentBattleAction().getTitle());
			}
		});
		
		opponentBattleActionsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				opponent.chooseBattleAction((int)id);
				setMessage("opponent chose " + opponent.getCurrentBattleAction().getTitle());
			}
		});
	}

}