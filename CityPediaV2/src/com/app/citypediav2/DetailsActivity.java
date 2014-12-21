package com.app.citypediav2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.citypediav2.utils.Constants;
import com.citypedia.app.providers.ContentDescriptor;

public class DetailsActivity extends Activity implements OnClickListener {

	private static final String TAG = "DetailsActivity";

	private int id;
	private String tableName;

	private String numTocall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.restaurantdetails);
		Button call = (Button) findViewById(R.id.call);
		call.setOnClickListener(this);

		Intent i = getIntent();
		id = (int) i.getLongExtra(Constants.ITEM_ID, -1);
		tableName = i.getStringExtra(Constants.TABLE_NAME);
		System.out.println("id " + id + "table " + tableName);

		Cursor c = getContentResolver().query(Uri.parse(tableName), null,
				ContentDescriptor.Restaurants.Cols.ID + " = " + id, null, null);

		if (c.moveToFirst()) {
			((TextView) findViewById(R.id.name)).setText(c.getString(
					c.getColumnIndex(c.getColumnName(1))).toUpperCase());

			((TextView) findViewById(R.id.address_text)).setText(c.getString(c
					.getColumnIndex(c.getColumnName(2))));

		}

		String infoNA = getResources().getString(R.string.na);

		if (tableName
				.equalsIgnoreCase(ContentDescriptor.Restaurants.RESTAURANT_CONTENT_URI
						.toString())) {

			findViewById(R.id.l0).setVisibility(View.VISIBLE);
			findViewById(R.id.l1).setVisibility(View.VISIBLE);
			findViewById(R.id.l2).setVisibility(View.VISIBLE);
			findViewById(R.id.l3).setVisibility(View.VISIBLE);
			findViewById(R.id.l4).setVisibility(View.VISIBLE);

			String services = c.getString(c.getColumnIndex(c.getColumnName(4)));
			if (services.equalsIgnoreCase("null")
					|| TextUtils.isEmpty(services)) {
				((TextView) findViewById(R.id.services_text)).setText(services);
			} else {
				((TextView) findViewById(R.id.services_text)).setText(infoNA);

			}

			String cpp = c.getString(c.getColumnIndex(c.getColumnName(5)));
			if (cpp.equalsIgnoreCase("null") || TextUtils.isEmpty(cpp)) {
				((TextView) findViewById(R.id.cpp_text)).setText(infoNA);
			} else {
				((TextView) findViewById(R.id.cpp_text)).setText(cpp);
			}

			String timings = c.getString(c.getColumnIndex(c.getColumnName(8)));
			if (timings.equalsIgnoreCase("null") || TextUtils.isEmpty(timings)) {
				((TextView) findViewById(R.id.timings_text)).setText(infoNA);
			} else {
				((TextView) findViewById(R.id.timings_text)).setText(timings);
			}

			String paymentType = c.getString(c.getColumnIndex(c
					.getColumnName(9)));
			String cuisines = c.getString(c.getColumnIndex(c.getColumnName(3)));

			if (TextUtils.isEmpty(cuisines)
					|| cuisines.equalsIgnoreCase("null")) {
				((TextView) findViewById(R.id.cuisines_text))
						.setText(getResources().getString(R.string.na));
			} else {
				((TextView) findViewById(R.id.cuisines_text)).setText(cuisines);

			}

			if (TextUtils.isEmpty(paymentType)
					|| paymentType.equalsIgnoreCase("null")) {
				((TextView) findViewById(R.id.payments_text))
						.setText(getResources().getString(R.string.na));
			} else {
				((TextView) findViewById(R.id.payments_text))
						.setText(paymentType);
			}

			numTocall = c.getString(c.getColumnIndex(c.getColumnName(10)));
			if (TextUtils.isEmpty(numTocall)
					|| numTocall.equalsIgnoreCase("null")) {
				numTocall = null;
			}

		}

		else if (tableName
				.equalsIgnoreCase(ContentDescriptor.Places.PLACES_CONTENT_URI
						.toString())) {

			findViewById(R.id.l4).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.cuisines)).setText(getResources()
					.getString(R.string.famousfor));

			String famousFor = c
					.getString(c.getColumnIndex(c.getColumnName(5)));
			if (famousFor.equalsIgnoreCase("null")
					|| TextUtils.isEmpty(famousFor)) {
				((TextView) findViewById(R.id.cuisines_text)).setText(infoNA);

			} else {

				((TextView) findViewById(R.id.cuisines_text))
						.setText(famousFor);
			}

		}

		else if (tableName
				.equalsIgnoreCase(ContentDescriptor.Gyms.GYMS_CONTENT_URI
						.toString())) {

			String address = c.getString(c.getColumnIndex(c.getColumnName(5)));
			if (address.equalsIgnoreCase("null") || TextUtils.isEmpty(address)) {
				((TextView) findViewById(R.id.cuisines_text)).setText(infoNA);

			} else {

				((TextView) findViewById(R.id.address_text)).setText(address);
			}

			numTocall = c.getString(c.getColumnIndex(c.getColumnName(6)));
			if (TextUtils.isEmpty(numTocall)
					|| numTocall.equalsIgnoreCase("null")) {
				numTocall = null;
			}

		}

		else if (tableName
				.equalsIgnoreCase(ContentDescriptor.PetrolPumps.PETROLPUMP_CONTENT_URI
						.toString())) {

			String address = c.getString(c.getColumnIndex(c.getColumnName(5)));
			if (address.equalsIgnoreCase("null") || TextUtils.isEmpty(address)) {
				((TextView) findViewById(R.id.address_text)).setText(infoNA);

			} else {

				((TextView) findViewById(R.id.address_text)).setText(address);
			}

			numTocall = c.getString(c.getColumnIndex(c.getColumnName(6)));
			if (TextUtils.isEmpty(numTocall)
					|| numTocall.equalsIgnoreCase("null")) {
				numTocall = null;
			}

		}

	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(Intent.ACTION_DIAL);
		if (numTocall != null) {
			i.setData(Uri.parse("tel:" + numTocall));
			startActivity(i);

		} else {
			Toast.makeText(DetailsActivity.this,
					getResources().getString(R.string.na), Toast.LENGTH_LONG)
					.show();
		}

	}
}
