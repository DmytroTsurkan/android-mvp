package io.explod.mvpex.module.modules.service;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import io.explod.mvpex.module.modules.MockModule;
import io.explod.mvpex.network.service.AppService;
import io.explod.mvpex.network.service.mock.MockService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppServiceModule {

	private static AppService sService;

	@Provides
	AppService provideAppService(OkHttpClient okClient,
								 Gson gson,
								 MockModule.MockMode mockMode,
								 MockService mockService) {
		if (mockMode.isEnabled()) {
			return mockService;
		}

		synchronized (AppServiceModule.class) {
			if (sService == null) {
				Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(AppService.BASE_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(okClient)
					.build();
				sService = retrofit.create(AppService.class);
			}
		}

		return sService;
	}


}
