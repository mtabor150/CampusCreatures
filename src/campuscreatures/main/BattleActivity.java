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


@SuppressLint("NewApi")
public class BattleActivity extends Activity {

	//private boolean isSinglePlayer;
	
	//one battle, two creatures, one boolean to track the player's turn
	private Battle currentBattle;
	//private BattleCreature player;
	//private BattleCreature opponent;

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
		 * set up sample battle if 
		 */
		System.out.println("...onCreate: A");
		currentBattle = (Battle) getIntent().getSerializableExtra("Battle");
		System.out.println("got here A");
		if(currentBattle==null) {
			System.out.println("got here C");
			setupSampleBattle();
		}
		System.out.println("...onCreate: B");
		System.out.println(currentBattle);
		setupBattleStats();
		System.out.println("determination");
		/*
		 * set up scrollViews
		 */
		addBattleActionScrollViews();
		System.out.println("got here z");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}
	
	//The only button should now be connected to this function
	public void implementRound(View view) {
		System.out.println("Got here AA");
		currentBattle.implementRound();
		System.out.println("Got here AB");
		refreshStats();
		System.out.println("Got ehre AC");
		refreshMessage();
		System.out.println("Got here ZZ");
	}

	//for the message textView at the bottom of the page, this function sets the value
	private void refreshMessage() {
		messageTextView.setText(currentBattle.battlePromptAsString());
	}
	
	//refreshes stats
	private void refreshStats() {
		BattleCreature player = currentBattle.getPlayerBattleCreature();
		BattleCreature opponent = currentBattle.getOpponentBattleCreature();
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerLevel.setText("Level: " + player.getLevel());
		playerXP.setText("Experience: " + player.getCreatureExperience());
		
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
		oppLevel.setText("Level: " + opponent.getLevel());
		oppXP.setText("Experience: " + opponent.getCreatureExperience());		
	}
	
	/*
	 * This sets up the forms and their stats properly for the battle sample
	 */
	private void setupBattleStats() {
		if(currentBattle == null) { //otherwise this function will crash
			return;
		}
		System.out.println("Setup A");
		//create TableRows and set them equal to those in the activity_battle.xml file
		TableRow playerTitleTR= (TableRow) this.findViewById(R.id.playerTitle);
		TableRow playerHealthTR = (TableRow) this.findViewById(R.id.playerHealth);
		TableRow playerLevelTR = (TableRow) this.findViewById(R.id.playerLevel);
		TableRow playerXPTR = (TableRow) this.findViewById(R.id.playerExperience);
		TableRow playerTurnTR = (TableRow) this.findViewById(R.id.playerTurn);
		
		System.out.println("Setup B");
		TableRow oppTitleTR = (TableRow) this.findViewById(R.id.oppTitle);
		TableRow oppHealthTR = (TableRow) this.findViewById(R.id.oppHealth);
		TableRow oppLevelTR = (TableRow) this.findViewById(R.id.oppLevel);
		TableRow oppXPTR = (TableRow) this.findViewById(R.id.oppExperience);
		TableRow oppTurnTR = (TableRow) this.findViewById(R.id.oppTurn);

		System.out.println("Setup C");
		//create player textViews to be added to tableRows
		BattleCreature player = currentBattle.getPlayerBattleCreature();
		System.out.println(player);
		System.out.println("Setup C.1");
		playerTitle = new TextView(this);
		playerHealth = new TextView(this);
		System.out.println("Setup C.1.2");
		playerLevel = new TextView(this);
		playerXP = new TextView(this);
		playerTitle.setText(player.getTitle());
		System.out.println("Setup C.2");
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerLevel.setText("Level: " + player.getLevel());
		playerXP.setText("Experience: " + player.getCreatureExperience());
		playerTitleTR.addView(playerTitle);
		playerHealthTR.addView(playerHealth);
		playerLevelTR.addView(playerLevel);
		playerXPTR.addView(playerXP);

		System.out.println("Setup D");
		//create opponent TextViews to be added to TableRows
		BattleCreature opponent = currentBattle.getOpponentBattleCreature();
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

		System.out.println("Setup E");
		//create message TextView
		messageTextView = (TextView) this.findViewById(R.id.battleMessage);
		messageTextView.setText("Fight!");
		System.out.println("Setup F");
	}
	
	private void addBattleActionScrollViews() {
		//instantiate battle action scrollviews
		final BattleCreature player = currentBattle.getPlayerBattleCreature();
		playerBattleActionsListView = (ListView) this.findViewById(R.id.listview1);
		String[] playerBattleActions = new String[player.getBattleActions().size()];
		for(int i=0; i<player.getBattleActions().size(); i++) {
			playerBattleActions[i] = player.getBattleActions().get(i).getTitle();
		}
		//new ArrayAdapter<String>();
		playerBattleActionsListView.setAdapter(new ArrayAdapter<String>( this, R.layout.battle_actions_list , playerBattleActions));
		playerBattleActionsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentBattle.choosePlayerBattleAction((int)id);
			    //player.chooseBattleAction((int)id);
			    //currentBattle.addBattlePromptMessage("player chose " + player.getCurrentBattleAction().getTitle());
			    refreshMessage();
			}
		});
		
		if(!currentBattle.isSinglePlayer()){
			final BattleCreature opponent = currentBattle.getOpponentBattleCreature();
			opponentBattleActionsListView = (ListView) this.findViewById(R.id.listview2);
			String[] oppBattleActions = new String[opponent.getBattleActions().size()];
			for(int i=0; i<player.getBattleActions().size(); i++) {
				oppBattleActions[i] = opponent.getBattleActions().get(i).getTitle();
			}
			opponentBattleActionsListView.setAdapter(new ArrayAdapter<String>(this, R.layout.battle_actions_list, oppBattleActions));
			opponentBattleActionsListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					currentBattle.chooseOppBattleAction((int)id);
					//opponent.chooseBattleAction((int)id);
					//currentBattle.addBattlePromptMessage("opponent chose " + opponent.getCurrentBattleAction().getTitle());
					refreshMessage();
				}
			});
		}
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
		BattleCreature player = new BattleCreature("Phil",1,4,10,10,0,simpleActions1);
		BattleCreature opponent = new BattleCreature("Mark",1,3,10,10,0,simpleActions2);
		System.out.println("got here F");
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
		System.out.println("got here G");
	}

}