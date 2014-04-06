package campuscreatures.main;

import java.util.List;

import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/*
 * This activity is for PJ to test the database functionality
 */
public class DatabaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		DatabaseHelper database = new DatabaseHelper(this);
		/*
		 * test creatures database, and tests for all functions (add, delete, getAll)
		 * ID,Name, Region, District, Type, Health, Magic, Attack, Defense, Speed, Moves Per Turn, Experience, Level
		 * 
		 */
		Log.d("From Database Activity", "....");
		database.addCreature(new Creatures(1, "Marcus Taborius", "Ritter Hall", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10));
		//Log.d("From Database Activity", "Desi Djinn");
		database.addCreature(new Creatures(2, "Desi Djinn ", "Simon Rec", "Saint Louis University", "fire",  10, 10, 10, 10, 10, 10, 10, 10));
		database.addCreature(new Creatures(3, "Philanderphil", "Pius Library", "Saint Louis University", "space", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(4, "Weazel Man", "Tegler Field", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(5, "Scan Bot", "Ritter Hall", "Saint Louis University", "electric", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(6, "Chamber Wolf", "Ritter Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(7, "Adam the Intern", "DuBourgh Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(8, "Lescher the Lecturer", "Ritter Hall", "Saint Louis University", "psychic", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(9, "Clueless Freshman", "Griesidieck Hall", "Saint Louis University", "spirit", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(10, "Roadrunner", "Reinert Hall", "Saint Louis University", "normal", 10, 10, 10, 10, 10, 10, 10, 10 ));
		database.addCreature(new Creatures(11, "Billiken", "Saint Louis University", "Saint Louis University", "earth", 10, 10, 10, 10, 10, 10, 10, 10 ));
		
		//get all Creatures
		database.getAllCreatures();
		database.getCreaturesCount();
		database.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
