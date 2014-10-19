package com.app.twitterclient.view;

import android.app.Activity;
import android.os.Bundle;

import com.app.twitterclient.R;
import com.app.twitterclient.utils.LogUtils;

public class HomeActivity extends Activity {

	private static final String TAG = "HomeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.LOGD(TAG, "comes here .. login done");
		setContentView(R.layout.loading);

	}

}
