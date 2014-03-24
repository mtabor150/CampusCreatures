package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CreatureDataBase {
	
	public static final String COLUMN_ID = "id"; 				//references a row number
	public static final String COLUMN_NAME = "creature_name";	//references creature's name according to row_id
	public static final String COLUMN_REGION = "region";
	public static final String COLUMN_DISTRICT = "district";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_HEALTH = "health";
	public static final String COLUMN_MAGIC = " magic";
	public static final String COLUMN_ATTACK = "attack";
	public static final String COLUMN_DEFENSE = "defense";
	public static final String COLUMN_SPEED = "speed";
	public static final String COLUMN_MPT = "moves_per_turn";
	public static final String COLUMN_EXPERIENCE = "experience";
	public static final String COLUMN_LEVEL = "level";
	
	private static final String DATABASE_NAME = "CampusCreaturesDB";		//Campus Creatures Database
	private static final String DATABASE_TABLE = "creaturesTable";				//creatures table
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {			
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_NAME + " TEXT NOT NULL, " + 
					COLUMN_REGION + " TEXT NOT NULL, " + 
					COLUMN_DISTRICT + " TEXT NOT NULL, " + 
					COLUMN_TYPE + " TEXT NOT NULL, " + 
					COLUMN_HEALTH +  " INTEGER NOT NULL, " + 
					COLUMN_MAGIC + " INTEGER NOT NULL, " + 
					COLUMN_ATTACK + " INTEGER NOT NULL, " +
					COLUMN_DEFENSE + " INTEGER NOT NULL, " +
					COLUMN_SPEED + " INTEGER, " +
					COLUMN_MPT + " INTEGER NOT NULL, " +
					COLUMN_EXPERIENCE + " INTEGER NOT NULL," +
					COLUMN_LEVEL + " INTEGER);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(DatabaseHelper.class.getName(),
					"Upgrading database from version " + oldVersion + " to "
							+ newVersion + ", which will destroy all old data.");
			//drop older creatures table if existed
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE); 
			//create a fresh new creatures table
			onCreate(db); 
		}
	}
	
	public CreatureDataBase(Context c){
		ourContext = c;
	}
	
	public CreatureDataBase open(){
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourDatabase.close();
	}
	
}
