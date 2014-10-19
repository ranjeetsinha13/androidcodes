package com.app.twitterclient.utils;

import android.util.Log;

/**
 * 
 * @author ranjeet_sinha An Utility to enable/Disable logs
 * 
 */

public class LogUtils {
	static final boolean LOG = true;

	public static void LOGD(final String tag, String message) {
		if (LOG) {
			Log.d(tag, message);
		}
	}

	public static void LOGV(final String tag, String message) {
		if (LOG) {
			Log.v(tag, message);
		}
	}

	public static void LOGI(final String tag, String message) {
		if (LOG) {
			Log.i(tag, message);
		}
	}

	public static void LOGW(final String tag, String message) {
		if (LOG) {
			Log.w(tag, message);
		}
	}

	public static void LOGE(final String tag, String message) {
		if (LOG) {
			Log.e(tag, message);
		}
	}

}