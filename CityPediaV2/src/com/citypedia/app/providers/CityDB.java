package com.citypedia.app.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

/**
 * Class to manage the creation and modification of database structure. It is
 * also used to manage connection to the SQLite database (hence the OpenHelper
 * in the name) Note that Android SDK will create DB once. Once created it's
 * structure won't change until version number is changed.
 * 
 * @author ranjeet
 * 
 */
public class CityDB extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "citypedia.db";
	private static final int DATABASE_VERSION = 1;

	private static final String ALTER_TABLE = "ALTER TABLE ";
	private static final String ADD = " ADD ";
	private final Handler mHandler;

	public CityDB(Context ctx, Handler handler) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		mHandler = handler;
	}

	/**
	 * What to do when the database is created the first time
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + ContentDescriptor.Restaurants.NAME + " ( "
				+ ContentDescriptor.Restaurants.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT
				+ " TEXT NOT NULL, "
				+ ContentDescriptor.Restaurants.Cols.TAG_ADDRESS + " TEXT , "
				+ ContentDescriptor.Restaurants.Cols.TAG_CUISINES + " TEXT, "
				+ ContentDescriptor.Restaurants.Cols.TAG_SERVICES + " TEXT, "
				+ ContentDescriptor.Restaurants.Cols.TAG_AVG_COST_PER_PERSON
				+ " TEXT, " + ContentDescriptor.Restaurants.Cols.TAG_LATITUDE
				+ " TEXT, " + ContentDescriptor.Restaurants.Cols.TAG_LONGITUDE
				+ " TEXT, " + ContentDescriptor.Restaurants.Cols.TAG_TIMINGS
				+ " TEXT , " + ContentDescriptor.Restaurants.Cols.TAG_PAYMENT
				+ " TEXT , "
				+ ContentDescriptor.Restaurants.Cols.TAG_CONTACT_DETAILS
				+ " TEXT , " + ContentDescriptor.Restaurants.Cols.TAG_LOCALITY
				+ " TEXT ," + "UNIQUE ("
				+ ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT
				+ ") ON CONFLICT REPLACE)");

		db.execSQL("CREATE TABLE " + ContentDescriptor.ATMs.NAME + " ( "
				+ ContentDescriptor.ATMs.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.ATMs.Cols.TAG_ATM_NAME + " TEXT NOT NULL, "
				+ ContentDescriptor.ATMs.Cols.TAG_ADDRESS + " TEXT , "
				+ ContentDescriptor.ATMs.Cols.TAG_LOCALITY + " TEXT, "
				+ ContentDescriptor.ATMs.Cols.ATM_ID + " TEXT NOT NULL, " +

				"UNIQUE (" + ContentDescriptor.ATMs.Cols.ATM_ID
				+ ") ON CONFLICT REPLACE)");

		db.execSQL("CREATE TABLE " + ContentDescriptor.Gyms.NAME + " ( "
				+ ContentDescriptor.Gyms.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.Gyms.Cols.TAG_GYM_NAME + " TEXT NOT NULL, "
				+ ContentDescriptor.Gyms.Cols.TAG_GYM_LOCALITY + " TEXT , "
				+ ContentDescriptor.Gyms.Cols.TAG_LATITUDE + " TEXT, "
				+ ContentDescriptor.Gyms.Cols.TAG_LONGITUDE + " TEXT, "
				+ ContentDescriptor.Gyms.Cols.TAG_GYM_ADDRESS + " TEXT, "
				+ ContentDescriptor.Gyms.Cols.TAG_PHONE_NUMBER + " TEXT, "
				+ ContentDescriptor.Gyms.Cols.PINCODE + " TEXT,  "
				+ ContentDescriptor.Gyms.Cols.GYM_ID + " TEXT NOT NULL, " +

				"UNIQUE (" + ContentDescriptor.Gyms.Cols.GYM_ID
				+ ") ON CONFLICT REPLACE)");
		db.execSQL("CREATE TABLE " + ContentDescriptor.PetrolPumps.NAME + " ( "
				+ ContentDescriptor.PetrolPumps.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_NAME
				+ " TEXT NOT NULL, "
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_LOCALITY
				+ " TEXT , " + ContentDescriptor.PetrolPumps.Cols.TAG_LATITUDE
				+ " TEXT, " + ContentDescriptor.PetrolPumps.Cols.TAG_LONGITUDE
				+ " TEXT, "
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_ADDRESS
				+ " TEXT, "
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PHONE_NUMBER
				+ " TEXT, " + ContentDescriptor.PetrolPumps.Cols.PINCODE
				+ " TEXT,  " + ContentDescriptor.PetrolPumps.Cols.PP_ID
				+ " TEXT NOT NULL, " +

				"UNIQUE (" + ContentDescriptor.PetrolPumps.Cols.PP_ID
				+ ") ON CONFLICT REPLACE)");

		db.execSQL("CREATE TABLE " + ContentDescriptor.Places.NAME + " ( "
				+ ContentDescriptor.Places.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.Places.Cols.TAG_PLACE_NAME
				+ " TEXT NOT NULL, "
				+ ContentDescriptor.Places.Cols.TAG_ADDRESS + " TEXT , "
				+ ContentDescriptor.Places.Cols.TAG_LATITUDE + " TEXT, "
				+ ContentDescriptor.Places.Cols.TAG_LONGITUDE + " TEXT , "
				+ ContentDescriptor.Places.Cols.FAMOUS_FOR + " TEXT, "
				+ ContentDescriptor.Places.Cols.PINCODE + " TEXT,  " +

				"UNIQUE (" + ContentDescriptor.Places.Cols.TAG_PLACE_NAME
				+ ") ON CONFLICT REPLACE)");

		db.execSQL("CREATE TABLE " + ContentDescriptor.Cabs.NAME + " ( "
				+ ContentDescriptor.Cabs.Cols.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ContentDescriptor.Cabs.Cols.TAG_CABS_NAME
				+ " TEXT NOT NULL, "
				+ ContentDescriptor.Cabs.Cols.TAG_PHONE_NUMBER + " TEXT , " +

				"UNIQUE (" + ContentDescriptor.Cabs.Cols.TAG_CABS_NAME
				+ ") ON CONFLICT REPLACE)");

	}

	/**
	 * What to do when the database version changes: drop table and recreate
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "
					+ ContentDescriptor.Restaurants.NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.ATMs.NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Places.NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Gyms.NAME);
			db.execSQL("DROP TABLE IF EXISTS "
					+ ContentDescriptor.PetrolPumps.NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Gyms.NAME);
			db.execSQL("DROP TABLE IF EXISTS " + ContentDescriptor.Cabs.NAME);

			onCreate(db);
		}
	}

}
