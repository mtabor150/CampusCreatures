package campuscreatures.main;

import campuscreatures.battleMechanics.Battle;
import campuscreatures.battleMechanics.BattleCreature;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class CreatureEntryActivity extends Activity {
	
	private BattleCreature creature;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creature_entry);
		creature = (BattleCreature) getIntent().getSerializableExtra("creature");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creature_entry, menu);
		setAllStats();
		return true;
	}
	
	private void setAllStats() {
		setStatText(R.id.Title, creature.getTitle());
		setStatText(R.id.HP, "HP: " + creature.getCurrentHealth() + "/" + creature.getMaxHealth());
		setStatText(R.id.ATK, "ATK: " + "no attack value yet");
		setStatText(R.id.DFN, "DFN: " + "no defense value yet");
		setStatText(R.id.SPD, "SPD: " + creature.getSpeed());
		setStatText(R.id.TypeField, "no type value yet");
		setStatText(R.id.DistrictField, "no distric value yet");
		setStatText(R.id.DescriptionField, "Description: " + "no description value yet");
	}
	
	//sets an individual text field with a certain id
	private void setStatText(int id, String value) {
		String name = getResources().getResourceName(id);
		if (name == null) {
		    return;
		}
		TextView textView = (TextView) findViewById(id);
		textView.setText(value);
	}

}