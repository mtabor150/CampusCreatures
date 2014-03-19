package campuscreatures.database;

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

	//Database name
	private static final String DATABASE_NAME = "CreatureDB";
	//Database Version number
	private static final int DATABASE_VERSION = 1;
		
	//MySQLiteHelper constuctor must call the super class constructor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Creatures Table Name
	private static final String Table_Creatures = "Creatures";
	//Creatures Table Columns names:
	private static final String KEY_ID = "_ID";
	private static final String KEY_NAME = "Name";
	private static final String KEY_REGION = "Region";
	private static final String KEY_DISTRICT = "District";
	private static final String KEY_TYPE = "Type";
	private static final String KEY_HEALTH = "Health";
	private static final String KEY_MAGIC = " Magic";
	private static final String KEY_ATTACK = "Attack";
	private static final String KEY_DEFENSE = "Defense";
	private static final String KEY_SPEED = "Speed";
	private static final String KEY_MPT = "Moves Per Turn";
	private static final String KEY_EXPERIENCE = "Experience";
	private static final String KEY_LEVEL = "Level";
	
			
	// Method is called during creation of the tables
	@Override
	public void onCreate(SQLiteDatabase database) {
		String CREATE_CREATURE_TABLE = "CREATE TABLE creatures ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "region TEXT" +
                "district TEXT" + 
                "type TEXT" +
                "health TEXT" +
                "magic TEXT" +
                "attack TEXT" +
                "defense TEXT" +
                "speed TEXT" +
                "mpt TEXT" +
                "experience TEXT" +
                "level TEXT )";
                
		//creates database table
		database.execSQL(CREATE_CREATURE_TABLE);
		
	}
	
	// Method is called during an upgrade of the database,
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data.");
		//drop older creatures table if existed
		database.execSQL("DROP TABLE IF EXISTS CreatureDB"); 
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
	

	private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_REGION, KEY_DISTRICT, KEY_TYPE,
		KEY_HEALTH, KEY_MAGIC, KEY_ATTACK, KEY_DEFENSE, KEY_SPEED, KEY_MPT, KEY_EXPERIENCE, KEY_LEVEL
	};
	
	public void addCreature(Creatures creature){
		//for logging
		Log.d("addCreature", creature.toString());
		
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		//change values.put to (String, string) for string values and
		//values.put to (String, int)
		
		values.put(KEY_ID, creature.getId());   				//creature ID
		values.put(KEY_NAME, creature.getName());				//creature name
		values.put(KEY_REGION, creature.getRegion());			//creature region
		values.put(KEY_DISTRICT, creature.getDistrict());		//creature district
		values.put(KEY_TYPE, creature.getType());				//creature type
		values.put(KEY_HEALTH, creature.getHealth());			//creature health
		values.put(KEY_MAGIC, creature.getMagic());				//creature magic
		values.put(KEY_ATTACK, creature.getAttack());			//creature attack
		values.put(KEY_DEFENSE, creature.getDefense());			//creature defense
		values.put(KEY_SPEED, creature.getSpeed());				//creature speed
		values.put(KEY_MPT, creature.getMovesPerTurn());		//creature moves per turn
		values.put(KEY_EXPERIENCE, creature.getExperience());	//creature experience
		values.put(KEY_LEVEL, creature.getLevel());				//creature level
		
//		//3. updating row
//		int i = database.update(Table_Creatures, //table
//				values,					   	     // column/value
//				KEY_ID+" = ?",			  		 //selections 
//				new String [] {String.valueOf(creature.getId()) }); // selection
//		
//		//4. close
//		database.close();
//		
//		return i;
		
		//3. inserting row
		database.insert(Table_Creatures, null, values);
		
		database.close();
		
	}
	
	public Creatures getCreature(int id){
		
		//1. get reference to readable Database
		SQLiteDatabase database = this.getReadableDatabase();
		
		//2. build query
		Cursor cursor =
				database.query(Table_Creatures, //a. table
						COLUMNS,				//b. columns 
						"id = ?",				//c. selections 
						new String[] {String.valueOf(id)},	//d. selection args
						null,					//e. group by 
						null, 					//f. having
						null, 					//g. order by
						null); 					//h. limit
		
		//3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		
		//4. build creatures object
		Creatures creature = new Creatures();
		creature.setId(Integer.parseInt(cursor.getString(0)));
				
		return creature;
		
	}
	
	public List<Creatures> getAllCreatures(){
		List<Creatures> creatures = new LinkedList<Creatures>();
		
		//1. build the query
		String query = "SELECT * FROM " + Table_Creatures;
		
		//2. get reference to writeable Database
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(query, null);
		
		//3. go over each row, build creature and add it to the list
		Creatures creature = null;
		if (cursor.moveToFirst()) {
			do {
				creature = new Creatures();
				creature.setId(Integer.parseInt(cursor.getString(0)));
				creature.setName(cursor.getString(1));
				creature.setRegion(cursor.getString(2));
				creature.setDistrict(cursor.getString(3));
				creature.setType(cursor.getString(4));
				creature.setHealth(Integer.parseInt(cursor.getString(5)));
				creature.setMagic(Integer.parseInt(cursor.getString(6)));
				creature.setAttack(Integer.parseInt(cursor.getString(7)));
				creature.setDefense(Integer.parseInt(cursor.getString(8)));
				creature.setSpeed(Integer.parseInt(cursor.getString(9)));
				creature.setMovesPerTurn(Integer.parseInt(cursor.getString(10)));
				creature.setExperience(Integer.parseInt(cursor.getString(11)));
				creature.setLevel(Integer.parseInt(cursor.getString(12)));
			
				//Add creature to creatures
				creatures.add(creature);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllCreatures()", creatures.toString());
		
		//return creatures list
		return creatures;	
	}
	
	public int updateCreature(Creatures creature){
		
		//1. get reference to writable database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put("name", creature.getName());				//get name
		values.put("region", creature.getRegion());			//get region
		values.put("district", creature.getDistrict());		//get district
		values.put("type", creature.getType());				//get type
		values.put("health", creature.getHealth());			//get health
		values.put("magic", creature.getName());			//get magic
		values.put("attack", creature.getName());			//get attack
		values.put("defense", creature.getName());			//get defense
		values.put("speed", creature.getName());			//get speed	
		values.put("moves per turn", creature.getName());	//get moves per turn
		values.put("experience", creature.getName());		//get experience
		values.put("level", creature.getName());			//get level
		
		//3. updating row
		int x = database.update(Table_Creatures, 				 //table
				values, 										 //column/value
				KEY_ID+" = ?", 									 //selections
				new String[] {String.valueOf(creature.getId())});//selection args
		
		//4. close
		database.close();
		
		return x;
	}
	
	public void deleteCreature(Creatures creature){
		//1. get reference to writable Database
		SQLiteDatabase database = this.getWritableDatabase();
		
		//2. delete
		database.delete(Table_Creatures,							//table
				KEY_ID+" = ",   									//selections
				new String[] {String.valueOf(creature.getId()) });	//selec
		
		//3. close
		database.close();
		
		//log
		Log.d("deleteCreature", creature.toString());
	}
	
	
	
	
	
	
	
	

	
	
	
}