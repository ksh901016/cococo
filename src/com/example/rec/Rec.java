package com.example.rec;

import java.io.IOException;



import android.content.*;
import android.os.*;
import android.view.*;
import android.util.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Rec extends Activity {
	Vibrator mVib;
	boolean Vib = true;
	LinearLayout layout;

    protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);
        mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        layout=(LinearLayout)findViewById(R.id.main);
		layout.setBackgroundResource(R.drawable.backimg);
        findViewById(R.id.recording).setOnClickListener(mClickListener);
        findViewById(R.id.select).setOnClickListener(mClickListener);
        findViewById(R.id.connection).setOnClickListener(mClickListener);
        findViewById(R.id.control).setOnClickListener(mClickListener);
        findViewById(R.id.setting).setOnClickListener(mClickListener);
        findViewById(R.id.play_btn).setOnClickListener(mClickListener);
   }
    Button.OnClickListener mClickListener=new OnClickListener(){
    	public void onClick(View v){
    		if(Vib)
    			mVib.vibrate(100);
    		switch(v.getId()){
    		case R.id.recording : 
    			Log.i("onClick", "recording");
    			Intent recordingActivity=new Intent(Rec.this,RecPage.class);
    			startActivity(recordingActivity);
    			break;
    		case R.id.select : 
    			Log.i("onClick", "select");
    			Intent selectActivity=new Intent(Rec.this,SelectPage.class);
    			startActivity(selectActivity);
    			break;
    		case R.id.connection :
    			Log.i("onClick", "connection");
    			Intent connectionActivity=new Intent(Rec.this,MapPage.class);
    			startActivity(connectionActivity);
    			break;
    		case R.id.control : 
    			Toast.makeText(Rec.this, "기기에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
    			break;
    		case R.id.setting : 
    			if(Vib)
    				Vib = false;
    			else
    				Vib = true;
    			break;
    		case R.id.play_btn :
    			Log.i("onClick", "play_btn");
    			Intent playActivity=new Intent(Rec.this,PlayPage.class);
    			startActivity(playActivity);
    			break;
    		}
    	}
    };
   
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rec, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}