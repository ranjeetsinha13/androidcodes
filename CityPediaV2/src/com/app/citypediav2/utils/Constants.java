package com.app.citypediav2.utils;

import com.citypedia.app.providers.ContentDescriptor;

public class Constants {

	// JSON Node restaurants names
	public static final String TAG_ID = "ID";
	public static final String TAG_NAME_OF_RESTAURANT = "NAME_OF_RESTAURANT";
	public static final String TAG_ADDRESS = "ADDRESS";
	public static final String TAG_CUISINES = "CUISINES";
	public static final String TAG_SERVICES = "SERVICES";
	public static final String TAG_AVG_COST_PER_PERSON = "AVG_COST_PER_PERSON";
	public static final String TAG_LATITUDE = "LATITUDE";
	public static final String TAG_LONGITUDE = "LONGITUDE";
	public static final String TAG_TIMINGS = "TIMINGS";
	public static final String TAG_LOCALITY = "LOCALITY";
	public static final String TAG_PAYMENT = "PAYMENT";
	public static final String TAG_CONTACT_DETAILS = "CONTACT_DETAILS";
	public static final String TAG_USER_RATINGS_ID = "USER_RATINGS_ID";

	// JSON Node Atms names

	public static final String TAG_ATM_NAME = "ATM_NAME";
	public static final String TAG_ATM_ADDRESS = "ATM_ADDRESS";
	public static final String TAG_ATM_LOCALITY = "ATM_LOCALITY";
	public static final String TAG_ATMID = "ATM_ID";

	// JSON Node to get places

	public static final String TAG_PLACE_NAME = "NAME_OF_PLACE";
	public static final String TAG_FAMOUS_FOR = "FAMOUS_FOR";
	public static final String TAG_PINCODE = "PINCODE";

	// JSON Node to get petrol pumps

	public static final String TAG_PETROL_PUMP_NAME = "PETROL_PUMP_NAME";
	public static final String TAG_CONTACT_NUMBER = "CONTACT_NUMBER";
	public static final String TAG_PIN_CODE = "PINCODE";

	// JSON Node to get gym data

	public static final String TAG_GS_ID = "GS_ID";
	public static final String TAG_GYM_NAME = "GYM_NAME";
	
	public static final String TAG_CABS_NAME = "CAB_NAME";// unique
	public static final String TAG_PHONE_NUMBER = "CAB_NUMBER";

	// url to make request

	public static final String restaurant_url = "http://server.letsappit.in/admin/json_data/json.php?key=RYGDFG345FSFDF";
	public static final String gyms_url = "http://server.letsappit.in/admin/json_data/json.php?key=FJGHSDFGR234RE";
	public static final String places_url = "http://server.letsappit.in/admin/json_data/json.php?key=CVSSADF234DFSD";
	public static final String petrol_pumps = "http://server.letsappit.in/admin/json_data/json.php?key=SGFSHGAS242SDF";
	public static final String atms_banks = "http://server.letsappit.in/admin/json_data/json.php?key=LKFHJSVV56FGDFG";

	// databse query

	final static String[] restColumns = {
			ContentDescriptor.Restaurants.Cols.ID,
			ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT,
			ContentDescriptor.Restaurants.Cols.TAG_ADDRESS };

	public static final String FIRST_INSTALL = "first_install";
	public static final String IS_APP_INSTALL = "is_app_install";

	public static final String REST_NAME = "rest_name";
	public static final String LOCALITY = "locality";
	public static final String CUISINE = "cuisine";

	public static final String REST_NAME_AND = "rest_name_and";
	public static final String LOCALITY_AND = "locality_and";
	public static final String AND = "AND ";
	public static final String OR = "OR ";

	public static final String NO_DATA_FOUND = "No Data Found";

	public static final String SEARCH_SCREEN_FLAG = "search_screen_flag";
	public static final String SEARCH_SCREEN_PREFS = "search_screen_prefs";

	public static final String SINGLE_SEARCH_CRITERIA = "single_search_criteria";

	// constants for details screen

	public static final String ITEM_ID = "item_id";
	public static final String TABLE_NAME = "table_name";

	
	public static final String FEED_ID = "feedid";

	public static final String DB_IS_TRUE = "=1";
	public static final String DB_IS_FALSE = "=0";
	public static final String DB_IS_NULL = " IS NULL";
	public static final String DB_IS_NOT_NULL = " IS NOT NULL";
	public static final String DB_DESC = " DESC";
	public static final String DB_ASC = " ASC";
	public static final String DB_ARG = "=?";
	public static final String DB_AND = " AND ";
	public static final String DB_OR = " OR ";

	public static final String HTTP_SCHEME = "http://";
	public static final String HTTPS_SCHEME = "https://";
	public static final String FILE_SCHEME = "file://";

	public static final String HTML_LT = "&lt;";
	public static final String HTML_GT = "&gt;";
	public static final String LT = "<";
	public static final String GT = ">";

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	public static final String ENCLOSURE_SEPARATOR = "[@]"; // exactly three
															// characters!

	public static final String HTML_QUOT = "&quot;";
	public static final String QUOT = "\"";
	public static final String HTML_APOSTROPHE = "&#39;";
	public static final String APOSTROPHE = "'";
	public static final String AMP = "&";
	public static final String AMP_SG = "&amp;";
	public static final String SLASH = "/";
	public static final String COMMA_SPACE = ", ";

	public static final String UTF8 = "UTF-8";

	public static final String FROM_AUTO_REFRESH = "from_auto_refresh";

	public static final String MIMETYPE_TEXT_PLAIN = "text/plain";

	public static final int UPDATE_THROTTLE_DELAY = 500;

}
