package campuscreatures.database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.util.Log;

/**
 * 
 * Class DatabaseHelper extends SQLiteOpenHelper
 * Creates a Database "CreatureDB" with two tables: Creatures and Player
 * Both tables use the same column names, but Player has 3 more columns
 * 
 */

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private Context myContext;
	//Database Name and Version number 
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "CreatureDB";
	
	//Creatures Table Names
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
	
	//Player's table column names
	private static final String PLAYER_ID = "id";
	private static final String COLUMN_SEEN = "seen_or_not";
	private static final String COLUMN_SEEN_AT = "seen_at";
	private static final String COLUMN_CAPTURED = "captured";
	
	//Creature Table create statement
	private static final String CREATE_CREATURES_TABLE = "CREATE TABLE " 
			+ TABLE_CREATURES + " (" 				
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
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
			+ COLUMN_LEVEL + " INTEGER NOT NULL" + ")";
	
	//Player table create statement
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
	
	
	// MySQLiteHelper constuctor must call the super class constructor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		myContext = context;
	}
	
	//Creating Tables
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("onCreate function in DatabaseHelper", "Creating DB for first time");
		//creates required database tables
		database.execSQL(CREATE_CREATURES_TABLE);
		database.execSQL(CREATE_PLAYER_TABLE);		
	}

	/* Method is called for an upgrade of the database, 
	 * if table exists then drop it and call on create method. 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data.");
		//drop older creatures table if existed
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATURES); 
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
		
		//create a fresh new creatures table and player table
		onCreate(database); 
	}
	/**
	 * 
	 * Creatures Table - 13 columns
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * | ID  | Name | Region | District | Type | Health | Magic | Attack | Defense | Speed | Moves Per Turn | Experience | Level |   
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|    
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * 
	 * All CRUD Operations (Create, Read, Update, Delete)
	 * 
	 */
	
	
	// -------------------------------"CREATURES TABLE" methods -------------------------------//
	
	/* 
	 * INSERTING NEW CREATURE 
	 */
	public long addCreature(Creatures creature){
		
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();		
		Log.d("Putting name into name column: ", creature.getName());
		values.put(COLUMN_NAME, creature.getName());				//creature name
		values.put(COLUMN_REGION, creature.getRegion());			//creature region
		values.put(COLUMN_DISTRICT, creature.getDistrict());		//creature district
		values.put(COLUMN_TYPE, creature.getType());				//creature type
		values.put(COLUMN_HEALTH, creature.getHealth());			//creature health
		values.put(COLUMN_MAGIC, creature.getMagic());				//creature magic
		values.put(COLUMN_ATTACK, creature.getAttack());			//creature attack
		values.put(COLUMN_DEFENSE, creature.getDefense());			//creature defense
		values.put(COLUMN_SPEED, creature.getSpeed());				//creature speed
		values.put(COLUMN_MPT, creature.getMovesPerTurn());			//creature moves per turn
		values.put(COLUMN_EXPERIENCE, creature.getExperience());	//creature experience
		values.put(COLUMN_LEVEL, creature.getLevel());				//creature level
		
		//3. insert rows
		Log.d("About to insert the entire row for:", creature.getName());
		long creature_id = database.insert(TABLE_CREATURES, null, values);
		Log.d("Inserted the row for: ", creature.getName());
		
		Log.d("KEY ID for: ", creature.getName() + " is " + creature.getId());
		
		return creature_id;
		//Close database connection
		//database.close();
	}
	
	/* 
	 * READING ROW(S) 
	 */
	//get a single creature
	public Creatures getCreature(int creature_id){
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES + " WHERE "
	            + KEY_ID + " = " + creature_id;
		//1. get reference to readable Database
		SQLiteDatabase database = this.getReadableDatabase();	
						
		//2. build query
		Cursor cursor = database.rawQuery(selectQuery, null);

		Creatures creature = null; 
		//3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		
		//4. build creatures object		
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
		//Log
		Log.d("getCreature(" + creature_id + ")", creature.toString());
		//return creature
		return creature;
	}
		
	//getting all creatures and all their attribute values
	public List<Creatures> getAllCreatures(){
		List<Creatures> creaturesList = new ArrayList<Creatures>();
		//1. build the query
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES;
		
		Log.d("Logging Query for getAllCreatures", selectQuery);
		
		//2. get reference to writeable Database
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//3. go over each row, build creature and add it to the list
		Creatures creature = null;
		if (cursor.moveToFirst()) {
			do {
				//Creatures creature = new Creatures();
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
				
				//Add creature to creaturesList
				creaturesList.add(creature);
				Log.d("ID and NAME", String.valueOf(creature.getId()) + " " + creature.getName());
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllCreatures().toString() function", creaturesList.toString());
		
		//return creatures list
		return creaturesList;		
	}
	
	//get creatures count
	public int getCreaturesCount(){
		String countQuery = " SELECT * FROM " + TABLE_CREATURES;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		
		//return count
		Log.d("Logging countQuery getCreaturesCount()", String.valueOf(count));
		return count;
	}
	
	//get all creatures from a certain region
	public List<Creatures> getAllCreaturesByRegion(String region){
		List<Creatures> regionCreatures = new ArrayList<Creatures>();
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES + " WHERE "
		            + COLUMN_REGION + " = " + region;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//looping through all rows and adding to regionCreatures list
		if (cursor.moveToFirst()){
			do {
				Creatures creature = new Creatures();
				creature.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				creature.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
				creature.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_REGION)));
				creature.setDistrict(cursor.getString(cursor.getColumnIndex(COLUMN_DISTRICT)));
				creature.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
				creature.setHealth(cursor.getInt(cursor.getColumnIndex(COLUMN_HEALTH)));
				creature.setMagic(cursor.getInt(cursor.getColumnIndex(COLUMN_MAGIC)));
				creature.setAttack(cursor.getInt(cursor.getColumnIndex(COLUMN_ATTACK)));
				creature.setDefense(cursor.getInt(cursor.getColumnIndex(COLUMN_DEFENSE)));
				creature.setSpeed(cursor.getInt(cursor.getColumnIndex(COLUMN_SPEED)));
				creature.setMovesPerTurn(cursor.getInt(cursor.getColumnIndex(COLUMN_MPT)));
				creature.setExperience(cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCE)));
				creature.setLevel(cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL)));
				//adding to regionCreatures List
				regionCreatures.add(creature);
			} while (cursor.moveToNext());
		}
		Log.d("Logging getAllCreaturesByRegion()", regionCreatures.toString());
		return regionCreatures;
	}
	
	//UPDATE WITH NEW TABLE CRITERIA
	//Used by Recon to find list of creatures in area
	public List<Creatures> getLocalCreatures(String region, String district){
		List<Creatures> creaturesList = new LinkedList<Creatures>();
		
		//1. build the query
		String selectQuery = " SELECT * FROM " + TABLE_CREATURES + " WHERE " + COLUMN_REGION + " = " + region + " AND " + COLUMN_DISTRICT + " = " + district;
		
		//2. get reference to writeable Database
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//3. go over each row, build creature and add it to the list
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

				//Add creature to creatures
				creaturesList.add(creature);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllCreatures()", creaturesList.toString());
		
		//return creatures list
		return creaturesList;	
	}
	
	//updating single creature
	public long updateCreature(Creatures creature){
		
		//1. get reference to writable database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
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
		
		//3. updating row
		long x = database.update(TABLE_CREATURES, 				 //table
				values, 										 //column/value
				KEY_ID + " = ?", 									 //selections
				new String[] { String.valueOf(creature.getId()) });//selection args
		//4. close
		database.close();
		return x;
	}
	
	//delete single creature
	public void deleteCreature(long creature_id){
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		//2. delete
		database.delete(TABLE_CREATURES,							//table
				KEY_ID + " = ?",   									//selections
				new String[] {String.valueOf(creature_id) });	//selec
		
		//3. close
		database.close();
		
		//log
		Log.d("deleteCreature", String.valueOf(creature_id));
	}
	
	/**
	 * 
	 * Player Table - 16 columns
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * | ID  | Name | Region | District | Type | Health | Magic | Attack | Defense | Speed | Moves Per Turn | Experience | Level | Seen | SeenAt | Captured |   
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|    
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|______|________|__________|  
	 * 
	 * All CRUD Operations (Create, Read, Update, Delete)
	 * 
	 */
	
	// -------------------------------"PLAYER TABLE" methods -------------------------------//

	public void addPlayerCreature(Player playerCreature){
		// 1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase(); 	
		//2. create content values to add key "column"/value
		ContentValues playerValues = new ContentValues();
		Log.d("Putting creature name into player table's name column", playerCreature.getName());
		playerValues.put(COLUMN_NAME, playerCreature.getName());
		playerValues.put(COLUMN_REGION, playerCreature.getRegion());			//creature region
		playerValues.put(COLUMN_DISTRICT, playerCreature.getDistrict());		//creature district
		playerValues.put(COLUMN_TYPE, playerCreature.getType());				//creature type
		playerValues.put(COLUMN_HEALTH, playerCreature.getHealth());			//creature health
		playerValues.put(COLUMN_MAGIC, playerCreature.getMagic());			//creature magic
		playerValues.put(COLUMN_ATTACK, playerCreature.getAttack());			//creature attack
		playerValues.put(COLUMN_DEFENSE, playerCreature.getDefense());		//creature defense
		playerValues.put(COLUMN_SPEED, playerCreature.getSpeed());			//creature speed
		playerValues.put(COLUMN_MPT, playerCreature.getMovesPerTurn());		//creature moves per turn
		playerValues.put(COLUMN_EXPERIENCE, playerCreature.getExperience());	//creature experience
		playerValues.put(COLUMN_LEVEL, playerCreature.getLevel());			//creature level
		playerValues.put(COLUMN_SEEN, playerCreature.getSeen()=="yes");
		playerValues.put(COLUMN_SEEN_AT, playerCreature.getRegion());
		playerValues.put(COLUMN_CAPTURED, playerCreature.getCaptured()=="yes");
	
		//3. insert rows
		Log.d("About to insert the entire row for:", playerCreature.getName());
		database.insert(TABLE_PLAYER, null, playerValues);
		Log.d("Inserted the row for: ", playerCreature.getName());
		Log.d("KEY ID for: ", playerCreature.getName() + " is " + playerCreature.getId());
		
		//Close database connection
		database.close();
	}
	
	public Player getPlayerCreature(int playerCreature_id){
		String selectQuery = "SELECT * FROM " + TABLE_PLAYER + " WHERE "
	            + KEY_ID + " = " + playerCreature_id;
		SQLiteDatabase database = this.getReadableDatabase();	
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
		//Log
		Log.d("getCreature(" + playerCreature_id + ")", playerCreature.toString());
		//return creature
		return playerCreature;		
	}	
}