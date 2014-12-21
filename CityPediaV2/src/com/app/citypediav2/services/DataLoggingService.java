package com.app.citypediav2.services;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.app.citypediav2.Options;
import com.app.citypediav2.R;
import com.app.citypediav2.utils.Constants;
import com.citypedia.app.enities.Atms;
import com.citypedia.app.enities.Cabs;
import com.citypedia.app.enities.Gyms;
import com.citypedia.app.enities.PetrolPumps;
import com.citypedia.app.enities.PlacesToVisit;
import com.citypedia.app.enities.Restaurants;
import com.citypedia.app.providers.CityDB;
import com.citypedia.app.providers.CityPediaProvider;
import com.citypedia.app.providers.ContentDescriptor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoggingService extends Service {

	private static final String TAG = "DataLoggingService";

	private static final Class<?>[] mSetForegroundSignature = new Class[] { boolean.class };

	private static final Class<?>[] mStartForegroundSignature = new Class[] {
			int.class, Notification.class };

	private static final Class<?>[] mStopForegroundSignature = new Class[] { boolean.class };

	ArrayList<String> mUrls;

	private SharedPreferences mSharedPreferences;

	private NotificationManager mNM;

	private Method mSetForeground;

	private Method mStartForeground;

	private Method mStopForeground;

	private JsonFactory jsonFactory;

	private ArrayList<Atms> atmslist;

	private ArrayList<Restaurants> restaurants;

	private ArrayList<Gyms> gymslist;

	private ArrayList<PetrolPumps> ppList;

	private ArrayList<PlacesToVisit> pList;
	private ArrayList<Cabs> cabsList;

	private Object[] mSetForegroundArgs = new Object[1];

	private Object[] mStartForegroundArgs = new Object[2];

	private Object[] mStopForegroundArgs = new Object[1];

	static int count;

	void invokeMethod(Method method, Object[] args) {
		try {
			method.invoke(this, args);
		} catch (InvocationTargetException e) {
			Log.w(TAG, "Unable to invoke method", e);
		} catch (IllegalAccessException e) {
			Log.w(TAG, "Unable to invoke method", e);
		}
	}

	public void startForegroundCompat(int id, Notification notification) {
		if (mStartForeground != null) {
			mStartForegroundArgs[0] = Integer.valueOf(id);
			mStartForegroundArgs[1] = notification;
			invokeMethod(mStartForeground, mStartForegroundArgs);
			return;
		}

		mSetForegroundArgs[0] = Boolean.TRUE;
		invokeMethod(mSetForeground, mSetForegroundArgs);
		mNM.notify(id, notification);

	}

	public void stopForegroundCompat(int id) {
		if (mStopForeground != null) {
			mStopForegroundArgs[0] = Boolean.TRUE;
			invokeMethod(mStopForeground, mStopForegroundArgs);
			return;
		}

		// Note to cancel BEFORE changing the
		// foreground state, since we could be killed at that point.
		mNM.cancel(id);
		mSetForegroundArgs[0] = Boolean.FALSE;
		invokeMethod(mSetForeground, mSetForegroundArgs);

	}

	@Override
	public void onCreate() {
		super.onCreate();

		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		try {
			mStartForeground = getClass().getMethod("startForeground",
					mStartForegroundSignature);
			mStopForeground = getClass().getMethod("stopForeground",
					mStopForegroundSignature);

			return;
		} catch (NoSuchMethodException e) {
			mStartForeground = mStopForeground = null;
		}
		try {
			mSetForeground = getClass().getMethod("setForeground",
					mSetForegroundSignature);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"OS doesn't have Service.startForeground OR Service.setForeground!");
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		super.onStartCommand(intent, flags, startId);
		/*
		 * start filling data
		 */

		DataTask dataTask = (DataTask) new DataTask();
		dataTask.execute();

		return START_STICKY;
	}

	private void writeGymData(InputStream is) throws IOException,
			JsonParseException {

		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		Gyms r;
		gymslist = new ArrayList<Gyms>();
		while (dataset != null) {
			r = new Gyms();

			if (dataset != null) {

				r.setGymName(dataset.get(Constants.TAG_GYM_NAME).asText());
				r.setAddr(dataset.get(Constants.TAG_ADDRESS).asText());
				r.setContactNo(dataset.get(Constants.TAG_CONTACT_NUMBER)
						.asText());
				r.setId(dataset.get(Constants.TAG_GS_ID).asText());
				r.setPincode(dataset.get(Constants.TAG_PINCODE).asText());

			}
			gymslist.add(r);
			dataset = node.get(++i);
		}

		bulkinsertGyms();

	}

	private void bulkinsertGyms() {

		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.Gyms.NAME + " ("
				+ ContentDescriptor.Gyms.Cols.GYM_ID + " , "
				+ ContentDescriptor.Gyms.Cols.PINCODE + " , "
				+ ContentDescriptor.Gyms.Cols.TAG_GYM_ADDRESS + " , "
				+ ContentDescriptor.Gyms.Cols.TAG_GYM_NAME + " , "
				+ ContentDescriptor.Gyms.Cols.TAG_PHONE_NUMBER

				+ " ) " + "VALUES (?,?,?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (Gyms g : gymslist) {
			statement.clearBindings();

			statement.bindString(1, g.getId());
			statement.bindString(2, g.getPincode());
			statement.bindString(3, g.getAddr());
			statement.bindString(4, g.getGymName());
			statement.bindString(5, g.getContactNo());

			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	private void writePPData(InputStream is) throws IOException,
			JsonParseException {

		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		PetrolPumps p;
		ppList = new ArrayList<PetrolPumps>();
		while (dataset != null) {
			p = new PetrolPumps();

			if (dataset != null) {

				p.setpName(dataset.get(Constants.TAG_PETROL_PUMP_NAME).asText());
				p.setAddress(dataset.get(Constants.TAG_ADDRESS).asText());
				p.setContactNo(dataset.get(Constants.TAG_CONTACT_NUMBER)
						.asText());

				p.setId(dataset.get(Constants.TAG_ID).asText());

				p.setPinCode(dataset.get(Constants.TAG_PINCODE).asText());

			}
			ppList.add(p);
			dataset = node.get(++i);
		}

		bulkinsertPetrolPumps();

	}

	private void bulkinsertPetrolPumps() {
		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.PetrolPumps.NAME + " ("
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_NAME
				+ " , "
				+ ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_ADDRESS
				+ " , " + ContentDescriptor.PetrolPumps.Cols.TAG_PHONE_NUMBER
				+ " , " + ContentDescriptor.PetrolPumps.Cols.PINCODE + " , "
				+ ContentDescriptor.PetrolPumps.Cols.PP_ID + " ) "
				+ "VALUES (?,?,?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (PetrolPumps p : ppList) {
			statement.clearBindings();

			statement.bindString(1, p.getpName());
			statement.bindString(2, p.getAddress());

			statement.bindString(3, p.getContactNo());
			statement.bindString(4, p.getPinCode());
			statement.bindString(5, p.getId());
			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	private void bulkinsertPlaces() {

		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.Places.NAME + " ("
				+ ContentDescriptor.Places.Cols.TAG_PLACE_NAME + " , "
				+ ContentDescriptor.Places.Cols.TAG_ADDRESS + " , "
				+ ContentDescriptor.Places.Cols.FAMOUS_FOR + " , "
				+ ContentDescriptor.Places.Cols.PINCODE + " ) "
				+ "VALUES (?,?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (PlacesToVisit p : pList) {
			statement.clearBindings();

			statement.bindString(1, p.getpName());
			statement.bindString(2, p.getAddress());

			statement.bindString(3, p.getFamousFor());
			statement.bindString(4, p.getPincode());
			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	private void writePlacesData(InputStream is) throws IOException,
			JsonParseException {

		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		PlacesToVisit pp;
		pList = new ArrayList<PlacesToVisit>();
		while (dataset != null) {
			pp = new PlacesToVisit();

			if (dataset != null) {

				pp.setpName(dataset.get(Constants.TAG_PLACE_NAME).asText());
				pp.setAddress(dataset.get(Constants.TAG_ADDRESS).asText());
				pp.setFamousFor(dataset.get(Constants.TAG_FAMOUS_FOR).asText());

				pp.setPincode(dataset.get(Constants.TAG_PINCODE).asText());

			}
			pList.add(pp);
			dataset = node.get(++i);
		}

		bulkinsertPlaces();

	}

	private void writeCabsData(InputStream is) throws IOException {

		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		Cabs c;
		cabsList = new ArrayList<Cabs>();
		while (dataset != null) {
			c = new Cabs();

			if (dataset != null) {

				c.setName(dataset.get(Constants.TAG_CABS_NAME).asText());
				c.setNumber(dataset.get(Constants.TAG_PHONE_NUMBER).asText());

			}
			cabsList.add(c);
			dataset = node.get(++i);
		}

		bulkinsertCabs();

	}

	private void bulkinsertCabs() {

		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.Cabs.NAME + " ("
				+ ContentDescriptor.Cabs.Cols.TAG_CABS_NAME + " , "
				+ ContentDescriptor.Cabs.Cols.TAG_PHONE_NUMBER

				+ " ) " + "VALUES (?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (Cabs c : cabsList) {
			statement.clearBindings();

			statement.bindString(1, c.getName());
			statement.bindString(2, c.getNumber());

			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	private void writeRestaurantsToDB(InputStream is) throws IOException,
			JsonParseException {
		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		Restaurants r;
		restaurants = new ArrayList<Restaurants>();
		while (dataset != null) {
			r = new Restaurants();

			if (dataset != null) {

				r.setNameRestaurant(dataset.get(
						Constants.TAG_NAME_OF_RESTAURANT).asText());
				r.setAddress(dataset.get(Constants.TAG_ADDRESS).asText());
				r.setAvgCostPerPerson(dataset.get(
						Constants.TAG_AVG_COST_PER_PERSON).asText());
				r.setContactDetails(dataset.get(Constants.TAG_CONTACT_DETAILS)
						.asText());
				r.setCuisines(dataset.get(Constants.TAG_CUISINES).asText());

				r.setPayments(dataset.get(Constants.TAG_PAYMENT).asText());
				r.setTimings(dataset.get(Constants.TAG_TIMINGS).asText());
				r.setLocality(dataset.get(Constants.TAG_LOCALITY).asText());
				r.setServices(dataset.get(Constants.TAG_SERVICES).asText());

			}
			restaurants.add(r);
			dataset = node.get(++i);
		}

		bulkinsertRestaurants();
	}

	private void bulkinsertRestaurants() {
		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.Restaurants.NAME + " ("
				+ ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_ADDRESS
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_CUISINES
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_SERVICES
				+ " , "
				+ ContentDescriptor.Restaurants.Cols.TAG_AVG_COST_PER_PERSON
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_TIMINGS
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_PAYMENT
				+ " , "
				+ ContentDescriptor.Restaurants.Cols.TAG_CONTACT_DETAILS
				+ " , " + ContentDescriptor.Restaurants.Cols.TAG_LOCALITY
				+ " ) " + "VALUES (?,?,?,?,?,?,?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (Restaurants r : restaurants) {
			statement.clearBindings();

			statement.bindString(1, r.getNameRestaurant());
			statement.bindString(2, r.getAddress());
			statement.bindString(3, r.getCuisines());
			statement.bindString(4, r.getServices());
			statement.bindString(5, r.getAvgCostPerPerson());

			statement.bindString(6, r.getTimings());
			statement.bindString(7, r.getPayments());
			statement.bindString(8, r.getContactDetails());
			statement.bindString(9, r.getLocality());

			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	private void writeATMsToDB(InputStream is) throws IOException,
			JsonParseException {

		JsonParser jp = jsonFactory.createParser(is);

		// create an ObjectMapper instance.
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(jp);
		JsonNode dataset = node.get(0);
		int i = 0;
		Atms a;
		atmslist = new ArrayList<Atms>();
		while (dataset != null) {
			a = new Atms();

			if (dataset != null) {
				a.setAtmAddr(dataset.get(Constants.TAG_ATM_ADDRESS).asText());
				a.setAtmName(dataset.get(Constants.TAG_ATM_NAME).asText());
				a.setId(dataset.get(Constants.TAG_ATMID).asText());

			}
			atmslist.add(a);
			dataset = node.get(++i);
		}
		bulkInsertATMs();

	}

	private void bulkInsertATMs() {
		CityDB cityDB = CityPediaProvider.getDBInstance();
		SQLiteDatabase db = cityDB.getWritableDatabase();
		String sql = "INSERT INTO " + ContentDescriptor.ATMs.NAME + " ("
				+ ContentDescriptor.ATMs.Cols.TAG_ATM_NAME + " , "
				+ ContentDescriptor.ATMs.Cols.TAG_ADDRESS + " , "
				+ ContentDescriptor.ATMs.Cols.ATM_ID + " ) "
				+ "VALUES (?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);

		db.beginTransaction();

		for (Atms s : atmslist) {
			statement.clearBindings();

			statement.bindString(1, s.getAtmName());
			statement.bindString(2, s.getAtmAddr());

			statement.bindString(3, s.getId());
			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		stopForegroundCompat(R.string.foreground_service_started);

	}

	private class DataTask extends AsyncTask<Void, Integer, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			AssetManager manager = getApplicationContext().getAssets();

			jsonFactory = new JsonFactory();
			InputStream atms;
			try {
				int[] arr;

				atms = manager.open("atms.json");
				InputStream restaurants = manager.open("restaurants.json");
				InputStream gyms = manager.open("gyms.json");
				InputStream places = manager.open("places.json");
				InputStream pp = manager.open("petrolpumps.json");
				InputStream cabs = manager.open("cabs.json");
				writeATMsToDB(atms);
				writeRestaurantsToDB(restaurants);
				writePlacesData(places);
				writePPData(pp);
				writeGymData(gyms);
				writeCabsData(cabs);

			} catch (JsonParseException ex) {
				ex.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);

			// start activity
			mSharedPreferences = getSharedPreferences(Constants.FIRST_INSTALL,
					Context.MODE_PRIVATE);
			mSharedPreferences.edit()
					.putBoolean(Constants.IS_APP_INSTALL, true).commit();

			Intent i = new Intent(DataLoggingService.this, Options.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getApplicationContext().startActivity(i);
			stopSelf();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values);
		}
	}

}
