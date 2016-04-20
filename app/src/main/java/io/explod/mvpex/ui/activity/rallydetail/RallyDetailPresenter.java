package io.explod.mvpex.ui.activity.rallydetail;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.util.dagger.VisibleForInjection;
import io.explod.mvpex.util.presenter.RxPresenter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static io.explod.mvpex.App.getApp;
import static io.explod.mvpex.util.DateUtils.formatDateTime;

@VisibleForInjection
public class RallyDetailPresenter extends RxPresenter<RallyDetailView> {

	private static final long INTERVAL_PARTICIPANTS = 2000L;

	private static final long START_NOW = 0L;

	@Inject
	DevelopingService mDevelopingService;

	private Subscription mNumParticipantsSubscription;

	private final long mRallyId;

	public RallyDetailPresenter(long rallyId) {
		getApp().getObjectGraph().inject(this);
		mRallyId = rallyId;
	}

	public void onStart() {
		loadRally();
		monitorRallyParticipants();
	}

	private void loadRally() {
		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.showLoadingProgressBar();

		bind(mDevelopingService.getRally(mRallyId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				this::onRally,
				this::onNetworkError
			));
	}

	private void monitorRallyParticipants() {
		mNumParticipantsSubscription = Observable.interval(START_NOW, INTERVAL_PARTICIPANTS, TimeUnit.MILLISECONDS, Schedulers.io())
			.flatMap(x -> mDevelopingService.getNumParticipants(mRallyId).toObservable())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				this::onNumParticipants,
				this::onNetworkError
			);
		bind(mNumParticipantsSubscription);
	}

	private void onNumParticipants(int numParticipants) {
		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.setNumParticipants(numParticipants);
	}

	@VisibleForTesting
	void onRally(@NonNull Rally rally) {
		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.hideLoadingProgressBar();
		view.setTitle(rally.name);
		view.setLocation(rally.location);

		CharSequence dateTimeString = formatDateTime(rally.time);
		view.setTime(dateTimeString);
	}

	@VisibleForTesting
	void onNetworkError(@NonNull Throwable t) {

		if (mNumParticipantsSubscription != null) {
			mNumParticipantsSubscription.unsubscribe();
		}

		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.hideLoadingProgressBar();
		view.showNetworkErrorSnackbar();
	}


}
