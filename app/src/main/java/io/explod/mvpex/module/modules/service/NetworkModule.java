package io.explod.mvpex.module.modules.service;

import android.app.Application;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;


@Module
public class NetworkModule {


	private static final int HTTP_DISK_CACHE_SIZE_MB = 1024 * 1024 * 24;

	private static final String HTTP_DISK_CACHE_DIR = "cache";


	private static final Interceptor ALLOW_LOCAL_CACHE_INTERCEPTOR = chain -> {
		Response originalResponse = chain.proceed(chain.request());
		return originalResponse.newBuilder()
			.removeHeader("Pragma")
			.build();
	};

	@Provides
	@Singleton
	OkHttpClient providesOkHttpClient(Cache cache) {
		return new OkHttpClient.Builder()
			.cache(cache)
			.addNetworkInterceptor(ALLOW_LOCAL_CACHE_INTERCEPTOR)
			.build();
	}

	@Provides
	@Singleton
	Cache providesHttpResponseCache(Application context) {
		// Create an HTTP cache in the application cache directory.
		File cacheDir = new File(context.getCacheDir(), HTTP_DISK_CACHE_DIR);
		return new Cache(cacheDir, HTTP_DISK_CACHE_SIZE_MB);
	}

}
