package com.app.citypediav2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.app.citypediav2.utils.Constants;
import com.app.citypediav2.utils.LogUtils;
import com.citypedia.app.providers.ContentDescriptor;

public class ItemListActivity extends FragmentActivity implements
		LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener,
		OnScrollListener {

	private static final String TAG = "ItemListActivity";

	private SharedPreferences screenPrefs;
	private int listNumber;
	private ItemListAdapter mAdapter;

	protected String searchText;

	private static class ClassConstants {
		static String[] columns;
		static Uri tableUri;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_restaurants);

		screenPrefs = getSharedPreferences(Constants.SEARCH_SCREEN_PREFS,
				MODE_PRIVATE);
		listNumber = screenPrefs.getInt(Constants.SEARCH_SCREEN_FLAG, -1);

		ListView restListView = (ListView) findViewById(R.id.restaunrantlist);

		Bundle arg = new Bundle();
		initData(listNumber);

		getSupportLoaderManager().initLoader(0, arg, this);
		mAdapter = new ItemListAdapter(ItemListActivity.this);
		restListView.setAdapter(mAdapter);

		restListView.setOnItemClickListener(this);

		EditText search = (EditText) findViewById(R.id.search);

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				searchText = s.toString();
				getSupportLoaderManager().restartLoader(0, null,
						ItemListActivity.this);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void initData(int listNumber2) {
		ClassConstants.columns = new String[3];
		switch (listNumber2) {
		case 0:

			ClassConstants.columns[0] = ContentDescriptor.Restaurants.Cols.ID;
			ClassConstants.columns[1] = ContentDescriptor.Restaurants.Cols.TAG_NAME_OF_RESTAURANT;
			ClassConstants.columns[2] = ContentDescriptor.Restaurants.Cols.TAG_ADDRESS;
			ClassConstants.tableUri = ContentDescriptor.Restaurants.RESTAURANT_CONTENT_URI;

			break;
		case 1:

			ClassConstants.columns[0] = ContentDescriptor.Places.Cols.ID;
			ClassConstants.columns[1] = ContentDescriptor.Places.Cols.TAG_PLACE_NAME;
			ClassConstants.columns[2] = ContentDescriptor.Places.Cols.TAG_ADDRESS;
			ClassConstants.tableUri = ContentDescriptor.Places.PLACES_CONTENT_URI;
			break;
		case 2:
			ClassConstants.columns[0] = ContentDescriptor.Gyms.Cols.ID;
			ClassConstants.columns[1] = ContentDescriptor.Gyms.Cols.TAG_GYM_NAME;
			ClassConstants.columns[2] = ContentDescriptor.Gyms.Cols.TAG_GYM_ADDRESS;
			ClassConstants.tableUri = ContentDescriptor.Gyms.GYMS_CONTENT_URI;
			break;
		case 3:
			ClassConstants.columns[0] = ContentDescriptor.ATMs.Cols.ID;
			ClassConstants.columns[1] = ContentDescriptor.ATMs.Cols.TAG_ATM_NAME;
			ClassConstants.columns[2] = ContentDescriptor.ATMs.Cols.TAG_ADDRESS;
			ClassConstants.tableUri = ContentDescriptor.ATMs.ATMS_CONTENT_URI;
			break;
		case 4:

			ClassConstants.columns[0] = ContentDescriptor.PetrolPumps.Cols.ID;
			ClassConstants.columns[1] = ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_NAME;
			ClassConstants.columns[2] = ContentDescriptor.PetrolPumps.Cols.TAG_PETROL_PUMP_ADDRESS;
			ClassConstants.tableUri = ContentDescriptor.PetrolPumps.PETROLPUMP_CONTENT_URI;
			break;

		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		LogUtils.LOGD(TAG, "onScroll" + firstVisibleItem + "//"
				+ visibleItemCount + "//" + totalItemCount);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		LogUtils.LOGD(TAG, "onScrollStateChanged" + scrollState);

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {

		Intent i = new Intent(ItemListActivity.this, DetailsActivity.class);
		i.putExtra(Constants.ITEM_ID, id);
		i.putExtra(Constants.TABLE_NAME, ClassConstants.tableUri.toString());
		startActivity(i);

	}

	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {

		switch (loaderID) {

		case 0:
			// Returns a new CursorLoader
			LogUtils.LOGD(TAG, "initloader" + "//" + searchText);
			String[] params;
			String select = ClassConstants.columns[1] + " like ?";

			if (searchText != null && searchText.length() > 0) {

				params = new String[] { "%" + searchText + "%" };
			} else {
				params = null;
				select = null;
			}

			return new CursorLoader(ItemListActivity.this, // Context
					ClassConstants.tableUri, // Table
												// to
												// query
					ClassConstants.columns, // Projection to return
					select, // No selection clause
					params, // No selection arguments
					null // Default sort order
			);
		default:
			// An invalid id was passed in
			return null;

		}

	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor returnCursor) {
		mAdapter.changeCursor(returnCursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

		if (mAdapter != null)
			mAdapter.changeCursor(null);

	}
}
