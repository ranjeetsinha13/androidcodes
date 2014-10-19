package com.app.twitterclient.view;

import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.app.twitterclient.model.TwitterBackend;
import com.app.twitterclient.utils.ConsumerKeyConstants;

public class BootActivity extends Activity {
	private TwitterBackend mTwitterBackend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTwitterBackend = new TwitterBackend(
				ConsumerKeyConstants.TWITTER_CONSUMER_KEY,
				ConsumerKeyConstants.TWITTER_CONSUMER_SECRET, this);

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

		if (mTwitterBackend.tokenExists()) {
			AccessToken token = mTwitterBackend.getToken();
			mTwitterBackend.twitterInit(token);
			finish();
			Intent intent = new Intent(getApplicationContext(),
					HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(0, 0);
			startActivity(intent);
		} else {
			Intent intent = new Intent(getApplicationContext(),
					NewAccountActivity.class);
			overridePendingTransition(0, 0);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}

	}
}
