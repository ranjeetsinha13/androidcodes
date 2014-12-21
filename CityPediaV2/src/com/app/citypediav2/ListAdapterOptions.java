package com.app.citypediav2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapterOptions extends BaseAdapter {

	private Context mContext;

	private static final String TAG = "ListAdapterOptions";
	private int[] d = { R.drawable.grid_selector1, R.drawable.grid_selector3,
			R.drawable.grid_selector4, R.drawable.grid_selector2,
			R.drawable.grid_selector6, R.drawable.grid_selector5,
			R.drawable.grid_selector7 };
	private String[] categoryArr;

	public ListAdapterOptions(Context context, int resource) {

		mContext = context;
		categoryArr = mContext.getResources().getStringArray(
				R.array.categories_list);
		System.out.println(categoryArr);

	}

	@Override
	public int getCount() {
		if (d != null) {
			return d.length;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			LayoutInflater inflator = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflator.inflate(R.layout.single_options_layout, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) v.findViewById(R.id.category_icon);
			holder.categorylabel = (TextView) v
					.findViewById(R.id.category_label);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();

		}
		holder.icon.setBackgroundResource(d[position]);
		holder.categorylabel.setText(categoryArr[position].toUpperCase());

		return v;

	}

	static class ViewHolder {
		ImageView icon;
		TextView categorylabel;

	}

}
