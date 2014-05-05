package campuscreatures.main;

import java.util.ArrayList;
import java.util.List;

import campuscreatures.battleMechanics.BattleCreature;
import campuscreatures.database.Creatures;
import campuscreatures.database.DatabaseHelper;
import campuscreatures.database.Player;
import android.content.Intent;
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

	private ArrayList<Creatures> seenCreatureList;

	private List<Creatures> creatureList;

	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    DatabaseHelper database = new DatabaseHelper(getActivity());
		
		//get all Creatures
	    Log.d("From CreatureAtlasFragment", "...");
		creatureList = database.getAllCreatures();
		database.close();
		
		seenCreatureList = new ArrayList();
		/*
		for (int i=0; i <creatureList.size(); i++){
			if (creatureList.get(i).getSeen() == 1){
				seenCreatureList.add(creatureList.get(i));
			}
		}
		*/
		for (int i=0; i <creatureList.size(); i++){
			
				seenCreatureList.add(creatureList.get(i));
			
		}
		final String[] creatureNames = new String[seenCreatureList.size()];
	    for (int i = 0; i < seenCreatureList.size(); i++) {
	    	creatureNames[i] = seenCreatureList.get(i).getName();

	    }
		
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
	    		android.R.layout.simple_list_item_1, creatureNames);
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
		  BattleCreature tempBC = new BattleCreature(creatureList.get(position));
		  Intent i = new Intent(getActivity(), CreatureEntryActivity.class);
		  i.putExtra("creature", tempBC);
		  startActivity(i);
	  }

}