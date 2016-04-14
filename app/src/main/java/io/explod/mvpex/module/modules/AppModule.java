package io.explod.mvpex.module.modules;

import android.app.Application;
import android.util.Log;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static io.explod.mvpex.util.TagUtils.makeTag;

@Module
public class AppModule {

	private static final String PICASSO_LOG_TAG = makeTag("Picasso");

	private Application mApplication;

	public AppModule(Application application) {
		mApplication = application;
	}

	@Provides
	@Singleton
	Application providesApplicationContext() {
		return mApplication;
	}

	@Provides
	@Singleton
	Picasso providesPicasso(Application context) {
		return new Picasso.Builder(context)
			.listener((picasso1, uri, exception) -> {
				String message = exception == null ? "Unknown Error" : exception.getMessage();
				String uriMessage = uri == null ? "no-uri-specified" : uri.toString();
				Log.d(PICASSO_LOG_TAG, message + ": " + uriMessage);
			})
			.build();
	}

}
