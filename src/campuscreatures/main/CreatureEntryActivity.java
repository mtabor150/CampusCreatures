package campuscreatures.main;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreatureEntryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creature_entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creature_entry, menu);
		return true;
	}

}