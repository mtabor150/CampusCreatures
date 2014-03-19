package campuscreatures.main;

import com.example.campuscreatures.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * This activity is for PJ to test the database functionality
 */
public class DatabaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.database, menu);
		return true;
	}

}
