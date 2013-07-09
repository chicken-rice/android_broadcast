package com.blogspot.mohammedari.receivebroadcast;

import com.blogspot.mohammedari.receivebroadcast.GraphicView;
import com.blogspot.mohammedari.receivebroadcast.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String broadcastActionName = "SmartWatchAccelerometerUpdated";
	
	private class Receiver extends BroadcastReceiver
	{
		private boolean running = false;
		
		
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if(running)
				return;
			
			running = true;
			try
			{
				Bundle bundle = intent.getExtras();
				float x = Float.parseFloat(bundle.getString("x"));
				float y = Float.parseFloat(bundle.getString("y"));
				float z = Float.parseFloat(bundle.getString("z"));

				//TODO:ここに処理を書く！
				gView.iconMove(x, y);
				gView.iconJump(z);
	
				//TextView view = (TextView)findViewById(R.id.dumpview);
				//view.setText(String.format("%.1f", x) + "," + String.format("%.1f", y) + "," + String.format("%.1f", z));
			}
			finally
			{
				running = false;
			}
		}
	}
	
	private Receiver receiver;
	GraphicView gView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gView = (GraphicView) findViewById(R.id.graphic_view);
		
        
		IntentFilter filter = new IntentFilter(broadcastActionName);
		receiver = new Receiver();
		registerReceiver(receiver, filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onDestroy()
	{
		unregisterReceiver(receiver);
	}

}
