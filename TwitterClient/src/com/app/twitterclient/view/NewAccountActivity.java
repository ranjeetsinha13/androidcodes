package com.app.twitterclient.view;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.app.twitterclient.R;
import com.app.twitterclient.model.TwitterBackend;
import com.app.twitterclient.utils.ConnectionDetector;
import com.app.twitterclient.utils.ConsumerKeyConstants;

public class NewAccountActivity extends Activity {

	private static final int OAUTH_REQUEST = 1;
	private RequestToken mRequestToken;

	// Twitter
	private static Twitter mTwitter;

	private TwitterBackend mTwitterBackend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mTwitterBackend = new TwitterBackend(
				ConsumerKeyConstants.TWITTER_CONSUMER_KEY,
				ConsumerKeyConstants.TWITTER_CONSUMER_SECRET, this);
		ConnectionDetector connectionDetector = new ConnectionDetector(this);
		if (connectionDetector.isConnectingToInternet()) {
			loginToTwitter();

		} else {
			Toast.makeText(this, getString(R.string.no_internet_connection),
					Toast.LENGTH_LONG).show();
		}

	}

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == OAUTH_REQUEST && resultCode == RESULT_OK) {
			Uri url = Uri.parse(data.getExtras().getString("url"));
			String verifier = url.getQueryParameter("oauth_verifier");
			mTwitterBackend.setAccessToken(verifier);
			mTwitterBackend.twitterInit();
			mTwitterBackend.saveToken();
			finish();
			startActivity(new Intent(getApplicationContext(),
					HomeActivity.class));
		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, getString(R.string.cannot_connect_to_twitter),
					Toast.LENGTH_SHORT).show();
		}
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
			String url = mTwitterBackend.getAuthorizationURL();
			Intent intent = new Intent(getApplicationContext(),
					TwitterAuthActivity.class);
			intent.putExtra("url", url);
			overridePendingTransition(0, 0);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, OAUTH_REQUEST);
			

		}

	};

}
