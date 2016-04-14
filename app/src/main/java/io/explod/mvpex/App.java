package io.explod.mvpex;

import android.app.Application;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;

import net.danlew.android.joda.JodaTimeAndroid;

import io.explod.mvpex.module.DaggerObjectGraph;
import io.explod.mvpex.module.ObjectGraph;
import io.explod.mvpex.module.modules.AppModule;

import static io.explod.mvpex.util.TagUtils.makeTag;

public class App extends Application {

	private static final String TAG = makeTag(App.class);

	private static App sInstance;

	public static App getApp() {
		return sInstance;
	}

	ObjectGraph mObjectGraph = DaggerObjectGraph.builder()
		.appModule(new AppModule(this))
		.build();

	@NonNull
	public ObjectGraph getObjectGraph() {
		return mObjectGraph;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;

		if (BuildConfig.DEBUG) {
			onCreateDebugMode();
		} else {
			onCreateInProductionMode();
		}

		JodaTimeAndroid.init(this);

	}

	private void onCreateInProductionMode() {
		Log.d(TAG, "Running in production mode");
	}

	private void onCreateDebugMode() {
		Log.d(TAG, "Running in debug mode");
		enableStrictMode();
	}

	private void enableStrictMode() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectAll()
			.penaltyLog()
			.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
			.detectAll()
			.penaltyLog()
			.build());
	}

}
