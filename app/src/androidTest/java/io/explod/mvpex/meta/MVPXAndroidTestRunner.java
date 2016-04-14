package io.explod.mvpex.meta;

import android.support.test.runner.AndroidJUnitRunner;

import rx.Scheduler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

public class MVPXAndroidTestRunner extends AndroidJUnitRunner {

	public MVPXAndroidTestRunner() {
		overrideRxJavaSchedulers();
	}

	private void overrideRxJavaSchedulers() {
		RxJavaTestPlugins.resetPlugins();
		RxJavaPlugins.getInstance().registerSchedulersHook(new ImmediateSchedulersHook());
	}

	private static class ImmediateSchedulersHook extends RxJavaSchedulersHook {
		@Override
		public Scheduler getComputationScheduler() {
			return Schedulers.immediate();
		}

		@Override
		public Scheduler getIOScheduler() {
			return Schedulers.immediate();
		}

		@Override
		public Scheduler getNewThreadScheduler() {
			return Schedulers.immediate();
		}
	}
}
