package com.app.twitterclient.model;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import com.app.twitterclient.R;
import com.app.twitterclient.utils.ConsumerKeyConstants;
import com.app.twitterclient.utils.LogUtils;

public class TwitterBackend {

	private static Twitter twitter;
	private static final String CALLBACK_URL = "http://oauth.gmodules.com/gadgets/oauthcallback";
	private AccessToken token;
	private RequestToken requestToken;
	private SharedPreferences mAccountConfigured;

	public TwitterBackend(String consumerKey, String consumerSecret,
			Context context) {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		mAccountConfigured = context.getSharedPreferences(
				context.getString(R.string.account_config_pref),
				Context.MODE_PRIVATE);
	}

	public String getAuthorizationURL() {
		try {
			return new AuthURLGetter().execute().get();
		} catch (Exception e) {
			Log.e("twitter", e.getMessage());
		}
		return null;
	}

	public AccessToken getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken, String tokenSecret) {
		token = new AccessToken(accessToken, tokenSecret);
	}

	public void setAccessToken(String verifier) {
		try {
			token = new AccessTokenGetter().execute(verifier).get();
		} catch (Exception e) {
			Log.e("Twitter", e.getMessage());
		}
	}

	public void twitterInit(AccessToken accessToken) {
		setAccessToken(accessToken.getToken(), accessToken.getTokenSecret());
		twitterInit();
	}

	public void twitterInit() {
		twitter.setOAuthAccessToken(token);
	}

	public static Twitter getTwitterInstance() {
		return twitter;
	}

	private class AuthURLGetter extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				requestToken = twitter.getOAuthRequestToken(CALLBACK_URL);
				return requestToken.getAuthorizationURL();
			} catch (TwitterException e) {
				Log.e("Twitter", e.getMessage());
				return null;
			}
		}
	}

	private class AccessTokenGetter extends
			AsyncTask<String, Integer, AccessToken> {

		@Override
		protected AccessToken doInBackground(String... arg0) {
			try {
				return twitter.getOAuthAccessToken(requestToken, arg0[0]);
			} catch (TwitterException e) {
				Log.e("Twitter", e.getMessage());
				return null;
			}
		}
	}

	public boolean tokenExists() {
		return mAccountConfigured
				.contains(ConsumerKeyConstants.PREF_KEY_OAUTH_TOKEN)
				&& mAccountConfigured
						.contains(ConsumerKeyConstants.PREF_KEY_OAUTH_SECRET);
	}

	public void saveToken() {
		token = getAccessToken();
		LogUtils.LOGD("Twitter Backend", "value of token " + token);
		if (token != null) {
			Editor editor = mAccountConfigured.edit();
			editor.putString(ConsumerKeyConstants.PREF_KEY_OAUTH_TOKEN,
					token.getToken());
			editor.putString(ConsumerKeyConstants.PREF_KEY_OAUTH_SECRET,
					token.getTokenSecret());
			editor.commit();
		}
	}

	public AccessToken getToken() {
		String accessToken = mAccountConfigured.getString(
				ConsumerKeyConstants.PREF_KEY_OAUTH_TOKEN, null);
		String secret = mAccountConfigured.getString(
				ConsumerKeyConstants.PREF_KEY_OAUTH_TOKEN, null);
		if (accessToken != null && secret != null) {
			token = new AccessToken(accessToken, secret);
		}
		return token;
	}

}
