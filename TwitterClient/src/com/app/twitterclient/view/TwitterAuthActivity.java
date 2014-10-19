package com.app.twitterclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.twitterclient.R;

public class TwitterAuthActivity extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter_auth_signin);
		webView = (WebView) findViewById(R.id.twitter_auth_signin_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		String Url = getIntent().getExtras().getString("url");
		webView.setWebViewClient(new WebBrowser());
		webView.loadUrl(Url);
	}

	private class WebBrowser extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			Intent data = new Intent("result");
			if (url.contains("verifier")) {
				data.putExtra("url", url);
				setResult(RESULT_OK, data);
				finish();
			}
		}

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		finish();
	}

	

}
