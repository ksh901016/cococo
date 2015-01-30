package com.example.rec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class PlayPage extends Activity {	
	LinearLayout layout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playui);
		layout=(LinearLayout)findViewById(R.id.play_ui);
		layout.setBackgroundResource(R.drawable.backimg);
	}
}
