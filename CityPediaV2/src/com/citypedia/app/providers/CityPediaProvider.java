package com.citypedia.app.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Handler;

import com.app.citypediav2.utils.LogUtils;

/**
 * This class defines the CityPediaProvider.
 * 
 * @author ranjeet
 * 
 */
public class CityPediaProvider extends ContentProvider {
	private static CityDB cityDB;
	private static final String TAG = "CityPediaProvider.";

	@Override
	public boolean onCreate() {
		Context ctx = getContext();
		LogUtils.LOGD(TAG, "inside CP on create");
		cityDB = new CityDB(ctx, new Handler());
		return true;
	}

	public static CityDB getDBInstance() {

		return cityDB;

	}

	/**
	 * Utility function to return the mime type based on a given URI
	 */
	@Override
	public String getType(Uri uri) {
		final int match = ContentDescriptor.URI_MATCHER.match(uri);
		switch (match) {

		case ContentDescriptor.Restaurants.RESTAURANT_PATH_TOKEN:
			return ContentDescriptor.Restaurants.CONTENT_TYPE_DIR;

		case ContentDescriptor.ATMs.ATMS:
			return ContentDescriptor.ATMs.CONTENT_TYPE_DIR;

		case ContentDescriptor.Places.PLACES:
			return ContentDescriptor.Places.CONTENT_TYPE_DIR;
		case ContentDescriptor.Gyms.GYMS:
			return ContentDescriptor.Gyms.CONTENT_TYPE_DIR;
		case ContentDescriptor.PetrolPumps.PETROLPUMP:
			return ContentDescriptor.PetrolPumps.CONTENT_TYPE_DIR;

		case ContentDescriptor.Cabs.CABS:
			return ContentDescriptor.Cabs.CONTENT_TYPE_DIR;

		default:
			throw new UnsupportedOperationException("URI " + uri
					+ " is not supported.");
		}
	}

	/**
	 * Function to query the content provider. This example queries the backing
	 * database. It uses the SQLite API to retrieve appcategory data based on
	 * the URI specified.
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = cityDB.getReadableDatabase();
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		Cursor resultSet = null;
		final int match = ContentDescriptor.URI_MATCHER.match(uri);

		LogUtils.LOGD(TAG, "match is query ..." + match);
		switch (match) {

		// retrieve applications list
		case ContentDescriptor.Restaurants.RESTAURANT_PATH_TOKEN: {

			queryBuilder.setTables(ContentDescriptor.Restaurants.NAME);

			sortOrder = ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT;

			break;

		}

		case ContentDescriptor.ATMs.ATMS: {

			queryBuilder.setTables(ContentDescriptor.ATMs.NAME);

			sortOrder = ContentDescriptor.ATMs.Cols.ATM_ID;

			break;

		}

		case ContentDescriptor.Cabs.CABS: {

			queryBuilder.setTables(ContentDescriptor.Cabs.NAME);

			sortOrder = ContentDescriptor.Cabs.Cols.TAG_CABS_NAME;

			break;

		}

		case ContentDescriptor.Places.PLACES: {

			queryBuilder.setTables(ContentDescriptor.Places.NAME);
			sortOrder = ContentDescriptor.Places.Cols.TAG_PLACE_NAME;

			break;

		}

		case ContentDescriptor.Gyms.GYMS: {

			queryBuilder.setTables(ContentDescriptor.Gyms.NAME);
			sortOrder = ContentDescriptor.Gyms.Cols.TAG_GYM_NAME;

			break;

		}
		case ContentDescriptor.PetrolPumps.PETROLPUMP: {

			queryBuilder.setTables(ContentDescriptor.PetrolPumps.NAME);
			sortOrder = ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_NAME;

			break;

		}

		}

		resultSet = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);

		resultSet.setNotificationUri(getContext().getContentResolver(), uri);

		return resultSet;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
