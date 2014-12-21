package com.app.citypediav2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.app.citypediav2.utils.Constants;

public class Options extends Activity {

	private Intent i;
	private Context context;
	private SharedPreferences screenPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_list);
		context = getApplicationContext();
		screenPrefs = getSharedPreferences(Constants.SEARCH_SCREEN_PREFS,
				MODE_PRIVATE);

		GridView lv = (GridView) findViewById(R.id.options_listview);

		ListAdapterOptions lac = new ListAdapterOptions(
				getApplicationContext(), R.layout.single_options_layout);
		lv.setAdapter(lac);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					// RESTAURANTS

					i = new Intent(context, ItemListActivity.class);
					screenPrefs.edit().putInt(Constants.SEARCH_SCREEN_FLAG, 0)
							.commit();
					startActivity(i);
					break;
				case 1:
					// PLACES TO VISIT
					i = new Intent(context, ItemListActivity.class);
					screenPrefs.edit().putInt(Constants.SEARCH_SCREEN_FLAG, 1)
							.commit();

					startActivity(i);
					break;
				case 2:
					// GYMS
					i = new Intent(context, ItemListActivity.class);
					screenPrefs.edit().putInt(Constants.SEARCH_SCREEN_FLAG, 2)
							.commit();
					startActivity(i);
					break;
				case 3:
					// ATMS
					i = new Intent(context, ItemListActivity.class);
					screenPrefs.edit().putInt(Constants.SEARCH_SCREEN_FLAG, 3)
							.commit();
					startActivity(i);
					break;

				case 4:
					i = new Intent(context, ItemListActivity.class);
					screenPrefs.edit().putInt(Constants.SEARCH_SCREEN_FLAG, 4)
							.commit();
					startActivity(i);
					break;

				}

			}
		});

	}

}
