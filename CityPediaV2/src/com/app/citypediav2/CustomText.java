package com.app.citypediav2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomText extends TextView {

	private Typeface mTF;

	public CustomText(Context context) {
		super(context);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

}
