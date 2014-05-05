package campuscreatures.database;

import java.util.List;

import campuscreatures.main.R;
import campuscreatures.main.R.layout;
import campuscreatures.main.R.menu;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/*
 * This activity is for PJ to test the database functionality
 */
public class DatabaseService extends Service {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate();
		setContentView(R.layout.activity_database);
		DatabaseHelper database = new DatabaseHelper(this);
		/*
		 * test creatures database, and tests for all functions (add, delete, getAll)
		 * ID,Name, Region, District, Type, Health, Magic, Attack, Defense, Speed, Moves Per Turn, Experience, Level
		 * 
		 */
		Log.d("From DatabaseService", "....");
		database.addCreature(new Creatures("Marcus Taborius", "Ritter Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
		database.addCreature(new Creatures("Desi Djinn ", "Simon Rec", "Saint Louis University", "fire",  10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
		database.addCreature(new Creatures("Philanderphil", "Pius Library", "Saint Louis University", "space", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0));
		database.addCreature(new Creatures("Weazel Man", "Tegler Field", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Scan Bot", "Ritter Hall", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Chamber Wolf", "Ritter Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Adam the Intern", "DuBourgh Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Lescher the Lecturer", "Ritter Hall", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Clueless Freshman", "Griesidieck Hall", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Roadrunner", "Reinert Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		database.addCreature(new Creatures("Billiken", "Saint Louis University", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10, 0, 0 ));
		
		//get all Creatures
		database.getAllCreatures();
		database.getCreaturesCount();
		database.getCreature(1);
		//database.getAllCreaturesByRegion("Ritter Hall");
		//database.getLocalCreatures("Ritter Hall", "Saint Louis University");
		database.deleteCreature(10);
		database.getAllCreatures();
		database.getCreaturesCount();
		//database.close();
		
		
	}

	private void setContentView(int activityDatabase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
