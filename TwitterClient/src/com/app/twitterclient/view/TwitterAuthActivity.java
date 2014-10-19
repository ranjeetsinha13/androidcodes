package com.app.twitterclient.view;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.twitterclient.R;
import com.app.twitterclient.utils.ConsumerKeyConstants;
import com.app.twitterclient.utils.LogUtils;

public class TwitterAuthActivity extends Activity {

	private static final String TAG = "TwitterAuthActivity";
	private RequestToken mRequestToken;
	// Twitter
	private static Twitter mTwitter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loading);
		getActionBar().setTitle(R.string.authorize_twitter_account);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loginToTwitter();
	}

	/**
	 * Function to login twitter
	 * */
	private void loginToTwitter() {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(ConsumerKeyConstants.TWITTER_CONSUMER_KEY);
		builder.setOAuthConsumerSecret(ConsumerKeyConstants.TWITTER_CONSUMER_SECRET);
		Configuration configuration = builder.build();

		TwitterFactory factory = new TwitterFactory(configuration);
		mTwitter = factory.getInstance();

		try {
			mTwitterLogin.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void getAuthUrlCallback(String url, RequestToken requestToken) {

		mRequestToken = requestToken;
		LogUtils.LOGD(TAG, "url is " + url + "///" + requestToken);
		setContentView(R.layout.twitter_auth_signin);

		WebView webView = (WebView) findViewById(R.id.twitter_auth_signin_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// String scheme = getResources().getString(
				// R.string.twitter_callback );
				String path = "auth-callback";
				if (url.contains(path)) {
					Uri uri = Uri.parse(url);
					String oauthVerifier = uri
							.getQueryParameter("oauth_verifier");

					return true;
				}
				return false;
			}

			@Override
			public void onPageFinished(WebView webView, String url) {
				// Auto scroll to the bottom to save the user having to do this
				// themselves.
				webView.pageDown(true);
				super.onPageFinished(webView, url);
			}
		});

		webView.loadUrl(url);

	}

	private AsyncTask<Void, Void, RequestToken> mTwitterLogin = new AsyncTask<Void, Void, RequestToken>() {

		@Override
		protected RequestToken doInBackground(Void... params) {

			try {
				mRequestToken = mTwitter
						.getOAuthRequestToken(ConsumerKeyConstants.TWITTER_CALLBACK_URL);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mRequestToken;
		}

		@Override
		protected void onPostExecute(RequestToken requestToken) {
			// TODO Auto-generated method stub
			super.onPostExecute(requestToken);
			getAuthUrlCallback(Uri.parse(requestToken.getAuthenticationURL())
					.toString(), requestToken);

		}

	};

}
