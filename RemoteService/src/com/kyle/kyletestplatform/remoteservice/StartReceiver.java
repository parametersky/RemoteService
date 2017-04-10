package com.kyle.kyletestplatform.remoteservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("Receiver","get intent "+intent.getAction());
		Intent service = new Intent(context,RemoteService.class);
//		intent.putExtra(name, value)
		context.startService(service);
	}

}
