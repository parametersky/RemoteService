package com.kyle.kyletestapp;

import com.kyle.kyletestplatform.remoteservice.IRemoteService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private final String TAG = "TestMainA";
	private IRemoteService mService = null;
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i(TAG, "ServiceDisconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i(TAG, "ServiceConnected");
			mService = IRemoteService.Stub.asInterface(service);
			try {
				Log.i(TAG,
						"get value from Service :" + mService.getCurrentValue());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Log.i(TAG,
							"get value from Service is "
									+ mService.getCurrentValue());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
//		Intent intent = new Intent(MainActivity.this,
//				com.kyle.kyletestplatform.remoteservice.RemoteService.class);
		Intent intent = new Intent("com.kyle.applink.REMOTE_SERVICE");
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//		startService(intent);
	}

	public void onDestroy(){
		super.onDestroy();
		unbindService(mConnection);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
}
