package com.app.citypediav2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {

	private Typeface mTF;

	public CustomButton(Context context) {
		super(context);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mTF = Typeface.createFromAsset(context.getAssets(), "helvetica.ttf");
		setTypeface(mTF);

	}

}
