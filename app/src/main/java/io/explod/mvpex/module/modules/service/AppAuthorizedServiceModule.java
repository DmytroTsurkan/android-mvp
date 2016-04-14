package io.explod.mvpex.module.modules.service;


import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import io.explod.mvpex.module.modules.MockModule;
import io.explod.mvpex.network.service.AppAuthorizedService;
import io.explod.mvpex.network.service.mock.MockService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppAuthorizedServiceModule {

	private static AppAuthorizedService sService;

	@Provides
	AppAuthorizedService provideAppAuthorizedService(OkHttpClient okClient,
													 Gson gson,
													 MockModule.MockMode mockMode,
													 MockService mockService) {
		if (mockMode.isEnabled()) {
			return mockService;
		}

		synchronized (AppAuthorizedServiceModule.class) {
			if (sService == null) {
				Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(AppAuthorizedService.BASE_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(okClient)
					.build();
				sService = retrofit.create(AppAuthorizedService.class);
			}
		}

		return sService;
	}

}
