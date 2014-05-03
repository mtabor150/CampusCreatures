package campuscreatures.database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class DatabaseHelper extends SQLiteOpenHelper
 * Creates a Database "creature.db" with three tables: Creatures, Player, and MapZones
 * Both tables use the same column names, but Player has 3 additional columns
 * Includes all the functions needed to access the database, tables, creatures, and values
 */

public class DatabaseHelper extends SQLiteOpenHelper {
	
	Context myContext;
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "creature.db";
	private static final String TABLE_CREATURES = "Creatures";
	private static final String TABLE_PLAYER = "Player";
		
	//Common column names (Creatures Table consist of only these column names)
	public static final String KEY_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_REGION = "region";
	private static final String COLUMN_DISTRICT = "district";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_HEALTH = "health";
	private static final String COLUMN_MAGIC = " magic";
	private static final String COLUMN_ATTACK = "attack";
	private static final String COLUMN_DEFENSE = "defense";
	private static final String COLUMN_SPEED = "speed";
	private static final String COLUMN_MPT = "moves_per_turn";
	private static final String COLUMN_EXPERIENCE = "experience";
	private static final String COLUMN_LEVEL = "level";
	
	//Column names - unique to Player Table
	private static final String PLAYER_ID = "_id";
	private static final String COLUMN_SEEN = "seen_or_not";
	private static final String COLUMN_SEEN_AT = "seen_at";
	private static final String COLUMN_CAPTURED = "captured";
	
	private static final String CREATE_CREATURES_TABLE = "CREATE TABLE " 
			+ TABLE_CREATURES + " (" 				
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_NAME + " TEXT NOT NULL, "
			+ COLUMN_REGION + " TEXT NOT NULL, " 
			+ COLUMN_DISTRICT + " TEXT NOT NULL, "
			+ COLUMN_TYPE + " TEXT NOT NULL, "
			+ COLUMN_HEALTH + " INTEGER NOT NULL, "  
			+ COLUMN_MAGIC + " INTEGER NOT NULL, "
			+ COLUMN_ATTACK + " INTEGER NOT NULL, "
			+ COLUMN_DEFENSE + " INTEGER NOT NULL, "
			+ COLUMN_SPEED + " INTEGER NOT NULL, "
			+ COLUMN_MPT + " INTEGER NOT NULL, "
			+ COLUMN_EXPERIENCE + " INTEGER NOT NULL, "
			+ COLUMN_LEVEL + " INTEGER NOT NULL" + ")";
	
	private static final String CREATE_PLAYER_TABLE = "CREATE TABLE "
			+ TABLE_PLAYER + " (" 				
			+ PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_NAME + " TEXT NOT NULL, "
			+ COLUMN_REGION + " TEXT NOT NULL, " 
			+ COLUMN_DISTRICT + " TEXT NOT NULL, "
			+ COLUMN_TYPE + " TEXT NOT NULL, "
			+ COLUMN_HEALTH +  " INTEGER NOT NULL, "  
			+ COLUMN_MAGIC + " INTEGER NOT NULL, "
			+ COLUMN_ATTACK + " INTEGER NOT NULL, "
			+ COLUMN_DEFENSE + " INTEGER NOT NULL, "
			+ COLUMN_SPEED + " INTEGER NOT NULL, "
			+ COLUMN_MPT + " INTEGER NOT NULL, "
			+ COLUMN_EXPERIENCE + " INTEGER NOT NULL, "
			+ COLUMN_LEVEL + " INTEGER NOT NULL, " 
			+ COLUMN_SEEN + " TEXT NOT NULL, "
			+ COLUMN_SEEN_AT + " TEXT NOT NULL, "
			+ COLUMN_CAPTURED + " TEXT NOT NULL" + ")";
	
	
	// MySQLiteHelper constructor must call the super class constructor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("onCreate function in DatabaseHelper", "Creating DB for first time");
		database.execSQL(CREATE_CREATURES_TABLE);
		database.execSQL(CREATE_PLAYER_TABLE);		
		Log.d("Created Tables onCreate:", "Creatures Table and Player Table");
	}

	/* Method is called for an upgrade of the database, 
	 * if table exists then drop it and call on create method. 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data.");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATURES); 
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
		onCreate(database); 
	}
	/**
	 * 
	 * Creatures Table - 13 columns
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * | ID | Name | Region | District | Type | Health | Magic | Attack | Defense | Speed | Moves Per Turn | Experience | Level |   
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|    
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * 
	 * All CRUD Operations (Create, Read, Update, Delete)
	 * 
	 */
	
	
	// -------------------------------"CREATURES TABLE" methods -------------------------------//
	
	/**
	 * INSERTING NEW CREATURE 
	 * 	1. get reference to writable Database
	 * 	2. create ContentValues to add key "column"/value
	 * 	3. insert rows 
	 *	4. return creature_id ... which creates a key_ID in the table
	 */
	public long addCreature(Creatures creature){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();		
		Log.d("Putting name into name column: ", creature.getName());
		values.put(COLUMN_NAME, creature.getName());				
		values.put(COLUMN_REGION, creature.getRegion());			
		values.put(COLUMN_DISTRICT, creature.getDistrict());		
		values.put(COLUMN_TYPE, creature.getType());				
		values.put(COLUMN_HEALTH, creature.getHealth());			
		values.put(COLUMN_MAGIC, creature.getMagic());				
		values.put(COLUMN_ATTACK, creature.getAttack());			
		values.put(COLUMN_DEFENSE, creature.getDefense());			
		values.put(COLUMN_SPEED, creature.getSpeed());				
		values.put(COLUMN_MPT, creature.getMovesPerTurn());			
		values.put(COLUMN_EXPERIENCE, creature.getExperience());	
		values.put(COLUMN_LEVEL, creature.getLevel());				
		
		Log.d("About to insert the entire row for:", creature.getName());
		long creature_id = database.insert(TABLE_CREATURES, null, values);
		
		Log.d("Inserted the row for: ", creature.getName());
		Log.d("KEY ID for: ", creature.getName() + " is " + creature.getId());
		
		return creature_id;
		//Close database connection
		//DatabaseHelper.database.close();
	}
	
	/** 
	 * READING ROW(S) 
	 *  1. get reference to readable Database
	 * 	2. build query statement and create cursor
	 * 	3. if we get results, get the first one
	 * 	4. build creatures object
	 * 	5. return creature
	 * 
	 */
	public Creatures getCreature(int creature_id){
		SQLiteDatabase database = this.getReadableDatabase();	
		
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES + " WHERE "
	            + KEY_ID + " = " + creature_id;
		Cursor cursor = database.rawQuery(selectQuery, null);

		Creatures creature = null; 
		if (cursor != null)
			cursor.moveToFirst();
		
		creature = new Creatures();
		creature.setId(cursor.getInt(0));
		creature.setName(cursor.getString(1));
		creature.setRegion(cursor.getString(2));
		creature.setDistrict(cursor.getString(3));
		creature.setType(cursor.getString(4));
		creature.setHealth(cursor.getInt(5));
		creature.setMagic(cursor.getInt(6));
		creature.setAttack(cursor.getInt(7));
		creature.setDefense(cursor.getInt(8));
		creature.setSpeed(cursor.getInt(9));
		creature.setMovesPerTurn(cursor.getInt(10));
		creature.setExperience(cursor.getInt(11));
		creature.setLevel(cursor.getInt(12));
		Log.d("getCreature(" + creature_id + ")", creature.toString());
		return creature;
	}
		
	/**
	* getAllCreatures() - returns a list of all creatures in database
	* 	1. get reference to writable Database
	* 	2. build the query statement, create cursor
	* 	3. go over each row, build creature
	* 	4. add creature to creatureList 
	* 
	*/
	public List<Creatures> getAllCreatures(){
		List<Creatures> creaturesList = new ArrayList<Creatures>();
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES;
		Log.d("Logging Query for getAllCreatures", selectQuery);
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		Creatures creature = null;
		if (cursor.moveToFirst()) {
			do {
				creature = new Creatures();
				creature.setId(cursor.getInt(0));
				creature.setName(cursor.getString(1));
				creature.setRegion(cursor.getString(2));
				creature.setDistrict(cursor.getString(3));
				creature.setType(cursor.getString(4));
				creature.setHealth(cursor.getInt(5));
				creature.setMagic(cursor.getInt(6));
				creature.setAttack(cursor.getInt(7));
				creature.setDefense(cursor.getInt(8));
				creature.setSpeed(cursor.getInt(9));
				creature.setMovesPerTurn(cursor.getInt(10));
				creature.setExperience(cursor.getInt(11));
				creature.setLevel(cursor.getInt(12));
				creaturesList.add(creature);
				Log.d("ID and NAME", String.valueOf(creature.getId()) + " " + creature.getName());
			} while (cursor.moveToNext());
		}
		Log.d("getAllCreatures().toString() function", creaturesList.toString());
		database.close();
		return creaturesList;		
	}
	
	public int getCreaturesCount(){
		String countQuery = " SELECT * FROM " + TABLE_CREATURES;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		Log.d("Logging countQuery getCreaturesCount()", String.valueOf(count));
		return count;
	}
	/**
	 * PJ's
	 * getLocalCreatures() - used to find creatures in a certain region and district
	 * @param region
	 * @param district
	 * @return
	 * 	1. get reference to readable Database
	 * 	2. build the query statement, create cursor
	 * 	3. go over each row, build creature (if in that region)
	 * 	4. add creature to the list
	 * 	5. return that list
	 */
	public List<Creatures> getAllCreaturesByRegion(String region){
	
		List<Creatures> regionCreatures = new ArrayList<Creatures>();
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES + " WHERE "
		            + COLUMN_REGION + " = " + region;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		Creatures creature = null;
	
		if (cursor.moveToFirst()){
			do {
				creature = new Creatures();
				creature.setId(cursor.getInt(0));
				creature.setName(cursor.getString(1));
				creature.setRegion(cursor.getString(2));
				creature.setDistrict(cursor.getString(3));
				creature.setType(cursor.getString(4));
				creature.setHealth(cursor.getInt(5));
				creature.setMagic(cursor.getInt(6));
				creature.setAttack(cursor.getInt(7));
				creature.setDefense(cursor.getInt(8));
				creature.setSpeed(cursor.getInt(9));
				creature.setMovesPerTurn(cursor.getInt(10));
				creature.setExperience(cursor.getInt(11));
				creature.setLevel(cursor.getInt(12));
				regionCreatures.add(creature);
			} while (cursor.moveToNext());
		}
		Log.d("Logging getAllCreaturesByRegion()", regionCreatures.toString());
		database.close();
		return regionCreatures;
	}
	/**
	 * Adam's
	 * getLocalCreatures() - used to find creatures in a certain region and district
	 * @param region
	 * @param district
	 * @return
	 * 	1. get reference to readable Database
	 * 	2. build the query statement, create cursor
	 * 	3. go over each row, build creature and add it to the list
	 * 	4. return that list
	 */
	public Creatures getLocalCreatures(String region){
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = " SELECT * FROM " + TABLE_CREATURES 
				+ " WHERE " + COLUMN_REGION + " = " + region;
		Cursor cursor = database.rawQuery(selectQuery, null);
		Creatures creature = null;
		if (cursor != null)
			cursor.moveToFirst();
		creature = new Creatures();
		creature.setId(cursor.getInt(0));
		creature.setName(cursor.getString(1));
		creature.setRegion(cursor.getString(2));
		creature.setDistrict(cursor.getString(3));
		creature.setType(cursor.getString(4));
		creature.setHealth(cursor.getInt(5));
		creature.setMagic(cursor.getInt(6));
		creature.setAttack(cursor.getInt(7));
		creature.setDefense(cursor.getInt(8));
		creature.setSpeed(cursor.getInt(9));
		creature.setMovesPerTurn(cursor.getInt(10));
		creature.setExperience(cursor.getInt(11));
		creature.setLevel(cursor.getInt(12));
		//Log.d("getAllCreatures()", creature);
		return creature;	
	}
	/**
	* updatinCreature()
	* @param creature
	* @return
	* 	1. get reference to writable Database
	* 	2. create ContentValues to add key "column"/value
	* 	3. update row using database.update(table, column/value, selections, selection arguments)
	* 	4. return creature
	*/
	public long updateCreature(Creatures creature){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, creature.getName());			
		values.put(COLUMN_REGION, creature.getRegion());		
		values.put(COLUMN_DISTRICT, creature.getDistrict());	
		values.put(COLUMN_TYPE, creature.getType());	 		
		values.put(COLUMN_HEALTH, creature.getHealth());		
		values.put(COLUMN_MAGIC, creature.getMagic());			
		values.put(COLUMN_ATTACK, creature.getAttack());		
		values.put(COLUMN_DEFENSE, creature.getDefense());		
		values.put(COLUMN_SPEED, creature.getSpeed());		
		values.put(COLUMN_MPT, creature.getMovesPerTurn());		
		values.put(COLUMN_EXPERIENCE, creature.getExperience());		
		values.put(COLUMN_LEVEL, creature.getLevel());			
		
		long x = database.update(TABLE_CREATURES, 				 
				values, 										 	
				KEY_ID + " = ?", 									
				new String[] { String.valueOf(creature.getId()) });	
		//database.close();
		return x;
	}
	
	/**
	 * deleteCreature()
	 * @param creature_id
	 * 	1. get reference to writable Database
	 * 	2. use database.delete(table, selections, selection arguments)
	 * 	3. close database
	 */
	public void deleteCreature(long creature_id){
		
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(TABLE_CREATURES,							
				KEY_ID + " = ?",   									
				new String[] {String.valueOf(creature_id) });	
		database.close();
		Log.d("deleteCreature", String.valueOf(creature_id));
	}
	
	/**
	 * 
	 * Player Table - 16 columns
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * | ID | Name | Region | District | Type | Health | Magic | Attack | Defense | Speed | Moves Per Turn | Experience | Level | Seen | SeenAt | Captured |   
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|    
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * |____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * 
	 * All CRUD Operations (Create, Read, Update, Delete)
	 * 
	 */
	
	// -------------------------------"PLAYER TABLE" methods -------------------------------//
	
	/**
	 * INSERTING NEW CREATURE  
	 * 	1. get reference to writable Database
	 * 	2. create ContentValues to add key "column"/value
	 * 	3. insert rows 
	 *	4. return creature_id ... which creates a key_ID in the table
	 */
	public long addPlayerCreature(Player playerCreature){
		
		SQLiteDatabase database = this.getWritableDatabase(); 	
		ContentValues playerValues = new ContentValues();
		
		Log.d("Putting creature name into player table's name column", playerCreature.getName());
		playerValues.put(COLUMN_NAME, playerCreature.getName());
		playerValues.put(COLUMN_REGION, playerCreature.getRegion());			
		playerValues.put(COLUMN_DISTRICT, playerCreature.getDistrict());		
		playerValues.put(COLUMN_TYPE, playerCreature.getType());				
		playerValues.put(COLUMN_HEALTH, playerCreature.getHealth());		
		playerValues.put(COLUMN_MAGIC, playerCreature.getMagic());			
		playerValues.put(COLUMN_ATTACK, playerCreature.getAttack());		
		playerValues.put(COLUMN_DEFENSE, playerCreature.getDefense());		
		playerValues.put(COLUMN_SPEED, playerCreature.getSpeed());			
		playerValues.put(COLUMN_MPT, playerCreature.getMovesPerTurn());		
		playerValues.put(COLUMN_EXPERIENCE, playerCreature.getExperience());
		playerValues.put(COLUMN_LEVEL, playerCreature.getLevel());			
		playerValues.put(COLUMN_SEEN, playerCreature.getSeen()=="yes");
		playerValues.put(COLUMN_SEEN_AT, playerCreature.getRegion());
		playerValues.put(COLUMN_CAPTURED, playerCreature.getCaptured()=="yes");
	
		Log.d("About to insert the entire row for:", playerCreature.getName());
		long playerCreature_ID = database.insert(TABLE_PLAYER, null, playerValues);
		Log.d("Inserted the row for: ", playerCreature.getName());
		Log.d("KEY ID for: ", playerCreature.getName() + " is " + playerCreature.getId());
		
		return playerCreature_ID;
		//database.close();
	}
	
	/** 
	 * READING ROW(S) 
	 *  1. get reference to readable Database
	 * 	2. build query statement and create cursor
	 * 	3. if we get results, get the first one
	 * 	4. build creatures object
	 * 	5. return creature
	 */
	public Player getPlayerCreature(int playerCreature_id){
		
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + TABLE_PLAYER + " WHERE "
	            + KEY_ID + " = " + playerCreature_id;	
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		Player playerCreature = null; 
		if (cursor != null)
			cursor.moveToFirst();
		
		playerCreature = new Player();
		playerCreature.setId(cursor.getInt(0));
		playerCreature.setName(cursor.getString(1));
		playerCreature.setRegion(cursor.getString(2));
		playerCreature.setDistrict(cursor.getString(3));
		playerCreature.setType(cursor.getString(4));
		playerCreature.setHealth(cursor.getInt(5));
		playerCreature.setMagic(cursor.getInt(6));
		playerCreature.setAttack(cursor.getInt(7));
		playerCreature.setDefense(cursor.getInt(8));
		playerCreature.setSpeed(cursor.getInt(9));
		playerCreature.setMovesPerTurn(cursor.getInt(10));
		playerCreature.setExperience(cursor.getInt(11));
		playerCreature.setLevel(cursor.getInt(12));
		
		Log.d("getCreature(" + playerCreature_id + ")", playerCreature.toString());
		return playerCreature;		
	}	
}