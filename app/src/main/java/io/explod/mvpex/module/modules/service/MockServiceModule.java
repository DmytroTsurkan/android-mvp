package io.explod.mvpex.module.modules.service;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.explod.mvpex.network.service.mock.MockService;

@Module
public class MockServiceModule {

	@Provides
	@Singleton
	@NonNull
	public MockService providesMockService() {
		return new MockService();
	}
}
