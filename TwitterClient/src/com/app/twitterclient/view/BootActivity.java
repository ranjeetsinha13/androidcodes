package com.app.twitterclient.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.app.twitterclient.R;

public class BootActivity extends Activity {

	private SharedPreferences mAccountConfigured;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAccountConfigured = getSharedPreferences(
				getString(R.string.account_config_pref), Context.MODE_PRIVATE);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		jumpToNext();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		jumpToNext();

	}

	@Override
	protected void onResume() {
		super.onResume();
		jumpToNext();
	}

	/*
     *
	 */
	void jumpToNext() {

		// write login code
		String authToken = mAccountConfigured.getString(
				getString(R.string.auth_token), null);
		if (authToken == null || authToken.length() == 0) {
			Intent intent = new Intent(getApplicationContext(),
					NewAccountActivity.class);
			overridePendingTransition(0, 0);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		} else {

			Intent intent = new Intent(getApplicationContext(),
					HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

			overridePendingTransition(0, 0);
			startActivity(intent);
		}

	}

}
