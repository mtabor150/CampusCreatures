package database;

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
 * Creates a Database "CreatureDB" with one table "Creatures"
 * 
 */

public class DatabaseHelper extends SQLiteOpenHelper {

	//Database Version number
	private static final int DATABASE_VERSION = 1;
		
	//Database name
	private static final String DATABASE_NAME = "CreatureDB";
	
	//Creatures Table Name
	private static final String TABLE_CREATURES = "Creatures";
	
	//Creatures Table Columns names:
	private static final String KEY_ID = "_id";
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
	
	//MySQLiteHelper constuctor must call the super class constructor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Creating tables
	@Override
	public void onCreate(SQLiteDatabase database) {
		String CREATE_CREATURES_TABLE = "CREATE TABLE " 
				+ TABLE_CREATURES + " (" 				
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ COLUMN_NAME + " TEXT, "
				+ COLUMN_REGION + " TEXT, " 
				+ COLUMN_DISTRICT + " TEXT, "
				+ COLUMN_TYPE + " TEXT, "
				+ COLUMN_HEALTH +  "INTEGER, "  
				+ COLUMN_MAGIC + " INTEGER, "
				+ COLUMN_ATTACK + " INTEGER, "
				+ COLUMN_DEFENSE + " INTEGER, "
				+ COLUMN_SPEED + " INTEGER, "
				+ COLUMN_MPT + " INTEGER, "
				+ COLUMN_EXPERIENCE + " INTEGER,"
				+ COLUMN_LEVEL + " INTEGER); ";

		//creates database table
		database.execSQL(CREATE_CREATURES_TABLE);
	}

	// Method is called during an upgrade of the database,
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data.");
		//drop older creatures table if existed
		database.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATURES); 
		//create a fresh new creatures table
		onCreate(database); 
	}
	/**
	 * 
	 * Creatures Table
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * | ID  | Name | Region | District | Type | Health | Magic | Attack | Defense | Speed | Moves Per Turn | Experience | Level |   
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______|    
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * |_____|______|________|__________|______|________|_______|________|_________|_______|________________|____________|_______| 
	 * 
	 */
	
	/*
	 * INSERTING NEW CREATURE ++++++++++++++++++
	 */
	public void addCreature(Creatures creature){
		//for logging
		Log.d("addCreature", creature.toString());
		
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();		
		values.put(KEY_ID, creature.getId());   				    //creature ID
		database.insert(TABLE_CREATURES, KEY_ID, values);
		
		values.put(COLUMN_NAME, creature.getName());				//creature name
		database.insert(TABLE_CREATURES, COLUMN_NAME, values);
		
		values.put(COLUMN_REGION, creature.getRegion());			//creature region
		database.insert(TABLE_CREATURES, COLUMN_REGION, values);
		
		values.put(COLUMN_DISTRICT, creature.getDistrict());		//creature district
		database.insert(TABLE_CREATURES, COLUMN_DISTRICT, values);
		
		values.put(COLUMN_TYPE, creature.getType());				//creature type
		database.insert(TABLE_CREATURES, COLUMN_TYPE, values);
		
		values.put(COLUMN_HEALTH, creature.getHealth());			//creature health
		database.insert(TABLE_CREATURES, COLUMN_HEALTH, values);
		
		values.put(COLUMN_MAGIC, creature.getMagic());				//creature magic
		database.insert(TABLE_CREATURES, COLUMN_MAGIC, values);
		
		values.put(COLUMN_ATTACK, creature.getAttack());			//creature attack
		database.insert(TABLE_CREATURES, COLUMN_ATTACK, values);
		
		values.put(COLUMN_DEFENSE, creature.getDefense());			//creature defense
		database.insert(TABLE_CREATURES, COLUMN_DEFENSE, values);
		
		values.put(COLUMN_SPEED, creature.getSpeed());				//creature speed
		database.insert(TABLE_CREATURES, COLUMN_SPEED, values);
		
		values.put(COLUMN_MPT, creature.getMovesPerTurn());		//creature moves per turn
		database.insert(TABLE_CREATURES, COLUMN_MPT, values);
		
		values.put(COLUMN_EXPERIENCE, creature.getExperience());	//creature experience
		database.insert(TABLE_CREATURES, COLUMN_EXPERIENCE, values);
		
		values.put(COLUMN_LEVEL, creature.getLevel());				//creature level
		database.insert(TABLE_CREATURES, COLUMN_LEVEL, values);
		
		//3. insert rows
//		database.insert("Creatures", 
//				null, 
//				values);
				
		//Close database connection
		database.close();
		
	}
	
	/*
	 * READING ROW(S) ++++++++++++++
	 */
	
	//get a single creature
	public Creatures getCreature(int id){
		
		//1. get reference to readable Database
		SQLiteDatabase database = this.getReadableDatabase();
		
		//2. build query
		Cursor cursor = database.query(TABLE_CREATURES, //a. table
				new String [] { KEY_ID, COLUMN_NAME, 		//b. column names
				COLUMN_REGION, COLUMN_DISTRICT, COLUMN_TYPE, 
				COLUMN_HEALTH, COLUMN_MAGIC, COLUMN_ATTACK, 
				COLUMN_DEFENSE, COLUMN_SPEED, COLUMN_MPT, 
				COLUMN_EXPERIENCE, COLUMN_LEVEL }, 
				KEY_ID + "=?",							//c. selections
				new String[] {String.valueOf(id) },		//d. selection args
				null,									//e. group by
				null, 									//f. having
				null, 									//g. order by
				null);									//h. limit
		
		//3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		
		//4. build creatures object
		Creatures creature = new Creatures();
		creature.setId(Integer.parseInt(cursor.getString(0)));
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
		Log.d("getBook(" + id + ")", creature.toString());
		
		//return creature
		return creature;
		
	}
	
	//getting all creatures
	public List<Creatures> getAllCreatures(){
		List<Creatures> creaturesList = new LinkedList<Creatures>();
		
		//1. build the query
		String selectQuery = "SELECT * FROM " + TABLE_CREATURES;
		
		//2. get reference to writeable Database
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		//3. go over each row, build creature and add it to the list
		Creatures creature = null;
		if (cursor.moveToFirst()) {
			do {
				//Creatures creature = new Creatures();
				creature = new Creatures();
				creature.setId(Integer.parseInt(cursor.getString(0)));
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
	
	//get creatures count
	public int getCreaturesCount(){
		String countQuery = "SELECT * FROM " + TABLE_CREATURES;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		//return count
		return cursor.getCount();
	}
	
	//updating single creature
	public int updateCreature(Creatures creature){
		
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
		values.put(COLUMN_ATTACK, creature.getAttack());			//get attack
		values.put(COLUMN_DEFENSE, creature.getDefense());		//get defense
		values.put(COLUMN_SPEED, creature.getSpeed());			//get speed	
		values.put(COLUMN_MPT, creature.getMovesPerTurn());			//get moves per turn
		values.put(COLUMN_EXPERIENCE, creature.getExperience());		//get experience
		values.put(COLUMN_LEVEL, creature.getLevel());			//get level
		
//		ContentValues values = new ContentValues();
//		values.put("name", creature.getName());				//get name
//		values.put("region", creature.getRegion());			//get region
//		values.put("district", creature.getDistrict());		//get district
//		values.put("type", creature.getType());	 			//get type
//		values.put("health", creature.getHealth());			//get health
//		values.put("magic", creature.getMagic());			//get magic
//		values.put("attack", creature.getAttack());			//get attack
//		values.put("defense", creature.getDefense());		//get defense
//		values.put("speed", creature.getSpeed());			//get speed	
//		values.put("moves per turn", creature.getMovesPerTurn());	//get moves per turn
//		values.put("experience", creature.getExperience());	//get experience
//		values.put("level", creature.getLevel());			//get level
		
		//3. updating row
		int x = database.update(TABLE_CREATURES, 				 //table
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