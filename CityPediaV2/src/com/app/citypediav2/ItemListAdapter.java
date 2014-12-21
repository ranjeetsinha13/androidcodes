package com.app.citypediav2;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ItemListAdapter extends CursorAdapter {

	private static final String TAG = "AppGridAdapter.";
	private String field1;
	private String field2;
	private int id;

	public ItemListAdapter(Context context) {
		super(context, null, false);

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));

		field1 = cursor
				.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
		field2 = cursor
				.getString(cursor.getColumnIndex(cursor.getColumnName(2)));

		View v = new View(context);
		if (field1 != null && field1.length() > 0) {

			try {

				v = vi.inflate(R.layout.single_list_item, null);

				ViewHolder holder = new ViewHolder();
				holder.f1 = (TextView) v.findViewById(R.id.name_label);
				holder.f2 = (TextView) v.findViewById(R.id.address_label);
				v.setTag(holder);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return v;
	}

	@Override
	public long getItemId(int position) {
		Cursor cursor = getCursor();
		if (cursor != null) {
			cursor.moveToPosition(position);
			return cursor.getLong(cursor.getColumnIndex("_id"));
		}
		return -1;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if (cursor != null && !cursor.isAfterLast()) {

			id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));

			field1 = cursor.getString(cursor.getColumnIndex(cursor
					.getColumnName(1)));
			field2 = cursor.getString(cursor.getColumnIndex(cursor
					.getColumnName(2)));
		}

		try {
			if (TextUtils.isEmpty(field1) == false && view != null) {

				ViewHolder holder = (ViewHolder) view.getTag();

				holder.f1.setText(field1.toUpperCase());

				holder.f2.setText(field2);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	static class ViewHolder {
		TextView f1;
		TextView f2;

	}

}
