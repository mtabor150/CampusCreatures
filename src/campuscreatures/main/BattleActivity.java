package campuscreatures.main;

import java.util.ArrayList;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import campuscreatures.battleMechanics.BattleCreature.creatureType;
import campuscreatures.battleMechanics.BattleUpgrade;
import campuscreatures.main.R.id;
import campuscreatures.profile.UserProfile;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

//TODO on a 7" tablet, varying creature name lengths can completely reposition items on the screen. this needs to be fixed.

@SuppressLint("NewApi")
public class BattleActivity extends Activity {

	private Battle currentBattle;

	private BattleActivity thisBattleActivity;

	//four modifiable TextViews for player and opponent
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
	
	private ImageView playerSprite;
	private ImageView oppSprite;
	
	private boolean creatureHasBeenChosen;
	
	private boolean setBattleContentCalled = false;
	//UserProfile
	private UserProfile userProfile;
	private Sprites sprites;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userProfile = (new UserProfile()).loadProfile(this);
		/*
		 * set up sample battle if 
		 */
		currentBattle = (Battle) getIntent().getSerializableExtra("Battle");
		if (currentBattle == null) {
			onBackPressed();//if no battle is passed in the intent, this will back out
		}
		else  {
			if (!userProfile.userCurrentlyInBattle()){
				chooseCreatureAlert();
			}
			else {
				setBattleContent();
			}
		}
		
		thisBattleActivity = this;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}
	
	/*
	 * this is function allows us to call "setContentView" and other setup functions
	 * after choosing a creature. This is for aesthetic transition purposes
	 */
	private void setBattleContent() {
		if(setBattleContentCalled) {
			return;
		}
		setContentView(R.layout.activity_battle);
		
		//set up stats and buttons
		setupBattleStats();
		assignBattleButtons();
		messageTextView.setTextColor(0xFFFFFFFF);
		System.out.println("got here z");
		refreshStats();
		refreshMessage();
		setBattleContentCalled = true;
	}
	
	//The only button should now be connected to this function
	public void implementRound(View view) {
		
		currentBattle.implementRound();
		refreshStats();
		refreshMessage();
		userProfile.setBattle(currentBattle);
		if(currentBattle.getRound() == 0) { //this means the battle is over
			System.out.println("here tau");
			handleEndBattle();
			
		}
		else {
			System.out.println("here zeta");
		}
	
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
		
		playerTitle.setText("Lvl " + player.getLevel() + " " + player.getTitle());
		playerHealth.setText("Health: " + player.getCurrentHealth());
		oppTitle.setText("Lvl " + opponent.getLevel() + " " + opponent.getTitle());
		oppHealth.setText("Health: " + opponent.getCurrentHealth());
	}
	
	/*
	 * This sets up the forms and their stats properly for the battle sample
	 */
	private void setupBattleStats() {
		if(currentBattle == null) { //otherwise this function will crash
			return;
		}
		
		sprites = new Sprites(this.getApplicationContext());
		
		//create player textViews to be added to tableRows
		BattleCreature player = currentBattle.getPlayerBattleCreature();
		
		playerSprite = (ImageView)findViewById(R.id.playerSprite);
		playerSprite.setImageDrawable(sprites.getSprite(player.getTitle()));
		//playerSprite.setImageDrawable(getResources().getDrawable(R.drawable.markustaborius));
		
		playerTitle = (TextView)findViewById(R.id.playerTitle);
		playerTitle.setText("Lvl " + player.getLevel() + " " + player.getTitle());
		playerTitle.setTextColor(0xFFFFFFFF);
		
		playerHealth = (TextView)findViewById(R.id.playerHealth);
		playerHealth.setText("Health: " + player.getCurrentHealth());
		playerHealth.setTextColor(0xFFFFFFFF);
		
		//create opponent TextViews to be added to TableRows
		BattleCreature opponent = currentBattle.getOpponentBattleCreature();
		
		oppSprite = (ImageView)findViewById(R.id.oppSprite);
		oppSprite.setImageDrawable(sprites.getSprite(opponent.getTitle()));
		//oppSprite.setImageDrawable(getResources().getDrawable(R.drawable.markustaborius));
		
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
		BattleCreature player = new BattleCreature(0,"Philanthropist", "house", "room", "earth", 5, 2, 1,4,10,10,0,simpleActions1);
		BattleCreature opponent = new BattleCreature(0,"Philanthropist", "house", "room", "earth", 5, 2, 1,4,10,10,0,simpleActions1);;

		
		
		//create the battle for this activity
		//boolean isSinglePlayer= getIntent().getExtras().getBoolean("isSinglePlayer");
		currentBattle = new Battle(player,opponent, true);
	}
	

	//gives an alert with choices for which creature the user wants in the battle
	//Very Fragile code 
	private void chooseCreatureAlert() {
		//final UserProfile userProfile = (new UserProfile()).loadProfile(this);
		//custom dialog
		creatureHasBeenChosen = false;
		final Dialog dialog = new Dialog(this);
		final BattleActivity thisBattleActivity = this;
		dialog.setContentView(R.layout.choose_creature);
		dialog.setTitle("Choose Creature");
		
		/*
		 * setup arraylist of indexes of active creatures (creatures with more than zero health)
		 */
		final ArrayList<Integer> activeUserProfileCreatureIndexes; //<- All kinds of technical debt embodied in this variable
		int numCreatures = userProfile.getParty().size();
		activeUserProfileCreatureIndexes = new ArrayList();
		for(int i = 0; i< numCreatures; i++) {
			if(userProfile.getParty().getPartyMember(i).getCurrentHealth()>0) {
				activeUserProfileCreatureIndexes.add((Integer)i);
			}
		}
		numCreatures = activeUserProfileCreatureIndexes.size();
		if(numCreatures == 0) {
			noValidCreaturesAlert();
			return;
		}
		/*
		 * setup ListView. clicking on a userProfile creature exchanges that creature
		 */
		ListView creatureListView = (ListView) dialog.findViewById(R.id.chooseCreaturelistView);

		//int numCreatures = userProfile.getParty().size();
		String creatureNameList[] = new String[numCreatures];
		for(int i = 0; i<numCreatures; i++ ) {
			creatureNameList[i] = userProfile.getParty().getPartyMember(i).getTitle();
		}
		
		for(int i = 0; i<numCreatures; i++ ) {
			creatureNameList[i] = userProfile.getParty().getPartyMember(activeUserProfileCreatureIndexes.get(i)).getTitle();

		}
		System.out.println("creatureNameList.length = " + creatureNameList.length);
		//TODO currently using battle_actions_list.xml below. make a new xml and customize more.
		creatureListView.setAdapter(new ArrayAdapter<String>( this, R.layout.battle_actions_list , creatureNameList));
		creatureListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//currentBattle.exchangePlayerCreature(userProfile.getParty().getPartyMember(position));
				currentBattle.exchangePlayerCreature(userProfile.getParty().getPartyMember(activeUserProfileCreatureIndexes.get(position)));
				userProfile.setBattle(currentBattle);
				userProfile.saveProfile(thisBattleActivity);
				//thisBattleActivity.assignBattleButtons();
				creatureHasBeenChosen = true;
				

			}
		});
		
		/*
		 * setup okay button. If clicked, just dismiss the dialog
		 */
		Button dialogButton = (Button) dialog.findViewById(R.id.chooseCreatureOkayButton);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!creatureHasBeenChosen) {
					return;
				}
				setBattleContent();
				refreshStats();
				dialog.dismiss();
			}
		});
		
		/*
		 * setup exit button. if pressed, exit the activity
		 */
		Button exitButton = (Button) dialog.findViewById(R.id.chooseCreatureExitButton);
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				userProfile.destroyBattle();
				onBackPressed();
			}
		});
		
		
		dialog.setOnKeyListener(new Dialog.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_BACK){
					dialog.dismiss();
					thisBattleActivity.onBackPressed();
				}
				return false;
			}
		});
		
		//user gets only two ways to exit the alert box (back button, and exit button)
		dialog.setCanceledOnTouchOutside(false);
		
		dialog.show();
	}
	
	//no valid creatures alert
	private void noValidCreaturesAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("None of your creatures have enough health to fight!")
		       .setCancelable(false)
		       .setPositiveButton("return", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   userProfile.destroyBattle();
		        	   onBackPressed();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	//handleEndGame handles the end of 
	//TODO fill out this function
	private void handleEndBattle() {
		if(currentBattle.getRound() != 0) {
			return;
		}
		//player won
		if(currentBattle.getPlayerBattleCreature().getCurrentHealth() > 0) {
			captureCreaturePrompt();
			upgradePartyCreatures();
			userProfile.destroyBattle();
		}
		else {
			chooseCreatureAlert();//offer the user to continue battling
		}
		System.out.println(currentBattle.getPlayerBattleCreature().getCurrentHealth() + " dis health");
		userProfile.saveProfile(this);
	}
	
	//prompts user with the possibility of capturing defeated wild creature
	private void captureCreaturePrompt() {
		DialogInterface.OnClickListener captureClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //capture creature
		        	currentBattle.getOpponentBattleCreature().resetHealth();
		        	userProfile.addCreature(currentBattle.getOpponentBattleCreature());
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
		    }
		};
		AlertDialog.Builder ab = new AlertDialog.Builder(BattleActivity.this);
        ab.setMessage("Do you want to capture this creature?").setPositiveButton("Yes", captureClickListener)
        .setNegativeButton("No", captureClickListener).show();
	}
	
	//to be called at the end of battling
	private void upgradePartyCreatures() {
		//loop through user party and if a Creature has upgrade points, show an alert
		for(int i = 0; i< userProfile.getParty().size(); i++) {
			if(userProfile.getParty().getPartyMember(i).getUpgradePoints()>0) {
				upgradeSingleCreaturePrompt(userProfile.getParty().getPartyMember(i));
			}
		}
	}
	
	//prompt for upgrading a single creature
	private void upgradeSingleCreaturePrompt(final BattleCreature creature) {
		final Dialog dialog = new Dialog(this);
		//final BattleActivity thisBattleActivity = this;
		dialog.setContentView(R.layout.upgrade_creature);
		dialog.setTitle("Upgrade Creature");
		
		final BattleUpgrade upgrade = new BattleUpgrade(creature.getUpgradePoints());
		
		final TextView Header = (TextView) dialog.findViewById(R.id.upgradeHeader);
		final TextView Health = (TextView) dialog.findViewById(R.id.healthUpgrade);
		final TextView Attack = (TextView) dialog.findViewById(R.id.attackUpgrade);
		final TextView Speed = (TextView) dialog.findViewById(R.id.speedUpgrade);
		final TextView Defense = (TextView) dialog.findViewById(R.id.defenseUpgrade);
		final TextView healthAdd = (TextView) dialog.findViewById(R.id.healthAdd);
		final TextView attackAdd = (TextView) dialog.findViewById(R.id.attackAdd);
		final TextView speedAdd = (TextView) dialog.findViewById(R.id.speedAdd);
		final TextView defenseAdd = (TextView) dialog.findViewById(R.id.defenseAdd);
		
		Header.setText(creature.getTitle() + " upgrade points left: " + upgrade.getUpgradeLeft());
		Health.setText ("Health  " + creature.getMaxHealth());
		Attack.setText ("Attack  " + creature.getAttack());
		Speed.setText  ("Speed   " + creature.getSpeed());
		Defense.setText("Defense " + creature.getDefense());
		healthAdd.setText("0");
		attackAdd.setText("0");
		speedAdd.setText("0");
		defenseAdd.setText("0");
		
		OnClickListener onClickListener = new OnClickListener() {
		     @Override
		     public void onClick(View v) {
		         switch(v.getId()){
		             case R.id.healthMinus:
		                 upgrade.decrementHealth();
		                // healthAdd.setText(upgrade.getHealthPlus());
		             break;
		             case R.id.healthPlus:
		                 upgrade.incrementHealth();
		                 //healthAdd.setText(upgrade.getHealthPlus());
		             break;
		             case R.id.attackMinus:
		                 upgrade.decrementAttack();
		             break;
		             case R.id.attackPlus:
		            	 upgrade.incrementAttack();
		             break;
		             case R.id.speedMinus:
		            	 upgrade.decrementSpeed();
		             break;
		             case R.id.speedPlus:
		            	 upgrade.incrementSpeed();
		             break;
		             case R.id.defenseMinus:
		            	 upgrade.decrementDefense();
		             break;
		             case R.id.defensePlus:
		            	 upgrade.incrementDefense();
		             break;
		             case R.id.exitUpgrade:
		            	 if(upgrade.upgradeComplete()) {
		            		 creature.enactUpgrade(upgrade);
	            			 Health.setText ("Health  " + creature.getMaxHealth());
	            			 Attack.setText ("Attack  " + creature.getAttack());
	            			 Speed.setText  ("Speed   " + creature.getSpeed());
	            			 Defense.setText("Defense " + creature.getDefense());
	            			 userProfile.saveProfile(thisBattleActivity);
		            		 dialog.dismiss();
		            	 }
		             break;
		             default:
		             return;
		         }
		         Header.setText(creature.getTitle() + " upgrade points left: " + upgrade.getUpgradeLeft());
		         healthAdd.setText("" +upgrade.getHealthPlus());
		         attackAdd.setText("" +upgrade.getAttackPlus());
		         speedAdd.setText("" +upgrade.getSpeedPlus());
		         defenseAdd.setText("" +upgrade.getDefensePlus());
		   }
		};

		int[] buttonIds = {R.id.healthPlus, R.id.healthMinus, R.id.attackPlus, R.id.attackMinus,
				R.id.speedPlus, R.id.speedMinus, R.id.defensePlus, R.id.defenseMinus, R.id.exitUpgrade};
		System.out.println("buttonIds.length = " + buttonIds.length);
		for(int i = 0; i<buttonIds.length; i++) {
			dialog.findViewById(buttonIds[i]).setOnClickListener(onClickListener);
		}
		
		
		
		
		
		
		dialog.show();
	}
	
	public void onPause() {
		super.onPause();
		userProfile.saveProfile(this);
	}
	
	public void onResume() {
		super.onResume();
		userProfile = (new UserProfile()).loadProfile(this);
		System.out.println("current battle of user profile is " + userProfile.getCurrentUserBattle());
		if(userProfile.userCurrentlyInBattle()) {
			currentBattle = userProfile.getCurrentUserBattle();
		}
	}
	
	
	
	
	
	
	
	/*
	 * DO NOT TOUCH THE BELOW CODE... the program will blow up (I don't even know how it works)
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // TODO Auto-generated method stub

	    switch(keyCode)
	    {
	    case KeyEvent.KEYCODE_BACK:
	    	if(currentBattle.getRound() != 0) {//if game is over, this question is pointless
		        AlertDialog.Builder ab = new AlertDialog.Builder(BattleActivity.this);
		        ab.setMessage("Do you want to run from this battle?").setPositiveButton("Yes", dialogClickListener)
		        .setNegativeButton("No", dialogClickListener).show();
	    	}
	        break;
	    }

	    return super.onKeyDown(keyCode, event);
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	            onBackPressed();
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	            //No button clicked
	            break;
	        }
	    }
	};
	
	public void onBackPressed() {
		userProfile.destroyBattle();
		userProfile.saveProfile(this);
		super.onBackPressed();
	}
}