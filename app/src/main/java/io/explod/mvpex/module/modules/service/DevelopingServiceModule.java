package io.explod.mvpex.module.modules.service;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.network.service.mock.MockService;

@Module
public class DevelopingServiceModule {

	@Provides
	@Singleton
	@NonNull
	DevelopingService provideDevelopingService(MockService mockService) {
		return mockService;
	}

}
