package io.explod.mvpex.network.service.mock;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.explod.mvpex.model.Items;
import io.explod.mvpex.model.Rally;
import io.explod.mvpex.model.User;
import io.explod.mvpex.model.request.LoginRequest;
import io.explod.mvpex.network.service.AppAuthorizedService;
import io.explod.mvpex.network.service.AppService;
import io.explod.mvpex.network.service.DevelopingService;
import retrofit2.http.Body;
import rx.Single;
import rx.schedulers.Schedulers;

import static io.explod.mvpex.util.TagUtils.makeTag;

public class MockService implements AppService, AppAuthorizedService, DevelopingService {

	private static final String TAG = makeTag(MockService.class);

	private static final long DELAY_MIN = 250L;
	private static final long DELAY_MAX = 450L;

	static long delay() {
		return DELAY_MIN + (long) (Math.random() * (DELAY_MAX - DELAY_MIN));
	}

	static <T> Single<T> network(@NonNull String requestName, T responseData) {
		Log.d(TAG, "---> " + requestName);
		return Single
			.just(responseData)
			.doOnSuccess(x -> Log.d(TAG, "<--- " + requestName))
			.delay(delay(), TimeUnit.MILLISECONDS)
			.subscribeOn(Schedulers.io());
	}

	@Override
	public Single<User> login(@Body LoginRequest request) {
		return network("login", User.mock());
	}

	@Override
	public Single<Items<Rally>> getRallies() {
		return network("getRallies", Items.mock(Rally.mock(), 100));
	}
}
