package com.kyle.kyletestplatform.remoteservice;

import java.util.Map;

import com.ford.syncV4.proxy.IProxyListener;
import com.ford.syncV4.proxy.rpc.enums.Language;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class RemoteService extends Service {
	private static int mValue = 100;
	private final String TAG = "KyleRemoteService";
	private IBinder mBinder = new IRemoteService.Stub() {

		@Override
		public void registerCallback(IRemoteServiceCallback cb)
				throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void unregisterCallback(IRemoteServiceCallback cb)
				throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getCurrentValue() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "getCurrentValue: " + mValue);
			return mValue++;
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind is called");
		return mBinder;
	}

	public void onCreate() {
		Log.i(TAG, "onCreate");
		super.onCreate();
		showNotification();
	}

	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
		cancelNotification();
	}

	public int onStartCommand(Intent intent, int flag, int startid) {
		Log.i(TAG, "onStartCommand");
		return 0;
	}

	public void showNotification() {
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// notification.icon = R.drawable.ic_btn_speak_now
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_notification_overlay)
				.setContentTitle("AppLink is Running")
				.setContentText("hello i'm running");
		nm.notify(101, mBuilder.build());
	}

	public void cancelNotification() {
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(101);
	}
	public class SyncProxyPara{
		String mAppID = "";
		String mAppName = "";
		String mHMILanguage = "";
		String mLanguage = "";
		boolean mMediaApp = false;
		
		public SyncProxyPara(String appid, String appname, String mhmilanguage, String language,boolean ismediaapp){
			mAppID = appid;
			mAppName = appname;
			mHMILanguage = mhmilanguage;
			mLanguage = language;
			mMediaApp = ismediaapp;
		}
	}
	public class ProxyItem{
		SyncProxyPara mPara = null;
		IProxyListener proxylistener;
		public ProxyItem(SyncProxyPara para, IProxyListener listener){
			mPara = para;
			proxylistener = listener;
		}
	}
}
