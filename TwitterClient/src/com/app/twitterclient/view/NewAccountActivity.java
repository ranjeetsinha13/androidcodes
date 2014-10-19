package com.app.twitterclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.twitterclient.R;
import com.app.twitterclient.utils.ConnectionDetector;

public class NewAccountActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		ConnectionDetector connectionDetector = new ConnectionDetector(this);
		if (connectionDetector.isConnectingToInternet()) {
			Intent intent = new Intent(getApplicationContext(),
					TwitterAuthActivity.class);
			overridePendingTransition(0, 0);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		} else {
			Toast.makeText(this, getString(R.string.no_internet_connection),
					Toast.LENGTH_LONG).show();
		}

	}

}
