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
 * 
 * Class DatabaseHelper extends SQLiteOpenHelper
 * Creates a Database "CreatureDB" with two tables: Creatures and Player
 * Both tables use the same column names, but Player has 3 more columns
 * 
 */

public class DatabaseHelper extends SQLiteOpenHelper {

	//Logcat tag
	private static final String LOG = "DatabaseHelper";
	
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
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_SEEN = "seen_or_not";
	private static final String COLUMN_SEEN_AT = "seen_at";
	private static final String COLUMN_CAPTURED = "captured";
	
	/* Table Create Statements */
	
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
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
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
	}
	
	//Creating Tables
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("onCreate", "Creating DB for first time");
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
	public void addCreature(Creatures creature){
		
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
		database.insert("Creatures", null, values);
		Log.d("Inserted the row for: ", creature.getName());
		Log.d("KEY ID for: ", creature.getName() + " is " + creature.getId());
		//Log.d("getCreature Function", getCreature(0).toString());

		//Close database connection
		database.close();
	}
	
	
	/* READING ROW(S) */
	
	//get a single creature
	public Creatures getCreature(int get_id){
		
		//1. get reference to readable Database
		SQLiteDatabase database = this.getReadableDatabase();	
		
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES + " WHERE "
		            + KEY_ID + " = " + get_id;
	    
		Log.e(LOG, selectQuery);
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//2. build query
//		Cursor cursor = database.query(TABLE_CREATURES, //a. table
//				new String [] { KEY_ID, COLUMN_NAME, 	//b. column names
//				COLUMN_REGION, COLUMN_DISTRICT, COLUMN_TYPE, 
//				COLUMN_HEALTH, COLUMN_MAGIC, COLUMN_ATTACK, 
//				COLUMN_DEFENSE, COLUMN_SPEED, COLUMN_MPT, 
//				COLUMN_EXPERIENCE, COLUMN_LEVEL }, 
//				KEY_ID + "=?",								//c. selections
//				new String[] {String.valueOf(get_id) },		//d. selection args
//				null,										//e. group by
//				null, 										//f. having
//				null, 										//g. order by
//				null);										//h. limit
		//3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();

		//4. build creatures object		
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
		
		//Log
		Log.d("getCreature(" + get_id + ")", creature.toString());
		
		//return creature
		return creature;
	}
		
	//getting all creatures and all their attribute values
	public List<Creatures> getAllCreatures(){
		List<Creatures> creaturesList = new LinkedList<Creatures>();
		//List<Creatures> creaturesList = new ArrayList<Creatures>();
		
		//1. build the query
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES;
		
		Log.d("Logging Query for getAllCreatures", selectQuery);
		
		//2. get reference to writeable Database
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//3. go over each row, build creature and add it to the list
		Creatures creature = null;
		if (cursor.moveToFirst()) {
			do {
				//Creatures creature = new Creatures();
				creature = new Creatures();
				creature.setId(cursor.getInt(0));
				Log.d(COLUMN_ID, String.valueOf(creature.getId()));
				creature.setName(cursor.getString(1));
				Log.d(COLUMN_NAME, creature.getName());
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
		
		//Log.d("Logging getAllCreatures()", creaturesList.toString());
		
		//return creatures list
		return creaturesList;	
		
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
	
	//updating single creature
	public long updateCreature(Creatures creature){
		
		//1. get reference to writable database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, creature.getName());			//get name
		values.put(COLUMN_REGION, creature.getRegion());		//get region
		values.put(COLUMN_DISTRICT, creature.getDistrict());	//get district
		values.put(COLUMN_TYPE, creature.getType());	 		//get type
		values.put(COLUMN_HEALTH, creature.getHealth());		//get health
		values.put(COLUMN_MAGIC, creature.getMagic());			//get magic
		values.put(COLUMN_ATTACK, creature.getAttack());		//get attack
		values.put(COLUMN_DEFENSE, creature.getDefense());		//get defense
		values.put(COLUMN_SPEED, creature.getSpeed());			//get speed	
		values.put(COLUMN_MPT, creature.getMovesPerTurn());			//get moves per turn
		values.put(COLUMN_EXPERIENCE, creature.getExperience());		//get experience
		values.put(COLUMN_LEVEL, creature.getLevel());			//get level
		
		//3. updating row
		long x = database.update(TABLE_CREATURES, 				 //table
				values, 										 //column/value
				KEY_ID+" = ?", 									 //selections
				new String[] {String.valueOf(creature.getId())});//selection args
		
		//4. close
		database.close();
		return x;
	}
	
	//delete single creature
	public void deleteCreature(Creatures creature){
		
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. delete
		database.delete(TABLE_CREATURES,							//table
				KEY_ID + " = ?",   									//selections
				new String[] {String.valueOf(creature.getId()) });	//selec
		
		//3. close
		database.close();
		
		//log
		Log.d("deleteCreature", creature.toString());
	}
}