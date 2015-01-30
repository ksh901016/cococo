package com.example.rec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SelectPage extends Activity {
	LinearLayout layout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectui);
		layout = (LinearLayout)findViewById(R.id.select_ui);
		layout.setBackgroundResource(R.drawable.backimg);
	}
}