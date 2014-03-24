package campuscreatures.main;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	public void goToTrapCreatures(View view) {
		Intent i = new Intent(this, TrapCreaturesActivity.class);
		startActivity(i);
	}
	
	public void goToMap(View view) {
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}

}
