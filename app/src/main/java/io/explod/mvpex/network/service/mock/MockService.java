package io.explod.mvpex.network.service.mock;

import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
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

	private static final long DELAY_MIN = 1000L;
	private static final long DELAY_MAX = 2500L;

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
		return network("getRallies", Items.mock(n -> Rally.mock(), 100));
	}

	@Override
	public Single<Rally> getRally(long rallyId) {
		Rally rally = Rally.mock();
		Rally.mockSetNameForId(rally, rallyId);
		return network("getRally", rally);
	}

	@Override
	public Single<Integer> getNumParticipants(long rallyId) {
		Integer numParticipants = Participants.getNumParticipants(rallyId);
		return network("getNumParticipants", numParticipants);
	}

	static class Participants {

		@NonNull
		static final LongSparseArray<Integer> sRallyParticipants = new LongSparseArray<>(10);

		static Integer getNumParticipants(long rallyId) {
			Integer numParticipants;
			synchronized (sRallyParticipants) {
				numParticipants = sRallyParticipants.get(rallyId);
				if (numParticipants == null) {
					numParticipants = (int) (Math.random() * 100);
				}
				if (Math.random() > 0.75) {
					numParticipants += (int) (Math.random() * 10);
				}
				sRallyParticipants.put(rallyId, numParticipants);
			}
			return numParticipants;
		}


	}
}
