package io.explod.mvpex.ui.activity.rallydetail;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.util.dagger.VisibleForInjection;
import io.explod.mvpex.util.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;

import static io.explod.mvpex.App.getApp;
import static io.explod.mvpex.util.DateUtils.formatDateTime;

@VisibleForInjection
public class RallyDetailPresenter extends RxPresenter<RallyDetailView> {

	@Inject
	DevelopingService mDevelopingService;

	private final int mRallyId;

	public RallyDetailPresenter(int rallyId) {
		getApp().getObjectGraph().inject(this);
		mRallyId = rallyId;
	}

	public void onStart() {
		loadRally();
	}

	private void loadRally() {
		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.showLoadingProgressBar();

		bind(mDevelopingService.getRally(mRallyId)
			.observeOn(AndroidSchedulers.mainThread())
			.doOnSuccess(this::monitorRallyParticipants)
			.subscribe(
				this::onRally,
				this::onNetworkError
			));
	}

	private void monitorRallyParticipants(@NonNull Rally rally) {

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
		RallyDetailView view = getActiveView();
		if (view == null) return;

		view.hideLoadingProgressBar();
		view.showNetworkErrorSnackbar();
	}


}
