package com.citypedia.app.providers;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author ranjeet
 */
public class ContentDescriptor {

	// utility variables
	public static final String AUTHORITY = "com.citypedia.provider.app";
	private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

	public static final UriMatcher URI_MATCHER = buildUriMatcher();

	private ContentDescriptor() {
	};

	// register identifying URIs for AppsCategory entity
	// the TOKEN value is associated with each URI registered
	private static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = AUTHORITY;

		matcher.addURI(authority, Restaurants.RESTAURANT_PATH,
				Restaurants.RESTAURANT_PATH_TOKEN);
		matcher.addURI(authority, ATMs.ATMS_PATH, ATMs.ATMS);
		matcher.addURI(authority, Places.PLACES_PATH, Places.PLACES);
		matcher.addURI(authority, Gyms.GYMS_PATH, Gyms.GYMS);
		matcher.addURI(authority, PetrolPumps.PETROLPUMP_PATH,
				PetrolPumps.PETROLPUMP);

		return matcher;
	}

	// Define a static class that represents description of stored content
	// entity.
	// Here we define AppsCategory
	public static class Restaurants {
		// an identifying name for entity
		public static final String NAME = "restaurants";

		public static final String RESTAURANT_PATH = "restaurants";
		public static final int RESTAURANT_PATH_TOKEN = 100;

		public static final Uri RESTAURANT_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(RESTAURANT_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.restaurants.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.restaurants.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_NAME_OF_RESTAURANT = "name";
			public static final String TAG_ADDRESS = "address";
			public static final String TAG_CUISINES = "cuisines";
			public static final String TAG_SERVICES = "services";
			public static final String TAG_AVG_COST_PER_PERSON = "avg_cost";
			public static final String TAG_LATITUDE = "lat";
			public static final String TAG_LONGITUDE = "longitude";
			public static final String TAG_TIMINGS = "timings";
			public static final String TAG_PAYMENT = "PAYMENT";
			public static final String TAG_CONTACT_DETAILS = "contact_details";
			public static final String TAG_LOCALITY = "locality";

		}
	}

	public static class ATMs {
		// an identifying name for entity
		public static final String NAME = "atms";

		public static final String ATMS_PATH = "atms";
		public static final int ATMS = 101;

		public static final Uri ATMS_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(ATMS_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.atms.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.atms.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_ATM_NAME = "name";
			public static final String TAG_ADDRESS = "address";
			public static final String TAG_LOCALITY = "locality";
			public static final String ATM_ID = "atm_id"; // unique

		}

	}

	public static class Places {
		// an identifying name for entity
		public static final String NAME = "places";

		public static final String PLACES_PATH = "places";
		public static final int PLACES = 102;

		public static final Uri PLACES_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(PLACES_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.places.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.places.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_PLACE_NAME = "name";// unique
			public static final String TAG_ADDRESS = "address";
			public static final String TAG_LOCALITY = "locality";
			public static final String FAMOUS_FOR = "famous_for";
			public static final String TAG_LATITUDE = "lat";
			public static final String TAG_LONGITUDE = "longt";
			public static final String PINCODE = "pincode";

		}
	}

	public static class Gyms {
		// an identifying name for entity
		public static final String NAME = "gyms";

		public static final String GYMS_PATH = "gyms";
		public static final int GYMS = 103;

		public static final Uri GYMS_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(GYMS_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.gyms.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.gyms.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_GYM_NAME = "name";// unique
			public static final String TAG_GYM_ADDRESS = "address";
			public static final String TAG_GYM_LOCALITY = "locality";
			public static final String TAG_LATITUDE = "lat";
			public static final String TAG_PHONE_NUMBER = "phone_number";
			public static final String TAG_LONGITUDE = "longt";
			public static final String PINCODE = "pincode";
			public static final String GYM_ID = "gym_id";

		}
	}

	public static class PetrolPumps {
		// an identifying name for entity
		public static final String NAME = "petrols";

		public static final String PETROLPUMP_PATH = "petrolpump";
		public static final int PETROLPUMP = 104;

		public static final Uri PETROLPUMP_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(PETROLPUMP_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.petrolpump.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.petrolpump.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_PETROL_PUMP_NAME = "name";// unique
			public static final String TAG_PETROL_PUMP_ADDRESS = "address";
			public static final String TAG_PETROL_PUMP_LOCALITY = "locality";
			public static final String TAG_LATITUDE = "lat";
			public static final String TAG_PHONE_NUMBER = "phone_number";
			public static final String TAG_LONGITUDE = "longt";
			public static final String PINCODE = "pincode";
			public static final String PP_ID = "pp_id";

		}
	}

	public static class Cabs {
		public static final String NAME = "cabs";

		public static final String CABS_PATH = "cabs";
		public static final int CABS = 105;

		public static final Uri CABS_CONTENT_URI = BASE_URI.buildUpon()
				.appendPath(CABS_PATH).build();

		// define content mime type for entity
		public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.cabs.app";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cabs.app";

		// a static class to store columns in entity
		public static class Cols {
			public static final String ID = BaseColumns._ID; // convention
			public static final String TAG_CABS_NAME = "name";// unique
			public static final String TAG_PHONE_NUMBER = "phone_number";

		}

	}

}
