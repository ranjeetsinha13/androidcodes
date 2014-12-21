package com.app.citypediav2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {

	private Typeface mTF;

	public CustomEditText(Context context) {
		super(context);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

}
