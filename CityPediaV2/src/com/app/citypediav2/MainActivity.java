package com.app.citypediav2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.citypediav2.services.DataLoggingService;
import com.app.citypediav2.utils.Constants;
import com.app.citypediav2.utils.LogUtils;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";

	private SharedPreferences mSharedPreferences;

	private boolean value;
	private View v;
	private ProgressBar p;
	private TextView t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSharedPreferences = getSharedPreferences(Constants.FIRST_INSTALL,
				Context.MODE_PRIVATE);
		value = mSharedPreferences.getBoolean(Constants.IS_APP_INSTALL, false);
		LogUtils.LOGD(TAG, "value is " + value);

		setContentView(R.layout.activity_main);
		v = findViewById(R.id.mainview);
		p = (ProgressBar) findViewById(R.id.progressBar1);
		t = (TextView) findViewById(R.id.loadingdata);
		if (value == false) {
			try {
				getApplicationContext().startService(
						new Intent(getApplicationContext(),
								DataLoggingService.class));
				p.setVisibility(View.VISIBLE);
				t.setVisibility(View.VISIBLE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			p.setVisibility(View.GONE);
			t.setVisibility(View.GONE);
			v.setOnClickListener(this);
		}

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(MainActivity.this, Options.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getApplicationContext().startActivity(i);

	}

	@Override
	protected void onResume() {
		super.onResume();
		value = mSharedPreferences.getBoolean(Constants.IS_APP_INSTALL, false);
		LogUtils.LOGD(TAG, "value is resume " + value);

		if (value == false) {
			p.setVisibility(View.VISIBLE);
			t.setVisibility(View.VISIBLE);
		} else {
			p.setVisibility(View.GONE);
			t.setVisibility(View.GONE);
			v.setOnClickListener(this);

		}
	}

}
