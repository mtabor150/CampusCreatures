package campuscreatures.main;

import java.util.ArrayList;
import java.util.List;

import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.*;

public class CreatureAtlasFragment extends ListFragment {

	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    DatabaseHelper database = new DatabaseHelper(getActivity());
		/*
		 * test creatures database, and tests for all functions (add, delete, getAll)
		 * ID,Name, Region, District, Type, Health, Magic, Attack, Defense, Speed, Moves Per Turn, Experience, Level
		 * 
		 */
	    /*
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
		List<Creatures> creatureList = database.getAllCreatures();
		database.close();
		
		final String[] creatureNames = new String[] {};
	    for (int i = 0; i < creatureList.size(); ++i) {
	      creatureNames[i] = creatureList.get(i).getName();
	    }
	   */
	    
	    final String[] creatureNames = new String[] {"Marcus Taborius", "Desi Djinn", "Philanderphil",
	    		"Weazel Man", "Scan Bot", "Chamber Wolf", "Adam the Intern", "Lescher the Lecturer", 
	    		"Clueless Freshman", "Roadrunner", "Billiken"};
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
	    		android.R.layout.simple_list_item_1, creatureNames);
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
	  }

}