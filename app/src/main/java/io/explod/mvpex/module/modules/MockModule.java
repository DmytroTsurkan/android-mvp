package io.explod.mvpex.module.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockModule {

	public static class MockMode {
		boolean mEnabled = false;

		public boolean isEnabled() {
			return mEnabled;
		}

		public void setEnabled(boolean enabled) {
			mEnabled = enabled;
		}
	}

	private static MockMode sMockMode = new MockMode();

	public static MockMode getMockMode() {
		return sMockMode;
	}

	@Provides
	@Singleton
	@NonNull
	public MockMode providesMockMode() {
		return sMockMode;
	}
}
