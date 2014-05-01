package campuscreatures.main;

import java.util.ArrayList;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import android.os.Bundle;
import android.os.Handler;
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
	private BattleCreature player;
	//private BattleCreature opponent;

	//three modifiable TextViews for player and opponent
	//those for player:
	private TextView playerTitle;
	private TextView playerHealth;
	
	//those for opponent:
	private TextView oppTitle;
	private TextView oppHealth;

	private TextView messageTextView;

	private Button bActionButton1;
	private Button bActionButton2;
	private Button bActionButton3;
	private Button bActionButton4;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		/*
		 * set up sample battle if 
		 */
		currentBattle = (Battle) getIntent().getSerializableExtra("Battle");
		
		if(currentBattle==null) {
			
			setupSampleBattle();
		}
		
		setupBattleStats();
		
		/*
		 * set up Buttons
		 */
		assignBattleButtons();
		messageTextView.setTextColor(0xFFFFFFFF);
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
		
		currentBattle.implementRound();
		refreshStats();
		refreshMessage();
	
	}

	//for the message textView at the bottom of the page, this function sets the value
	private void refreshMessage() {
		messageTextView.setText(currentBattle.battlePromptAsString());
		
		/*
		 * the following code is used to allow the UIthread to finish, and thus
		 * update messageTextView to the proper size before implementing the Handler, h.
		 * If this is not done, the ScrollView scroll will scroll will scroll down to the bottom
		 * of the last version of messageTextView. This is because the UIThread has not
		 * updated the views until the activity thread finishes. This handler fixes the problem.
		 */
		 Handler h = new Handler();

		 h.postDelayed(new Runnable() {

		     @Override
		     public void run() {
		         ScrollView scroll = (ScrollView) findViewById(R.id.scrollView1);
		         scroll.fullScroll(View.FOCUS_DOWN);
		     }
		 }, 10);
		
	}
	
	//refreshes stats
	private void refreshStats() {
		BattleCreature player = currentBattle.getPlayerBattleCreature();
		BattleCreature opponent = currentBattle.getOpponentBattleCreature();
		
		playerHealth.setText("Health: " + player.getCurrentHealth());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
	}
	
	/*
	 * This sets up the forms and their stats properly for the battle sample
	 */
	private void setupBattleStats() {
		if(currentBattle == null) { //otherwise this function will crash
			return;
		}
		
		//create player textViews to be added to tableRows
		BattleCreature player = currentBattle.getPlayerBattleCreature();
		
		playerTitle = (TextView)findViewById(R.id.playerTitle);
		playerTitle.setText("Lvl " + player.getLevel() + " " + player.getTitle());
		playerTitle.setTextColor(0xFFFFFFFF);
		
		playerHealth = (TextView)findViewById(R.id.playerHealth);
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerHealth.setTextColor(0xFFFFFFFF);
		
		//create opponent TextViews to be added to TableRows
		BattleCreature opponent = currentBattle.getOpponentBattleCreature();
		
		oppTitle = (TextView)findViewById(R.id.oppTitle);
		oppTitle.setText("Lvl " + player.getLevel() + " " + opponent.getTitle());
		oppTitle.setTextColor(0xFFFFFFFF);
		
		oppHealth = (TextView)findViewById(R.id.oppHealth);
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
		oppHealth.setTextColor(0xFFFFFFFF);

		//create message TextView
		messageTextView = (TextView) this.findViewById(R.id.battleMessage);
		
		if(!currentBattle.isStarted()) {
			messageTextView.setText("Fight!");
		}
		else {
			refreshMessage();
		}
	}
	
	private void assignBattleButtons(){
		
		bActionButton1 = (Button)findViewById(R.id.button1);
		bActionButton2 = (Button)findViewById(R.id.button2);
		bActionButton3 = (Button)findViewById(R.id.button3);
		bActionButton4 = (Button)findViewById(R.id.button4);
		
		BattleCreature tempPlayer = currentBattle.getPlayerBattleCreature();
		
		//set text for buttons and assign battle actions
		bActionButton1.setText(tempPlayer.getBattleActions().get(0).getTitle());
		bActionButton1.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            	System.out.println("button fuck 4");
            	currentBattle.choosePlayerBattleAction(0);
            	refreshMessage();
            	implementRound(v); 
            	}
        });
		
		bActionButton2.setText(tempPlayer.getBattleActions().get(1).getTitle());
		bActionButton2.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            	currentBattle.choosePlayerBattleAction(1);
            	refreshMessage();
            	implementRound(v); 
            }
        });
		
		bActionButton3.setText(tempPlayer.getBattleActions().get(2).getTitle());
		bActionButton3.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            	currentBattle.choosePlayerBattleAction(2);
            	refreshMessage(); 
            	implementRound(v); 
            }
        });
		
		bActionButton4.setText(tempPlayer.getBattleActions().get(3).getTitle());
		bActionButton4.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            	currentBattle.choosePlayerBattleAction(3);
            	refreshMessage(); 
            	implementRound(v); 
            }
        });
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
		BattleCreature player = new BattleCreature("Phil",1,4,10,10,0,simpleActions1);
		BattleCreature opponent = new BattleCreature("Mark",1,3,10,10,0,simpleActions2);
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
	}

}