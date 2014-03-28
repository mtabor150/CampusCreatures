package campuscreatures.main;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class CreatureAtlasFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_creature_atlas, container, false);
         
        return rootView;
    }

}