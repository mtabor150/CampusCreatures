package campuscreatures.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InitDatabase {

	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;

	public final static String CREATURE_TABLE = "Creatures"; // name of table
	public final static String CREATURE_ID = "_id"; 		// id value for creature
	public final static String CREATURE_NAME = "name"; 		// name of creature

	/**
	 * 
	 * @param context
	 */
	public InitDatabase(Context context) {
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
	}

	public long createRecords(String id, String name) {
		ContentValues values = new ContentValues();
		values.put(CREATURE_ID, id);
		values.put(CREATURE_NAME, name);
		return database.insert(CREATURE_TABLE, null, values);
	}

	public Cursor selectRecords() {
		String[] colmns = new String[] { CREATURE_ID, CREATURE_NAME };
		Cursor dbCursor = database.query(true, CREATURE_TABLE, colmns, null,
				null, null, null, null, null);
		if (dbCursor != null) {
			dbCursor.moveToFirst();

		}
		return dbCursor; // iterate to get each value.
	}
}