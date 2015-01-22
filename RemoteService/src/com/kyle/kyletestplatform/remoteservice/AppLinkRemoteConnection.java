package com.kyle.kyletestplatform.remoteservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class AppLinkRemoteConnection {

	private Context mContext = null;
	private IRemoteService mService = null;
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}
	};
	
	
	public AppLinkRemoteConnection(Context context){
		mContext = context;
	}
	
	public void connect(){
		
		Intent intent = new Intent("com.kyle.applink.REMOTE_SERVICE");
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		
	}

}
