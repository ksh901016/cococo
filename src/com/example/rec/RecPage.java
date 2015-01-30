package com.example.rec;

import java.io.IOException;
import java.util.Date;

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
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecPage extends Activity {
	LinearLayout layout;
	MediaRecorder mRecorder = null;
	Button mStartBtn, mPlayBtn;
	boolean mIsStart = false;
	String Path = "";
	String[] dateSplit;
	String date1;
	Handler mHandler;
	TextView tv;
	int hour, min, sec;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recui);
		layout = (LinearLayout)findViewById(R.id.RecUI);
		layout.setBackgroundResource(R.drawable.backimg);
		mStartBtn = (Button) findViewById(R.id.recorded);
		mPlayBtn = (Button) findViewById(R.id.play);
		tv = (TextView) findViewById(R.id.recordTime);
		mHandler = new Handler();
		mStartBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (mIsStart == false) {
					String sd = Environment.getExternalStorageDirectory()
							.getAbsolutePath();
					Date date = new Date();
					date1 = date.toString();
					dateSplit = date1.split(" ");

					Path = sd + "/" + dateSplit[2] + ".3gp";
					if (mRecorder == null) {
						mRecorder = new MediaRecorder();
					} else {
						mRecorder.reset();
					}
					mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mRecorder.setOutputFile(Path);
					try {
						mRecorder.prepare();
					} catch (IllegalStateException e) {
						Toast.makeText(RecPage.this, "IllegalStateException", 1)
								.show();
					} catch (IOException e) {
						Toast.makeText(RecPage.this, "IOException", 1).show();
					}
					mRecorder.start();
					Runnable r = new Runnable() {
						public void run() {
							mHandler.postDelayed(this, 1000); // �ڱ� �ڽ��� �ٽ� ȣ��
							sec++;
							if (sec == 60) {
								min++;
								sec = 0;
							}
							if (min == 60) {
								hour++;
								min = 0;
							}
							if (hour < 10 && min < 10 && sec < 10)
								tv.setText("0" + hour + ":0" + min + ":0" + sec);
							else if (hour < 10 && min < 10)
								tv.setText("0" + hour + ":0" + min + ":" + sec);
							else if (min < 10 && sec < 10)
								tv.setText(hour + ":0" + min + ":0" + sec);
							else if (hour < 10 && sec < 10)
								tv.setText("0" + hour + ":" + min + ":0" + sec);
							else if (sec < 10)
								tv.setText(hour + ":" + min + ":0" + sec);
							else if (min < 10)
								tv.setText(hour + ":0" + min + ":" + sec);
							else if (hour < 10)
								tv.setText("0" + hour + ":" + min + ":" + sec);
							else
								tv.setText(hour + ":" + min + ":" + sec);
						}
					};
					mHandler.postDelayed(r, 1000);
					mIsStart = true;
					mStartBtn.setText("����");
				} else {
					mRecorder.stop();
					mHandler.removeMessages(0);
					mHandler.sendEmptyMessage(0);
					sec = 0; min = 0; hour = 0;
					tv.setText("0" + hour + ":0" + min + ":0" + sec);
					mRecorder.release();
					mRecorder = null;
					mIsStart = false;
					mStartBtn.setText("����");
				}
			}
		});

		mPlayBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View V) {
				if (Path.length() == 0 || mIsStart) {
					Toast.makeText(RecPage.this, "������ ���� �Ͻʽÿ�.", 0).show();
					return;
				}
				MediaPlayer player = new MediaPlayer();
				try {
					player.setDataSource(Path);
					player.prepare();
					player.start();
				} catch (Exception e) {
					Toast.makeText(RecPage.this, "error : " + e.getMessage(), 0).show();
				}
			}
		});
	}

	public void onDestory() {
		super.onDestroy();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}
	}

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
