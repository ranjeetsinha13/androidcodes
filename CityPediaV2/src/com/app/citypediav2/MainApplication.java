package com.app.citypediav2;

import android.app.Application;
import android.content.Context;

import com.app.citypediav2.utils.PrefUtils;

public class MainApplication extends Application {

	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		PrefUtils.putBoolean(PrefUtils.IS_REFRESHING, false);

	}

	public static Context getContext() {
		return context;
	}
}
