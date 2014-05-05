package campuscreatures.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import campuscreatures.battleMechanics.BattleAction;
import campuscreatures.battleMechanics.BattleCreature;
import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import campuscreatures.database.MapZones;
import campuscreatures.database.Player;
import campuscreatures.location.LocationService;
import campuscreatures.profile.UserProfile;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private MainPagerAdapter mAdapter;
	private Intent locationIntent = null;
	public static LocationService location = null;

	DatabaseHelper dbHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		System.out.println("gere 2");
		if (!userProfileExists()) { //make sure a file called "userProfile has been created
			UserProfile templateProfile = new UserProfile();
			templateProfile.saveProfile(this);
			registrationPrompt();
		}
		else {
			UserProfile tempProf = new UserProfile();
			tempProf = tempProf.loadProfile(this);
			if(!tempProf.hasSignedUp()) { //if no user has signed up, then prompt with registration
				registrationPrompt();
			}
			else {
				setupMAdapter();
			}
		}		
		// setup mAdapter
		//setUserInfo();

		//initialize location service
		locationIntent = new Intent(this, LocationService.class);
		startService(locationIntent);
		location = new LocationService(this);

		//TODO generate and fill CampusCreatures database;
		dbHelper = new DatabaseHelper(this);
		if (dbHelper.getAllCreatures().size() == 0){  
			Log.d("From MainActivity", "....");
			dbHelper.getAllCreatures();

			dbHelper.addCreature(new Creatures("Marcus Taborius", "Ritter Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));			
			dbHelper.addCreature(new Creatures("Desi Djinn ", "Simon Rec", "Saint Louis University", "fire",  10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Philanthropist", "Pius Library", "Saint Louis University", "space", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Weasel Man", "Tegeler Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Adom", "Lecture Halls", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Scan Bot", "Ritter Hall", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Chamber Wolf", "Ritter Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Lescher the Lecturer", "Lecture Halls", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Clueless Freshman", "Griesidieck Hall", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Roadrunner", "Adorjan Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Billiken", "Saint Louis University", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Inyourway", "BSC", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Biondi", "DuBourgh Hall", "Saint Louis University", "fire",  10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Bartanneke", "Beracha Hall", "Saint Louis University", "space", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Clair Bear", "Chafeitz Center", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Drushel", " Clock Tower", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Freemanster", "College Church", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Frittsterer", "Lecture Halls", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Silverwasser", "Ritter Hall", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Harristowe", "Cupples House", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Hebda", "B School", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Kalliongis", "Demattias Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Lamarcus", "DesPeres Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
			dbHelper.addCreature(new Creatures("Markist", "Fitzgerald Hall", "Saint Louis University", "fire",  10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
			dbHelper.addCreature(new Creatures("Parrishable", "Fusz Hall", "Saint Louis University", "space", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Rainbolt", "Intramural Field", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Lovasosa", "Macelwane Hall", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Shpeegle", "Marguerite Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Srivasta", "McDonnell Doug", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Evinstevin", "McGannon Hall", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Sudowsky", "Monsanto Hall", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Tsauster", "Pruellage Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Wackerle", "Shannon Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
			dbHelper.addCreature(new Creatures("Rasal Ghul", "Verhaegen Hall", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Turingsteen", "Village 1", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Dijkstra", "Village 2", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Pikachu", "Xavier Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Algarithmo", "Clock Tower", "Saint Louis University", "fire", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Pied Piper", "Simon Rec", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Fire Fox", "BSC", "Saint Louis University", "fire", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
			dbHelper.addCreature(new Creatures("Internet Explorer", "Simon Rec", "Saint Louis University", "normal", 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 ));
			
			dbHelper.addRegion(new MapZones("Chafeitz Center",	38.632545, 	-90.228032,		38.63306, -90.227817));		
			dbHelper.addRegion(new MapZones("Clocktower",		38.636543, 	-90.236804,		38.636673, -90.236758));
			dbHelper.addRegion(new MapZones("Cupples House",	38.636764, 	-90.235763,		38.636911, -90.235708));
			dbHelper.addRegion(new MapZones("Demattias Hall",	38.637661, 	-90.239811,		38.637584, -90.239575));
			dbHelper.addRegion(new MapZones("Lecture Halls", 	38.636307, 	-90.232845, 	38.636203, -90.232432));
			dbHelper.addRegion(new MapZones("Pruellage Hall",	38.637307, 	-90.238712,		38.637265, -90.238537));

			dbHelper.addRegion(new MapZones("Adorjan Hall", 	38.638323, 	-90.2392, 		38.638223, -90.238734, 		38.638059,-90.239281,		38.637971, -90.238825));	
			dbHelper.addRegion(new MapZones("Beracha Hall", 	38.635922, 	-90.238154, 	38.635855, -90.237816, 		38.635444,-90.238294, 		38.635390, -90.237993));
			dbHelper.addRegion(new MapZones("BSC", 				38.635599,	-90.233192, 	38.635474, -90.232377, 		38.634648, -90.233401,		38.63445, -90.232607));
			dbHelper.addRegion(new MapZones("College Church",	38.637395, 	-90.233947,		38.637179, -90.232971,		38.637014, -90.234052,		38.636838, -90.233137));
			dbHelper.addRegion(new MapZones("B School",			38.637779, 	-90.236086,		38.637588, -90.235270,		38.637246, -90.236224,		38.637025, -90.235338));
			dbHelper.addRegion(new MapZones("DesPeres Hall",	38.636381, 	-90.236793,		38.636314, -90.236434,		38.635895, -90.236960,		38.635838, -90.236597));
			dbHelper.addRegion(new MapZones("DuBourgh Hall",	38.636997, 	-90.234232,		38.636773, -90.233129,		38.636547, -90.234334,		38.635981, -90.233403));
			dbHelper.addRegion(new MapZones("Griesidieck Hall",	38.636077, 	-90.235002,		38.635857, -90.234025,		38.635489, -90.235187,		38.635315, -90.234234));
			dbHelper.addRegion(new MapZones("Fitzgerald Hall",	38.636743, 	-90.231195,		38.636649, -90.230691,		38.636513, -90.231230,		38.636408, -90.230750));
			dbHelper.addRegion(new MapZones("Fusz Hall",		38.636663, 	-90.238116,		38.636447, -90.237005,		38.636229, -90.238140,		38.636229, -90.238140));
			dbHelper.addRegion(new MapZones("Intramural Field",	38.636681, 	-90.241389,		38.636467, -90.240391,		38.636211, -90.2451528,		38.636018, -90.240563));
			dbHelper.addRegion(new MapZones("Lecture Halls",	38.634941, 	-90.232123,		38.634909, -90.231992,		38.634703, -90.232198,		38.634684, -90.232080));
			dbHelper.addRegion(new MapZones("Macelwane Hall",	38.634629, 	-90.232258,		38.634504, -90.231610,		38.634320, -90.232360,		38.634167, -90.231680));
			dbHelper.addRegion(new MapZones("Marguerite Hall",	38.637757, 	-90.239436,		38.637726, -90.239194,		38.637364, -90.239589,		38.63733, -90.239315));
			dbHelper.addRegion(new MapZones("McDonnell Doug",	38.636550, 	-90.230309,		38.636299, -90.228984,		38.636238, -90.230390,		38.635972, -90.229094));
			dbHelper.addRegion(new MapZones("McGannon Hall",	38.638415, 	-90.238616,		38.638202, -90.238068,		38.638005, -90.238781,		38.63795, -90.238189));
			dbHelper.addRegion(new MapZones("Monsanto Hall",	38.635286, 	-90.231498,		38.635193, -90.231074,		38.634604, -90.231634,		38.634542, -90.231295));
			dbHelper.addRegion(new MapZones("Pius Library",		38.637133, 	-90.235248,		38.637198, -90.234328,		38.636515, -90.235441,		38.636408, -90.234618));
			dbHelper.addRegion(new MapZones("Ritter Hall",		38.636307, 	-90.232845,		38.636203, -90.232432,		38.635798, -90.232569,		38.635867, -90.232968));	
			dbHelper.addRegion(new MapZones("Shannon Hall",		38.635353, 	-90.232038,		38.635262, -90.231605,		38.634990, -90.232156,		38.634931, -90.231718));
			dbHelper.addRegion(new MapZones("Simon Rec",		38.635650, 	-90.236276,		38.635405, -90.235063,		38.635090, -90.236485,		38.634841, -90.235232));
			dbHelper.addRegion(new MapZones("Tegeler Hall",		38.636888, 	-90.231852,		38.636775, -90.231278,		38.636542, -90.231978,		38.636458, -90.231431));
			dbHelper.addRegion(new MapZones("Verhaegen Hall",	38.637414, 	-90.234197,		38.637357, -90.233974,		38.637054, -90.234334,		38.637022, -90.234082));
			dbHelper.addRegion(new MapZones("Village 1",		38.637069, 	-90.239633,		38.636767, -90.238249,		38.636515, -90.239880,		38.636218, -90.238426));
			dbHelper.addRegion(new MapZones("Village 2",		38.636496, 	-90.240273,		38.636274, -90.239393,		38.635964, -90.240466,		38.635771, -90.239458));
			dbHelper.addRegion(new MapZones("Xavier Hall",		38.637499, 	-90.237901,		38.637388, -90.237308,		38.636979, -90.238102,		38.636860, -90.237504));
			
			//For Player table testing purposes, take out later
			dbHelper.addPlayerCreature(new Player(new Creatures("Markus Taborius", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 1, 1)));			
			dbHelper.addPlayerCreature(new Player(new Creatures("Desi Djinn", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 1, 1)));	
			dbHelper.addPlayerCreature(new Player(new Creatures("Adam the Intern", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 1, 1)));	
			dbHelper.addPlayerCreature(new Player(new Creatures("Algarithmo", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 1, 1)));			
			dbHelper.addPlayerCreature(new Player(new Creatures("Pikachu", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0)));	
			dbHelper.addPlayerCreature(new Player(new Creatures("Rasal Ghul", "Ritter Hall", "Saint Louis University", 
					"earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0)));	

		}


		dbHelper.getAllPlayerCreatures();
		//get all Creatures
		//dbHelper.getAllCreatures();
		dbHelper.getCreaturesCount();
		dbHelper.getRegionsCount();
		dbHelper.getCreature(35);
		dbHelper.getAllCreaturesByRegion("Ritter Hall");	
		dbHelper.close();
	}

	private void setupMAdapter() {

		mAdapter = new MainPagerAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(mAdapter);
		viewPager.setCurrentItem(1);
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
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
	}

	protected void onDestroy(){
		stopService(locationIntent);
		super.onDestroy();
	}

	private boolean userProfileExists() {
		File directory = getFilesDir();
		File filesList[] = directory.listFiles();
		int listSize = filesList.length;
		for(int i = 0; i < listSize; i++ ) {
			if(filesList[i].getName().equals("userProfile")) { //this will be the name of the file to hold the user info
				System.out.println("there is a file with name 'userProfile'");
				//System.out.println(filesList[i].);
				UserProfile tempProf = new UserProfile();
				tempProf = tempProf.loadProfile(this);
				if(tempProf!=null) {
					System.out.println("as user has signed up = " + tempProf.hasSignedUp());
				}
				return true;
			}
		}
		return false;
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

	public void goToMap(View view) {
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}

	public void goToBattle(View view) {
		Intent i = new Intent(this, BattleActivity.class);
		startActivity(i);
	}

	public void goToCreatureStats(View view) {
		BattleCreature tempCreature;
		UserProfile tempProfile = new UserProfile(view.getContext());
		int creatureListIndex = 0;
		int id = view.getId();
		if (id == R.id.imageButton1) {
			creatureListIndex = 0;
		} else if (id == R.id.imageButton2) {
			creatureListIndex = 1;
		} else if (id == R.id.imageButton3) {
			creatureListIndex = 2;
		} else if (id == R.id.imageButton4) {
			creatureListIndex = 3;
		} else if (id == R.id.imageButton5) {
			creatureListIndex = 4;
		} else if (id == R.id.imageButton6) {
			creatureListIndex = 5;
		} else {
			throw new RuntimeException("Unknow button ID");
		}
		if (creatureListIndex < tempProfile.getParty().size()) {
			tempCreature = tempProfile.getParty().getPartyMember(creatureListIndex);
		}
		else {
			return;
		}
		Intent i = new Intent(this, CreatureEntryActivity.class);
		i.putExtra("creature", tempCreature);
		startActivity(i);
	}

	//creates an alert box to register for a profile
	public void registrationPrompt() {
		// custom dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.register);
		dialog.setTitle("Register");
		final EditText text1 = (EditText) dialog.findViewById(R.id.editText1);
		final EditText text2 = (EditText) dialog.findViewById(R.id.editText2);
		final EditText text3 = (EditText) dialog.findViewById(R.id.editText3);
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstName = text1.getText().toString();
				String lastName = text2.getText().toString();
				String userName = text3.getText().toString();
				if(firstName.length()==0 | lastName.length()==0 | userName.length()==0) {
					return;
				}
				else {
					UserProfile newProfile = new UserProfile();
					newProfile.setInitialProfile(firstName, lastName, userName);

					//TODO below addCreature() is just for test purposes. remove when no longer necessary
					//start of test code alpha
					Creatures tempo = new Creatures(0,"Phil", "house", "room", "earth", 5, 2, 1, 4, 10, 1, 0, 22, 1, 1);
					Creatures tempo2 = new Creatures(1,"Markus Taborius", "Ritter Hall", "Saint Louis University", "psychic",10, 10, 10, 10, 10, 10, 10, 10, 1, 1);
					Creatures tempo3 = new Creatures(2, "Weazel Man", "Tegeler Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 1, 1);
					ArrayList<BattleAction> simpleActions1 = tempo.getMoveSet(tempo);
					ArrayList<BattleAction> simpleActions2 = tempo2.getMoveSet(tempo2);
					ArrayList<BattleAction> simpleActions3 = tempo3.getMoveSet(tempo3);
					newProfile.addCreature(new BattleCreature(0,"Phil", "house", "room", "earth", 5, 2, 22,4,10,10,0,simpleActions1));
					newProfile.addCreature(new BattleCreature(1,"Markus Taborius", "Ritter Hall", "Saint Louis University", "psychic",10, 10 ,10,10,30,30,10,simpleActions2));
					newProfile.addCreature(new BattleCreature(1,"Weazel Man", "Tegeler Hall", "Saint Louis University", "normal",10, 10 ,10,10,30,30,10,simpleActions3));
					//end of test code alpha

					newProfile.saveProfile(v.getContext());
					UserProfile testProfile = new UserProfile();
					testProfile = testProfile.loadProfile(v.getContext());
					System.out.println("firstName = " + testProfile.getFirstName());
					setupMAdapter();
					dialog.dismiss();
				}

			}
		});
 
		dialog.show();

	}

}